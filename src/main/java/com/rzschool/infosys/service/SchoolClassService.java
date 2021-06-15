package com.rzschool.infosys.service;

import com.rzschool.infosys.db.entity.SchoolClass;
import com.rzschool.infosys.db.repository.SchoolClassRepository;
import com.rzschool.infosys.db.vo.SchoolClassVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolClassService {

    @Autowired
    private SchoolClassRepository schoolClassRepository;


    public Boolean addClass(SchoolClass schoolClass) {
        schoolClassRepository.save(schoolClass);
        return true;
    }

    public List<SchoolClassVo> getSchoolClass(){
        return null;
    }

}
