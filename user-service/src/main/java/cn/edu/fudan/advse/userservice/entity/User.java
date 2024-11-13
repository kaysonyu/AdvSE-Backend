package cn.edu.fudan.advse.userservice.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // database id

    @Column(name = "username", unique = true)
    @NotBlank(message = "username cannot be empty")
    @Size(min = 2, max = 20, message = "username must be between 2 and 20 characters long")
    private String username;

    @Column(name = "password")
    @NotBlank(message = "password cannot be empty")
    @Size(min = 6, message = "password must contain at least 6 characters")
    private String password;

    @Column(name = "email")
    @NotBlank(message = "email cannot be empty")
    @Email(message = "email is not formatted correctly")
    private String email;

    @Column(name = "institution")
    @NotBlank(message = "institution cannot be empty")
    private String institution;

    @Column(name = "region")
    @NotBlank(message = "region cannot be empty")
    private String region;

    @Column(name = "role")
    @NotNull(message = "role cannot be empty")
    @Enumerated(EnumType.STRING)
    private Role role;

    public enum Role {
        ADMIN,
        TEACHER,
        STUDENT
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", institution='" + institution + '\'' +
                ", region='" + region + '\'' +
                ", role=" + role +
                '}';
    }
}

