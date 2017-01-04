/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pavikumbhar.javaheart.bean;

import java.util.List;

/**
 *
 * @author pravinkumbhar
 */
public class FromBean {
    private String fname;
    private String lname;
    private String email;
    private List<BaseFile> baseFiles;

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<BaseFile> getBaseFiles() {
        return baseFiles;
    }

    public void setBaseFiles(List<BaseFile> baseFiles) {
        this.baseFiles = baseFiles;
    }

    @Override
    public String toString() {
        return "FromBean{" + "fname=" + fname + ", lname=" + lname + ", email=" + email + ", baseFiles=" + baseFiles + '}';
    }
    


    
}
