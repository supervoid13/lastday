package com.uneev.app.lastday.last_day_app.communication.info;

public class NHLGame {
    private String dateTime;

    private String awayTeam;

    private String homeTeam;

    public NHLGame() {
    }

    public NHLGame(String dateTime, String awayTeam, String homeTeam) {
        this.dateTime = dateTime;
        this.awayTeam = awayTeam;
        this.homeTeam = homeTeam;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }
}
