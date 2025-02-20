package com.appInventory.appInventory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.appInventory.appInventory.model.AppInformation;

public interface AppInfoRepo extends JpaRepository<AppInformation, Integer> {
    @Query(value = "SELECT Id, appname, appowner FROM appinfo WHERE id > 1", nativeQuery = true)
    List<AppInformation> getAllAppsWithIdGreaterThan1();
}
