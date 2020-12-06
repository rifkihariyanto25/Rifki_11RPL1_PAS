package com.example.rifki_11rpl1_29;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import io.realm.RealmObject;

public class ModelFavourite extends RealmObject {

    String strTeam;
    String strAlternate;
    String strLeague;
    String strStadium;
    String strTeamBadge;
    String strDescriptionEN;
    String strStadiumLocation;
    int id;

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeam_name() {
        return strTeam;
    }

    public void setTeam_name(String strTeam) {
        this.strTeam = strTeam;
    }

    public String getAlternate_name() {
        return strAlternate;
    }

    public void setAlternate_name(String strAlternate) {
        this.strAlternate = strAlternate;
    }

    public String getLeague() {
        return strLeague;
    }

    public void setLeague(String strLeague) {
        this.strLeague = strLeague;
    }

    public String getStadium() {
        return strStadium;
    }

    public void setStadium(String strStadium) {
        this.strStadium = strStadium;
    }

    public String getBadge_path() {
        return strTeamBadge;
    }

    public void setBadge_path(String strTeamBadge) {
        this.strTeamBadge = strTeamBadge;
    }

    public String getDescription() {
        return strDescriptionEN;
    }

    public void setDescription(String strDescriptionEN) {
        this.strDescriptionEN = strDescriptionEN;
    }

    public String getStadium_location() {
        return strStadiumLocation;
    }

    public void setStadium_location(String strStadiumLocation) {
        this.strStadiumLocation = strStadiumLocation;
    }
}