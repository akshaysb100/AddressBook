package com.addressbook.objectfactory;

import com.addressbook.model.Address;
import com.addressbook.model.AddressBook;
import com.addressbook.model.PersonData;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public class ObjectFactory {

    public Gson gson = new Gson();
    public List<PersonData> personList = new ArrayList<>();
    public PersonData personData = new PersonData();
    public Address address = new Address();
    public BufferedReader bufferedReader;
    public AddressBook showData = new AddressBook();
}