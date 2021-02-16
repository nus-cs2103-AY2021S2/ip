package duke;

import java.util.ArrayList;

public class ContactList {
    private ArrayList<Contact> contactList;

    public ContactList() {
        this.contactList = new ArrayList<>();
    }

    /**
     * Adds a contact to the contact list.
     *
     * @param contact Contact to be added.
     */
    public void addContact(Contact contact) {
        contactList.add(contact);
    }

    /**
     * Deletes a contact from the contact list.
     *
     * @param index Index of contact to be deleted.
     * @return Deleted contact.
     */
    public Contact deleteContact(int index) {
        Contact removed = contactList.remove(index);
        return removed;
    }

    /**
     * Replaces the contact at index with the contact provided.
     *
     * @param contact Contact to be added.
     * @param index Index of contact to be removed.
     */
    public void editContact(Contact contact, int index) {
        contactList.add(index, contact);
        contactList.remove(index + 1);
    }

    /**
     * Gets the size of the contact list.
     *
     * @return Size of contact list.
     */
    public int getSize() {
        return contactList.size();
    }

    /**
     * Gets the contact at the index.
     *
     * @param index Index of contact to be fetched.
     * @return Contact at index.
     */
    public Contact getContact(int index) {
        return contactList.get(index);
    }
}
