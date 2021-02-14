public class Contact {
    protected String name;
    protected String number;

    public Contact(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return this.name;
    }

    public String getNumber() {
        return this.number;
    }

    public Contact parseContact(String record) {
        String[] contactSeg = record.split(" ");
        String newName = contactSeg[0].replace(":", "");
        String newNumber = contactSeg[1];
        return new Contact(newName, newNumber);
    }

    @Override
    public String toString() {
        return name + ": " + number;
    }
}
