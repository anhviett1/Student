package com.example.Student.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Student.model.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    
}
