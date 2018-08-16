package com.ingamedeo.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tokens")
public class DeviceToken {

    private String deviceID;
    private String model;
    private String androidVersion;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUsedTs;

    @Id
    public String getDeviceID() {
        return deviceID;
    }

    public String getModel() {
        return model;
    }

    public String getAndroidVersion() {
        return androidVersion;
    }

    public Date getLastUsedTs() {
        return lastUsedTs;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setAndroidVersion(String androidVersion) {
        this.androidVersion = androidVersion;
    }

    public void setLastUsedTs(Date lastUsedTs) {
        this.lastUsedTs = lastUsedTs;
    }
}
