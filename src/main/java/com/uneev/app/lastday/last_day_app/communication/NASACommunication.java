package com.uneev.app.lastday.last_day_app.communication;

import com.uneev.app.lastday.last_day_app.communication.info.Info;
import com.uneev.app.lastday.last_day_app.communication.info.NASAInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class NASACommunication implements Communication {

    @Autowired
    private RestTemplate restTemplate;

    private final String URL = "https://api.nasa.gov/planetary/apod";

    private final String API_KEY = "k01SXScJUhJX7zuzVOCaOAJsSefjv1wVLDbV22qg";

    public NASAInfo getInfo() {
        NASAInfo nasaInfo = restTemplate.getForObject(URL + "?api_key=" + API_KEY, NASAInfo.class);

        return nasaInfo;
    }
}
