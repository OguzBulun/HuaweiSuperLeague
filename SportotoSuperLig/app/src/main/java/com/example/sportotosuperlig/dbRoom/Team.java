package com.example.sportotosuperlig.dbRoom;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Team implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "fistTeam")
    public String fistTeam;

    @ColumnInfo(name = "secondTeam")
    public String secondTeam;

    @ColumnInfo(name = "macthDate")
    public String macthDate;

    @ColumnInfo(name = "macthWeek")
    public String macthWeek;

    @ColumnInfo(name = "macthStadium")
    public String macthStadium;

    @ColumnInfo(name = "macthScore")
    public String macthScore;

    @ColumnInfo(name = "fistTeamImg")
    public String fistTeamImg;

    @ColumnInfo(name = "secondTeamImg")
    public String secondTeamImg;

    public String getMacthStadium() {
        return macthStadium;
    }

    public void setMacthStadium(String macthStadium) {
        this.macthStadium = macthStadium;
    }

    public String getMacthScore() {
        return macthScore;
    }

    public void setMacthScore(String macthScore) {
        this.macthScore = macthScore;
    }

    public String getFistTeamImg() {
        return fistTeamImg;
    }

    public void setFistTeamImg(String fistTeamImg) {
        this.fistTeamImg = fistTeamImg;
    }

    public String getSecondTeamImg() {
        return secondTeamImg;
    }

    public void setSecondTeamImg(String secondTeamImg) {
        this.secondTeamImg = secondTeamImg;
    }

    public String getMacthWeek() {
        return macthWeek;
    }

    public void setMacthWeek(String macthWeek) {
        this.macthWeek = macthWeek;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFistTeam() {
        return fistTeam;
    }

    public void setFistTeam(String fistTeam) {
        this.fistTeam = fistTeam;
    }

    public String getSecondTeam() {
        return secondTeam;
    }

    public void setSecondTeam(String secondTeam) {
        this.secondTeam = secondTeam;
    }

    public String getMacthDate() {
        return macthDate;
    }

    public void setMacthDate(String macthDate) {
        this.macthDate = macthDate;
    }
}
