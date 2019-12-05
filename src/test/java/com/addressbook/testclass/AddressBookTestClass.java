package com.addressbook.testclass;

import com.addressbook.customexception.AddressBookCustomException;
import com.addressbook.services.AddressBookServicesImplementation;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class AddressBookTestClass {

    AddressBookServicesImplementation addressBookServices = new AddressBookServicesImplementation();

    @Test
    public void check_WhenAddressBook_Created_ReturnTrue() throws IOException, AddressBookCustomException {

        Assert.assertEquals(true,addressBookServices.createAddressBook( "/home/user/IdeaProjects/AddressBookProject/src/test/java/com/addressbook/jesonfile/newaddressbook.json"));
    }


    @Test
    public void WhenFilePath_AndReturnTypeWrong_TrowException() {

        try {
            Assert.assertEquals(true,addressBookServices.createAddressBook( ""));
        } catch (AddressBookCustomException e) {
            System.out.println("Exception is : "+ e.getMessage());
            Assert.assertEquals(AddressBookCustomException.ExceptionType.NO_SUCH_FILE, e.type);
        }
    }
}
