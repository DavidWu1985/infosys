package com.rzschool.infosys.controller;

import com.rzschool.infosys.db.entity.RzUser;
import com.rzschool.infosys.result.RtnResult;
import com.rzschool.infosys.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/teacher/**")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping("myList")
    public RtnResult<List<RzUser>> getMyTeachers(RzUser rzUser) {
        return RtnResult.success(teacherService.getMyTeachers(rzUser.getId()));
    }

}
