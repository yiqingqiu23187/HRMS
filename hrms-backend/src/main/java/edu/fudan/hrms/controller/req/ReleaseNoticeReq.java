package edu.fudan.hrms.controller.req;

public class ReleaseNoticeReq {
    public String notice;
    public Long userID;

    public ReleaseNoticeReq(String notice, Long userID) {
        this.notice = notice;
        this.userID = userID;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }
}
