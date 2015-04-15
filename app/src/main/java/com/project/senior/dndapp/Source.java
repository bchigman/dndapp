package com.project.senior.dndapp;

/**
 * Created by Ben on 4/15/2015.
 */
public class Source {
    private String name;
    private int page;

    public Source(String name, int page){
        this.name = name;
        this.page = page;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
