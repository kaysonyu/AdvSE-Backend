package cn.edu.fudan.advse.assignmentservice.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "assignments")
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "course_id")
    private Integer courseId;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;
}
