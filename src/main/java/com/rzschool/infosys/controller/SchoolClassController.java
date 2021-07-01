package com.rzschool.infosys.controller;


import com.rzschool.infosys.db.entity.RzUser;
import com.rzschool.infosys.db.entity.SchoolClass;
import com.rzschool.infosys.db.entity.Student;
import com.rzschool.infosys.db.vo.SchoolClassVo;
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


    @GetMapping("allMyClasses")
    public RtnResult<List<SchoolClassVo>> getMySchoolClasses(RzUser user){
        return RtnResult.success(schoolClassService.getMySchoolClasses(user.getId()));
    }


    @PostMapping("add_student")
    public RtnResult<Boolean> classAddStudent(@RequestBody Student student){
        return RtnResult.success(schoolClassService.classAddStudent(student));
    }

    /**
     * 获取学校的班级
     * @param schoolId
     * @return
     */
    @GetMapping("allClass/{schoolId}")
    public RtnResult<List<SchoolClass>> getClassBySchoolId(@PathVariable("schoolId") int schoolId){
        return RtnResult.success(schoolClassService.getClassBySchoolId(schoolId));
    }

    /**
     * 获取的我课程
     * @param schoolId
     * @param user
     * @return
     */
    @GetMapping("myClass/{schoolId}")
    public RtnResult<List<SchoolClass>> getMyClass(@PathVariable("schoolId") int schoolId, RzUser user){
        return RtnResult.success(schoolClassService.getMyClassBySchoolId(schoolId, user.getId()));
    }


}
