package com.edu.java6assm.service.impl;


import java.io.File;
import java.util.Date;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.edu.java6assm.service.UploadService;

@Service
public class UploadServiceImpl implements UploadService {
    @Autowired
    ServletContext app;

    public File save(MultipartFile file, String folder) {
        File dir = new File(app.getRealPath("assets/" + folder));
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String s = System.currentTimeMillis() + "-file-" + file.getOriginalFilename();
        String name = Integer.toHexString(s.hashCode()) + s.substring(s.lastIndexOf("."));
        try {
            File savedFile = new File(dir, s);
            file.transferTo(savedFile);
            System.out.println(savedFile.getAbsolutePath());
            return savedFile;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
