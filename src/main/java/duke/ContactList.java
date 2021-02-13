package duke;

import java.util.ArrayList;

public class ContactList {
    private ArrayList<Contact> contactList;

    public ContactList() {
        this.contactList = new ArrayList<>();
    }

    public void addContact(Contact contact) {
        contactList.add(contact);
    }

    public Contact deleteContact(int index) {
        Contact removed = contactList.remove(index);
        return removed;
    }

    public void editContact(Contact contact, int index) {
        contactList.add(index, contact);
        contactList.remove(index + 1);
    }
}
