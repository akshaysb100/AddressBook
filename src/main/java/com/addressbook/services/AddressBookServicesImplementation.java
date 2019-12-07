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
import com.addressbook.model.PersonData;
import com.addressbook.objectfactory.ObjectFactory;

import java.io.*;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Comparator;

public class AddressBookServicesImplementation implements AddressBookServices {

    ObjectFactory objectFactory = new ObjectFactory();
    @Override
    public boolean createAddressBook(String addressBookPath) throws AddressBookCustomException {

        saveDataInFile(objectFactory.showData, addressBookPath);
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

        saveDataInFile(objectFactory.showData, addressBookPath);
        System.out.println("Add person data into file");

        int value=objectFactory.showData.getPersonData().size()-1;
        return objectFactory.showData.getPersonData().get(value).getMobileNumber();
    }

    public void saveDataInFile(AddressBook addressBook, String addressBookPath) throws AddressBookCustomException {

        objectFactory.showData.setPersonData(objectFactory.personList);
        String json = objectFactory.gson.toJson(addressBook);
        FileWriter writer = null;
        try {
            writer = new FileWriter(addressBookPath);
            writer.write(json);
            writer.close();
        } catch (FileNotFoundException e) {
            throw new AddressBookCustomException(AddressBookCustomException.ExceptionType.NO_SUCH_FILE, "please Enter proper file path or type ", e);
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

        for (int i = 0; i < objectFactory.showData.getPersonData().size(); i++) {

            System.out.println("First Name : " + objectFactory.showData.getPersonData().get(i).getFirstName());
            System.out.println("Last Name : " + objectFactory.showData.getPersonData().get(i).getLastName());
            System.out.println("Mobile Number : " + objectFactory.showData.getPersonData().get(i).getMobileNumber());
            System.out.println("City Name: " + objectFactory.showData.getPersonData().get(i).getAddress().getCityName());
            System.out.println("State Name : " + objectFactory.showData.getPersonData().get(i).getAddress().getStateName());
            System.out.println("Pin Code : " + objectFactory.showData.getPersonData().get(i).getAddress().getZipCode());
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
            for (int index = 0; index < objectFactory.showData.getPersonData().size(); index++) {

                if (mobileNumber.equals(objectFactory.showData.getPersonData().get(index).getMobileNumber())) {

                    System.out.println("Person Information");
                    System.out.println("First Name : " + objectFactory.showData.getPersonData().get(index).getFirstName());
                    System.out.println("Last Name : " + objectFactory.showData.getPersonData().get(index).getLastName());
                    System.out.println("Mobile Number : " + objectFactory.showData.getPersonData().get(index).getMobileNumber());
                    System.out.println("City Name : " + objectFactory.showData.getPersonData().get(index).getAddress().getCityName());
                    System.out.println("State Name : " + objectFactory.showData.getPersonData().get(index).getAddress().getStateName());
                    System.out.println("Pin Code : " + objectFactory.showData.getPersonData().get(index).getAddress().getZipCode());
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
            for (int index = 0; index < objectFactory.showData.getPersonData().size(); index++) {

                if (mobileNumber.equals(objectFactory.showData.getPersonData().get(index).getMobileNumber())) {

                    if (filedName.equals("firstName")) {
                        objectFactory.showData.getPersonData().get(index).setFirstName(fieldValue);
                    }else if (filedName.equals("lastName")){
                        objectFactory.showData.getPersonData().get(index).setLastName(fieldValue);
                    }else if (filedName.equals("cityName")){
                        objectFactory.showData.getPersonData().get(index).getAddress().setCityName(fieldValue);
                    } else if (filedName.equals("pinCode")){
                        objectFactory.showData.getPersonData().get(index).getAddress().setZipCode(Integer.parseInt(fieldValue));
                    }else if (filedName.equals("stateName")){
                        objectFactory.showData.getPersonData().get(index).getAddress().setStateName(fieldValue);
                    }
                    saveDataInFile(objectFactory.showData, addressBookName);
                    return true;
                }
            }
        } catch (AddressBookCustomException e) {
            e.printStackTrace();
        }
        throw new AddressBookCustomException(AddressBookCustomException.ExceptionType.NO_SUCH_DATA, "Person not found");
    }

    @Override
    public boolean deletePersonDetails(String addressBookName, String mobileNumber) throws AddressBookCustomException {

        try {
            readData(addressBookName);
            for (int index = 0; index < objectFactory.showData.getPersonData().size(); index++) {

                if (mobileNumber.equals(objectFactory.showData.getPersonData().get(index).getMobileNumber())) {
                    objectFactory.showData.getPersonData().remove(index);
                    saveDataInFile(objectFactory.showData, addressBookName);
                    return true;
                }
                objectFactory.showData.getPersonData().addAll(objectFactory.showData.getPersonData());
                saveDataInFile(objectFactory.showData, addressBookName);
            }
        } catch (AddressBookCustomException e) {
            e.printStackTrace();
        }
        throw new AddressBookCustomException(AddressBookCustomException.ExceptionType.NO_SUCH_DATA, "Person not found");
    }

    @Override
    public boolean sortPersonDetails(String addressBookName,String fieldName) throws AddressBookCustomException {

        readData(addressBookName);
        File fileName = new File(addressBookName);
        Collections.sort(objectFactory.personList,new Comparator<PersonData>()
        {
            @Override
            public int compare(PersonData o1, PersonData o2)
            {
                try
                {
                    Field fieldType = PersonData.class.getDeclaredField(fieldName);
                    fieldType.setAccessible(true);
                    Comparable stateCensusFieldValue1 = (Comparable) fieldType.get(o1);
                    Comparable stateCensusFieldValue2 = (Comparable) fieldType.get(o2);
                    return stateCensusFieldValue1.compareTo(stateCensusFieldValue2);
                }
                catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e)
                {
                    e.printStackTrace();
                    return 0;
                }
            }
        });
        objectFactory.showData.setPersonData(objectFactory.personList);
        saveDataInFile(objectFactory.showData, addressBookName);
        return true;
    }
}
