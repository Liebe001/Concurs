package com.example.cocursapi.controllers;

import com.example.cocursapi.DTO.SigninRequest;
import com.example.cocursapi.DTO.SignupRequest;
import com.example.cocursapi.models.User;
import com.example.cocursapi.repositorys.UserRepository;
import com.example.cocursapi.utils.JwtCore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class SecurityController {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JwtCore jwtCore;


    @Autowired
    public void setJwtCore(JwtCore jwtCore) {
        this.jwtCore = jwtCore;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/check")
    ResponseEntity<?> check(@RequestBody String jwt){
        if(jwtCore.validateToken(jwt))
            return ResponseEntity.ok("Yes");
        else return ResponseEntity.badRequest().body("NOO");


    }

    @PostMapping("/signup")
    ResponseEntity<?>signup(@RequestBody SignupRequest signupRequest){
        if(userRepository.existsUsersByUsername(signupRequest.getUsername()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Choose different name");
        if (userRepository.existsUsersByEmail(signupRequest.getEmail())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Choose different email");
        }
        String hashed = passwordEncoder.encode(signupRequest.getPassword());
        User user = new User();
        user.setUsername(signupRequest.getUsername());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(hashed);
        userRepository.save(user);
        return ResponseEntity.ok("Success, daddy");
    }

    @PostMapping("/signin")
    ResponseEntity<?>signin(@RequestBody SigninRequest signinRequest){
        Authentication authentication = null;
        try{
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getUsername(),signinRequest.getPassword()));
 
        }catch(BadCredentialsException e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtCore.generationToken(authentication);
        return ResponseEntity.ok(jwt);
    }

}