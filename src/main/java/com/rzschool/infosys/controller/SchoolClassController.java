package com.rzschool.infosys.controller;


import com.rzschool.infosys.db.entity.RzUser;
import com.rzschool.infosys.db.entity.SchoolClass;
import com.rzschool.infosys.result.RtnResult;
import com.rzschool.infosys.service.SchoolClassService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/school_class/**")
@Slf4j
public class SchoolClassController {

    @Autowired
    private SchoolClassService schoolClassService;

    @PostMapping("add")
    public RtnResult<Boolean> assClass(@RequestBody SchoolClass schoolClass, RzUser user){
        log.info(user.getUserName());
        return RtnResult.success(schoolClassService.addClass(schoolClass));
    }


}
