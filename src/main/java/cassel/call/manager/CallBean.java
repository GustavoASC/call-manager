/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cassel.call.manager;

/**
 * Bean with call information
 */
public class CallBean {

    /* Call subject */
    private String subject;
    /* Call date */
    private String date;
    /* Call general information */
    private String generalInfo;

    public CallBean() {

    }

    public CallBean(String subject) {
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDate() {
        return date;
    }

    public CallBean setDate(String date) {
        this.date = date;
        return this;
    }

    public String getGeneralInfo() {
        return generalInfo;
    }

    public CallBean setGeneralInfo(String generalInfo) {
        this.generalInfo = generalInfo;
        return this;
    }

}
