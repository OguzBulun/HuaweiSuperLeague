package com.example.sportotosuperlig.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Team {

    @SerializedName("Id")
    @Expose
    private int Id;

    @SerializedName("Name")
    @Expose
    private String Name;

    @SerializedName("ImagePath")
    @Expose
    private String ImagePath;

    @SerializedName("Stadium")
    @Expose
    private String Stadium;

    @SerializedName("Capacity")
    @Expose
    private String Capacity;

    @SerializedName("Coach")
    @Expose
    private String Coach;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImagePath() {
        return ImagePath;
    }

    public void setImagePath(String imagePath) {
        ImagePath = imagePath;
    }

    public String getStadium() {
        return Stadium;
    }

    public void setStadium(String stadium) {
        Stadium = stadium;
    }

    public String getCapacity() {
        return Capacity;
    }

    public void setCapacity(String capacity) {
        Capacity = capacity;
    }

    public String getCoach() {
        return Coach;
    }

    public void setCoach(String coach) {
        Coach = coach;
    }
}
