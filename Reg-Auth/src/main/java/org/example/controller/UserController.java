package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public String showAllUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users";
    }
}