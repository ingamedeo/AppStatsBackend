package com.ingamedeo.controller;

import com.ingamedeo.entity.AppLifecycleEvent;
import com.ingamedeo.entity.DeviceToken;
import com.ingamedeo.entity.UserPerDay;
import com.ingamedeo.service.AppLifecycleEventService;
import com.ingamedeo.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stats")
public class AppStatsController {

    @Autowired
    private AppLifecycleEventService appLifecycleEventService;

    @Autowired
    private TokenService tokenService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Iterable<AppLifecycleEvent> getAll() {
        return appLifecycleEventService.getAll();
    }

    //findByDeviceID

    @RequestMapping(value = "/byDeviceID/{id}", method = RequestMethod.GET)
    public Iterable<AppLifecycleEvent> getLogsByDeviceID(@PathVariable("id") String deviceID) {
        return appLifecycleEventService.getAppOpenEntriesByDeviceID(deviceID);
    }

    @RequestMapping(value = "/graph/numUsersPerDay")
    public Iterable<UserPerDay> findNumOfUsersPerDay(@RequestParam(value = "year", required = true) String year, @RequestParam(value = "month", required = true) String month, @RequestParam(value = "app_package", required = true) String app_package) {
        return appLifecycleEventService.findNumOfUsersPerDay(year, month, app_package);
    }

    @RequestMapping(value = "/graph/avgUsersPerDay")
    public String findAvgUsersPerDay(@RequestParam(value = "year", required = true) String year, @RequestParam(value = "month", required = true) String month, @RequestParam(value = "app_package", required = true) String app_package) {
        return appLifecycleEventService.findAvgUsersPerDay(year, month, app_package);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{deviceid}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> add(@PathVariable("deviceid") String deviceID, @RequestBody AppLifecycleEvent entry) {

        DeviceToken token = tokenService.getTokenByDeviceID(deviceID);
        if (deviceID==null || token==null) {
            //Return 404 error if the device token sent can't be found in the database
            return new ResponseEntity<>(-1, HttpStatus.NOT_FOUND);
        }

        //Update token last ts
        tokenService.updateLastUsed(deviceID);

        //Set correct token
        entry.setDeviceID(token);

        return new ResponseEntity<>(appLifecycleEventService.add(entry), HttpStatus.OK);
    }
}
