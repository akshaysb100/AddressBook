/******************************************************************************
 *  Purpose: this class provide address book all services
 *
 *  @author  Akshay
 *  @version 1.0
 *  @since   04-12-2019
 *
 *******************************************************************************/
package com.addressbook.services;

import com.addressbook.customexception.AddressBookCustomException;

import com.addressbook.model.AddressBook;
import com.addressbook.objectfactory.ObjectFactory;

import java.io.*;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class AddressBookServicesImplementation implements AddressBookServices{

    ObjectFactory objectFactory = new ObjectFactory();
    String filepath="/home/user/IdeaProjects/AddressBookProject/src/test/java/com/addressbook/jesonfile";

    @Override
    public boolean createAddressBook(String addressBookPath) throws AddressBookCustomException {

        writeDataIntoFile(objectFactory.showData,addressBookPath);
        System.out.println("Address book created successfully");
        return true;
    }

    @Override
    public String writeDataInAddressBook(String addressBookPath, String firstName, String lastName, String mobileNumber, String cityName, String stateName, int zipCode) throws AddressBookCustomException {

        readData(addressBookPath);
        objectFactory.personData.setFirstName(firstName);
        objectFactory.personData.setLastName(lastName);
        objectFactory.personData.setMobileNumber(mobileNumber);
        objectFactory.address.setCityName(cityName);
        objectFactory.address.setStateName(stateName);
        objectFactory.address.setZipCode(zipCode);
        objectFactory.personData.setAddress(objectFactory.address);

        objectFactory.personList.add(objectFactory.personData);

        writeDataIntoFile(objectFactory.showData,addressBookPath);
        System.out.println("Add person data into file");

        return objectFactory.showData.getPersonData().get(0).getMobileNumber();
    }

    public void writeDataIntoFile(AddressBook addressBook, String addressBookPath) throws AddressBookCustomException {

        objectFactory.showData.setPersonData(objectFactory.personList);
        String json = objectFactory.gson.toJson(addressBook);
        FileWriter writer = null;
        try {
            writer = new FileWriter(addressBookPath);
            writer.write(json);
            writer.close();
        }catch (FileNotFoundException e){
            throw new AddressBookCustomException(AddressBookCustomException.ExceptionType.NO_SUCH_FILE, "please Enter proper file path or type ",e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean openAddressBook(String addressBookName) throws AddressBookCustomException {

        boolean value = readData(addressBookName);
        readFromFile();
        return value;
    }

    @Override
    public void readFromFile() {

        for (int i=0;i<objectFactory.showData.getPersonData().size();i++){

            System.out.println("First Name : "+objectFactory.showData.getPersonData().get(i).getFirstName());
            System.out.println("Last Name : "+objectFactory.showData.getPersonData().get(i).getLastName());
            System.out.println("Mobile Number : "+objectFactory.showData.getPersonData().get(i).getMobileNumber());
            System.out.println("City Name: "+objectFactory.showData.getPersonData().get(i).getAddress().getCityName());
            System.out.println("State Name : "+objectFactory.showData.getPersonData().get(i).getAddress().getStateName());
            System.out.println("Pin Code : "+objectFactory.showData.getPersonData().get(i).getAddress().getZipCode());
            System.out.println("***********************************************************************************");
        }
    }

    @Override
    public boolean readData(String addressBookName) throws AddressBookCustomException {

        File fileName = new File(addressBookName);
        if (fileName.exists()) {
            if (fileName.length() != 0) {

                try {
                    objectFactory.bufferedReader = new BufferedReader(new FileReader(fileName));
                } catch (FileNotFoundException e) {
                    throw new AddressBookCustomException(AddressBookCustomException.ExceptionType.NO_SUCH_FILE, "file not found", e);
                }
                objectFactory.showData = objectFactory.gson.fromJson(objectFactory.bufferedReader, AddressBook.class);
                objectFactory.personList.addAll(objectFactory.showData.getPersonData());
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean searchPersonDataFromFile(String addressBookName, String mobileNumber) throws AddressBookCustomException {

        try {
            readData(addressBookName);
            for(int index = 0; index<objectFactory.showData.getPersonData().size() ; index++){

                if (mobileNumber.equals(objectFactory.showData.getPersonData().get(index).getMobileNumber())){

                    System.out.println("Person Information");
                    System.out.println("First Name : "+objectFactory.showData.getPersonData().get(index).getFirstName());
                    System.out.println("Last Name : "+objectFactory.showData.getPersonData().get(index).getLastName());
                    System.out.println("Mobile Number : "+objectFactory.showData.getPersonData().get(index).getMobileNumber());
                    System.out.println("City Name : "+objectFactory.showData.getPersonData().get(index).getAddress().getCityName());
                    System.out.println("State Name : "+objectFactory.showData.getPersonData().get(index).getAddress().getStateName());
                    System.out.println("Pin Code : "+objectFactory.showData.getPersonData().get(index).getAddress().getZipCode());
                    return true;
                }
            }
        } catch (AddressBookCustomException e) {
            throw new AddressBookCustomException(AddressBookCustomException.ExceptionType.NO_SUCH_FILE, "file not found", e);
        }
        throw new AddressBookCustomException(AddressBookCustomException.ExceptionType.NO_SUCH_DATA, "Person not found");
    }
    @Override
    public boolean editPersonData(String addressBookName, String mobileNumber, String filedName, String fieldValue) throws AddressBookCustomException {

        try {
            readData(addressBookName);
            for (int index = 0; index<objectFactory.showData.getPersonData().size() ; index++){

                if (mobileNumber.equals(objectFactory.showData.getPersonData().get(index).getMobileNumber())){

                    if (filedName.equals("firstName")){
                        System.out.println("fieldValue="+fieldValue);
                        objectFactory.showData.getPersonData().get(index).setFirstName(fieldValue);
                    }
                    writeDataIntoFile(objectFactory.showData,addressBookName);
                    return true;
                }
            }
        } catch (AddressBookCustomException e) {
            e.printStackTrace();
        }
        throw new AddressBookCustomException(AddressBookCustomException.ExceptionType.NO_SUCH_DATA, "Person not found");
    }
}