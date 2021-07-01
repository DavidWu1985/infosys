package com.rzschool.infosys.controller;

import com.rzschool.infosys.db.entity.Lesson;
import com.rzschool.infosys.db.entity.RzUser;
import com.rzschool.infosys.db.entity.TeacherClassLesson;
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

    /**
     * 管理端课程列表
     * @return
     */
    @GetMapping("/list")
    public RtnResult<List<LessonVo>> getLessons(){
        return RtnResult.success(lessonService.getLessons());
    }

    @DeleteMapping("/remove/{id}")
    public RtnResult<Boolean> deleteLesson(@PathVariable("id") int id){
        return RtnResult.success(lessonService.deleteLesson(id));
    }

    @GetMapping("/listByGradeId/{gradeId}")
    public RtnResult<List<Lesson>> getLessonsByGradeId(@PathVariable("gradeId") int gradeId){
        return RtnResult.success(lessonService.getLessonsByGradeId(gradeId));
    }


    /**
     * 教师根据classId 获取相应课程
     * @param user
     * @param classId
     * @return
     */
    @GetMapping("/myLessons/{classId}")
    public RtnResult<List<LessonVo>> getMyLessonsByClassId(RzUser user, @PathVariable("classId") int classId){
        return RtnResult.success(lessonService.getMyLessonsByClassId(user.getId(), classId));
    }

    /**
     * 给老师分配课程
     */
    @PostMapping("allocate")
    public RtnResult<Boolean> allocateLesson(@RequestBody TeacherClassLesson tcl){
        return RtnResult.success(lessonService.allocateLesson(tcl));
    }


    @DeleteMapping("teacher/{id}")
    public RtnResult<Boolean> deleteLessonTeacher(@PathVariable("id") int id){
        return RtnResult.success(lessonService.deleteLessonTeacher(id));
    }

}
