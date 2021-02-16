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

    public Contact changeName(String newName) {
        return new Contact(newName, number, address);
    }

    public Contact changeNumber(int newNumber) {
        return new Contact(name, newNumber, address);
    }

    public Contact changeAddress(String newAddress) {
        return new Contact(name, number, newAddress);
    }

    /**
     * Returns the contact in a format that can be saved by the Storage.
     *
     * @return String of contact in the format to be saved.
     */
    public String formatToSave() {
        return String.format("/name %s /number %d /address %s", name, number, address);
    }

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
