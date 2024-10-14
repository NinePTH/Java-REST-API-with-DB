package com.appInventory.appInventory.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="appinfo")
public class AppInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    @Column(name="appname")
    private String appName;
    @Column(name="appowner")
    private String appOwner;

    public Integer getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppOwner() {
        return appOwner;
    }

    public void setAppOwner(String appOwner) {
        this.appOwner = appOwner;
    }
}
