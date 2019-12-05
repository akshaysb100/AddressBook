package com.addressbook.services;

import com.addressbook.customexception.AddressBookCustomException;

public interface AddressBookServices {

    public boolean createAddressBook(String jsonFilePath) throws AddressBookCustomException;
}