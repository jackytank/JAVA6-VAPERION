package com.edu.java6assm.service.impl;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import com.edu.java6assm.service.UploadService;

@Service
public class UploadServiceImpl implements UploadService {
    @Autowired
    ServletContext context;

    @Autowired
    HttpServletRequest req;

    public File save(MultipartFile file, String folder) {
        try {
            String shit = context.getResourceAsStream("upload/" + folder).toString();
            System.out.println("""



                """
                        +shit+
                        """


                    """);
            File dir = new File("");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            String filename = file.getOriginalFilename() + "-file-" + System.currentTimeMillis();
            // String name = Integer.toHexString(s.hashCode()) +
            // s.substring(s.lastIndexOf("."));
            File savedFile = new File(dir, filename);
            file.transferTo(savedFile);
            System.out.println(savedFile.getAbsolutePath());
            return savedFile;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
