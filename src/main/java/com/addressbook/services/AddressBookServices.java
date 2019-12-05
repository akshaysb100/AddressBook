package com.addressbook.services;

import com.addressbook.customexception.AddressBookCustomException;

public interface AddressBookServices {

    public boolean createAddressBook(String jsonFilePath) throws AddressBookCustomException;
    public String writeDataInAddressBook(String addressBookPath,String firstName,String lastName,String mobileNumber,String cityName,String stateName,int zipCode) throws AddressBookCustomException;
}