package com.kuldeep.getstatus;

/**
 * Created by ADMIN on 2/22/2018.
 */

public class Users extends UserId {

    String userName;
    String userStatus;

    public  Users(){

    }

    public Users(String userName, String userStatus) {
        this.userName = userName;
        this.userStatus = userStatus;
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
