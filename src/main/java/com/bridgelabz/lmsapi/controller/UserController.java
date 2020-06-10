package com.bridgelabz.lmsapi.controller;

import com.bridgelabz.lmsapi.response.Response;
import com.bridgelabz.lmsapi.dto.UserDTO;
import com.bridgelabz.lmsapi.exception.LmsApiApplicationException;
import com.bridgelabz.lmsapi.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bridgelabz.lmsapi.dto.LoginDTO;
import com.bridgelabz.lmsapi.response.JwtResponse;

import javax.mail.MessagingException;

/**
 * User controller to register, authenticate, and reset the forgotten password
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * @param userDTO
     * @return User saved successfully
     */
    @PostMapping("/register")
    public ResponseEntity<Response> saveUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.save(userDTO));
    }

    /**
     * @param loginRequest
     * @return JWT token after valid authentication
     * @throws Exception
     */
    @PostMapping("/authenticate")
    public ResponseEntity<JwtResponse> authenticate(@RequestBody LoginDTO loginRequest) throws Exception {
        String token = userService.getAuthenticationToken(loginRequest);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    /**
     * @param email
     * @return mail to reset password
     * @throws MessagingException
     * @throws LmsApiApplicationException
     */
    @PostMapping("/forgot-password")
    public ResponseEntity<Response> forgotPassword(@RequestParam String email) throws MessagingException, LmsApiApplicationException {
        return ResponseEntity.ok(userService.sendMail(email));
    }

    /**
     * @param token
     * @param password
     * @return password reset successfully
     * @throws LmsApiApplicationException
     */
    @PostMapping("/reset-password")
    public ResponseEntity<Response> resetPassword(@RequestParam String token, @RequestParam("password") String password) throws LmsApiApplicationException {
        return ResponseEntity.ok(userService.resetPassword(token, password));
    }
}
