package cn.edu.fudan.advse.userservice.service.impl;

import cn.edu.fudan.advse.userservice.entity.User;
import cn.edu.fudan.advse.userservice.repository.UserRepository;
import cn.edu.fudan.advse.userservice.service.UserService;
import cn.edu.fudan.advse.userservice.security.JwtTokenProvider;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    // password encode
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public User register(@Valid User user) throws Exception {
        // check username
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new Exception("username already exists");
        }
        // encrypt the password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // Check whether the role is TEACHER or STUDENT
        if (user.getRole() == null ||
                (!user.getRole().equals(User.Role.TEACHER) && !user.getRole().equals(User.Role.STUDENT))) {
            throw new Exception("role must be TEACHER or STUDENT");
        }
        return userRepository.save(user);
    }

    @Override
    public String login(String username, String password) throws Exception {
        User user = userRepository.findByUsername(username);
        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            throw new Exception("username or password is incorrect");
        }
        String token = jwtTokenProvider.generateToken(user);
        return token;
    }

}
