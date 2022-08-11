package com.edu.java6assm.controller.rest;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.java6assm.entity.Order;
import com.edu.java6assm.entity.Product;
import com.edu.java6assm.entity.User;
import com.edu.java6assm.service.ExportService;
import com.edu.java6assm.service.ProductService;

@CrossOrigin("*")
@RestController
public class ExportRestController {

    @Autowired
    ProductService productService;

    @Autowired
    ExportService exportService;

    // export excel

    @GetMapping("/admin/products/export-excel")
    public ResponseEntity<String> exportProductExcel(HttpServletResponse response) {
        try {
            exportService.exportExcel(new Product(), "products", response);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/admin/users/export-excel")
    public ResponseEntity<String> exportUsersExcel(HttpServletResponse response) {
        try {
            exportService.exportExcel(new User(), "users", response);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/admin/orders/export-excel")
    public ResponseEntity<String> exportOrdersExcel(HttpServletResponse response) {
        try {
            exportService.exportExcel(new Order(), "orders", response);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // export pdf
    @GetMapping("/admin/products/export-pdf")
    public ResponseEntity<String> exportProductPdf(HttpServletResponse response) {
        try {
            exportService.exportPDF(new Product(), "products", response);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/admin/users/export-pdf")
    public ResponseEntity<String> exportUsersPdf(HttpServletResponse response) {
        try {
            exportService.exportPDF(new User(), "users", response);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/admin/orders/export-pdf")
    public ResponseEntity<String> exportOrdersPdf(HttpServletResponse response) {
        try {
            exportService.exportPDF(new Order(), "orders", response);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
