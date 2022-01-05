package edu.fudan.hrms.controller.req;

public class HandlerSalaryReq {
    int applicationID;
    boolean agree;
    String reason = "";

    public HandlerSalaryReq(int applicationID, boolean agree, String reason) {
        this.applicationID = applicationID;
        this.agree = agree;
        this.reason = reason;
    }

    public int getApplicationID() {
        return applicationID;
    }

    public void setApplicationID(int applicationID) {
        this.applicationID = applicationID;
    }

    public boolean isAgree() {
        return agree;
    }

    public void setAgree(boolean agree) {
        this.agree = agree;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
