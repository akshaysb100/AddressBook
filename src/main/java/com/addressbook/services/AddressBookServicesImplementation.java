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
<<<<<<< HEAD
import com.addressbook.model.AddressBook;
=======
import com.addressbook.model.PersonData;
>>>>>>> master
import com.addressbook.objectfactory.ObjectFactory;
import com.google.gson.Gson;

<<<<<<< HEAD
import java.io.*;
=======
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
>>>>>>> master

public class AddressBookServicesImplementation implements AddressBookServices{

    ObjectFactory objectFactory = new ObjectFactory();

    @Override
    public boolean createAddressBook(String addressBookPath) throws AddressBookCustomException {

        writeDataIntoFile(objectFactory.personList,addressBookPath);
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

        writeDataIntoFile(objectFactory.personList,addressBookPath);
        System.out.println("Add person data into file");

        return objectFactory.personList.get(0).getMobileNumber();
    }

    public void writeDataIntoFile(List<PersonData> addressBookList, String addressBookPath) throws AddressBookCustomException {

        String json = objectFactory.gson.toJson(addressBookList);
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

        File fileName = new File(addressBookName + ".json");
        if (fileName.exists())
        {
            if (fileName.length() != 0)
            {

                try
                {
                    objectFactory.bufferedReader = new BufferedReader(new FileReader(fileName));
                }
                catch (FileNotFoundException e)
                {
                    throw new AddressBookCustomException(AddressBookCustomException.ExceptionType.NO_SUCH_FILE, "file not found",e);
                }
                objectFactory.showData = objectFactory.gson.fromJson(objectFactory.bufferedReader , AddressBook.class);
                objectFactory.personList.addAll( objectFactory.showData.getPersonData());

                ReadFromFile();

                return true;
            }
        }
        return false;
    }

    @Override
    public void ReadFromFile() {

        for (int i=0;i<objectFactory.personList.size();i++){

            System.out.println("First Name : "+objectFactory.showData.getPersonData().get(0).getFirstName());
            System.out.println("Last Name : "+objectFactory.showData.getPersonData().get(0).getLastName());
            System.out.println("Mobile Number : "+objectFactory.showData.getPersonData().get(0).getMobileNumber());
            System.out.println("City Name: "+objectFactory.showData.getPersonData().get(0).getAddress().getCityName());
            System.out.println("State Name : "+objectFactory.showData.getPersonData().get(0).getAddress().getStateName());
            System.out.println("Pin Code : "+objectFactory.showData.getPersonData().get(0).getAddress().getZipCode());
            System.out.println("***********************************************************************************");
        }
    }


}