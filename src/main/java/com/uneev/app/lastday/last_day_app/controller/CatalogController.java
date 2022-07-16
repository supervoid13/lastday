package com.uneev.app.lastday.last_day_app.controller;

import com.uneev.app.lastday.last_day_app.communication.*;
import com.uneev.app.lastday.last_day_app.communication.info.*;
import com.uneev.app.lastday.last_day_app.enums.ZodiacSign;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/catalog")
public class CatalogController {

    @Autowired
    private NASACommunication nasaCommunication;

    @Autowired
    private ThisDayInHistoryCommunication thisDayInHistoryCommunication;

    @Autowired
    private HoroscopeCommunication horoscopeCommunication;

    @Autowired
    NHLCommunication nhlCommunication;

    @Autowired
    WeatherCommunication weatherCommunication;

    @GetMapping("/")
    public String showCatalog() {
        return "redirect:/";
    }

    @GetMapping("/history")
    public String showThisDayInHistory(Model model) {
        getDateAndWeather(model, weatherCommunication);

        Info thisDayInHistoryInfo = thisDayInHistoryCommunication.getInfo();
        model.addAttribute("historyInfo", thisDayInHistoryInfo);

        return "catalog/history";
    }

    @GetMapping("/NHL")
    public String showNHLGames(Model model) throws ParseException {
        getDateAndWeather(model, weatherCommunication);

        NHLInfo nhlInfo = nhlCommunication.getInfo();
        model.addAttribute("gameList", nhlInfo.getMatches());

        boolean isAnyGames = !nhlInfo.getMatches().isEmpty();
        model.addAttribute("isAnyGames", isAnyGames);

        return "/catalog/nhl";
    }

    @GetMapping("/NASA")
    public String showNASAPictureOfTheDay(Model model) {
        getDateAndWeather(model, weatherCommunication);

        NASAInfo nasaInfo = nasaCommunication.getInfo();
        model.addAttribute("nasaInfo", nasaInfo);

//        return nasaInfo;
        return "catalog/NASA";
    }


    @GetMapping("/horoscope")
    public String showHoroscope(@RequestParam(required = false) String sign, Model model) {
//        HoroscopeInfo horoscopeInfo = horoscopeCommunication.getInfo();
//        if (sign != null) {
//            model.addAttribute("currentSign", sign); // добавляем в модель нужный знак зодиака
//            model.addAttribute("signHoroscope", horoscopeInfo.getHoroscope()
//                    .get(ZodiacSign.valueOf(sign))); // добавляем в модель гороскоп по указанному знаку зодиака
//
//            return "catalog/horoscope";
//        } else {
//            model.addAttribute("signSet", horoscopeInfo.getHoroscope().keySet());
//
//            return "catalog/horoscope-signs";
//        }
        getDateAndWeather(model, weatherCommunication);

        HoroscopeInfo horoscopeInfo = horoscopeCommunication.getInfo();

        List<ZodiacSign> signList = new ArrayList<>(horoscopeInfo.getHoroscope().keySet());
        Collections.sort(signList);
        model.addAttribute("signList", signList);

        if (sign != null) {
            model.addAttribute("currentSign", sign); // добавляем в модель нужный знак зодиака
            model.addAttribute("signHoroscope", horoscopeInfo.getHoroscope()
                    .get(ZodiacSign.valueOf(sign))); // добавляем в модель гороскоп по указанному знаку зодиака
        }

        return "catalog/horoscope";
    }

    private static void getDateAndWeather(Model model, WeatherCommunication weatherCommunication) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("EEE, d MMM", Locale.ENGLISH);
        String date = formatter.format(calendar.getTime());
        model.addAttribute("date", date);

        WeatherInfo weatherInfo = weatherCommunication.getInfo();
        model.addAttribute("weather", weatherInfo);
    }
}
