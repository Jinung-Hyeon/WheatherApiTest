package com.example.wheatherapitest;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

public class ObjectResult {
    @SerializedName("response")
    public JsonObject object;

    @Override
    public String toString() {
        return "ObjectResult{" +
                "object=" + object +
                '}';
    }

    public Object getObject() {
        return object;
    }

    public void setObject(JsonObject object) {
        this.object = object;
    }

}
