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

    public Contact(String name, int number) {
        this.name = name;
        this.number = number;
        this.address = null;
    }

    public Contact(String name, String address) {
        this.name = name;
        this.number = 0;
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
