package com.koublis.controller;

import com.koublis.converters.WineConverter;
import com.koublis.model.dto.globalwinescore.LatestResults;
import com.koublis.model.dto.globalwinescore.WineDto;
import com.koublis.model.entities.Wine;
import com.koublis.repository.WineRepository;
import com.koublis.services.WebClientService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class GlobalWineScoreController {

    @Autowired
    private WineRepository wineRepository;

    @Autowired
    private WebClientService webClientService;

    private final Logger logger = Logger.getLogger(GlobalWineScoreController.class);

    private final List<Wine> winesToSave = new ArrayList<>();

    @PostMapping("/wines/update")
    @ResponseStatus(HttpStatus.OK)
    public String update() {

        Runnable runnable = () -> {
            boolean sendRequest = true;
            String limitOffset = winesToSave.size() == 0 ? "" : "?limit=100&offset=" + winesToSave.size();

            while (sendRequest) {
                WebClient.RequestHeadersSpec<?> requestGetLatestWines = webClientService
                        .globalWineService.get()
                        .uri("/globalwinescores/latest/" + limitOffset);
                ClientResponse clientResponse = Objects.requireNonNull(requestGetLatestWines.exchange().block());
                if (clientResponse.statusCode().value() == 429) {
                    logger.warn("GlobalWineScore API Rate limiting : " + clientResponse.statusCode().getReasonPhrase());
                    try {
                        Thread.sleep(600 * 1000);
                    } catch (InterruptedException e) {
                        logger.error(e.getMessage());
                    }
                } else if (clientResponse.statusCode().is5xxServerError()) {
                    logger.warn("GlobalWineScore Server Error");
                    sendRequest = false;
                } else if (clientResponse.statusCode().is4xxClientError()) {
                    logger.warn("Client error");
                    sendRequest = false;
                } else if (clientResponse.statusCode().isError()) {
                    logger.warn("Parsing error");
                    sendRequest = false;
                } else {
                    LatestResults latestResults = clientResponse
                            .bodyToMono(LatestResults.class)
                            .block();
                    List<WineDto> dtos = Stream.of(Objects.requireNonNull(latestResults).results).filter(r -> r.wine_id != null).collect(Collectors.toList());
                    List<Wine> wines = dtos.stream().map(WineConverter::WineDtoToWine).collect(Collectors.toList());
                    winesToSave.addAll(wines);
                    logger.debug("Update : " + winesToSave.size() + "/" + latestResults.count + " (" + winesToSave.size() * 100 / latestResults.count + "%)");

                    // Check if another request is needed
                    if (latestResults.next == null || winesToSave.size() >= latestResults.count) {
                        sendRequest = false;
                        winesToSave.clear();
                    } else {
                        // additional limit/offset for next request
                        String regex = "[^/]*$";
                        Pattern pattern = Pattern.compile(regex);
                        Matcher matcher = pattern.matcher(latestResults.next);
                        boolean b = matcher.find();
                        if (b) limitOffset = matcher.group();
                    }
                }
            }
            wineRepository.saveAll(winesToSave);
            logger.debug("GlobalWineScore : Updating wines is over.");
        };
        Thread t = new Thread(runnable);
        t.start();

        return "Updating wines. May took several minutes.";
    }
}
