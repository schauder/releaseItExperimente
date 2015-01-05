package de.schauderhaft.releaseit.main.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BaneService {

    public void connectToBane() {
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject("http://localhost:8090", String.class);
        System.out.println(response);
    }
}
