package com.example.dream11backend.service;

import com.example.dream11backend.entity.User;
import com.example.dream11backend.exception.InsufficientBalanceException;
import com.example.dream11backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("null")
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found with username: " + username));
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long id, User updatedUser) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        user.setEmail(updatedUser.getEmail());
        user.setFullName(updatedUser.getFullName());

        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Transactional
    public void addBalance(String username, Double amount) {
        User user = getUserByUsername(username);
        user.setBalance(user.getBalance() + amount);
        userRepository.save(user);
    }

    @Transactional
    public void deductBalance(String username, Double amount) {
        User user = getUserByUsername(username);
        if (user.getBalance() < amount) {
            throw new InsufficientBalanceException(amount, user.getBalance());
        }
        user.setBalance(user.getBalance() - amount);
        userRepository.save(user);
    }

    @Transactional
    public void incrementContestsJoined(String username) {
        User user = getUserByUsername(username);
        user.setContestsJoined(user.getContestsJoined() + 1);
        userRepository.save(user);
    }

    @Transactional
    public void incrementContestsWon(String username, Double winnings) {
        User user = getUserByUsername(username);
        user.setContestsWon(user.getContestsWon() + 1);
        user.setTotalWinnings(user.getTotalWinnings() + winnings);
        userRepository.save(user);
    }
}
