package com.uneev.app.lastday.last_day_app.communication;

import com.uneev.app.lastday.last_day_app.communication.info.Info;
import com.uneev.app.lastday.last_day_app.communication.info.NHLInfo;
import com.uneev.app.lastday.last_day_app.communication.info.WeatherInfo;
import com.uneev.app.lastday.last_day_app.parser.NhlJsonParser;
import com.uneev.app.lastday.last_day_app.parser.WeatherJsonParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherCommunication implements Communication {

    @Autowired
    private RestTemplate restTemplate;

    private final String URL = "https://api.openweathermap.org/data/2.5/weather?q=Moscow,rus";

    private final String API_KEY = "b1b098580c795257ea8ea9edfcc0ed90";


    @Override
    public WeatherInfo getInfo() {
        String json = restTemplate.getForObject(URL + "&APPID=" + API_KEY, String.class);

        WeatherInfo weatherInfo = null;
        try {
            weatherInfo = WeatherJsonParser.parse(json);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return weatherInfo;
    }
}
