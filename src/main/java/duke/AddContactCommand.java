package duke;

public class AddContactCommand extends Command {

    private ContactList contactList;

    public AddContactCommand(String command, String input, TaskList taskList, ContactList contactList) {
        super(command, input, taskList);
        this.contactList = contactList;
    }

    /**
     * Checks if the user input is formatted into a correct Add Contact command.
     * If it is, it adds the contact and prints the Add Contact message.
     * Otherwise, it prints the exception faced.
     *
     * @return Message to be printed.
     */
    private String add() {
        try {
            if (parser.canParseAddContactCommand(input)) {
                String name = parser.parseAddContactName(input);
                int number = parser.parseAddContactNumber(input);
                String address = parser.parseAddContactAddress(input);
                Contact contact = new Contact(name, number, address);
                contactList.addContact(contact);
                return ui.getAddContact(contact);
            } else {
                throw new WrongFormatDukeException(command);
            }
        } catch (DukeException e) {
            return ui.getError(e);
        }
    }

    /**
     * Executes the add contact command.
     *
     * @return Message of command.
     */
    @Override
    public String execute() {
        return add();
    }
}
