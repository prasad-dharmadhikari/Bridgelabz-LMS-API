package com.bridgelabz.lmsapi.service;

import java.time.LocalDateTime;
import java.util.ArrayList;

import com.bridgelabz.lmsapi.dto.LoginDTO;
import com.bridgelabz.lmsapi.dto.Response;
import com.bridgelabz.lmsapi.dto.UserDTO;
import com.bridgelabz.lmsapi.model.User;
import com.bridgelabz.lmsapi.repository.UserRepository;
import com.bridgelabz.lmsapi.util.JwtTokenUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class UserService implements IUserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), new ArrayList<>());
    }

    @Override
    public Response save(UserDTO userDTO) {
        userDTO.setCreatorStamp(LocalDateTime.now());
        userDTO.setCreatorUser(userDTO.getFirstName());
        userDTO.setVerified("yes");
        userDTO.setPassword(bcryptEncoder.encode(userDTO.getPassword()));
        User newUser = modelMapper.map(userDTO, User.class);
        userRepository.save(newUser);
        return new Response(200, "Registration Successful");
    }

    @Override
    public String getAuthenticationToken(LoginDTO loginRequest) throws Exception {
        authenticate(loginRequest.getUsername(), loginRequest.getPassword());
        final UserDetails userDetails = loadUserByUsername(loginRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return token;
    }

    @Override
    public Response sendMail(String email) throws MessagingException {
        User user = userRepository.findByEmail(email);
        final String token = jwtTokenUtil.generatePasswordResetToken(String.valueOf(user.getId()));
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setTo(email);
        helper.setText("Hii " + user.getFirstName() + "\n" + " You requested to reset password, if YES then click on link put your new password and NO then ignore \n" +
                "http://localhost:8080/user/reset-password?token=" + token);
        helper.setSubject("Password Reset Request");
        javaMailSender.send(message);
        return new Response(200, "Mail Sent Successfully");
    }

    @Override
    public Response resetPassword(String token, String password) {
        if (jwtTokenUtil.isTokenExpired(token)) {
            return null;
        }
        String encodedPassword = bcryptEncoder.encode(password);
        long id = Long.parseLong(jwtTokenUtil.getSubjectFromToken(token));
        User user = userRepository.findById(id).get();
        user.setPassword(encodedPassword);
        User updatedUser = userRepository.save(user);
        if (updatedUser != null && updatedUser.getPassword().equalsIgnoreCase(encodedPassword))
            return new Response(200, "Password Reset Successfully");
        return new Response(500, "Something went wrong");
    }

    public void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}