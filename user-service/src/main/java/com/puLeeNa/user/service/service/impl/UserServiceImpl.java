package com.puLeeNa.user.service.service.impl;

import com.puLeeNa.user.service.exception.UserException;
import com.puLeeNa.user.service.modal.User;
import com.puLeeNa.user.service.repository.UserRepository;
import com.puLeeNa.user.service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) throws UserException {
        Optional<User> user = userRepository.findById(id);  //  Optional<User> - whether user is present or not
        if(user.isPresent()) {
            return user.get();
        }
        throw new UserException("user not found");
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Long id) throws UserException {
        Optional<User> userCheck = userRepository.findById(id);
        if(userCheck.isEmpty()) {
            throw new UserException("user not found");
        }
        User existinguser = userCheck.get();
        userRepository.deleteById(existinguser.getId());
    }

    @Override
    public User updateUser(Long id, User user) throws UserException {
        Optional<User> userCheck = userRepository.findById(id);
        if(userCheck.isEmpty()) {
            throw new UserException("user not found");
        }
        User existinguser = userCheck.get();
        existinguser.setFullName(user.getFullName());
        existinguser.setEmail(user.getEmail());
        existinguser.setPhone(user.getPhone());
        existinguser.setRole(user.getRole());
        existinguser.setPassword(user.getPassword());
        existinguser.setUsername(user.getUsername());
        return userRepository.save(existinguser);
    }
}
