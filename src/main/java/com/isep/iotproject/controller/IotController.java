package com.isep.iotproject.controller;

import com.isep.iotproject.Bean.ChartData;
import com.isep.iotproject.Bean.Item;
import com.isep.iotproject.Bean.ItemInterval;
import com.isep.iotproject.service.IoTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/api")
public class IotController {
    @Autowired
    private IoTService ioTService;

    @GetMapping("/getLatestItems")
    public List<ItemInterval> getLatestItems() {
        return this.ioTService.getLatestItems();
    }

    @GetMapping("/getHistory")
    public List<Item> getHistory(@RequestParam String sensor) {
        return this.ioTService.getHistory(sensor);
    }

    @PostMapping("/addFixData")
    public String addFixData(@RequestParam String sensor, @RequestParam float value, @RequestParam String datetime) {
        return this.ioTService.addFixData(sensor, value, datetime);
    }

    @GetMapping("/getChartData")
    public List<ChartData> getChartData() {
        return this.ioTService.getChartData();
    }
}
