package com.example.Student.export;

import java.util.List;

public interface  ExcelExportable {
    List<String> getHeaders();           
    List<String> getRowData();
}
