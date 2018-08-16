package com.ingamedeo.dao;

import com.ingamedeo.entity.DeviceToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface DeviceTokenDao extends JpaRepository<DeviceToken, Long> {

    DeviceToken findByDeviceID(String deviceID);
}
