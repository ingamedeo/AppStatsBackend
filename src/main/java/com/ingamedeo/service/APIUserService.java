package com.ingamedeo.service;

import com.ingamedeo.dao.APIUserDao;
import com.ingamedeo.entity.APIUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

import static java.util.Collections.emptyList;

@Service
public class APIUserService implements UserDetailsService {

    @Autowired
    private APIUserDao apiUserDao;

    @Override
    public UserDetails loadUserByUsername(String applicationID) throws UsernameNotFoundException {
        APIUser apiUser = apiUserDao.findByApplicationID(applicationID);
        if (apiUser == null) {
            throw new UsernameNotFoundException(applicationID);
        }
        return new User(apiUser.getApplicationID(), apiUser.getSecretKey(), emptyList());
    }
}
