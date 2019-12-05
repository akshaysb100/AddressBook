package com.addressbook.objectfactory;

import com.addressbook.model.PersonData;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ObjectFactory {

    public Gson gson = new Gson();
    public List<PersonData> personList = new ArrayList<>();
}