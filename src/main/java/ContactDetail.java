/**
 * Class that takes care of contact details of a Task.
 */

public class ContactDetail {
    private String contactName;
    private int contactNumber;

    /**
     * Constructor for ContactDetail
     */
    public ContactDetail() {
        this.contactName = "";
        this.contactNumber = 000;
    }

    /**
     * Adds contact details to a Task
     * @param contactName Name of the contact
     * @param contactNumber Number of the contact
     */
    public void addContactDetails(String contactName, int contactNumber) {
        this.contactName = contactName;
        this.contactNumber = contactNumber;
    }

    /**
     * Get contact details of a Task
     * @return String representation of contact details
     */
    public String getContactDetails() {
        if (!hasContactDetails()) {
            return "No details of the contact";
        }
        return this.contactName + " (" + this.contactNumber + ")";
    }

    /**
     * Check whether the task has contact details
     * @return Boolean regarding the existence of contactdetails
     */

    public boolean hasContactDetails() {
        return !this.contactName.isEmpty();
    }
}
