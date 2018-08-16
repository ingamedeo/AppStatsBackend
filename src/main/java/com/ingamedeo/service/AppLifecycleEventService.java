package com.ingamedeo.service;

import com.ingamedeo.dao.AppLifecycleEventDao;
import com.ingamedeo.entity.AppLifecycleEvent;
import com.ingamedeo.entity.UserPerDay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.Date;

@Service
public class AppLifecycleEventService {

    @Autowired
    private AppLifecycleEventDao appLifecycleEventDao;

    public Iterable<AppLifecycleEvent> getAll() {
        return appLifecycleEventDao.findAll();
    }

    public Iterable<AppLifecycleEvent> getAppOpenEntriesByDeviceID(String deviceID) {
        return appLifecycleEventDao.findByDeviceID(deviceID);
    }

    public Iterable<UserPerDay> findNumOfUsersPerDay(String year, String month, String app_package) {
        return appLifecycleEventDao.findNumOfUsersPerDay(year, month, app_package);
    }

    public String findAvgUsersPerDay(String year, String month, String app_package) {
        Iterable<UserPerDay> numUsersDay = appLifecycleEventDao.findNumOfUsersPerDay(year, month, app_package);
        int numDays = 0;
        double tot = 0;
        for (UserPerDay user : numUsersDay) {
            numDays++;
            tot+=user.getNumber();
        }

        DecimalFormat df = new DecimalFormat("#.##");

        //Dividing by 0
        if (numDays==0) {
            return "0";
        }

        return df.format(tot/numDays);
    }

    public Long add(AppLifecycleEvent event) {

        //Set timestamp upon uploading
        event.setTimestamp(new Date());

        AppLifecycleEvent lo = appLifecycleEventDao.save(event);
        if (lo!=null) {
            return lo.getId();
        }
        return null;
    }

}
