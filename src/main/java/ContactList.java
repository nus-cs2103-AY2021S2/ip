import java.util.ArrayList;

public class ContactList {
    protected ArrayList<Contact> contactList;
    protected Ui ui;

    public ContactList(ArrayList<Contact> contactList, Ui ui) {
        this.contactList = contactList;
        this.ui = ui;
    }

    public int numberOfContacts() {
        return contactList.size();
    }

    public String addContacts(String newContact) {
        String description = newContact.substring(8);
        String[] contactSeg = description.split(" ");
        Contact myContact = new Contact(contactSeg[0], contactSeg[1]);
        contactList.add(myContact);
        return ui.addContactConfirmMessage(myContact.toString());
    }

    public String getContacts() {
        if (contactList.size() == 0) {
            return ui.noContact();
        } else {
            return ui.listingContactsTitle(numberOfContacts())
                    + ui.printContacts(contactList);
        }
    }

    public String remove(Integer index) {
        String removedContact = contactList.get(index - 1).toString();
        contactList.remove(index - 1);
        return ui.removeContactConfirmMessage(removedContact);
    }

    public String searchContact(String name) {
        ArrayList<Contact> searchResults = new ArrayList<>();
        if (contactList.size() == 0) {
            return ui.noContact();
        } else {
            contactList.forEach((c) -> {
                if (c.toString().contains(name)) {
                    searchResults.add(c);
                }
            });
        }

        if (searchResults.size() == 0) {
            return ui.noMatchingContactMessage();
        } else {
            return ui.matchingContactsTitle(searchResults.size()) + ui.printContacts(searchResults);
        }
    }
}
