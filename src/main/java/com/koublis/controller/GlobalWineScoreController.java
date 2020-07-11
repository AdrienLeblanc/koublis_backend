package com.koublis.controller;

import com.koublis.converters.WineConverter;
import com.koublis.model.dto.globalwinescore.LatestResults;
import com.koublis.model.dto.globalwinescore.WineDto;
import com.koublis.model.entities.Wine;
import com.koublis.repository.WineRepository;
import com.koublis.services.utils.HttpUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class GlobalWineScoreController {

    @Autowired
    private WineRepository wineRepository;

    private static final String token = "d84bed838c7eedc086973876a7de117ab2dd91fa";
    private final Logger logger = Logger.getLogger(GlobalWineScoreController.class);

    @PostMapping("/wines/update")
    public void update() {

        int n = 0;
        boolean sendRequest = true;
        boolean parsingError = false;
        String additionalParameters = "";

        while (sendRequest) {
            try {
                final String hostname = "api.globalwinescore.com";
                final String path = "/globalwinescores/latest/";
                LatestResults result = HttpUtils.httpGetBuilder(hostname, path + additionalParameters, null, token, LatestResults.class);
                if (result != null) {
                    List<WineDto> dtos = Stream.of(result.results).filter(r -> r.wine_id != null).collect(Collectors.toList());
                    List<Wine> wines = dtos.stream().map(WineConverter::WineDtoToWine).collect(Collectors.toList());
                    wineRepository.saveAll(wines);
                    n++;
                    logger.debug("GlobalWineScore : Request #" + n + " sended & saved.");
                    parsingError = false;

                    // Checking if another request is needed
                    if (result.next == null) {
                        sendRequest = false;
                    } else {
                        // get additional parameters (limit/offset) for next request
                        String regex = "/[^/]*$";
                        Pattern pattern = Pattern.compile(regex);
                        Matcher matcher = pattern.matcher(result.next);
                        boolean b = matcher.find();
                        if (b) additionalParameters = matcher.group();
                    }
                }
            } catch (IOException e) {
                try {
                    if (parsingError) {
                        logger.warn("GlobalWineScore : Parsing error.");
                    } else {
                        parsingError = true;
                        logger.warn("GlobalWineScore API Rate limiting : For API requests, the rate limit allows for up to +-10 requests per minute.");
                        Thread.sleep(600 * 1000);
                        n = 0;
                    }
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        logger.debug("GlobalWineScore : Updating wines is over.");
    }
}
