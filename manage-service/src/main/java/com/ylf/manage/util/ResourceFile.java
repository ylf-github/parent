package com.ylf.manage.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public class ResourceFile {
    public static String save(MultipartFile file){
        try{
            String fileName=file.getOriginalFilename();
            int index=fileName.lastIndexOf(".");
            String changeFileName=UUID.getCode()+fileName.substring(index);
            String path="E:/idea-workspace/parent/manage-web/src/main/resources/resource/"+changeFileName;
            file.transferTo(new File(path));
            return path;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
}
