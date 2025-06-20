package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.entity.Post;
import org.example.entity.User;
import org.example.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUserWithPosts(user, user.getPosts());
    }

    @GetMapping("/by-name")
    public List<User> getByName(@RequestParam String name) {
        return userService.getUsersByName(name);
    }

    @GetMapping("/by-domain")
    public List<User> getByDomain(@RequestParam String domain) {
        return userService.getUsersByEmailDomain(domain);
    }

    @GetMapping("/{userId}/posts")
    public List<Post> getPostsByUserId(@PathVariable Long userId) {
        return userService.getPostsByUserId(userId);
    }
}