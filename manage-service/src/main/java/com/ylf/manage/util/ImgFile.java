package com.ylf.manage.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

public class ImgFile {
    public  static String save(MultipartFile img){
        try{
            String fileName=img.getOriginalFilename();
            int index=fileName.lastIndexOf(".");
            String changeFileName=UUID.getCode()+fileName.substring(index);
            String path="E:/idea-workspace/parent/manage-web/src/main/webapp/img/"+changeFileName;
            img.transferTo(new File(path));
            return "http://localhost:8080/img/"+changeFileName;
        }catch (Exception e){
            e.printStackTrace();
            return "http://localhost:8080/img/base.png";
        }
    }
}
