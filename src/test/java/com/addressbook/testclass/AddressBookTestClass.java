package com.addressbook.testclass;

import com.addressbook.services.AddressBookServicesImplementation;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class AddressBookTestClass {

    AddressBookServicesImplementation addressBookServices = new AddressBookServicesImplementation();

    @Test
    public void check_WhenAddressBook_Created_ReturnTrue() throws IOException {

        Assert.assertEquals(true,addressBookServices.createAddressBook( "/home/user/IdeaProjects/AddressBookProject/src/test/java/com/addressbook/jesonfile/newaddressbook.json"));
    }
}
