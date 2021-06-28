package com.rzschool.infosys.service;

import com.rzschool.infosys.db.entity.RzUser;
import com.rzschool.infosys.db.entity.SchoolTeacher;
import com.rzschool.infosys.db.repository.SchoolRepository;
import com.rzschool.infosys.db.repository.SchoolTeacherRepository;
import com.rzschool.infosys.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<RzUser> getMyTeachers(Integer userId) {
        List<SchoolTeacher> schools = schoolTeacherRepository.findByUserId(userId);
        if(schools.size() == 0){
            return new ArrayList<>();
        }else{
            List<SchoolTeacher>  teachers = schoolTeacherRepository.findBySchoolIdIn(schools.stream().map(SchoolTeacher::getSchoolId).collect(Collectors.toList()));
            return userRepository.findAllByIdIn(teachers.stream().map(SchoolTeacher::getUserId).collect(Collectors.toList()));
        }
    }
}
