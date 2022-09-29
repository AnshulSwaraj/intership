package com.example.application;

public class PostModel {

    private int department_id=2;
    private String name;
    private String phone;
    private String gender;
    private String email;
    private String address;


    public PostModel(int department_id,String name, String phone, String gender, String email, String address) {
        this.department_id=department_id;
        this.name = name;
        this.phone = phone;
        this.gender = gender;
        this.email = email;
        this.address = address;
    }


    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }

    public int getDepartment_id() {
        return department_id;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
