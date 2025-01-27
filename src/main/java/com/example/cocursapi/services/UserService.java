package com.example.cocursapi.services;

import com.example.cocursapi.models.User;
import com.example.cocursapi.repositorys.UserRepository;
import com.example.cocursapi.utils.UserDetailsImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    @Autowired
    public void setUserRepository(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username).orElseThrow(()-> new UsernameNotFoundException(
                String.format("User '%s' not found",username)
        ));

        return UserDetailsImplementation.build(user);
    }
}