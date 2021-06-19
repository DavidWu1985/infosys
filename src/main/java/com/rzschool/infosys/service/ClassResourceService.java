package com.rzschool.infosys.service;

import com.rzschool.infosys.db.entity.ClassResource;
import com.rzschool.infosys.db.repository.ClassResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassResourceService {

    @Autowired
    private ClassResourceRepository classResourceRepository;

    public boolean addClassResource(ClassResource resource){
        classResourceRepository.save(resource);
        return true;
    }


    public List<ClassResource> getClassResources() {
        return classResourceRepository.findAll();

    }
}
