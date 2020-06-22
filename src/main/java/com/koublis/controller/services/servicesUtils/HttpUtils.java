package com.koublis.controller.services.servicesUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

public class HttpUtils {

    public static <T extends Serializable> T httpGetBuilder(String hostname, String path, MultiValueMap<String, String> parameters, String token, Class<T> type) throws IOException {

        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host(hostname)
                .path(path)
                .queryParams(parameters)
                .build();

        URL url = new URL(uriComponents.toUriString());
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod(RequestMethod.GET.name());
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("Authorization", "Token " + token);
        con.setConnectTimeout(25000);
        con.setReadTimeout(25000);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String json = in.lines().collect(Collectors.joining("\n"));

        ObjectMapper objectMapper = new ObjectMapper();
        T result = objectMapper.readValue(json, type);
        con.disconnect();

        return result;
    }

}
