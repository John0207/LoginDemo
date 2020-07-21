package com.zybooks.logindemo;

public class UserProfile {
    public String userAge;
    public String userEmail;
    public String userName;
//  function overloading, concept of oop, same functions but increasing parameters
    public UserProfile(){

   }

    //constructor, assign values that are provided from other activities to the variables within it
    public UserProfile(String userAge, String userEmail, String userName) {
        this.userAge = userAge;
        this.userEmail = userEmail;
        this.userName = userName;
    }

    //getters and setters to access variables

    public String getUserAge() {
        return userAge;
    }

    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
