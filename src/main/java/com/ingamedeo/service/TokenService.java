package com.ingamedeo.service;

import com.ingamedeo.dao.DeviceTokenDao;
import com.ingamedeo.entity.DeviceToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class TokenService {

    @Autowired
    private DeviceTokenDao deviceTokenDao;

    public Iterable<DeviceToken> getAll() {
        return deviceTokenDao.findAll();
    }

    public DeviceToken getTokenByDeviceID(String deviceID) {
        return deviceTokenDao.findByDeviceID(deviceID);
    }

    public Date updateLastUsed(String deviceID) {

        DeviceToken token = deviceTokenDao.findByDeviceID(deviceID);
        if (token!=null) {
            token.setLastUsedTs(new Date());
            return token.getLastUsedTs();
        }

        return null;
    }

    public DeviceToken add(DeviceToken token) {

        //Set timestamp upon uploading
        token.setLastUsedTs(new Date());

        String tempUUID = null;
        DeviceToken conflictingToken = null;

        //Loop till we find a free UUID
        do {
            tempUUID = UUID.randomUUID().toString();
            conflictingToken = deviceTokenDao.findByDeviceID(tempUUID);
        } while (conflictingToken!=null);

        //Set unique UUID
        token.setDeviceID(tempUUID);

        return deviceTokenDao.save(token);
    }
}
