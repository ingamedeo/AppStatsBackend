package com.ingamedeo.controller;

import com.ingamedeo.entity.DeviceToken;
import com.ingamedeo.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
public class DeviceTokenController {

        @Autowired
        private TokenService tokenService;

        @RequestMapping(value = "/all", method = RequestMethod.GET)
        public Iterable<DeviceToken> getAll() {
            return tokenService.getAll();
        }

        //findByDeviceID

        @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
        public DeviceToken add(@RequestBody DeviceToken token) {
            return tokenService.add(token);
        }

}