package cn.edu.fudan.advse.assignmentservice.service.impl;

import cn.edu.fudan.advse.assignmentservice.dto.AssignmentDTO;
import cn.edu.fudan.advse.assignmentservice.entity.Assignment;
import cn.edu.fudan.advse.assignmentservice.repository.AssignmentRepository;
import cn.edu.fudan.advse.assignmentservice.service.AssignmentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignmentServiceImpl implements AssignmentService {
    @Autowired
    private AssignmentRepository assignmentRepository;

    @Override
    public Assignment getAssignmentById(Integer id) {
        return assignmentRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public Assignment CreateAssignment(AssignmentDTO assignment) {
        Assignment assign = new Assignment();
        BeanUtils.copyProperties(assignment, assign);
        assignmentRepository.save(assign);
        return assign;
    }

    @Override
    public List<Assignment> getAssignmentsByCourseId(Integer courseId) {
        return assignmentRepository.findByCourseId(courseId);
    }
}
