import java.util.ArrayList;

public class ContactList {
    private final ArrayList<Contact> contactList;

    /**
     * Initialise Contact List with new arrayList.
     */
    public ContactList() {
        this.contactList = new ArrayList<>();
    }

    /**
     * Get size of the list.
     *
     * @return The size of the list.
     */
    public int getSize() {
        if (contactList.isEmpty()) {
            return 0;
        } else {
            return contactList.size();
        }
    }

    /**
     * Get task list.
     *
     * @return Task List.
     */
    public ArrayList<Contact> getContactList() {
        return contactList;
    }

    /**
     * Add contact to the list.
     *
     * @param contact The contact to be added to the list.
     */
    public void add(Contact contact) {
        contactList.add(contact);
    }

    /**
     * Delete contact from the list.
     *
     * @param itemNum The item number corresponding to the contact to be deleted.
     */
    public void delete(int itemNum) {
        contactList.remove(itemNum);
    }

    /**
     * A toString method to display all the contacts in the contact list.
     *
     * @return A string containing all the contacts in the contact list.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 1; i <= contactList.size(); i++) {
            if (i == contactList.size()) {
                result.append(i).append(". ").append(contactList.get(i - 1));
            } else {
                result.append(i).append(". ").append(contactList.get(i - 1)).append("\n");
            }
        }
        return result.toString();
    }
}
