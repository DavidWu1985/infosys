package com.rzschool.infosys.service;

import com.rzschool.infosys.db.entity.RzUser;
import com.rzschool.infosys.db.entity.School;
import com.rzschool.infosys.db.entity.SchoolTeacher;
import com.rzschool.infosys.db.repository.SchoolRepository;
import com.rzschool.infosys.db.repository.SchoolTeacherRepository;
import com.rzschool.infosys.db.repository.UserRepository;
import com.rzschool.infosys.db.vo.UserVo;
import org.springframework.beans.BeanUtils;
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
                vo.setSchoolId(teachers.stream().filter(t->{return u.getId().equals(t.getUserId());}).findFirst().get().getSchoolId());
                vo.setSchoolName(ss.stream().filter(t->{return vo.getSchoolId().equals(t.getId());}).findFirst().get().getSchoolName());
                return vo;
            }).collect(Collectors.toList());
        }
    }
}
