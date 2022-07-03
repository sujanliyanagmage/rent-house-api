package com.sns.core.service.serviceImpl;

import com.sns.core.model.User;

import java.util.List;

public interface IuserService {

     User saveUser(User user) ;

     List<User> getAll() ;

     User getUserDetailsByUserName();
     void processOAuthPostLogin(String username);
}
