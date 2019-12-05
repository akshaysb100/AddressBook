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
import com.addressbook.objectfactory.ObjectFactory;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class AddressBookServicesImplementation implements AddressBookServices{

    ObjectFactory objectFactory = new ObjectFactory();

    @Override
    public boolean createAddressBook(String addressBookPath) throws AddressBookCustomException {

        String json = objectFactory.gson.toJson(objectFactory.personList);
        FileWriter writer = null;
        try {
            writer = new FileWriter(addressBookPath);
            writer.write(json);
            writer.close();
        }catch (FileNotFoundException e){
            throw new AddressBookCustomException(AddressBookCustomException.ExceptionType.NO_SUCH_FILE, "please Enter proper file path or type ",e);

        }  catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Address book created successfully");
        return true;
    }

    @Override
    public String writeDataInAddressBook(String addressBookPath, String firstName, String lastName, String mobileNumber, String cityName, String stateName, int zipCode) throws AddressBookCustomException {

        objectFactory.personData.setFirstName(firstName);
        objectFactory.personData.setLastName(lastName);
        objectFactory.personData.setMobileNumber(mobileNumber);
        objectFactory.address.setCityName(cityName);
        objectFactory.address.setStateName(stateName);
        objectFactory.address.setZipCode(zipCode);
        objectFactory.personData.setAddress(objectFactory.address);

        objectFactory.personList.add(objectFactory.personData);

        String json = objectFactory.gson.toJson(objectFactory.personList);
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
        System.out.println("Add person data into file");

        return objectFactory.personList.get(0).getMobileNumber();
    }
}