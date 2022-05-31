package com.example.project8;

public class UserDetails {
    private String username;
    private String email;
    private String phone;
    private int rollNumber;
    private String branch;
    private int semester;

    public UserDetails(String username , String email , String phone , int rollNumber , String branch , int sem) {
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.rollNumber = rollNumber;
        this.branch = branch;
        this.semester = sem;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public String getBranch() {
        return branch;
    }

    public int getSemester() {
        return semester;
    }
}
