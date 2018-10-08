package com.softices.lit.models;

/**
 * Created by Softices on 5/2/2018.
 */

public class StudentModel {
    String enrollment, firstName, lastName, email, sem,department,address, mobileNo,cpi;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEnrollment(String enrollment) {
        this.enrollment = enrollment;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public void setSem(String sem) {
        this.sem = sem;
    }

    public String getAddress() {
        return address;
    }

    public String getDepartment() {
        return department;
    }

    public String getEmail() {
        return email;
    }

    public String getEnrollment() {
        return enrollment;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public String getSem() {
        return sem;
    }

    public void setCpi(String cpi) {
        this.cpi = cpi;
    }

    public String getCpi() {
        return cpi;
    }
}