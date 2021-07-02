package com.rzschool.infosys.service;

import com.rzschool.infosys.db.entity.*;
import com.rzschool.infosys.db.repository.*;
import com.rzschool.infosys.db.vo.LessonTeacher;
import com.rzschool.infosys.db.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherService {

    @Autowired
    private SchoolTeacherRepository schoolTeacherRepository;

    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClassTeacherRepository classTeacherRepository;

    @Autowired
    private TeacherClassLessonRepository teacherClassLessonRepository;

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private SchoolClassRepository schoolClassRepository;

    public List<UserVo> getMyTeachers(Integer userId) {
        List<SchoolTeacher> schools = schoolTeacherRepository.findByUserId(userId);
        if(schools.size() == 0){
            return new ArrayList<>();
        }else{
            List<SchoolTeacher>  teachers = schoolTeacherRepository.findBySchoolIdIn(schools.stream().map(SchoolTeacher::getSchoolId).collect(Collectors.toList()));
            List<RzUser> users =  userRepository.findAllByIdIn(teachers.stream().map(SchoolTeacher::getUserId).collect(Collectors.toList()));
            List<School> ss = schoolRepository.findAllByIdIn(teachers.stream().map(SchoolTeacher::getSchoolId).collect(Collectors.toList()));
            return users.stream().map(u->{
                UserVo vo = new UserVo();
                BeanUtils.copyProperties(u, vo);
                vo.setUserPwd("");
                vo.setSchoolId(teachers.stream().filter(t->{return u.getId().equals(t.getUserId());}).findFirst().get().getSchoolId());
                vo.setSchoolName(ss.stream().filter(t->{return vo.getSchoolId().equals(t.getId());}).findFirst().get().getSchoolName());
                return vo;
            }).collect(Collectors.toList());
        }
    }

    public List<RzUser> getClassTeachers(int classId) {
        return userRepository.getClassTeachers(classId);
    }

    public Boolean addClassTeacher(ClassTeacher teacher) {
        ClassTeacher saved = classTeacherRepository.findByUserIdAndClassId(teacher.getUserId(), teacher.getClassId());
        if(saved != null){
            return true;
        }
        classTeacherRepository.save(teacher);
        SchoolClass klz = schoolClassRepository.getOne(teacher.getClassId());
        List<Lesson> lessons = lessonRepository.findByGradeId(klz.getGradeId());
        List<TeacherClassLesson> tcls = lessons.stream().map(l->{
            TeacherClassLesson tcl = new TeacherClassLesson();
            tcl.setClassId(teacher.getClassId());
            tcl.setLessonId(l.getId());
            tcl.setUserId(teacher.getUserId());
            return tcl;
        }).collect(Collectors.toList());
        teacherClassLessonRepository.saveAll(tcls);
        return true;
    }

    /**
     * 删除教师，删除相应的课程
     * @param id
     * @return
     */
    @Transactional
    public Boolean removeClassTeacher(int id) {
        ClassTeacher klzTeacher = classTeacherRepository.getOne(id);
        classTeacherRepository.deleteById(id);
        teacherClassLessonRepository.deleteTeacherLessons(klzTeacher.getClassId(), klzTeacher.getUserId());
        return true;
    }

    public List<LessonTeacher> getLessonTeacherByLessonId(int lessonId) {
        List<Object[]> objects = userRepository.getLessonTeacherByLessonId(lessonId);
        return objects.stream().map(o->{
            LessonTeacher teacher = new LessonTeacher();
            teacher.setUserName((String) o[0]);
            teacher.setTeacherLessonId((Integer) o[1]);
            return teacher;
        }).collect(Collectors.toList());
    }

    public List<RzUser> getSchoolTeacherBySchoolId(int schoolId) {
        return userRepository.getSchoolTeacherBySchoolId(schoolId);
    }
}
