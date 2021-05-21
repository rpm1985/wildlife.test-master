package com.example.animalapp;

public class DataItem {
    String resIdThumbnail;
    String reserveName;
    String shortDesc;
    String type;

    public DataItem(String resIdThumbnail, String reserveName,String type,  String shortDesc) {
        this.resIdThumbnail = resIdThumbnail;
        this.reserveName = reserveName;
        this.shortDesc = shortDesc;
        this.type = type;

    }

    public String getResIdThumbnail() {
        return resIdThumbnail;
    }

    public void setResIdThumbnail(String resIdThumbnail) {
        this.resIdThumbnail = resIdThumbnail;
    }

    public String getReserveName() {
        return reserveName;
    }

    public void setReserveName(String reserveName) {
        this.reserveName = reserveName;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
