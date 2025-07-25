package com.example.Student.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Student.model.Teacher;
import com.example.Student.repository.TeacherRepository;

@Service

public class TeacherService {
    @Autowired
    private TeacherRepository repository;

    public List<Teacher> getAllTeachers(){
        return repository.findAll();
    }

    public Teacher getById(Long id){
        return repository.findById(id).orElseThrow(()-> new RuntimeException("Not found"));
    }

    public Teacher create(Teacher teacher){
        return repository.save(teacher);
    }

    public Teacher update(Long id, Teacher newTeacher){
        Teacher t = getById(id);
        t.setName(newTeacher.getName());
        t.setEmail(newTeacher.getEmail());
        t.setPhoneNumber(newTeacher.getPhoneNumber());
        t.setDepartment(newTeacher.getDepartment());
        return repository.save(t);
    }

    public void delete(Long id){
    repository.deleteById(id);
    }

}
