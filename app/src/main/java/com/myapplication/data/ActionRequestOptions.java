package com.myapplication.data;

public class ActionRequestOptions {

    private String key;
    private String type;
    private double minPrice;
    private double maxPrice;
    private double minAccessibility;
    private double maxAccessibility;
    private Integer participants;

    public ActionRequestOptions(
            String type,
            double minPrice,
            double maxPrice,
            double minAccessibility,
            double maxAccessibility,
            Integer participants
    ) {
        this.type = type;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.minAccessibility = minAccessibility;
        this.maxAccessibility = maxAccessibility;
        this.participants = participants;
    }

    public ActionRequestOptions(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getParticipants() {
        return participants;
    }

    public void setParticipants(Integer participants) {
        this.participants = participants;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Float minPrice) {
        this.minPrice = minPrice;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Float maxPrice) {
        this.maxPrice = maxPrice;
    }

    public double getMinAccessibility() {
        return minAccessibility;
    }

    public void setMinAccessibility(Float minAccessibility) {
        this.minAccessibility = minAccessibility;
    }

    public double getMaxAccessibility() {
        return maxAccessibility;
    }

    public void setMaxAccessibility(Float maxAccessibility) {
        this.maxAccessibility = maxAccessibility;
    }
}