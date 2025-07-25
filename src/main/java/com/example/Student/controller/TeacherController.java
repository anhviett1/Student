package com.example.Student.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.Student.export.ExcelExporter;
import com.example.Student.model.Teacher;
import com.example.Student.service.TeacherService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/teachers")
@Tag(name = "Teacher", description = "Quản lý giảng viên")
public class TeacherController {
    @Autowired
    private TeacherService service;

    @GetMapping
    public List<Teacher> getAllTeachers() {
        return service.getAllTeachers();
    }

    @GetMapping("/{id}")
    public Teacher getById(@PathVariable Long id) {
        return service.getById(id);
    }
    
    @PostMapping
    public Teacher create(@RequestBody Teacher teacher){
        return service.create(teacher);
    }

    @PutMapping("/{id}")
    public Teacher update(@PathVariable Long id, @RequestBody Teacher teacher) {
        return service.update(id, teacher);
    }
       
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/export")
    public ResponseEntity<Resource> exportTeachersToExcel() throws IOException {
        List<Teacher> teachers = service.getAllTeachers();
        ExcelExporter exporter = new ExcelExporter();
        ByteArrayInputStream excelFile = exporter.exportToExcel("Teacher",teachers);

        InputStreamResource file = new InputStreamResource(excelFile);

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=teachers.xlsx")
            .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
            .body(file);
    }
}
