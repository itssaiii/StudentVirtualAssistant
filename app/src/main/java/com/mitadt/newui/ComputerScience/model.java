package com.mitadt.newui.ComputerScience;

import java.lang.ref.Reference;

public class model {

    String fileurl;
    String filename;

    public Reference getUrl() {
        return url;
    }

    public model(Reference url) {
        this.url = url;
    }

    public void setUrl(Reference url) {
        this.url = url;
    }

    Reference url;

    public model() {

    }

    public model(String name, String email) {
        this.filename = name;
        this.fileurl = email;
    }

    public String getFilename() {
        return filename;
    }

    public void setName(String name) {
        this.filename = name;
    }

    public String getFileurl() {
        return fileurl;
    }

    public void setFileurl(String email) {
        this.fileurl = email;
    }
}
