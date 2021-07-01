package com.rzschool.infosys.service;

import com.rzschool.infosys.db.dto.SchoolMaster;
import com.rzschool.infosys.db.entity.School;
import com.rzschool.infosys.db.entity.SchoolTeacher;
import com.rzschool.infosys.db.repository.SchoolRepository;
import com.rzschool.infosys.db.repository.SchoolTeacherRepository;
import com.rzschool.infosys.db.repository.UserRepository;
import com.rzschool.infosys.db.vo.SchoolVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchoolService {

    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SchoolTeacherRepository schoolTeacherRepository;

    public List<SchoolVo> getSchoolList(int userId){
       List<School> schools = schoolRepository.findAllByUserId(userId);
       return schools.stream().map(s->{
           SchoolVo vo = new SchoolVo();
           BeanUtils.copyProperties(s, vo);
           if(s.getMasterId() != 0){
               vo.setMasterName(userRepository.getOne(s.getMasterId()).getUserName());
           }
           return vo;
       }).collect(Collectors.toList());
    }


    public boolean addSchool(School school) {
        schoolRepository.save(school);
        return true;
    }

    @Transactional
    public boolean addMaster(SchoolMaster schoolMaster) {
        School school = schoolRepository.getOne(schoolMaster.getId());
        schoolTeacherRepository.deleteByUserIdAndSchoolId(school.getMasterId(), school.getId());
        school.setMasterId(schoolMaster.getUserId());
        schoolRepository.save(school);
        SchoolTeacher teacher = new SchoolTeacher();
        teacher.setUserId(schoolMaster.getUserId());
        teacher.setSchoolId(school.getId());
        schoolTeacherRepository.save(teacher);
        return true;
    }

    public List<School> getMySchoolList(Integer userId) {
        List<School> schools = schoolRepository.findAllByUserId(userId);
        return schools;
    }
}
