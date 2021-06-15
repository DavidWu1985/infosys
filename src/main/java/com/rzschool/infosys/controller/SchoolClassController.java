package com.rzschool.infosys.controller;


import com.rzschool.infosys.db.entity.SchoolClass;
import com.rzschool.infosys.result.RtnResult;
import com.rzschool.infosys.service.SchoolClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/school_class/**")
public class SchoolClassController {

    @Autowired
    private SchoolClassService schoolClassService;

    @PostMapping("add")
    public RtnResult<Boolean> assClass(@RequestBody SchoolClass schoolClass){
        return RtnResult.success(schoolClassService.addClass(schoolClass));
    }


}
