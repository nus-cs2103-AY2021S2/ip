public class ContactDetail {
    private String contactName;
    private int contactNumber;

    public ContactDetail() {
        this.contactName = "";
        this.contactNumber = 000;
    }

    public ContactDetail(String contactName, int contactNumber) {
        this.contactName = contactName;
        this.contactNumber = contactNumber;
    }

    public void addContactDetails(String contactName, int contactNumber) {
        this.contactName = contactName;
        this.contactNumber = contactNumber;
    }

    public String getContactDetails() {
        if (!hasContactDetails()) {
            return "No details of the contact";
        }
        return this.contactName + " (" + this.contactNumber + ")";
    }

    public boolean hasContactDetails() {
        return !this.contactName.isEmpty();
    }
}
