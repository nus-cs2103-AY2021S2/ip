public class Contact {

    /**
     * The name of the contact.
     */
    protected String name;
    /**
     * The phone number of the contact.
     */
    protected String phoneNumber;

    /**
     * Creates a new contact with a given input by the user.
     *
     * @param name        The name of the contact.
     * @param phoneNumber the phone number of the contact.
     */
    public Contact(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    /**
     * A toString to show the contact information.
     *
     * @return Show the name, followed by the phone number.
     */
    @Override
    public String toString() {
        return this.name + ": " + this.phoneNumber;
    }
}
