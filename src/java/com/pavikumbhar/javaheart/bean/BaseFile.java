/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pavikumbhar.javaheart.bean;

/**
 *
 * @author pravinkumbhar
 */
public class BaseFile {

    private long filesize;
    private String filetype;
    private String filename;
    private String base64;

    public long getFilesize() {
        return filesize;
    }

    public void setFilesize(long filesize) {
        this.filesize = filesize;
    }

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }

    @Override
    public String toString() {
        return "BaseFile{" + "filesize=" + filesize + ", filetype=" + filetype + ", filename=" + filename + ", base64=" + base64 + '}';
    }
    
}
