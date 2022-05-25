package com.isep.iotproject.Bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

public class ChartData {

    private float value;
    private String series;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+2")
    private Timestamp datetime;

    public ChartData(float value, String series, Timestamp datetime) {
        this.value = value;
        this.series = series;
        this.datetime = datetime;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public Timestamp getdatetime() {
        return datetime;
    }

    public void setdatetime(Timestamp datetime) {
        this.datetime = datetime;
    }
}
