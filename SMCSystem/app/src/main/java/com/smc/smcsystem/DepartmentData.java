package com.smc.smcsystem;

public class DepartmentData {
    String DepartmentName;
    String UserID;
    String Password;

    public String getDepartmentName() {
        return DepartmentName;
    }

    public void setDepartmentName(String departmentName) {
        DepartmentName = departmentName;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public DepartmentData(String departmentName, String userID, String password) {
        DepartmentName = departmentName;
        UserID = userID;
        Password = password;
    }

    public DepartmentData() {
    }
}
