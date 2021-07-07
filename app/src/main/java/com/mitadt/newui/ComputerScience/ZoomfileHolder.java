package com.mitadt.newui.ComputerScience;

public class ZoomfileHolder {
    String LectureDate,LectureTime,MeetingLink,Topic;

    public ZoomfileHolder() {
    }

    public ZoomfileHolder(String lectureDate, String lectureTime, String meetingLink, String topic) {
        LectureDate = lectureDate;
        LectureTime = lectureTime;
        MeetingLink = meetingLink;
        Topic = topic;
    }

    public String getLectureDate() {
        return LectureDate;
    }

    public void setLectureDate(String lectureDate) {
        LectureDate = lectureDate;
    }

    public String getLectureTime() {
        return LectureTime;
    }

    public void setLectureTime(String lectureTime) {
        LectureTime = lectureTime;
    }

    public String getMeetingLink() {
        return MeetingLink;
    }

    public void setMeetingLink(String meetingLink) {
        MeetingLink = meetingLink;
    }

    public String getTopic() {
        return Topic;
    }

    public void setTopic(String topic) {
        Topic = topic;
    }
}
