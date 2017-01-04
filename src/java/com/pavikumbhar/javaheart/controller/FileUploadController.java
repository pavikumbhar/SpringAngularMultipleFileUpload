/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pavikumbhar.javaheart.controller;

import com.pavikumbhar.javaheart.bean.BaseFile;
import com.pavikumbhar.javaheart.bean.FromBean;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author pravinkumbhar
 */

@RestController
@RequestMapping("/api/")
public class FileUploadController {
    
     @RequestMapping(value = "/fileBase", method = POST)
    public void base(@RequestBody List<FromBean> fromBean) throws FileNotFoundException, IOException {
        
       // System.out.println("com.clover.smt.controller.FileController.submit()" + fromBean.toString());
        
        for (FromBean bean : fromBean) {
          
            System.out.println("fname : "+bean.getFname());
            System.out.println("lname : "+bean.getLname());
            System.out.println("email :"+bean.getEmail());
            
            List<BaseFile> baseFiles = bean.getBaseFiles();
            
            for (BaseFile baseFile : baseFiles) {
                   System.out.println("baseFile :   "+baseFile.getFilename());
              
                   
                //   Base64.decodeBase64(baseFile.getBase64());
                byte[] decodeBase64 = org.apache.commons.codec.binary.Base64.decodeBase64(baseFile.getBase64());
                String path="/app/TejyaTheBoss/"+baseFile.getFilename();
                
                FileUtils.writeByteArrayToFile(new File(path), decodeBase64);
                
            }
            System.out.println("################################# "); 
        }

    }
    
    
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public void download(@RequestParam ("name") String name, final HttpServletRequest request, final HttpServletResponse response) {
         System.out.println("name : {}"+ name);

        File file = new File ("/app/" + name);
         System.out.println("Write response...");
        try (InputStream fileInputStream = new FileInputStream(file);
                OutputStream output = response.getOutputStream();) {

            response.reset();

            response.setContentType("application/octet-stream");
            response.setContentLength((int) (file.length()));

            response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");

            IOUtils.copyLarge(fileInputStream, output);
            output.flush();
        } catch (IOException e) {
             System.out.println(e.getMessage());
        }

    }

    
}
