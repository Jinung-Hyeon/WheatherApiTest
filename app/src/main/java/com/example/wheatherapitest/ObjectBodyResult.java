package com.example.wheatherapitest;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

public class ObjectBodyResult {
    @SerializedName("body")
    public JsonObject object;
}
