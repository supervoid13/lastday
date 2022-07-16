package com.uneev.app.lastday.last_day_app.communication.info;


import com.uneev.app.lastday.last_day_app.enums.ZodiacSign;

import java.util.HashMap;
import java.util.Map;

public class HoroscopeInfo implements Info {

    Map<ZodiacSign, String> horoscope = new HashMap<>();

    public HoroscopeInfo() {
    }

    public Map<ZodiacSign, String> getHoroscope() {
        return horoscope;
    }

    public void setHoroscope(Map<ZodiacSign, String> horoscope) {
        this.horoscope = horoscope;
    }
}
