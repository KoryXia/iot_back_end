package com.isep.iotproject.service;


import com.isep.iotproject.Bean.ChartData;
import com.isep.iotproject.Bean.Item;
import com.isep.iotproject.Bean.ItemInterval;

import java.util.List;

public interface IoTService {
    List<ItemInterval> getLatestItems();

    List<Item> getHistory(String sensor);

    String addFixData(String sensor, float value, String datetime);

    List<ChartData> getChartData();
}
