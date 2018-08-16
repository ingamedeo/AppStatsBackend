package com.ingamedeo.dao;

import com.ingamedeo.entity.APIUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface APIUserDao extends JpaRepository<APIUser, Long> {
    APIUser findByApplicationID(String applicationID);
}
