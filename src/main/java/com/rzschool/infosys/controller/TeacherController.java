package com.rzschool.infosys.controller;

import com.rzschool.infosys.db.entity.ClassTeacher;
import com.rzschool.infosys.db.entity.RzUser;
import com.rzschool.infosys.db.vo.UserVo;
import com.rzschool.infosys.result.RtnResult;
import com.rzschool.infosys.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher/**")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping("myList")
    public RtnResult<List<UserVo>> getMyTeachers(RzUser rzUser) {
        return RtnResult.success(teacherService.getMyTeachers(rzUser.getId()));
    }

    @GetMapping("class_teachers/{classId}")
    public RtnResult<List<RzUser>> getClassTeachers(@PathVariable("classId") int classId) {
        return RtnResult.success(teacherService.getClassTeachers(classId));
    }

    @PostMapping("class_teacher")
    public RtnResult<Boolean> addClassTeacher(@RequestBody ClassTeacher teacher){
        return RtnResult.success(teacherService.addClassTeacher(teacher));

    }

    @DeleteMapping("class_teacher/{id}")
    public RtnResult<Boolean> removeClassTeacher(@PathVariable("id") int id){
        return RtnResult.success(teacherService.removeClassTeacher(id));
    }

}
