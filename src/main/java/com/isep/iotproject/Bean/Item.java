package com.isep.iotproject.Bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

public class Item {
    private int key;
    private String type;
    private float value;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+2")
    private Timestamp datetime;

    public Item(int key, String type, float value, Timestamp datetime) {
        this.key = key;
        this.type = type;
        this.value = value;
        this.datetime = datetime;
    }

    public int getkey() {
        return key;
    }

    public void setkey(int key) {
        this.key = key;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public Timestamp getDatetime() {
        return datetime;
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        return "Item{" +
                "key=" + key +
                ", type='" + type + '\'' +
                ", value=" + value +
                ", datetime=" + datetime +
                '}';
    }
}
