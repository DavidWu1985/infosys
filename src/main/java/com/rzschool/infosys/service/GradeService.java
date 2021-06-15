package com.rzschool.infosys.service;

import com.rzschool.infosys.db.entity.Grade;
import com.rzschool.infosys.db.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeService {

    @Autowired
    private GradeRepository classGradeRepository;


    public boolean addGrade(Grade grade) {
        classGradeRepository.save(grade);
        return true;
    }

    public List<Grade> listClassGrade() {
        return classGradeRepository.findAll();
    }
}
