package duke;

public class Contact {
    private String name;
    private int number;
    private String address;

    public Contact(String name, int number, String address) {
        this.name = name;
        this.number = number;
        this.address = address;
    }

    /**
     * Changes the name of the contact.
     *
     * @param newName Changed name.
     */
    public void changeName(String newName) {
        name = newName;
    }

    /**
     * Changes the number of the contact.
     *
     * @param newNumber Changed number.
     */
    public void changeNumber(int newNumber) {
        number = newNumber;
    }

    /**
     * Changes the address of the contact.
     *
     * @param newAddress Changed address.
     */
    public void changeAddress(String newAddress) {
        address = newAddress;
    }

    /**
     * Returns the contact in a format that can be saved by the Storage.
     *
     * @return String of contact in the format to be saved.
     */
    public String formatToSave() {
        return String.format("/name %s /number %d /address %s", name, number, address);
    }

    /**
     * Returns a String that has been formatted which contains the information of the Contact.
     * String is formatted into a form to be printed by the Ui.
     *
     * @return String to be printed by Ui.
     */
    @Override
    public String toString() {
        if (number == 0) {
            return String.format("%s [Address: %s]", name, address);
        }
        if (address.equals("")) {
            return String.format("%s [Num: %d]", name, number);
        }
        return String.format("%s [Num: %d] [Address: %s]", name, number, address);
    }
}
