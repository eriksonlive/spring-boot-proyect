package com.example.energias.renovables.controller;

import com.example.energias.renovables.service.DatabaseExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.List;

@RestController
public class DatabaseExportController {

    @Autowired
    private DatabaseExportService exportService;

    @GetMapping("/api/export-db")
    public Map<String, List<Map<String, Object>>> exportDatabase() {
        return exportService.exportAllTables();
    }
}
