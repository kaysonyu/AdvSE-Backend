package cn.edu.fudan.advse.assignmentservice.controller;

import cn.edu.fudan.advse.assignmentservice.dto.AssignmentDTO;
import cn.edu.fudan.advse.assignmentservice.entity.Assignment;
import cn.edu.fudan.advse.assignmentservice.service.AssignmentService;
import cn.edu.fudan.advse.commonlibrary.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/assignment")
public class AssignmentController {
    @Autowired
    private AssignmentService assignmentService;

    @GetMapping
    public List<Assignment> getAssignmentsByCourseId(@RequestParam("courseId") Integer courseId) {
        return assignmentService.getAssignmentsByCourseId(courseId);
    }

    @PostMapping
    public ApiResponse<Assignment> createAssignment(@RequestBody AssignmentDTO assignment) {
        return ApiResponse.success(assignmentService.CreateAssignment(assignment));
    }

//    @PutMapping
//    public String updateAssignment(@RequestBody AssignmentDTO assignment) {}



//    @GetMapping
//    public String getAssignmentsByCourseId() {
//        return "hello";
//    }

//    @GetMapping("/{id}")
//    public Assignment getAssignmentById(@PathVariable Integer id) {
//        return assignmentService.getAssignmentById(id);
//    }
}


