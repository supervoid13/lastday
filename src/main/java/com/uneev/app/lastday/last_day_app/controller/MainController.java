package com.uneev.app.lastday.last_day_app.controller;

import com.uneev.app.lastday.last_day_app.communication.WeatherCommunication;
import com.uneev.app.lastday.last_day_app.communication.info.WeatherInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

@Controller
public class MainController {

    @Autowired
    WeatherCommunication weatherCommunication;

    @RequestMapping("/")
    public String mainPage(Model model) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("EEE, d MMM", Locale.ENGLISH);
        String date = formatter.format(calendar.getTime());
        model.addAttribute("date", date);

        WeatherInfo weatherInfo = weatherCommunication.getInfo();
        model.addAttribute("weather", weatherInfo);

        return "index.html";
    }
}
