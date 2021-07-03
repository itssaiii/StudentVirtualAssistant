package com.mitadt.newui.DiscussionForum;

public class commentfileholder {
    String cname,cdate,ctime,cques,comment;

    public commentfileholder() {
    }

    public commentfileholder(String cname, String cdate, String ctime, String cques, String comment) {
        this.cname = cname;
        this.cdate = cdate;
        this.ctime = ctime;
        this.cques = cques;
        this.comment = comment;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCdate() {
        return cdate;
    }

    public void setCdate(String cdate) {
        this.cdate = cdate;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getCques() {
        return cques;
    }

    public void setCques(String cques) {
        this.cques = cques;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
