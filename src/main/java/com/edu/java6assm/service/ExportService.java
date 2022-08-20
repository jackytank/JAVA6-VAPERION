package com.edu.java6assm.service;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

public interface ExportService {
    void exportExcel(Object entity, String fileAndSheetName, HttpServletResponse response) throws IOException;

    void exportPDF(Object entity, String fileAndTitleName, HttpServletResponse response) throws IOException;

    void exportCSV(Object entity, String fileAndTitleName, HttpServletResponse response) throws IOException;
}
