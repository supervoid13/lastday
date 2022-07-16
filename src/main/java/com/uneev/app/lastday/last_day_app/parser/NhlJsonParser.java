package com.uneev.app.lastday.last_day_app.parser;

import com.uneev.app.lastday.last_day_app.communication.info.NHLGame;
import com.uneev.app.lastday.last_day_app.communication.info.NHLInfo;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Iterator;

public class NhlJsonParser {

    public static NHLInfo parse(String json) throws ParseException {
        NHLInfo nhlInfo = new NHLInfo();

        Object object = new JSONParser().parse(json);
        JSONArray jsonArray = (JSONArray) object;
        Iterator matchesIterator = jsonArray.iterator();

        for (Object jsonObject : jsonArray){
            JSONObject match = (JSONObject) jsonObject;

            String status = (String) match.get("Status");

            if (!status.equals("Canceled")) {

                NHLGame nhlGame = new NHLGame();

                nhlGame.setDateTime((String) match.get("DateTime"));
                nhlGame.setAwayTeam((String) match.get("AwayTeam"));
                nhlGame.setHomeTeam((String) match.get("HomeTeam"));

                nhlInfo.getMatches().add(nhlGame);
            }
        }

//        while (matchesIterator.hasNext()) {
//            JSONObject match = (JSONObject) matchesIterator.next();
//
//            String status = (String) match.get("Status");
//
//            if (!status.equals("Canceled")) {
//
//                NHLGame nhlGame = new NHLGame();
//
//                nhlGame.setDateTime((String) match.get("DateTime"));
//                nhlGame.setAwayTeam((String) match.get("AwayTeam"));
//                nhlGame.setHomeTeam((String) match.get("HomeTeam"));
//
//                nhlInfo.getMatches().add(nhlGame);
//                System.out.println(nhlInfo.getMatches());
//            }
//        }
        return nhlInfo;
    }
}
