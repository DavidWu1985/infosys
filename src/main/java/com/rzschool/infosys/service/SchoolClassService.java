package com.rzschool.infosys.service;

import com.rzschool.infosys.db.entity.Grade;
import com.rzschool.infosys.db.entity.School;
import com.rzschool.infosys.db.entity.SchoolClass;
import com.rzschool.infosys.db.entity.Student;
import com.rzschool.infosys.db.repository.GradeRepository;
import com.rzschool.infosys.db.repository.SchoolClassRepository;
import com.rzschool.infosys.db.repository.SchoolRepository;
import com.rzschool.infosys.db.repository.StudentRepository;
import com.rzschool.infosys.db.vo.SchoolClassVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    public Boolean addClass(SchoolClass schoolClass) {
        schoolClassRepository.save(schoolClass);
        return true;
    }

    public List<SchoolClassVo> getSchoolClass(){
        return null;
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
}
