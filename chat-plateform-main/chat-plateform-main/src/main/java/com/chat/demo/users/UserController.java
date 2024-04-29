package com.chat.demo.users;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;



@Controller
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @GetMapping("/")
    public String in() {
        return userService.in();
    }

    @GetMapping("/index")
    public String index() {
        return userService.index();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users")
    public String users(Model model) {
        return userService.users(model);
    }

    @GetMapping("/template1")
    public String navbar(Model model) {
        return userService.navbar(model);
    }
    @GetMapping("/userinfo")
    public String userinfo() {
        return userService.userinfo();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/adduser")
    public String adduser(Model model) {
        return userService.adduser(model);
    }

    @PostMapping("/save")
    public String login(Model model, @Valid User user, BindingResult bindingResult) {
        return userService.saveUser(model, user, bindingResult);
    }
}
