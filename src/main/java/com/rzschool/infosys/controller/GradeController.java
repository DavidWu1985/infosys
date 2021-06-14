package com.rzschool.infosys.controller;

import com.rzschool.infosys.result.RtnResult;
import com.rzschool.infosys.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GradeController {

    @Autowired
    private GradeService gradeService;

    public RtnResult<Boolean> addGrade(){


        return null;
    }


}
