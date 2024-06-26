package com.example.resttemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class RestTemplateApplication {

    @Autowired
    ItunesProxy shawnMendesClient;

    @Autowired
    SampleShawnMendesServerProxy sampleShawnMendesServerProxy;

    public static void main(String[] args) {
        SpringApplication.run(RestTemplateApplication.class, args);
    }

    @EventListener(ApplicationStartedEvent.class)
    public void run() throws JsonProcessingException {
//        String json = shawnMendesClient.makeRequest("shawnmendes", 3);
//        if (json != null) {
//            ShawnMendesResponse shawnMendesResponse = mapJsonToShawnMendesResponse(json);
//            System.out.println(shawnMendesResponse);
//        }

//        String postJsonSampleShawnMendesServer = sampleShawnMendesServerProxy.makePostRequest();
        String getJsonSampleShawnMendesServer = sampleShawnMendesServerProxy.makeGetRequest();
        if (getJsonSampleShawnMendesServer != null) {
            SampleServerShawnMendesResponse sampleShawnMendesResponse = mapJsonToSampleShawnMendesResponse(getJsonSampleShawnMendesServer);
            System.out.println(sampleShawnMendesResponse);
        }
        sampleShawnMendesServerProxy.makeDeleteRequest("0");
        String getJsonSampleShawnMendesServer2 = sampleShawnMendesServerProxy.makeGetRequest();
//        if (postJsonSampleShawnMendesServer != null) {
//            SampleServerShawnMendesResponse sampleShawnMendesResponse = mapJsonToSampleShawnMendesResponse(postJsonSampleShawnMendesServer);
//            System.out.println(sampleShawnMendesResponse);
//        }

        if (getJsonSampleShawnMendesServer2 != null) {
            SampleServerShawnMendesResponse sampleShawnMendesResponse = mapJsonToSampleShawnMendesResponse(getJsonSampleShawnMendesServer2);
            System.out.println(sampleShawnMendesResponse);
        }
    }

    private ShawnMendesResponse mapJsonToShawnMendesResponse(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, ShawnMendesResponse.class);
    }

    private SampleServerShawnMendesResponse mapJsonToSampleShawnMendesResponse(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, SampleServerShawnMendesResponse.class);
    }
}