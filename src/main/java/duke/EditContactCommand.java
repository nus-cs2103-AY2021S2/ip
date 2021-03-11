package duke;

public class EditContactCommand extends Command {
    private ContactList contactList;

    public EditContactCommand(String command, String input, TaskList taskList, ContactList contactList) {
        super(command, input, taskList);
        this.contactList = contactList;
    }

    /**
     * Checks if the user input is formatted into a correct Edit Contact command.
     * If it is, it edits the contact and prints the Edit Contact message.
     * Otherwise, it prints the exception faced.
     *
     * @return Message to be printed.
     */
    private String edit() {
        try {
            int size = contactList.getSize();
            if (parser.canParseEditContactCommand(input, size)) {
                int index = parser.parseContactEditIndex(input, size);
                String field = parser.parseContactEditField(input);
                String change = parser.parseContactEditChange(input);
                Contact contact = contactList.getContact(index);
                if (field.equals("/name")) {
                    contact.changeName(change);
                }
                if (field.equals("/number")) {
                    int number = Integer.valueOf(change);
                    contact.changeNumber(number);
                }
                if (field.equals("/address")) {
                    contact.changeAddress(change);
                }
                return ui.getEditContact(contact);
            } else {
                throw new WrongFormatDukeException(command);
            }
        } catch (DukeException e) {
            return ui.getError(e);
        }
    }

    /**
     * Executes the edit command.
     *
     * @return Message of edit command.
     */
    @Override
    public String execute() {
        return edit();
    }
}
