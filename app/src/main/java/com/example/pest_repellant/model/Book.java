package com.example.pest_repellant.model;

public class Book {
    private String name;
    private String desc;
    private String imageUrl;

    public Book(String name, String desc, String imageUrl) {
        this.name = name;
        this.desc = desc;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
