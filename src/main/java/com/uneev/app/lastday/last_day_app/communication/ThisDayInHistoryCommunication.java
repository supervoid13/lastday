package com.uneev.app.lastday.last_day_app.communication;

import com.uneev.app.lastday.last_day_app.communication.info.Info;
import com.uneev.app.lastday.last_day_app.communication.info.ThisDayInHistoryInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Calendar;

@Component
public class ThisDayInHistoryCommunication implements Communication {

    @Autowired
    private RestTemplate restTemplate;

    private final String URL = "http://numbersapi.com";

    private Calendar calendar;

    @Override
    public ThisDayInHistoryInfo getInfo() {
        Calendar calendar = Calendar.getInstance();
        String URL = this.URL + "/" + (calendar.get(Calendar.MONTH) + 1) + "/" +
                calendar.get(Calendar.DAY_OF_MONTH) + "/date";
        ResponseEntity<String> responseEntity =
                restTemplate.exchange(URL, HttpMethod.GET, null,
                        new ParameterizedTypeReference<>() {
                        });
        ThisDayInHistoryInfo info = new ThisDayInHistoryInfo(responseEntity.getBody());

        return info;
    }
}
