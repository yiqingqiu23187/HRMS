package edu.fudan.hrms.controller.req;

import java.sql.Timestamp;

public class ApplySalaryReq {
    int userID;
    int type;
    Long newValue;
    Long time;

    public ApplySalaryReq(int userID, int type, Long newValue, Long time) {
        this.userID = userID;
        this.type = type;
        this.newValue = newValue;
        this.time = time;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Long getNewValue() {
        return newValue;
    }

    public void setNewValue(Long newValue) {
        this.newValue = newValue;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
