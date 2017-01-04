/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pavikumbhar.javaheart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author pravinkumbhar
 */

@Controller
@RequestMapping("/")
public class IndexController {

    @RequestMapping
    public String getIndexPage() {
        return "index";
    }

    @RequestMapping("/contactus")
    public String getContactusPartialPage() {
        return "views/contactus";

    }
    
    
    @RequestMapping("/gallery")
    public String getGalleryPartialPage() {
        return "views/gallery";
    }
    
    
     @RequestMapping("/clientMasterActivity")
    public String getClientMasterActivityPartialPage() {
        return "views/ClientMasterActivity";
    }
    
    
     @RequestMapping("/fileUpload")
    public String getfileUploadPartialPage() {
        return "views/fileupload";
    }
}
