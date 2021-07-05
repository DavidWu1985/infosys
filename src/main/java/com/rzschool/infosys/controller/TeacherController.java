package com.rzschool.infosys.controller;

import com.rzschool.infosys.db.entity.ClassTeacher;
import com.rzschool.infosys.db.entity.RzUser;
import com.rzschool.infosys.db.vo.KlzTeacher;
import com.rzschool.infosys.db.vo.LessonTeacher;
import com.rzschool.infosys.db.vo.UserVo;
import com.rzschool.infosys.result.RtnResult;
import com.rzschool.infosys.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher/**")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping("myList")
    public RtnResult<List<UserVo>> getMyTeachers(RzUser rzUser) {
        return RtnResult.success(teacherService.getMyTeachers(rzUser.getId()));
    }

    @GetMapping("class_teachers/{classId}")
    public RtnResult<List<KlzTeacher>> getClassTeachers(@PathVariable("classId") int classId) {
        return RtnResult.success(teacherService.getClassTeachers(classId));
    }

    /**
     * 班级分配教师
     * @param teacher
     * @return
     */
    @PostMapping("class_teacher")
    public RtnResult<Boolean> addClassTeacher(@RequestBody ClassTeacher teacher){
        return RtnResult.success(teacherService.addClassTeacher(teacher));

    }

    @DeleteMapping("class_teacher/{id}")
    public RtnResult<Boolean> removeClassTeacher(@PathVariable("id") int id){
        return RtnResult.success(teacherService.removeClassTeacher(id));
    }

    @GetMapping("/lesson_teachers/{lessonId}")
    public RtnResult<List<LessonTeacher>> getLessonTeacherByLessonId(@PathVariable("lessonId") int lessonId){
        return RtnResult.success(teacherService.getLessonTeacherByLessonId(lessonId));
    }

    @GetMapping("/school_teachers/{schoolId}")
    public RtnResult<List<RzUser>> getSchoolTeacherBySchoolId(@PathVariable("schoolId") int schoolId){
        return RtnResult.success(teacherService.getSchoolTeacherBySchoolId(schoolId));
    }
}
