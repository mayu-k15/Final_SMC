package com.smc.smcsystem;

public class ModelPost {
    public ModelPost() {
    }

    String description,area;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPtime() {
        return ptime;
    }

    public void setPtime(String ptime) {
        this.ptime = ptime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUdp() {
        return udp;
    }

    public void setUdp(String udp) {
        this.udp = udp;
    }

    public String getUemail() {
        return uemail;
    }

    public void setUemail(String uemail) {
        this.uemail = uemail;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUimage() {
        return uimage;
    }

    public void setUimage(String uimage) {
        this.uimage = uimage;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPlike() {
        return plike;
    }

    public void setPlike(String plike) {
        this.plike = plike;
    }

    String pid;
    String AssignedTo;

    public String getAssignedTo() {
        return AssignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        AssignedTo = assignedTo;
    }

    public ModelPost(String description, String area, String pid, String assignedTo, String ptime, String pcomments, String type, String reply, String replyimg, String title, String status, String udp, String uemail, String uid, String uimage, String uname, String plike) {
        this.description = description;
        this.area = area;
        this.pid = pid;
        AssignedTo = assignedTo;
        this.ptime = ptime;
        this.pcomments = pcomments;
        this.type = type;
        this.reply = reply;
        this.replyimg = replyimg;
        this.title = title;
        this.status = status;
        this.udp = udp;
        this.uemail = uemail;
        this.uid = uid;
        this.uimage = uimage;
        this.uname = uname;
        this.plike = plike;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPcomments() {
        return pcomments;
    }

    public void setPcomments(String pcomments) {
        this.pcomments = pcomments;
    }

    public ModelPost(String description, String pid, String ptime, String pcomments, String title, String udp, String uemail, String uid, String uimage, String uname, String plike,String status) {
        this.description = description;
        this.pid = pid;
        this.ptime = ptime;
        this.pcomments = pcomments;
        this.title = title;
        this.udp = udp;
        this.uemail = uemail;
        this.uid = uid;
        this.uimage = uimage;
        this.uname = uname;
        this.plike = plike;
        this.status=status;
    }

    String ptime, pcomments;

    public ModelPost(String description, String area, String pid, String ptime, String pcomments, String type, String reply, String replyimg, String title, String status, String udp, String uemail, String uid, String uimage, String uname, String plike) {
        this.description = description;
        this.area = area;
        this.pid = pid;
        this.ptime = ptime;
        this.pcomments = pcomments;
        this.type = type;
        this.reply = reply;
        this.replyimg = replyimg;
        this.title = title;
        this.status = status;
        this.udp = udp;
        this.uemail = uemail;
        this.uid = uid;
        this.uimage = uimage;
        this.uname = uname;
        this.plike = plike;
    }

    String type,reply,replyimg;
    String title,status;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getReplyimg() {
        return replyimg;
    }

    public void setReplyimg(String replyimg) {
        this.replyimg = replyimg;
    }

    String udp;
    String uemail;
    String uid;
    String uimage;

    String uname, plike;

}

