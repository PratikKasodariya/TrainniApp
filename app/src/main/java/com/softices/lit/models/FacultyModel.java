package com.softices.lit.models;

import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * Created by Softices on 5/3/2018.
 */

public class FacultyModel {
    String firstname, lastname, mobile, email, subject, address, degree, department,Student;
    ImageView show_image;
    Bitmap imgBitmap;

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }


    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLastname() {
        return lastname;
    }


    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile() {
        return mobile;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getDegree() {
        return degree;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    public ImageView getShow_image() {
        return show_image;
    }

    public void setShow_image(ImageView show_image) {
        this.show_image = show_image;
    }


    public void setImgBitmap(Bitmap imgBitmap) {
        this.imgBitmap = imgBitmap;
    }

    public Bitmap getImgBitmap() {
        return imgBitmap;
    }
}


