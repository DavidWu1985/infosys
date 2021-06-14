package com.rzschool.infosys.controller;

import com.rzschool.infosys.db.dto.SchoolMaster;
import com.rzschool.infosys.db.entity.School;
import com.rzschool.infosys.db.vo.SchoolVo;
import com.rzschool.infosys.result.RtnResult;
import com.rzschool.infosys.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/school/**")
public class SchoolController {


    @Autowired
    private SchoolService schoolService;

    @GetMapping("list")
    public RtnResult<List<SchoolVo>> getSchoolList(){
        return RtnResult.success(schoolService.getSchoolList());
    }

    @PostMapping("add")
    public RtnResult<Boolean> addSchool(@RequestBody School school){
        return RtnResult.success(schoolService.addSchool(school));
    }

    @PostMapping("addMaster")
    public RtnResult<Boolean> addMaster(@RequestBody SchoolMaster schoolMaster){
        return RtnResult.success(schoolService.addMaster(schoolMaster));
    }

}
