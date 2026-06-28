package com.nimbus.gateway.client;

import com.nimbus.gateway.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Component
public class MetadataClient {
    private final RestClient restClient;

    public MetadataClient(RestClient metadataRestClient) {
        this.restClient = metadataRestClient;
    }

    public String createObject(
            String filename,
            Long size,
            String contentType,
            Integer chunkCount
    ){
        Map<String,String> response=
                restClient.post()
                        .uri(uriBuilder -> uriBuilder
                                .path("/objects")
                                .queryParam("filename",filename)
                                .queryParam("size",size)
                                .queryParam("contentType",contentType)
                                .queryParam("chunkCount",chunkCount)
                                .build())

                        .retrieve()
                        .body(Map.class);
        return response.get("objectId");
    }

    public ObjectMetadata getObject(String objectId) {

        return restClient.get()
                .uri("/objects/{objectId}", objectId)
                .retrieve()
                .body(ObjectMetadata.class);
    }
}
