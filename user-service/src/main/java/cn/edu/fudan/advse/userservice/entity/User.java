package cn.edu.fudan.advse.userservice.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // database id

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    public User(User user) {
        this.id = user.id;
        this.name = user.name;
        this.password = user.password;
    }

}
