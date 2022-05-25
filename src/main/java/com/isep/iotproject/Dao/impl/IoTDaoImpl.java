package com.isep.iotproject.Dao.impl;

import com.isep.iotproject.Dao.ItemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.List;

@Repository
public class IoTDaoImpl implements ItemDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Map<String, Object> getLatestItem(String sensor) {
        String sql = String.format("select value,dateTime from %s order by dateTime desc limit 1", sensor);
        try {
            return this.jdbcTemplate.queryForMap(sql);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Map<String, Object>> getHistory(String sensor) {
        String sql = String.format("select value,dateTime from %s order by dateTime", sensor);
        try {
            return this.jdbcTemplate.queryForList(sql);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void addFixData(String sensor, float value, String datetime) {
        String sql = String.format("insert into %s (value, dateTime) values (?,?)", sensor);
        String sqlChart = "insert into chartData (type, value, dateTime) values (?,?,?)";
        this.jdbcTemplate.update(sql, value, datetime);
        this.jdbcTemplate.update(sqlChart, sensor, value, datetime);
    }

    @Override
    public List<Map<String, Object>> getChartData() {
        String sql = "select type,value,dateTime from chartData order by dateTime limit 40";
        try {
            return this.jdbcTemplate.queryForList(sql);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}