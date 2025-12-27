package com.example.md4_session07.service;

import com.example.md4_session07.model.constant.Role;
import com.example.md4_session07.model.dto.UserDTO;
import com.example.md4_session07.model.entity.User;
import com.example.md4_session07.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

    public  User login(String username, String password, HttpSession session) {
        try{
            User user = userRepository.findByUsername(username);
            if (user != null && passwordEncoder.matches(password, user.getPassword())) {
                session.setAttribute("USER_SESSION", user);
                return user;
            }else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public User register(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRole(Role.valueOf(userDTO.getRole().toUpperCase()));
        try {
            return userRepository.save(user);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public void logout(HttpSession session){
        session.removeAttribute("USER_SESSION");
    }
    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }
}
