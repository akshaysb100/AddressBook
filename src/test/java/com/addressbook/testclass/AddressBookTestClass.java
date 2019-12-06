package com.addressbook.testclass;

import com.addressbook.customexception.AddressBookCustomException;
import com.addressbook.services.AddressBookServicesImplementation;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class AddressBookTestClass {

    AddressBookServicesImplementation addressBookServices = new AddressBookServicesImplementation();
   // String addressBookName = "/home/user/IdeaProjects/AddressBookProject/src/test/java/com/addressbook/jesonfile/person.json";
    String jsonFilePath = "/home/user/IdeaProjects/AddressBookProject/src/test/java/com/addressbook/jesonfile/";

    @Test
    public void check_WhenAddressBook_Created_ReturnTrue() throws IOException, AddressBookCustomException {

        String fileName="personList";
        Assert.assertEquals(true,addressBookServices.createAddressBook(jsonFilePath+fileName+".json"));
    }

    @Test
    public void WhenFilePathEmpty_AndReturnTypeWrong_TrowException() {

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
            String fileName="personList";
            Assert.assertEquals("9403167854",addressBookServices.writeDataInAddressBook(
                    jsonFilePath+fileName+".json","Tushar","ombale","9403167854","Pune"
                    ,"Maharashtra",412807));
        } catch (AddressBookCustomException e) {
            System.out.println("Exception is : "+ e.getMessage());
            Assert.assertEquals(AddressBookCustomException.ExceptionType.NO_SUCH_FILE,e.type);
        }
    }

    @Test
    public void openAddressBook_AndReadAddressBook() {

        try {
            String fileName="personList";
            Assert.assertEquals(true,addressBookServices.openAddressBook(jsonFilePath+fileName+".json"));
        } catch (AddressBookCustomException e) {
            System.out.println("Exception is : "+ e.getMessage());
            Assert.assertEquals(AddressBookCustomException.ExceptionType.NO_SUCH_FILE,e.type);
        }
    }

    @Test
    public void givenAddressBook_SearchPersonData_WhenFound_ReturnTrue() {

        try {
            String fileName="personList";
            Assert.assertEquals(true,addressBookServices.searchPersonDataFromFile(jsonFilePath+fileName+".json","7350055232"));
        } catch (AddressBookCustomException e) {
            System.out.println("Exception is : "+ e.getMessage());
            Assert.assertEquals(AddressBookCustomException.ExceptionType.NO_SUCH_DATA,e.type);
        }
    }

    @Test
    public void givenAddressBook_SearchPersonData_WhenPersonNotFound_ReturnFalse() {

        try {
            String fileName="personList";
            Assert.assertEquals(true,addressBookServices.searchPersonDataFromFile(jsonFilePath+fileName+".json","983434152"));
        } catch (AddressBookCustomException e) {
            System.out.println("Exception is : "+ e.getMessage());
            Assert.assertEquals(AddressBookCustomException.ExceptionType.NO_SUCH_DATA,e.type);
        }
    }


    @Test
    public void givenAddressBook_EditPersonFirstName_WhenPersonFound_ReturnTrue() {

        try {
            String fileName="personList";
            Assert.assertEquals(true,addressBookServices.editPersonData(jsonFilePath+fileName+".json","7350055232",
                    "firstName","Rohit"));
        } catch (AddressBookCustomException e) {
            System.out.println("Exception is : "+ e.getMessage());
            Assert.assertEquals(AddressBookCustomException.ExceptionType.NO_SUCH_DATA,e.type);
        }
    }

    @Test
    public void givenAddressBook_EditPersonLastName_WhenPersonFound_ReturnTrue() {

        try {
            String fileName="personList";
            Assert.assertEquals(true,addressBookServices.editPersonData(jsonFilePath+fileName+".json","9834341522",
                    "lastName","Badole"));
        } catch (AddressBookCustomException e) {
            System.out.println("Exception is : "+ e.getMessage());
            Assert.assertEquals(AddressBookCustomException.ExceptionType.NO_SUCH_DATA,e.type);
        }
    }

    @Test
    public void givenAddressBook_EditPersonCityName_WhenPersonFound_ReturnTrue() {

        try {
            String fileName="personList";
            Assert.assertEquals(true,addressBookServices.editPersonData(jsonFilePath+fileName+".json","9834341522",
                    "cityName","Mahabaleshwar"));
        } catch (AddressBookCustomException e) {
            System.out.println("Exception is : "+ e.getMessage());
            Assert.assertEquals(AddressBookCustomException.ExceptionType.NO_SUCH_DATA,e.type);
        }
    }

    @Test
    public void givenAddressBook_EditPersonPinCode_WhenPersonFound_ReturnTrue() {

        try {
            String fileName="personList";
            Assert.assertEquals(true,addressBookServices.editPersonData(jsonFilePath+fileName+".json","9834341522",
                    "pinCode","411412"));
        } catch (AddressBookCustomException e) {
            System.out.println("Exception is : "+ e.getMessage());
            Assert.assertEquals(AddressBookCustomException.ExceptionType.NO_SUCH_DATA,e.type);
        }
    }

    @Test
    public void givenAddressBook_EditPersonStateName_WhenPersonFound_ReturnTrue() {

        try {
            String fileName="personList";
            Assert.assertEquals(true,addressBookServices.editPersonData(jsonFilePath+fileName+".json","9834341522",
                    "stateName","MP"));
        } catch (AddressBookCustomException e) {
            System.out.println("Exception is : "+ e.getMessage());
            Assert.assertEquals(AddressBookCustomException.ExceptionType.NO_SUCH_DATA,e.type);
        }
    }

    @Test
    public void givenAddressBook_DeletePersonData_ReturnTrue() {

        try {
            String fileName="personList";
            Assert.assertEquals(true,addressBookServices.deletePersonDetails(jsonFilePath+fileName+".json","9834641522"));
        } catch (AddressBookCustomException e) {
            System.out.println("Exception is : "+ e.getMessage());
            Assert.assertEquals(AddressBookCustomException.ExceptionType.NO_SUCH_DATA,e.type);
        }
    }

    @Test
    public void givenAddressBook_SortBasedByFirstName_ShouldReturnTrue() {

        try {
            String fileName="personList";
            Assert.assertEquals(true,addressBookServices.sortPersonDetails(jsonFilePath+fileName+".json","firstName"));
        } catch (AddressBookCustomException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAddressBook_SortBasedByLastName_ShouldReturnTrue() {

        try {
            String fileName="personList";
            Assert.assertEquals(true,addressBookServices.sortPersonDetails(jsonFilePath+fileName+".json","lastName"));
        } catch (AddressBookCustomException e) {
            e.printStackTrace();
        }
    }
}
