package com.tts.UsersAPI.repo;

import com.tts.UsersAPI.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findByState(String state);

    @Query("SELECT DISTINCT u.state FROM User u")
    List<String> findByState();

    List<User> findAll();
}