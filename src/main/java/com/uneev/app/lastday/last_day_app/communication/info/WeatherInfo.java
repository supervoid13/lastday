package com.uneev.app.lastday.last_day_app.communication.info;

public class WeatherInfo implements Info {

    private int temperature;

    private String  main;

    private String description;

    public WeatherInfo() {
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public WeatherInfo(int temperature, String main, String description) {
        this.temperature = temperature;
        this.main = main;
        this.description = description;
    }
}
