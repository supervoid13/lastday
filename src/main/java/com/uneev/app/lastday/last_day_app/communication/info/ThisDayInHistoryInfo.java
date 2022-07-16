package com.uneev.app.lastday.last_day_app.communication.info;

public class ThisDayInHistoryInfo implements Info {
    String historyFact;

    public ThisDayInHistoryInfo() {
    }

    public ThisDayInHistoryInfo(String historyFact) {
        this.historyFact = historyFact;
    }

    public String getHistoryFact() {
        return historyFact;
    }

    public void setHistoryFact(String historyFact) {
        this.historyFact = historyFact;
    }
}
