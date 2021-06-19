package com.rzschool.infosys.controller;

import com.rzschool.infosys.db.entity.ClassResource;
import com.rzschool.infosys.result.RtnResult;
import com.rzschool.infosys.service.ClassResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resource/**")
public class ClassResourceController {


    @Autowired
    private ClassResourceService classResourceService;

    @PostMapping("/class_file")
    public RtnResult<Boolean> addClassResource(@RequestBody ClassResource resource) {
        return RtnResult.success(classResourceService.addClassResource(resource));
    }

    @GetMapping("/class_file_list")
    public RtnResult<List<ClassResource>> getClassResources() {
        return RtnResult.success(classResourceService.getClassResources());
    }


}
