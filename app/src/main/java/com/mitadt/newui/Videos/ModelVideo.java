package com.mitadt.newui.Videos;

public class ModelVideo {
    String id, title, timestamp, videourl;

    //constructor
    public ModelVideo() {
        //firebase requires empty constructor
    }

    public ModelVideo(String id, String title, String timestamp, String videourl) {
        this.id = id;
        this.title = title;
        this.timestamp = timestamp;
        this.videourl = videourl;
    }

    /*Getters and Setters*/

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getVideourl() {
        return videourl;
    }

    public void setVideourl(String videourl) {
        this.videourl = videourl;
    }
}
