package com.iot.model;

import com.google.gson.annotations.SerializedName;

public class Channel {

    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("latitude")
    private String latitude;
    @SerializedName("longitude")
    private String longitude;
    @SerializedName("field1")
    private String field1;
    @SerializedName("field2")
    private String field2;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("last_entry_id")
    private String lastEntryId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public String getField2() {
        return field2;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getLastEntryId() {
        return lastEntryId;
    }

    public void setLastEntryId(String lastEntryId) {
        this.lastEntryId = lastEntryId;
    }
}
