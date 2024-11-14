package cn.edu.fudan.advse.assignmentservice.repository;

import cn.edu.fudan.advse.assignmentservice.entity.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignmentRepository extends JpaRepository<Assignment, Integer> {
    List<Assignment> findByCourseId(Integer courseId);
}
