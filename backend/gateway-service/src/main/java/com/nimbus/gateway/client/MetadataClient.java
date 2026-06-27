package com.nimbus.gateway.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class MetadataClient {
    private final RestClient restClient;

    public String createObject(
            String filename,
            Long size,
            String contentType
    ){
        Map<String,String> response=
                restClient.post()
                        .uri(uriBuilder -> uriBuilder
                                .path("/objects")
                                .queryParam("filename",filename)
                                .queryParam("size",size)
                                .queryParam("contentType",contentType)
                                .build())

                        .retrieve()
                        .body(Map.class);
        return response.get("objectId");
    }
}
