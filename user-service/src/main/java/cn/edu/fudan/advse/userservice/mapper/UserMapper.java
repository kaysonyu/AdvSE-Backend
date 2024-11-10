package cn.edu.fudan.advse.userservice.mapper;

import cn.edu.fudan.advse.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMapper extends JpaRepository<User, Integer> {

    User findById(int id);

    User findByName(String name);

}
