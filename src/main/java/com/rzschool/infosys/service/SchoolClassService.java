package com.rzschool.infosys.service;

import com.rzschool.infosys.db.entity.*;
import com.rzschool.infosys.db.repository.*;
import com.rzschool.infosys.db.vo.SchoolClassVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchoolClassService {

    @Autowired
    private SchoolClassRepository schoolClassRepository;

    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ClassTeacherRepository classTeacherRepository;

    @Autowired
    private TeacherClassLessonRepository teacherClassLessonRepository;

    @Autowired
    private LessonRepository lessonRepository;

    @Transactional
    public Boolean addClass(SchoolClass schoolClass) {
        if(schoolClass.getId() != null){
            SchoolClass saved = schoolClassRepository.getOne(schoolClass.getId());
            //如果班级等级不相等，处理班级课程
            if(!saved.getGradeId().equals(schoolClass.getGradeId())){
                //先删除课程
                teacherClassLessonRepository.deleteAllByClassId(schoolClass.getId());
                //给教师增加课程
                List<ClassTeacher> teachers = classTeacherRepository.findAllByClassId(schoolClass.getId());
                List<Lesson> lessons = lessonRepository.findByGradeId(schoolClass.getGradeId());
                List<TeacherClassLesson> tcls = new ArrayList<>();
                teachers.forEach(t-> {
                    lessons.forEach(l-> {
                        TeacherClassLesson tcl = new TeacherClassLesson();
                        tcl.setClassId(schoolClass.getId());
                        tcl.setLessonId(l.getId());
                        tcl.setUserId(t.getUserId());
                        tcls.add(tcl);
                    });
                });
                if(tcls.size() > 0){
                    teacherClassLessonRepository.saveAll(tcls);
                }
            }
        }
        schoolClassRepository.save(schoolClass);
        return true;
    }


    public List<SchoolClassVo> getMySchoolClasses(Integer userId) {
        List<School> schools = schoolRepository.findByMasterId(userId);
        List<Grade> grades = gradeRepository.findAll();
        if(schools.size()> 0){
            List<SchoolClass> classes = schoolClassRepository.findBySchoolIdIn(schools.stream().map(School::getId).collect(Collectors.toList()));
            return classes.stream().map(c->{
                SchoolClassVo vo = new SchoolClassVo();
                BeanUtils.copyProperties(c, vo);
                vo.setGradeName(grades.stream().filter(g->g.getId().equals(c.getGradeId())).findFirst().get().getGradeName());
                vo.setSchoolName(schools.stream().filter(s->s.getId()==c.getSchoolId()).findFirst().get().getSchoolName());
                return vo;
            }).collect(Collectors.toList());
        }else{
            return new ArrayList<>();
        }

    }

    public boolean classAddStudent(Student student) {
        Student saved = studentRepository.getOne(student.getId());
        saved.setClassId(student.getClassId());
        studentRepository.save(saved);
        return true;
    }

    public List<SchoolClass> getClassBySchoolId(int schoolId) {
        return schoolClassRepository.findAllBySchoolIdOrderByIdDesc(schoolId);
    }

    public List<SchoolClass> getMyClassBySchoolId(int schoolId, Integer userId) {
        return schoolClassRepository.getMyClassBySchoolId(schoolId, userId);

    }

    public Boolean removeClassById(int classId) {
        schoolClassRepository.deleteById(classId);
        classTeacherRepository.deleteAllByClassId(classId);
        teacherClassLessonRepository.deleteAllByClassId(classId);
        return true;


    }
}
