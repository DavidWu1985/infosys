package com.rzschool.infosys.service;

import com.rzschool.infosys.db.dto.ClassResourceDto;
import com.rzschool.infosys.db.entity.ClassResource;
import com.rzschool.infosys.db.entity.Grade;
import com.rzschool.infosys.db.entity.Lesson;
import com.rzschool.infosys.db.entity.OssResource;
import com.rzschool.infosys.db.repository.ClassResourceRepository;
import com.rzschool.infosys.db.vo.ClassResourceVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClassResourceService {

    @Autowired
    private ClassResourceRepository classResourceRepository;

    @Autowired
    private FileService fileService;

    public boolean addClassResource(ClassResourceDto resource){
        resource.getFileIds().forEach(r->{
            ClassResource re = new ClassResource();
            BeanUtils.copyProperties(resource, re);
            re.setResourceId(r);
            classResourceRepository.save(re);
        });
        return true;
    }


    public List<ClassResourceVo> getClassResources() {
        List<Object[]> resources = classResourceRepository.getResourcesByGradeAndLesson();
        return resources.stream().map(r->{
            ClassResourceVo vo = new ClassResourceVo();
            vo.setId((Integer) r[0]);
            vo.setGradeId((Integer) r[1]);
            vo.setLessonId((Integer) r[2]);
            vo.setResourceId((Integer) r[3]);
            vo.setGradeName((String) r[4]);
            vo.setLessonName((String) r[5]);
            vo.setOriginalName((String) r[6]);
            return vo;
        }).collect(Collectors.toList());
    }

    public Boolean removeLessonResource(int id) {
        ClassResource resource = classResourceRepository.getOne(id);
        classResourceRepository.deleteById(id);
        fileService.removeFile(resource.getResourceId());
        return true;
    }
}
