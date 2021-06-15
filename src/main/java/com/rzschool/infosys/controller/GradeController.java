package com.rzschool.infosys.controller;

import com.rzschool.infosys.db.entity.Grade;
import com.rzschool.infosys.result.RtnResult;
import com.rzschool.infosys.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grade/**")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @PostMapping("add")
    public RtnResult<Boolean> addGrade(@RequestBody Grade grade){
        return RtnResult.success(gradeService.addGrade(grade));
    }

    @GetMapping("list")
    public RtnResult<List<Grade>> listClassGrade(){
        return RtnResult.success(gradeService.listClassGrade());
    }

}
