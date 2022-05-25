package com.isep.iotproject.Bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

public class ItemInterval {
    private String type;
    private float value;
    private String interval;

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

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    @Override
    public String toString() {
        return "ItemInterval{" +
                "type='" + type + '\'' +
                ", value=" + value +
                ", interval='" + interval + '\'' +
                '}';
    }
}
