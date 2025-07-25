package com.example.Student.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;

import com.example.Student.model.Student;
import com.example.Student.service.StudentService;

import io.swagger.v3.oas.annotations.tags.Tag;


import com.example.Student.export.ExcelExporter;

import org.springframework.core.io.Resource;



@RestController
@RequestMapping("/students")
@Tag(name = "Student", description = "Quản lý sinh viên")
public class StudentController {
    @Autowired
    private StudentService service;

    @GetMapping
    public List<Student> getAllStudents() {
        return service.getAllStudents();
    }

    @GetMapping("/{id}")
    public Student getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public Student create(@RequestBody Student student) {
        return service.create(student);
    }

    @PutMapping("/{id}")
    public Student update(@PathVariable Long id, @RequestBody Student student) {
        return service.update(id, student);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/export")
    public ResponseEntity<Resource> exportStudentsToExcel() throws IOException {
        List<Student> students = service.getAllStudents();
        ExcelExporter exporter = new ExcelExporter();
        ByteArrayInputStream excelFile = exporter.exportToExcel("Student",students);

        InputStreamResource file = new InputStreamResource(excelFile);

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=students.xlsx")
            .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
            .body(file);
    }
}
    

