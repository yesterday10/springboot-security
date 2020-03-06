package com.blog.util;


import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 文件上传工具类
 * @author ngcly
 */
public class UploadUtil {

    public static String uploadFile(MultipartFile file,String fileName,String folder) throws FileNotFoundException {
        File path = new File(ResourceUtils.getURL("Classpath:").getPath());
//        String path = request.getSession().getServletContext().getRealPath("") + "upload" + File.separator + "imgs"
        File upload = new File(path.getAbsolutePath(), folder);
        if(!upload.exists()){
            upload.mkdirs();
        }

       String uploadPath = "D:\\项目\\后台管理\\springboot-blog2\\src\\main\\resources\\static\\upload"+"\\";
        System.out.println("图片上传后的路径"+uploadPath);
        try {
            File file1 = new File(uploadPath + fileName + ".jpg");
            file.transferTo(file1);
            return "/"+folder+fileName+".jpg";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error";
    }
}
