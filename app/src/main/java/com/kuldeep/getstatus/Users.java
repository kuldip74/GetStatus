package com.kuldeep.getstatus;

/**
 * Created by ADMIN on 2/22/2018.
 */

public class Users {

    String userName;
    String userStatus;
    String userId;


    public  Users(){

    }

    public Users(String userName, String userStatus, String userId) {
        this.userName = userName;
        this.userStatus = userStatus;
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public String getUserName() {
        return userName;
    }
}
