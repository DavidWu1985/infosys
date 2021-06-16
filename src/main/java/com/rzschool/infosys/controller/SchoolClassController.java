package com.rzschool.infosys.controller;


import com.rzschool.infosys.db.entity.RzUser;
import com.rzschool.infosys.db.entity.SchoolClass;
import com.rzschool.infosys.result.RtnResult;
import com.rzschool.infosys.service.SchoolClassService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/school_class/**")
@Slf4j
public class SchoolClassController {

    @Autowired
    private SchoolClassService schoolClassService;

    @PostMapping("add")
    public RtnResult<Boolean> assClass(@RequestBody SchoolClass schoolClass) {
        return RtnResult.success(schoolClassService.addClass(schoolClass));
    }


    @GetMapping("mySchoolClasses")
    public RtnResult<List<SchoolClass>> getMySchoolClasses(RzUser user){
        user = new RzUser();
        user.setId(2);
        return RtnResult.success(schoolClassService.getMySchoolClasses(user.getId()));
    }

}
