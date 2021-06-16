package com.rzschool.infosys.service;

import com.rzschool.infosys.db.entity.School;
import com.rzschool.infosys.db.entity.SchoolClass;
import com.rzschool.infosys.db.repository.GradeRepository;
import com.rzschool.infosys.db.repository.SchoolClassRepository;
import com.rzschool.infosys.db.repository.SchoolRepository;
import com.rzschool.infosys.db.vo.SchoolClassVo;
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


    public Boolean addClass(SchoolClass schoolClass) {
        schoolClassRepository.save(schoolClass);
        return true;
    }

    public List<SchoolClassVo> getSchoolClass(){
        return null;
    }

    public List<SchoolClass> getMySchoolClasses(Integer userId) {
        List<School> schools = schoolRepository.findByMasterId(userId);
        if(schools.size()> 0){
            List<SchoolClass> classes = schoolClassRepository.findBySchoolIdIn(schools.stream().map(School::getId).collect(Collectors.toList()));
            return classes;
        }else{
            return new ArrayList<>();
        }

    }
}
