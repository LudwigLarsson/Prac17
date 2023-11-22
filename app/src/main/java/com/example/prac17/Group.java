package com.example.prac17;


public class Group {
    private int id;
    private String Name;
    private String Author;

    public Group (int id, String name, String author ) {
        this.id = id;
        Name = name;
        Author = author;

    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getManga_Name() {
        return Name;
    }

    public void setManga_Name(String name) {
        Name = name;
    }

    public String getManga_Author(){
        return Author;
    }

    public void setManga_Author(String author) {
        Author = author;
    }
}
