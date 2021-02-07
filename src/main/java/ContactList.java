import java.util.ArrayList;

public class ContactList {
    private final ArrayList<Contact> contactList;

    public ContactList() {
        this.contactList = new ArrayList<>();
    }

    public ContactList(ArrayList<Contact> contactList) {
        this.contactList = contactList;
    }

    public int getSize() {
        if (contactList.isEmpty()) {
            return 0;
        } else {
            return contactList.size();
        }
    }

    public ArrayList<Contact> getContactList() {
        return contactList;
    }

    public void add(Contact contact) {
        contactList.add(contact);
    }

    public void delete(int itemNum) {
        contactList.remove(itemNum);
    }

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
