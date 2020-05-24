package com.bridgelabz.lmsapi.controller;

import com.bridgelabz.lmsapi.dto.Response;
import com.bridgelabz.lmsapi.dto.UserDTO;
import com.bridgelabz.lmsapi.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bridgelabz.lmsapi.dto.LoginDTO;
import com.bridgelabz.lmsapi.dto.JwtResponse;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/register")
    public ResponseEntity<Response> saveUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.save(userDTO));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<JwtResponse> authenticate(@RequestBody LoginDTO loginRequest) throws Exception {
        String token = userService.getAuthenticationToken(loginRequest);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<Response> forgotPassword(@RequestParam String email) throws MessagingException {
        return ResponseEntity.ok(userService.sendMail(email));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<Response> resetPassword(@RequestParam String token, @RequestParam("password") String password) {
        return ResponseEntity.ok(userService.resetPassword(token, password));
    }
}
