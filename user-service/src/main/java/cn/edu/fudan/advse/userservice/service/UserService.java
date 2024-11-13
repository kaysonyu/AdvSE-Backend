package cn.edu.fudan.advse.userservice.service;

import cn.edu.fudan.advse.userservice.entity.User;

public interface UserService {

    User register(User user) throws Exception;

    String login(String username, String password) throws Exception;

}
