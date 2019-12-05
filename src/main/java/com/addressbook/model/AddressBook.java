package com.addressbook.model;

import java.util.ArrayList;
import java.util.List;

public class AddressBook {

    public List<PersonData> personData = new ArrayList<>();

    public List<PersonData> getPersonData() {
        return personData;
    }

    public void setPersonData(List<PersonData> personData) {
        this.personData = personData;
    }
}
