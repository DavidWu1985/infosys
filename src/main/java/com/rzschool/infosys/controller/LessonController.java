package com.rzschool.infosys.controller;

import com.rzschool.infosys.db.entity.Lesson;
import com.rzschool.infosys.db.vo.LessonVo;
import com.rzschool.infosys.result.RtnResult;
import com.rzschool.infosys.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lesson/**")
public class LessonController {

    @Autowired
    private LessonService lessonService;

    @PostMapping("/add")
    public RtnResult<Boolean> addLesson(@RequestBody Lesson lesson){
        return RtnResult.success(lessonService.addLesson(lesson));
    }


    @GetMapping("/list")
    public RtnResult<List<LessonVo>> getLessons(){
        return RtnResult.success(lessonService.getLessons());
    }

    @DeleteMapping("/remove/{id}")
    public RtnResult<Boolean> deleteLesson(@PathVariable("id") int id){
        return RtnResult.success(lessonService.deleteLesson(id));
    }


}
