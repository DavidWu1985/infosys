package com.rzschool.infosys.service;

import com.rzschool.infosys.db.entity.Student;
import com.rzschool.infosys.db.repository.StudentRepository;
import com.rzschool.infosys.db.vo.StudentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Boolean addMyStudent(Student student) {
        studentRepository.save(student);
        return true;
    }

    public List<StudentVo> getAllMyStudents() {
        return null;
    }

    public List<Student> getMyClassStudents(int classId) {
        return studentRepository.findAllByClassId(classId);
    }

    @Transactional
    public Boolean removeStudent(int studentId) {
        studentRepository.deleteById(studentId);
        return true;
    }
}
