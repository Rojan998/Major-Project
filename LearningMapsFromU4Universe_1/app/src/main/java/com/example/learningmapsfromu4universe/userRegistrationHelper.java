package com.example.learningmapsfromu4universe;

public class userRegistrationHelper {

    public String UserName,
            Address,
            Email,
            Password,
            FamilyMembers,
            HouseNumber,
            PhoneNumber;



    public userRegistrationHelper() {


    }

    public userRegistrationHelper(String name, String address, String email,
                                  String password, String noOfFamilyMembers, String houseNumber, String phoneNumber) {
        UserName = name;
        Address = address;
        Email = email;
        Password = password;
        FamilyMembers = noOfFamilyMembers;
        HouseNumber = houseNumber;
        PhoneNumber = phoneNumber;

    }
}
