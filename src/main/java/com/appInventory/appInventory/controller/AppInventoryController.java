package com.appInventory.appInventory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appInventory.appInventory.model.AppInformation;
import com.appInventory.appInventory.repository.AppInfoRepo;

@RestController
@RequestMapping("/apps")
public class AppInventoryController {
    @Autowired
    AppInfoRepo appRepo;

    @GetMapping
    public ResponseEntity<List<AppInformation>> getApp() {
        List<AppInformation> allAppsInfo = appRepo.findAll();
        if (allAppsInfo.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(allAppsInfo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getAppById(@PathVariable int id) {
        AppInformation appInfo = appRepo.findById(id).orElse(null);
        if (appInfo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("There is no app Id: " + id));
        }
        return ResponseEntity.ok(appInfo);
    }

    @PostMapping
    public ResponseEntity<AppInformation> addApp(@RequestBody AppInformation newAppInfo) {
        appRepo.save(newAppInfo);
        return ResponseEntity.ok(newAppInfo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateApp(@PathVariable int id, @RequestBody AppInformation updatedAppInfo) {
        AppInformation oldAppInfo = appRepo.findById(id).orElse(null);
        if (oldAppInfo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("There is no app Id: " + id));
        }
        oldAppInfo.setAppName(updatedAppInfo.getAppName());
        oldAppInfo.setAppOwner(updatedAppInfo.getAppOwner());
        appRepo.save(oldAppInfo);
        return ResponseEntity.ok(oldAppInfo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteApp(@PathVariable int id) {
        AppInformation appInfo = appRepo.findById(id).orElse(null);
        if (appInfo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("There is no app Id: " + id));
        }
        appRepo.delete(appInfo);
        return ResponseEntity.ok(appInfo);
    }
}
