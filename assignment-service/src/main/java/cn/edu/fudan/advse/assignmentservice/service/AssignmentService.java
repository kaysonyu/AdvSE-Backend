package cn.edu.fudan.advse.assignmentservice.service;

import cn.edu.fudan.advse.assignmentservice.dto.AssignmentDTO;
import cn.edu.fudan.advse.assignmentservice.entity.Assignment;

import java.util.List;

public interface AssignmentService {
    public Assignment getAssignmentById(Integer id);
    public Assignment CreateAssignment(AssignmentDTO assignment);

    List<Assignment> getAssignmentsByCourseId(Integer courseId);
}
