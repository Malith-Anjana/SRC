package com.example.csse_project;

public class Trip_Class {
    private String tokenID;
    private String start;
    private String end;
    private String distance;
    private int qty;
    private float fee;

    public Trip_Class(float fee) {
        this.fee = fee;
    }

    private String date;

    public Trip_Class() {
    }

    public Trip_Class(String tokenID, String start, String end, String distance, int qty, float fee, String date) {
        this.tokenID = tokenID;
        this.start = start;
        this.end = end;
        this.distance = distance;
        this.qty = qty;
        this.fee = fee;
        this.date = date;
    }

    public String getTokenID() {
        return tokenID;
    }

    public void setTokenID(String tokenID) {
        this.tokenID = tokenID;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(float fee) {
        this.fee = fee;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
