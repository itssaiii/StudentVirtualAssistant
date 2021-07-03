package com.mitadt.newui.DiscussionForum;

public class questionFileholder {

    String date,description,name,time,title;
    Long likes,comments;

    public questionFileholder(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public questionFileholder(Long likes, Long comments) {
        this.likes = likes;
        this.comments = comments;
    }

    public Long getLikes() {
        return likes;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
    }

    public Long getComments() {
        return comments;
    }

    public void setComments(Long comments) {
        this.comments = comments;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }



    public questionFileholder(String date, String description, String name, String time) {
        this.date = date;
        this.description = description;
        this.name = name;
        this.time = time;

    }

    public questionFileholder() {
    }


}
