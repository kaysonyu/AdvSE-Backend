package cn.edu.fudan.advse.userservice.repository;

import cn.edu.fudan.advse.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);

}
