/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author DANH NGUYEN
 */
public class Student implements Serializable {

    private static final long serialVersionUID = 2L;

    private String id;
    private String name;
    private String phone;
    private String email;
    private String mountainCode;
    private String tuitionFee;

    public Student() {
    }

    public Student(String id, String name, String phone, String email, String mountainCode, String tutionFee) {
        this.setId(id);
        this.setName(name);
        this.setPhone(phone);
        this.setEmail(email);
        this.setMountainCode(mountainCode);
        this.setTuitionFee(tutionFee);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMountainCode() {
        return mountainCode;
    }

    public void setMountainCode(String mountainCode) {
        this.mountainCode = mountainCode;
    }

    public String getTuitionFee() {
        return tuitionFee;
    }

    public void setTuitionFee(String tuitionFee) {
        this.tuitionFee = tuitionFee;
    }

    @Override
    public String toString() {
        return String.format("%-11s | %-22s| %-11s| MT%-7s| %s", id, name, phone, mountainCode, tuitionFee);
    }
}
