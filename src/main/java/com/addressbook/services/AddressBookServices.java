package com.addressbook.services;

import com.addressbook.customexception.AddressBookCustomException;

public interface AddressBookServices {

    public boolean createAddressBook(String jsonFilePath) throws AddressBookCustomException;
    public String writeDataInAddressBook(String addressBookName,String firstName,String lastName,String mobileNumber,String cityName,String stateName,int zipCode) throws AddressBookCustomException;
    public boolean openAddressBook(String addressBookName) throws AddressBookCustomException;
    public void readFromFile();
    public boolean readData(String addressBookName) throws AddressBookCustomException;
    public boolean searchPersonDataFromFile(String addressBookName,String mobileNumber) throws AddressBookCustomException;
    public boolean editPersonData(String addressBookName,String mobileNumber,String filedName,String fieldValue) throws AddressBookCustomException;
    public boolean deletePersonDetails(String addressBookName, String mobileNumber) throws AddressBookCustomException;
}
