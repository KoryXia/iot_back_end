package com.isep.iotproject.service.impl;

import com.isep.iotproject.Bean.ChartData;
import com.isep.iotproject.Bean.Item;
import com.isep.iotproject.Bean.ItemInterval;
import com.isep.iotproject.Dao.ItemDao;
import com.isep.iotproject.service.IoTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IoTServiceImpl implements IoTService {
    @Autowired
    private ItemDao itemDao;

    @Override
    public List<ItemInterval> getLatestItems() {
        String[] sensorList = {"temp", "humidity", "heartRate", "bodyTemp"};
        List<ItemInterval> itemList = new ArrayList<>();
        for (String each : sensorList) {
            ItemInterval tempItem = new ItemInterval();
            tempItem.setType(each);
            Map<String, Object> map = this.itemDao.getLatestItem(each);
            tempItem.setValue((float) map.get("value"));
            Timestamp datetime = (Timestamp) map.get("dateTime");
            int intervalHour = (int) ((System.currentTimeMillis() - datetime.getTime()) / (1000 * 60 * 60));
            int intervalMin = (int) ((System.currentTimeMillis() - datetime.getTime()) / (1000 * 60));
            int intervalSecond = (int) ((System.currentTimeMillis() - datetime.getTime()) / 1000);
            int intervalDay = (int) ((System.currentTimeMillis() - datetime.getTime()) / (1000 * 60 * 60 * 24));
            if (intervalHour < 24 && intervalHour >= 1) {
                tempItem.setInterval(intervalHour + " h before");
            } else if (intervalHour < 1) {
                if (intervalMin >= 1) {
                    tempItem.setInterval(intervalMin + " min before");
                } else {
                    tempItem.setInterval(intervalSecond + " s before");
                }
            } else {
                tempItem.setInterval(intervalDay + " d before");
            }
            itemList.add(tempItem);
        }
        return itemList;
    }

    @Override
    public List<Item> getHistory(String sensor) {
        List<Item> list = new ArrayList<>();
        List<Map<String, Object>> dataList = this.itemDao.getHistory(sensor);
        for (int i = 0; i < dataList.size(); i++) {
            list.add(new Item(i + 1, sensor, (Float) dataList.get(i).get("value"), (Timestamp) dataList.get(i).get("datetime")));
        }
        return list;
    }

    @Override
    public String addFixData(String sensor, float value, String datetime) {
        try{
            this.itemDao.addFixData(sensor, value, datetime);
            return "success";
        }
        catch (Error e){
            return "fail";
        }
    }

    @Override
    public List<ChartData> getChartData() {
        List<ChartData> list = new ArrayList<>();
        for (Map<String, Object> stringObjectMap : this.itemDao.getChartData()) {
            String type = (String) stringObjectMap.get("type");
            String series = type.equals("bodyTemp") ? "Body Temperature" : type.equals("humidity") ? "Humidity" : type.equals("temp") ? "Temperature" : "Heart Rate";
            list.add(new ChartData((Float) stringObjectMap.get("value"), series, (Timestamp) stringObjectMap.get("datetime")));
        }
        return list;
    }
}