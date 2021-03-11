package duke;

public class DeleteContactCommand extends Command {
    private ContactList contactList;

    public DeleteContactCommand(String command, String input, TaskList taskList, ContactList contactList) {
        super(command, input, taskList);
        this.contactList = contactList;
    }

    /**
     * Checks if the user input is formatted into a correct Delete Contact command.
     * If it is, it removes the contact and prints the Delete Contact message.
     * Otherwise, it prints the exception faced.
     *
     * @return Message to print.
     */
    private String delete() {
        try {
            int size = contactList.getSize();
            if (parser.canParseContactDeleteCommand(input, size)) {
                int index = parser.parseContactDeleteCommand(input, size);
                assert index <= 0 && index < size : "Index out of bounds";
                Contact deleted = contactList.deleteContact(index);
                return ui.getDeleteContact(deleted);
            } else {
                throw new WrongFormatDukeException(command);
            }
        } catch (DukeException e) {
            return ui.getError(e);
        }
    }

    /**
     * Executes the delete command.
     *
     * @return Message of command.
     */
    @Override
    public String execute() {
        return delete();
    }
}
