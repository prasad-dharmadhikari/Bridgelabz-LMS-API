package com.bridgelabz.lmsapi.service;

import com.bridgelabz.lmsapi.dto.LoginDTO;
import com.bridgelabz.lmsapi.dto.Response;
import com.bridgelabz.lmsapi.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.mail.MessagingException;

public interface IUserService {
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
    Response save(UserDTO userDTO);
    String getAuthenticationToken(LoginDTO loginRequest) throws Exception;
    Response sendMail(String email) throws MessagingException;
    Response resetPassword(String token, String password);
}
