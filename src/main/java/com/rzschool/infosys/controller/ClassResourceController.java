package com.rzschool.infosys.controller;

import com.rzschool.infosys.db.dto.ClassResourceDto;
import com.rzschool.infosys.db.entity.ClassResource;
import com.rzschool.infosys.db.vo.ClassResourceVo;
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
    public RtnResult<Boolean> addClassResource(@RequestBody ClassResourceDto resource) {
        return RtnResult.success(classResourceService.addClassResource(resource));
    }

    @GetMapping("/class_file_list")
    public RtnResult<List<ClassResourceVo>> getClassResources() {
        return RtnResult.success(classResourceService.getClassResources());
    }

    @DeleteMapping("remove/{id}")
    public RtnResult<Boolean> removeLessonResource(@PathVariable("id") int id){
        return RtnResult.success(classResourceService.removeLessonResource(id));
    }

}
