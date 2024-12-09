package com.jhcompany.studyLink.login.service;

import com.jhcompany.studyLink.login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;



}
