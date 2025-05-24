package com.example.energias.renovables.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DatabaseExportService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Map<String, List<Map<String, Object>>> exportAllTables() {
        List<String> tables = jdbcTemplate.queryForList(
                "SHOW TABLES", String.class
        );
        Map<String, List<Map<String, Object>>> result = new HashMap<>();
        for (String table : tables) {
            List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT * FROM " + table);
            result.put(table, rows);
        }
        return result;
    }
}
