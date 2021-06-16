package com.rzschool.infosys.service;

import com.rzschool.infosys.db.dto.SchoolMaster;
import com.rzschool.infosys.db.entity.School;
import com.rzschool.infosys.db.repository.SchoolRepository;
import com.rzschool.infosys.db.repository.UserRepository;
import com.rzschool.infosys.db.vo.SchoolVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchoolService {

    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    private UserRepository userRepository;

    public List<SchoolVo> getSchoolList(){
       List<School> schools = schoolRepository.findAll();
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

    public boolean addMaster(SchoolMaster schoolMaster) {
        School school = schoolRepository.getOne(schoolMaster.getId());
        school.setMasterId(schoolMaster.getUserId());
        schoolRepository.save(school);
        return true;
    }

    public List<School> getMySchoolList(Integer id) {
        List<School> schools = schoolRepository.findByMasterId(id);
        return schools;
    }
}
