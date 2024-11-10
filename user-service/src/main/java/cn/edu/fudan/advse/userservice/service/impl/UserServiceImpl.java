package cn.edu.fudan.advse.userservice.service.impl;

import cn.edu.fudan.advse.userservice.entity.User;
import cn.edu.fudan.advse.userservice.mapper.UserMapper;
import cn.edu.fudan.advse.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int register(User user) {
        // check username
        User old = userMapper.findByName(user.getName());
        int result = 0;
        if (old != null) {
            result = -1;
        } else {
            // insert
            userMapper.save(user);
        }
        return result;
    }
}
