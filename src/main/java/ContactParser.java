import javafx.application.Platform;

public class ContactParser {

    /**
     * Process the user input to make sense for the system.
     *
     * @param nextInput The input given by user.
     * @param contacts  The Contact Arraylist containing user contacts in sequence.
     * @param ui        UI structure to show the user correct message.
     * @return Return the correct logic and GUI output.
     */
    public String processInput(String nextInput, ContactList contacts, Ui ui) {
        String command = nextInput.contains(" ") ? nextInput.split(" ")[0] : nextInput;
        try {
            switch (command.toLowerCase()) {
            case "add":
                return addContact(nextInput, contacts, ui);
            case "delete":
                return deleteContact(nextInput, contacts, ui);
            case "update":
                return updateContact(nextInput, contacts, ui);
            case "list":
                return listContacts(contacts, ui);
            case "exit":
                return exitCommand();
            default:
                return wrongCommand();
            }
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Add a contact.
     *
     * @param userInput The input given by user.
     * @param contacts  The Contact Arraylist containing user contacts in sequence.
     * @param ui        UI structure to show the user correct message.
     * @return A string showing correct GUI output.
     * @throws DukeException Exception thrown if the user input is invalid.
     */
    public String addContact(String userInput, ContactList contacts, Ui ui) throws DukeException {
        if (userInput.length() < 14) {
            throw new DukeException("OOPS!!! Please fill in the full detail of the contact.");
        }

        String name = userInput.split(" ")[1];
        String number = userInput.split(" ")[2];
        contacts.add(new Contact(name, number));

        return ui.showAddContactMsg(contacts);
    }

    /**
     * Delete a contact.
     *
     * @param userInput The input given by user.
     * @param contacts  The Contact Arraylist containing user contacts in sequence.
     * @param ui        UI structure to show the user correct message.
     * @return A string showing correct GUI output.
     * @throws DukeException Exception thrown if the user input is invalid.
     */
    public String deleteContact(String userInput, ContactList contacts, Ui ui) throws DukeException {
        if (userInput.length() < 8) {
            throw new DukeException("OOPS!!! The item number cannot be empty.");
        }

        String[] commandToWords = userInput.split(" ");

        int itemNum = Integer.parseInt(commandToWords[1]);
        if (itemNum > contacts.getSize() || itemNum < 1) {
            throw new DukeException("Item number selected is out of range.");
        }

        String taskRemoved = contacts.getContactList().get(itemNum - 1).toString();
        contacts.getContactList().remove(itemNum - 1);

        return ui.showDeleteContactMsg(taskRemoved, contacts.getSize());
    }

    /**
     * Update an old contact to a new version.
     *
     * @param userInput The input given by user.
     * @param contacts  The Contact Arraylist containing user contacts in sequence.
     * @param ui        UI structure to show the user correct message.
     * @return A string showing correct GUI output.
     * @throws DukeException Exception thrown if the user input is invalid.
     */
    public String updateContact(String userInput, ContactList contacts, Ui ui) throws DukeException {
        if (userInput.length() < 16) {
            throw new DukeException("OOPS!!! Please fill in the full detail of the contact");
        }

        String itemNumStr = userInput.split(" ")[1];
        int itemNum = Integer.parseInt(itemNumStr);
        if (itemNum > contacts.getSize() || itemNum < 1) {
            throw new DukeException("Item number selected is out of range.");
        }
        contacts.delete(itemNum - 1);

        String name = userInput.split(" ")[2];
        String number = userInput.split(" ")[3];
        contacts.add(new Contact(name, number));

        return ui.showUpdateContactMsg(contacts);
    }

    /**
     * List out all user inputs in sequence.
     *
     * @param contacts The Contact Arraylist containing user contacts in sequence.
     * @param ui       UI structure to show the user correct message.
     * @return A string showing correct GUI output.
     */
    public String listContacts(ContactList contacts, Ui ui) {
        return ui.showContactListMsg(contacts);
    }

    /**
     * Tells the user that the input given is invalid.
     *
     * @return A string showing correct GUI output.
     * @throws DukeException Exception thrown if the user input is invalid.
     */
    public String wrongCommand() throws DukeException {
        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * Terminate the GUI when the user decides to.
     *
     * @return Nothing since this command will close the GUI.
     */
    public String exitCommand() {
        Platform.exit();
        return null;
    }
}
