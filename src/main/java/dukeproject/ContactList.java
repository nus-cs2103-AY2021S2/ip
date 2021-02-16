package dukeproject;

import java.util.ArrayList;

/**
 * Represents a list of contacts for the user.
 * A contact list object contains a list of contact object which supports methods for
 * adding, removing and getting the contact information.
 */
public class ContactList {

    private final ArrayList<Contact> contacts;

    public ContactList() {
        this.contacts = new ArrayList<>();
    }

    /**
     * Constructor to create an initial list of contacts based on the contents given.
     * @param contents Represent contents of the contact list to be added.
     * @throws DukeException If any error occurs.
     */
    public ContactList(ArrayList<String> contents) throws DukeException {
        this.contacts = new ArrayList<>();

        try {
            for (String contact: contents) {
                String[] keyValuePairs = contact.split(",");
                String fullName = keyValuePairs[0].split("=")[1];
                String emailAddress = keyValuePairs[1].split("=")[1];
                int contactNumber = Integer.parseInt(keyValuePairs[2].split("=")[1]);
                addContact(fullName, emailAddress, contactNumber);
            }
        } catch (Exception ex) {
            throw new DukeException();
        }

    }

    public void addContact(String fullName, String emailAddress, int contactNumber) {
        contacts.add(new Contact(fullName, emailAddress, contactNumber));
    }

    public void removeContact(int index) {
        contacts.remove(index);
    }

    public Contact getContact(int index) {
        return contacts.get(index);
    }

    public int getContactsSize() {
        return contacts.size();
    }
}
