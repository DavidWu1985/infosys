package com.rzschool.infosys.controller;

import com.rzschool.infosys.db.entity.RzUser;
import com.rzschool.infosys.db.vo.UserVo;
import com.rzschool.infosys.result.RtnResult;
import com.rzschool.infosys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/**")
public class UserController {


    @Autowired
    private UserService userService;

    @PostMapping("schoolMaster")
    public RtnResult<Boolean> addSchoolMaster(@RequestBody RzUser rzUser){
        rzUser.setUserPwd("123456");
        return RtnResult.success(userService.save(rzUser));
    }

    @GetMapping("schoolMaster")
    public RtnResult<List<UserVo>> getSchoolMaster(){
        return RtnResult.success(userService.getSchoolMaster());
    }


}
