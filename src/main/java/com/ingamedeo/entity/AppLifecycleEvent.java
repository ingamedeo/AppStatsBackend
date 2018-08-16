package com.ingamedeo.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "appEvents")
public class AppLifecycleEvent {

    public enum Event {
        OPEN,
        CLOSE,
        CRASH
    }

    private long id;
    private String appPackage;
    private Integer versionCode;
    private DeviceToken deviceID;
    private Event event;
    private String data;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NotNull
    public String getAppPackage() {
        return appPackage;
    }

    public void setAppPackage(String appPackage) {
        this.appPackage = appPackage;
    }

    @NotNull
    public Integer getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(Integer versionCode) {
        this.versionCode = versionCode;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "token", nullable = false)
    public DeviceToken getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(DeviceToken deviceID) {
        this.deviceID = deviceID;
    }

    @NotNull
    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
