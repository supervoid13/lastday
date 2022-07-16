package com.uneev.app.lastday.last_day_app.communication.info;

import java.util.ArrayList;
import java.util.List;

public class NHLInfo implements Info {

    List<NHLGame> matches = new ArrayList<>();

    public NHLInfo() {
    }

    public List<NHLGame> getMatches() {
        return matches;
    }

    public void setMatches(List<NHLGame> matches) {
        this.matches = matches;
    }
}
