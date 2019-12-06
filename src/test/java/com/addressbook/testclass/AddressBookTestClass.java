package com.addressbook.testclass;

import com.addressbook.customexception.AddressBookCustomException;
import com.addressbook.services.AddressBookServicesImplementation;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class AddressBookTestClass {

    AddressBookServicesImplementation addressBookServices = new AddressBookServicesImplementation();
    String addressBookName = "/home/user/IdeaProjects/AddressBookProject/src/test/java/com/addressbook/jesonfile/person.json";

    @Test
    public void check_WhenAddressBook_Created_ReturnTrue() throws IOException, AddressBookCustomException {

        Assert.assertEquals(true,addressBookServices.createAddressBook(addressBookName));
    }

    @Test
    public void WhenFilePath_AndReturnTypeWrong_TrowException() {

        try {
            Assert.assertEquals(true,addressBookServices.createAddressBook( ""));
        } catch (AddressBookCustomException e) {
            System.out.println("Exception is : "+ e.getMessage());
            Assert.assertEquals(AddressBookCustomException.ExceptionType.NO_SUCH_FILE,e.type);
        }
    }

    @Test
    public void givenPersonData_Add_InAddressBook() {

        try {
            Assert.assertEquals("7350055253",addressBookServices.writeDataInAddressBook(
                    addressBookName,"Akash","Bavalekar","9834341522","Pune"
                    ,"Maharashtra",412807));
        } catch (AddressBookCustomException e) {
            System.out.println("Exception is : "+ e.getMessage());
            Assert.assertEquals(AddressBookCustomException.ExceptionType.NO_SUCH_FILE,e.type);
        }
    }

    @Test
    public void openAddressBook_AndReadAddressBook() {

        try {
            Assert.assertEquals(true,addressBookServices.openAddressBook(addressBookName));
        } catch (AddressBookCustomException e) {
            System.out.println("Exception is : "+ e.getMessage());
            Assert.assertEquals(AddressBookCustomException.ExceptionType.NO_SUCH_FILE,e.type);
        }
    }

    @Test
    public void givenAddressBook_SearchPersonData_WhenFound_ReturnTrue() {

        try {
            Assert.assertEquals(true,addressBookServices.searchPersonDataFromFile(addressBookName,"9834341522"));
        } catch (AddressBookCustomException e) {
            System.out.println("Exception is : "+ e.getMessage());
            Assert.assertEquals(AddressBookCustomException.ExceptionType.NO_SUCH_FILE,e.type);
        }
    }
}
