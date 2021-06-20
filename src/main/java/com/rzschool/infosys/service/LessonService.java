package com.rzschool.infosys.service;

import com.rzschool.infosys.db.entity.Grade;
import com.rzschool.infosys.db.entity.Lesson;
import com.rzschool.infosys.db.repository.GradeRepository;
import com.rzschool.infosys.db.repository.LessonRepository;
import com.rzschool.infosys.db.vo.LessonVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LessonService {

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private GradeRepository gradeRepository;

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


    public Boolean deleteLesson(int id) {
        lessonRepository.deleteById(id);
        return true;
    }
}
