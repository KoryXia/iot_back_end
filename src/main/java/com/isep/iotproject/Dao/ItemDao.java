package com.isep.iotproject.Dao;

import com.isep.iotproject.Bean.ChartData;

import java.util.List;
import java.util.Map;

public interface ItemDao {
    Map<String, Object> getLatestItem(String sensor);

    List<Map<String, Object>> getHistory(String sensor);

    void addFixData(String sensor, float value, String datetime);

    List<Map<String, Object>> getChartData();
}
