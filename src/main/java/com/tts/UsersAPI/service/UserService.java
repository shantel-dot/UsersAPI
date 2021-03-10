package com.tts.UsersAPI.service;

import com.tts.UsersAPI.model.User;
import com.tts.UsersAPI.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();}
    public void save(User user){
        userRepository.save(user);
    }
    public List<String> findByState() {
        return userRepository.findByState();}
}
