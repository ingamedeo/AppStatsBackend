package com.ingamedeo.dao;

import com.ingamedeo.entity.AppLifecycleEvent;
import com.ingamedeo.entity.UserPerDay;
import org.hibernate.annotations.NamedNativeQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface AppLifecycleEventDao extends JpaRepository<AppLifecycleEvent, Long> {

    Iterable<AppLifecycleEvent> findByDeviceID(String deviceID);

    @Query(value = "select new com.ingamedeo.entity.UserPerDay(count(distinct e.deviceID), function('date_format', e.timestamp, '%d-%m-%Y')) from AppLifecycleEvent e " +
            "where e.appPackage = :app_package and function('date_format', e.timestamp, '%c') = :month and function('date_format', e.timestamp, '%Y') = :year " +
            "group by function('date_format', e.timestamp, '%d-%m-%Y') order by e.timestamp desc ")
    Iterable<UserPerDay> findNumOfUsersPerDay(@Param("year") String year, @Param("month") String month, @Param("app_package") String app_package);

}
