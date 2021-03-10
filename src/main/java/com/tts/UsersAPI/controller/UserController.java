package com.tts.UsersAPI.controller;


import com.tts.UsersAPI.model.User;
import com.tts.UsersAPI.repo.UserRepository;
import com.tts.UsersAPI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    public UserController (UserRepository userRepository){

        this.userRepository = userRepository;
    }
    @PostMapping("/add")
    public ResponseEntity<Void> createUser(@RequestBody @Valid User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id){
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user.get(), HttpStatus.OK);
    }

    @GetMapping("/users")
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/users/state")
    public List<User> getUsers(@RequestParam(value="state", required=false) String state) {
        if (state != null) {
            return userRepository.findByState(state);
        }
        return (List<User>) userRepository.findAll();
    }
    @DeleteMapping("/users/{id}")
    public void createUser(@PathVariable(value="id") Long id){

        userRepository.deleteById(id);
    }
    @PutMapping("/users/{id}")
    public void createUser(@PathVariable(value="id") Long id, @RequestBody User user){

        userRepository.save(user);
    }
    @PostMapping("/users")
    public void createUser(@RequestBody User user){

        userRepository.save(user);
    }
}


