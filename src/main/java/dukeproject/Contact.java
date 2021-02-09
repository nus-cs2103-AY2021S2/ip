package dukeproject;

public class Contact {
    private String fullName;
    private String emailAddress;
    private int contactNumber;

    Contact(String fullName, String emailAddress, int contactNumber) {
        this.fullName = fullName;
        this.emailAddress = emailAddress;
        this.contactNumber = contactNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public int getContactNumber() {
        return contactNumber;
    }

    @Override
    public String toString() {
        return "fullName=" + fullName
            + ", emailAddress=" + emailAddress
            + ", contactNumber=" + contactNumber;
    }
}
