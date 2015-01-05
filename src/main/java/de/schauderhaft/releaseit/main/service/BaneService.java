package de.schauderhaft.releaseit.main.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BaneService {

    public String connectToBane() {
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject("http://www.heise.de", String.class);
        return response;
    }
}
