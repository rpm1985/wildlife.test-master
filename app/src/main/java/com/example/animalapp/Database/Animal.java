package com.example.animalapp.Database;


import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Animal {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "Type")
    private String type;

    @ColumnInfo(name = "Name")
    private String name;

    @ColumnInfo(name = "Scientific_Name")
    private String scientificName;

    @ColumnInfo(name = "Min Body Length Cm")
    private Integer minBodyLengthCm;

    @ColumnInfo(name = "Max Body Length Cm")
    private Integer maxBodyLengthCm;

    @ColumnInfo(name = "Min Wingspan Cm")
    private Integer minWingspanCm;

    @ColumnInfo(name = "Max Wingspan Cm")
    private Integer maxWingspanCm;

    @ColumnInfo(name = "Description")
    private String description;

    @ColumnInfo(name = "Habitat")
    private String habitat;

    @ColumnInfo(name = "Best Time of Year to see")
    private String bestTime;

    @ColumnInfo(name = "Best Walk to see on")
    private String bestWalk;

    @ColumnInfo(name = "Food Source")
    private String foodSource;

    @ColumnInfo(name = "Head Colour")
    private String headColour;

    @ColumnInfo(name = "Wing Colour")
    private String wingColour;

    @ColumnInfo(name = "Belly Colour")
    private String bellyColour;

    @ColumnInfo(name = "Fur Colour")
    private String furColour;

    @ColumnInfo(name = "Skin Colour")
    private String skinColour;

    @ColumnInfo(name = "Markings")
    private String markings;

    @ColumnInfo(name = "Animal Image")
    private String animalImage;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public Integer getMinBodyLengthCm() {
        return minBodyLengthCm;
    }

    public void setMinBodyLengthCm(Integer minBodyLengthCm) {
        this.minBodyLengthCm = minBodyLengthCm;
    }

    public Integer getMaxBodyLengthCm() {
        return maxBodyLengthCm;
    }

    public void setMaxBodyLengthCm(Integer maxBodyLengthCm) {
        this.maxBodyLengthCm = maxBodyLengthCm;
    }

    public Integer getMinWingspanCm() {
        return minWingspanCm;
    }

    public void setMinWingspanCm(Integer minWingspanCm) {
        this.minWingspanCm = minWingspanCm;
    }

    public Integer getMaxWingspanCm() {
        return maxWingspanCm;
    }

    public void setMaxWingspanCm(Integer maxWingspanCm) {
        this.maxWingspanCm = maxWingspanCm;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }

    public String getBestTime() {
        return bestTime;
    }

    public void setBestTime(String bestTime) {
        this.bestTime = bestTime;
    }

    public String getBestWalk() {
        return bestWalk;
    }

    public void setBestWalk(String bestWalk) {
        this.bestWalk = bestWalk;
    }

    public String getFoodSource() {
        return foodSource;
    }

    public void setFoodSource(String foodSource) {
        this.foodSource = foodSource;
    }
    public String getHeadColour() {
        return headColour;
    }

    public void setHeadColour(String headColours) {
//        if (headColours == null){
//            ArrayList<String> headColourList=new ArrayList<String>();
//            this.headColour = headColourList;
//
//        }else{
//            this.headColour = new ArrayList<String>(Arrays.asList(headColours.split(";")));
//        }
        this.headColour = headColours;
    }

    public String getWingColour() {
        return wingColour;
    }

    public void setWingColour(String wingColours) {
//        if (wingColours == null){
//            ArrayList<String> wingColoursList=new ArrayList<String>();
//            this.wingColour = wingColoursList;
//
//        }else{
//            this.wingColour = new ArrayList<String>(Arrays.asList(wingColours.split(";")));
//        }
        this.wingColour = wingColours;

    }
    public String getBellyColour() {
        return bellyColour;
    }

    public void setBellyColour(String bellyColours) {
//        if (bellyColours == null){
//            ArrayList<String> bellyColoursList=new ArrayList<String>();
//            this.bellyColour = bellyColoursList;
//
//        }else{
//            this.bellyColour = new ArrayList<String>(Arrays.asList(bellyColours.split(";")));
//        }
        this.bellyColour = bellyColours;

    }

    public String getFurColour() {
        return furColour;
    }

    public void setFurColour(String furColour) {
        this.furColour = furColour;
    }

    public String getSkinColour() {
        return skinColour;
    }

    public void setSkinColour(String skinColour) {
        this.skinColour = skinColour;
    }

    public String getMarkings() {
        return markings;
    }

    public void setMarkings(String markings) {
        this.markings = markings;
    }

    public String getAnimalImage( ) {
        return animalImage;
    }
    public void setAnimalImage(String imageName){
        this.animalImage = imageName;


    }


    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", scientificName='" + scientificName + '\'' +
                ", minBodyLengthCm=" + minBodyLengthCm +
                ", maxBodyLengthCm=" + maxBodyLengthCm +
                ", minWingspanCm=" + minWingspanCm +
                ", maxWingspanCm=" + maxWingspanCm +
                ", description='" + description + '\'' +
                ", habitat='" + habitat + '\'' +
                ", bestTime='" + bestTime + '\'' +
                ", bestWalk='" + bestWalk + '\'' +
                ", foodSource='" + foodSource + '\'' +
                ", headColour='" + headColour + '\'' +
                ", wingColour='" + wingColour + '\'' +
                ", bellyColour='" + bellyColour + '\'' +
                ", furColour='" + furColour + '\'' +
                ", skinColour='" + skinColour + '\'' +
                ", markings='" + markings + '\'' +
                ", animalImage='" + animalImage + '\'' +
                '}';
    }
}
