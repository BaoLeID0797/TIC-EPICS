package com.iot.model;

import com.google.gson.annotations.SerializedName;

public class Feeds {
    @SerializedName("created_at")
    private String created_at;
    @SerializedName("entry_id")
    private String entryId;
    @SerializedName("field1")
    private String field1;
    @SerializedName("field2")
    private String field2;

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getEntryId() {
        return entryId;
    }

    public void setEntryId(String entryId) {
        this.entryId = entryId;
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
}
