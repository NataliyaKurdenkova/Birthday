package com.example.birthday.repository;

import java.io.File;
import java.io.FilenameFilter;

public class MyFileNameFilter implements FilenameFilter {

    private String filter;

    public MyFileNameFilter(String filter){
        this.filter = filter.toLowerCase();
    }
    @Override
    public boolean accept(File dir, String name) {
        return name.toLowerCase().startsWith(filter);
    }
}
