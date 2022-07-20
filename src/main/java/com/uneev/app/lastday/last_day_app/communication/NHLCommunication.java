package com.uneev.app.lastday.last_day_app.communication;

import com.uneev.app.lastday.last_day_app.communication.info.Info;
import com.uneev.app.lastday.last_day_app.communication.info.NHLInfo;
import com.uneev.app.lastday.last_day_app.parser.NhlJsonParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

@Component
public class NHLCommunication implements Communication {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    NhlJsonParser nhlJsonParser;

    private final String URL = "https://api.sportsdata.io/v3/nhl/scores/json/GamesByDate";

    private final String API_KEY = "bf6ab98e4a414ccd94ef40ddcf58b025";


    @Override
    public NHLInfo getInfo() {
        Calendar calendar = Calendar.getInstance(Locale.US);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MMM-dd", Locale.ENGLISH);
        String date = formatter.format(calendar.getTime());

        HttpHeaders headers = new HttpHeaders();
        headers.set("Ocp-Apim-Subscription-Key", API_KEY);
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<String> responseEntity =
                restTemplate.exchange(URL + "/" + date, HttpMethod.GET, httpEntity,
                        String.class);

        String json = responseEntity.getBody();
        NHLInfo nhlInfo = null;
        try {
            nhlInfo = NhlJsonParser.parse(json);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return nhlInfo;
    }
}
