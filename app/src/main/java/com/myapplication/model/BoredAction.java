package com.myapplication.model;

import com.google.gson.annotations.SerializedName;

public class BoredAction {

    @SerializedName("key")
    private String key;

    @SerializedName("title")
    private String title;

    @SerializedName("activity")
    private String activity;

    @SerializedName("accessibility")
    private Float accessibility;

    @SerializedName("type")
    private EActionType type;

    @SerializedName("participants")
    private Float participants;

    @SerializedName("price")
    private Float price;

    @SerializedName("link")
    private String link;

    public BoredAction(String title, String key, String activity, Float accessibility, EActionType  type, Float participants, Float price, String link) {
        this.activity = activity;
        this.accessibility = accessibility;
        this.type = type;
        this.participants = participants;
        this.price = price;
        this.link = link;
        this.key = key;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public Float getAccessibility() {
        return accessibility;
    }

    public void setAccessibility(Float accessibility) {
        this.accessibility = accessibility;
    }

    public EActionType getType() {
        return type;
    }

    public void setType(EActionType type) {
        this.type = type;
    }

    public Float getParticipants() {
        return participants;
    }

    public void setParticipants(Float participants) {
        this.participants = participants;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
