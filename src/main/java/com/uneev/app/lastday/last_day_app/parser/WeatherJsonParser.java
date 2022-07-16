package com.uneev.app.lastday.last_day_app.parser;

import com.uneev.app.lastday.last_day_app.communication.info.NHLGame;
import com.uneev.app.lastday.last_day_app.communication.info.NHLInfo;
import com.uneev.app.lastday.last_day_app.communication.info.WeatherInfo;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Iterator;

public class WeatherJsonParser {

    public static WeatherInfo parse(String json) throws ParseException {
        WeatherInfo weatherInfo = new WeatherInfo();
        String main = null;
        String description = null;

        Object object = new JSONParser().parse(json);
        JSONObject jsonObject = (JSONObject) object;

        JSONArray jsonArray = (JSONArray) jsonObject.get("weather");

        Iterator weatherIterator = jsonArray.iterator();
        while (weatherIterator.hasNext()) {
            JSONObject weather = (JSONObject) weatherIterator.next();
            main = (String) weather.get("main");
            description = (String) weather.get("description");
        }

        JSONObject jsonObject1 = (JSONObject) jsonObject.get("main");
        Object temperatureObject = null;
        if ((temperatureObject = jsonObject1.get("temp")) instanceof Double) {
            double temperature = (Double) jsonObject1.get("temp");
            double temperatureCelsius = temperature - 273.15;
            weatherInfo.setTemperature((int) temperatureCelsius);
        } else if ((temperatureObject = jsonObject1.get("temp")) instanceof Long) {
            long temperature = (Long) jsonObject1.get("temp");
            double temperatureCelsius = temperature - 273.15;
            weatherInfo.setTemperature((int) temperatureCelsius);
        }

        weatherInfo.setMain(main);
        weatherInfo.setDescription(description);

        return weatherInfo;
    }
}
