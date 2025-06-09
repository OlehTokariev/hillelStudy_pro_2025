package org.example.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.entity.Post;
import org.example.entity.User;
import org.example.repository.PostRepository;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Transactional
    public User createUserWithPosts(User user, List<Post> posts) {
        User savedUser = userRepository.save(user);
        for (Post post : posts) {
            post.setUser(savedUser);
            postRepository.save(post);
        }
        return savedUser;
    }

    public List<User> getUsersByName(String name) {
        return userRepository.findByName(name);
    }

    public List<User> getUsersByEmailDomain(String domain) {
        return userRepository.findByEmailEndingWith(domain);
    }

    public List<Post> getPostsByUserId(Long userId) {
        return postRepository.findByUserId(userId);
    }
}