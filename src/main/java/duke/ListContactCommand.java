package duke;

public class ListContactCommand extends Command {
    private ContactList contactList;

    public ListContactCommand(String command, String input, TaskList taskList, ContactList contactList) {
        super(command, input, taskList);
        this.contactList = contactList;
    }

    /**
     * Checks if the user input is formatted into a correct List Contact command.
     * If it is, it prints the List Contact message.
     * Otherwise, it prints the exception faced.
     *
     * @return Message to be printed.
     */
    private String listContact() {
        try {
            if (parser.canParseListContactCommand(input)) {
                return ui.getContactList(contactList);
            } else {
                throw new WrongFormatDukeException(command);
            }
        } catch (DukeException e) {
            return ui.getError(e);
        }
    }

    /**
     * Executes list contact command.
     *
     * @return Message of command.
     */
    @Override
    public String execute() {
        return listContact();
    }
}
