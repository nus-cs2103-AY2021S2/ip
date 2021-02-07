public class Contact {

    protected String name;
    protected String phoneNumber;

    public Contact(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    /**
     * A toString to show the task information.
     *
     * @return Show the status of the task, together with its description.
     */
    @Override
    public String toString() {
        return this.name + ": " + this.phoneNumber;
    }
}
