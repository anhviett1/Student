package com.example.Student.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Student.model.Student;
import com.example.Student.repository.StudentRepository;


@Service

public class StudentService {
    @Autowired
    private StudentRepository repository;

    public List<Student> getAllStudents(){
        return repository.findAll();
    }

    public Student getById(Long id){
        return repository.findById(id).orElseThrow(()-> new RuntimeException("Not found"));
    }

    public Student create(Student student){
        return repository.save(student);
    }

    public Student update(Long id, Student newStudent){
        Student s = getById(id);
        s.setName(newStudent.getName());
        s.setEmail(newStudent.getEmail());
        s.setPhoneNumber(newStudent.getPhoneNumber());
        s.setMajor(newStudent.getMajor());
        s.setYear(newStudent.getYear());
        return repository.save(s);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}


