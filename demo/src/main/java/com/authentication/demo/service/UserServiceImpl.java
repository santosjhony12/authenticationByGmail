package com.authentication.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.authentication.demo.entity.User;
import com.authentication.demo.repository.UserRepository;
import com.authentication.demo.service.interfaces.UserService;
import com.authentication.demo.web.dto.UserRegistrationRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final JavaMailSender mailSender;
    private final Map<String, String> confirmationCodes = new HashMap<>();

    public void sendConfirmationCode(String email){
        String code = generateConfirmationCode();
        confirmationCodes.put(email, code);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Confirmation Code");
        message.setText("Your confirmation code is: " + code);

        mailSender.send(message);
    }

    @Transactional(readOnly = false)
    public boolean confirmCode(String email, String code){
        String storedCode = confirmationCodes.get(email);
        if(storedCode != null && storedCode.equals(code)){
            confirmationCodes.remove(email);
            User user = userRepository.findByEmail(email);
            if(user != null){
                user.setConfirmed(true);
                userRepository.save(user);
            }
            return true;
        }
        return false;
    }


    private String generateConfirmationCode() {
        return String.format("%06d", new Random().nextInt(1000000));
    }

    @Override
    @Transactional
    public User save(UserRegistrationRequest userRequest) {
        if(userRepository.findByEmail(userRequest.email()) != null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already exists.");
        }

        User user = new User();
        user.setEmail(userRequest.email());
        user.setPassword(userRequest.password());
        sendConfirmationCode(userRequest.email());

        return userRepository.save(user);
    }

    @Override
    public User getByEmail(String email) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getByEmail'");
    }

    @Override
    public List<User> getAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public void deleteByEmail(String email) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteByEmail'");
    }

}