package com.example.Student.model;

import java.util.Arrays;
import java.util.List;

import com.example.Student.export.ExcelExportable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Teacher")

public class Teacher implements ExcelExportable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "department")
    private String department;

    @Override
    public List<String> getHeaders() {
        return Arrays.asList("ID", "Họ Tên", "Email", "SĐT", "Khoa");
    }

    @Override
    public List<String> getRowData() {
        return Arrays.asList(
            String.valueOf(id),
            name,
            email,
            phoneNumber != null ? phoneNumber : "",
            department
        );
    }

}
