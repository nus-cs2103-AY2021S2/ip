package duke;

public class ListContactCommand extends Command {
    private ContactList contactList;

    public ListContactCommand(String command, String input, TaskList taskList, ContactList contactList) {
        super(command, input, taskList);
        this.contactList = contactList;
    }

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

    @Override
    public String execute() {
        return listContact();
    }
}
