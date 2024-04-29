package com.chat.demo.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import com.chat.demo.ChatApplication;
@SpringBootApplication
public interface UserRepository extends JpaRepository<User,Long> {
    public static void main(String[] args) {
        SpringApplication.run(ChatApplication.class, args);
    }

    public User findByEmail(String email);
    
}
