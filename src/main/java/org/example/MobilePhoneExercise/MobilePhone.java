package org.example.MobilePhoneExercise;

import java.util.ArrayList;

// 07/02/25 Section 10 Exercise 44
public class MobilePhone {
    private String myNumber;
    private ArrayList<Contact> myContacts;

    public MobilePhone(String myNumber) {
        this.myNumber = myNumber;
        this.myContacts = new ArrayList<>();
    }

    public boolean addNewContact(Contact contact) {
        if (findContact(contact) == -1) {
            this.myContacts.add(contact);
            return true;
        } else {
            return false;
        }
    }

    public boolean updateContact(Contact oldContact, Contact newContact) {
        if (removeContact(oldContact)) {
            addNewContact(newContact);
            return true;
        } else {
            return false;
        }
    }

    public boolean removeContact(Contact contact) {
        int index = findContact(contact);
        if (index == -1) {
            return false;
        } else {
            this.myContacts.remove(index);
            return true;
        }
    }

    private int findContact(Contact contact) {
        for (int i = 0; i < this.myContacts.size(); i++) {
            if (this.myContacts.get(i).getName().equals(contact.getName())) {
                return i;
            }
        }
        return -1;
    }

    private int findContact(String contactName) {
        for (int i = 0; i < this.myContacts.size(); i++) {
            if (this.myContacts.get(i).getName().equals(contactName)) {
                return i;
            }
        }
        return -1;
    }

    public Contact queryContact(String contactName) {
        int index = findContact(contactName);
        if (index == -1) {
            return null;
        } else {
            return this.myContacts.get(index);
        }
    }

    public void printContacts() {
        System.out.println("Contact List:");
        for (int i = 0; i < this.myContacts.size(); i++) {
            System.out.printf("%d. %s -> %s%n", i+1, this.myContacts.get(i).getName(), this.myContacts.get(i).getPhoneNumber());
        }
    }
}


