package com.rzschool.infosys.service;

import com.rzschool.infosys.db.entity.Grade;
import com.rzschool.infosys.db.entity.Lesson;
import com.rzschool.infosys.db.entity.TeacherClassLesson;
import com.rzschool.infosys.db.repository.ClassResourceRepository;
import com.rzschool.infosys.db.repository.GradeRepository;
import com.rzschool.infosys.db.repository.LessonRepository;
import com.rzschool.infosys.db.repository.TeacherClassLessonRepository;
import com.rzschool.infosys.db.vo.LessonVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LessonService {

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private TeacherClassLessonRepository teacherClassLessonRepository;

    @Autowired
    private ClassResourceRepository classResourceRepository;

    public boolean addLesson(Lesson lesson){
        lessonRepository.save(lesson);
        return true;
    }

    public List<LessonVo> getLessons(){
        List<Lesson> lessons = lessonRepository.findAll();
        List<Grade> grades = gradeRepository.findAll();
        return lessons.stream().map(l->{
            LessonVo vo = new LessonVo();
            BeanUtils.copyProperties(l, vo);
            vo.setGradeName(grades.stream().filter(g->g.getId().equals(l.getGradeId())).findFirst().get().getGradeName());
            return vo;
        }).collect(Collectors.toList());
    }


    /**
     * 删除课程，删除相应的教师和资源关联关系
     * @param id
     * @return
     */
    @Transactional
    public Boolean deleteLesson(int id) {
        lessonRepository.deleteById(id);
        classResourceRepository.deleteAllByLessonId(id);
        teacherClassLessonRepository.deleteAllByLessonId(id);
        return true;
    }

    public List<Lesson> getLessonsByGradeId(int gradeId) {
        return lessonRepository.findByGradeId(gradeId);
    }

    public List<LessonVo> getMyLessonsByClassId(int userId, int classId) {
        List<Lesson> lessons = lessonRepository.findMyLessonsByClassId(userId, classId);
        List<TeacherClassLesson> tcls = teacherClassLessonRepository.findAllByUserIdAndClassId(userId, classId);
        return lessons.stream().map(l->{
            LessonVo vo = new LessonVo();
            BeanUtils.copyProperties(l, vo);
            TeacherClassLesson tcl = tcls.stream().filter(t->{ return t.getLessonId() == l.getId(); }).findFirst().orElse(new TeacherClassLesson());
            vo.setStartTime(tcl.getStartTime());
            vo.setEndTime(tcl.getEndTime());
            return vo;
        }).collect(Collectors.toList());
    }

    /**
     * 给课程分配教师
     * @param tcl
     * @return
     */
    public boolean allocateLesson(TeacherClassLesson tcl) {
        teacherClassLessonRepository.save(tcl);
        return true;
    }

    /**
     * 删除课程对应的教师
     * @param id
     * @return
     */
    @Transactional
    public boolean deleteLessonTeacher(int id) {
        teacherClassLessonRepository.deleteById(id);
        return true;
    }
}
