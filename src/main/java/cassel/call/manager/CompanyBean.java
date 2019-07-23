/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cassel.call.manager;

import java.util.LinkedList;
import java.util.List;

/**
 * Bean with company information
 */
public class CompanyBean {

    /* Company name */
    private final String company;
    /* City name */
    private String city;
    /* Responsible 1 */
    private String responsible;
    /* Phone number */
    private String phoneNumber;
    /* E-mail */
    private String email;
    /* Responsible 2 */
    private String responsible2;
    /* Phone number 2 */
    private String phoneNumber2;
    /* E-mail 2 */
    private String email2;
    /* List of all company calls */
    private List<CallBean> calls;

    /**
     * Creates a new company bean with the company name
     *
     * @param company company name
     */
    public CompanyBean(String company) {
        this.company = company;
        this.calls = new LinkedList<>();
    }

    public String getCompany() {
        return company;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getResponsible2() {
        return responsible2;
    }

    public void setResponsible2(String responsible2) {
        this.responsible2 = responsible2;
    }

    public String getPhoneNumber2() {
        return phoneNumber2;
    }

    public void setPhoneNumber2(String phoneNumber2) {
        this.phoneNumber2 = phoneNumber2;
    }

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    public List<CallBean> getCalls() {
        return calls;
    }

    public void addCall(CallBean bean) {
        this.calls.add(bean);
    }

    public void setCalls(List<CallBean> calls) {
        this.calls = calls;
    }
    

}
