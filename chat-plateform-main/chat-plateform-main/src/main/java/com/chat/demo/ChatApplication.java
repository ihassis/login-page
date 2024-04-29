package com.chat.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.chat.demo.users.User;
import com.chat.demo.users.UserRepository;

@SpringBootApplication
public class ChatApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatApplication.class, args);
	}
	@Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository){
        return args -> {
            userRepository.save(new User(null,"hamza","hamimi@insea.ac.ma","h","ADMIN"));
            userRepository.save(new User(null,"zakaria","zamimi@insea.ac.ma","z","USER"));
            userRepository.save(new User(null,"ahmed","aamimi@insea.ac.ma","a","USER"));
            userRepository.save(new User(null,"mohammed","mamimi@insea.ac.ma","m","ADMIN"));
			userRepository.findAll().forEach(u->{
				System.out.println(u.getName()+" has been added to the database");
			} );
        };
    }

}
