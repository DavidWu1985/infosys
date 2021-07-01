package com.rzschool.infosys.controller;


import com.rzschool.infosys.db.entity.Student;
import com.rzschool.infosys.db.vo.StudentVo;
import com.rzschool.infosys.result.RtnResult;
import com.rzschool.infosys.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/student/**")
public class StudentController {


    @Autowired
    private StudentService studentService;


    @PostMapping("add")
    public RtnResult<Boolean> addMyStudent(@RequestBody @Valid Student student){
        return RtnResult.success(studentService.addMyStudent(student));
    }

    @GetMapping("list")
    public RtnResult<List<StudentVo>> getAllMyStudents(){
        return RtnResult.success(studentService.getAllMyStudents());
    }

    @GetMapping("list/{classId}")
    public RtnResult<List<Student>> getMyClassStudents(@PathVariable("classId") int classId){
        return RtnResult.success(studentService.getMyClassStudents(classId));
    }
}
