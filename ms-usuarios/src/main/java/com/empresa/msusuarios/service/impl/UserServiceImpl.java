package com.empresa.msusuarios.service.impl;


import com.empresa.msusuarios.model.User;
import com.empresa.msusuarios.repository.UserRepository;
import com.empresa.msusuarios.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(Long id, User user) {
        User existing = userRepository.findById(id).orElse(null);
        if (existing != null) {
            existing.setUsername(user.getUsername());
            existing.setPassword(user.getPassword());
            return userRepository.save(existing);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
