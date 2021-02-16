package duke;

/**
 * Class to give all Strings to the GUI.
 */

public class Ui {

    /**
     * Gets the greeting message.
     *
     * @return String greeting message.
     */
    public String getGreeting() {
        String str = "Hello I am LukBot!" +
                "\nYou may type the command 'help' to see my list of commands." +
                "\nHow may I assist you?";
        return str;
    }

    /**
     * Gets the goodbye message when Duke is exited.
     *
     * @return String goodbye message.
     */
    public String getFarewell() {
        String str = "Farewell sir/ma'am. I hope to see you again soon.";
        return str;
    }

    /**
     * Gets the string of a task.
     *
     * @param task Task wanted.
     * @return String of a task.
     */
    private String getTask(Task task) {
        return task.toString();
    }

    /**
     * Gets the message for when a task is added to the list.
     *
     * @param task Task to be added.
     * @param size Number of tasks currently in the list.
     * @return String of add task message.
     */
    public String getAddTask(Task task, int size) {
        String str = "I have added the following task to your list:\n" +
                getTask(task) +
                String.format("\nThere are %d tasks in your list. I hope this pleases you.", size);
        return str;
    }

    /**
     * Gets the done message for when a task is marked as done.
     *
     * @param task Task to be marked as done.
     * @return String of done task message.
     */
    public String getDoneTask(Task task) {
        String str = "Congratulations on conquering this task:\n" +
                getTask(task) +
                "\nYou are one step closer to victory";
        return str;
    }

    /**
     * Gets the message for when a task is being removed from the list.
     *
     * @param task Task to be removed.
     * @return String of remove task message.
     */
    public String getDeleteTask(Task task) {
        String str = "I have removed the following task from your list:\n" +
                getTask(task) +
                "\nDoes this bring you the satisfaction you so crave?";
        return str;
    }

    /**
     * Gets the message for when a task list is to be listed.
     *
     * @param taskList Task list to be listed.
     * @return String of task list.
     */
    public String getTaskList(TaskList taskList) {
        String str = "Here are the tasks in your list:\n";
        for (int i = 1; i <= taskList.getSize(); i++) {
            str += String.format("%d. ", i) +
                    taskList.getTask(i - 1).toString() +
                    "\n";
        }
        str += "Do what you will with this information.";
        return str;
    }

    /**
     * Gets the error message.
     *
     * @param e Exception that has occurred.
     * @return String of error message.
     */
    public String getError(Exception e) {
        return e.toString();
    }

    /**
     * Gets the help message.
     *
     * @return String of help message.
     */
    public String getHelp() {
        String str = "These are the following commands that I understand:" +
                "\n'help' (shows the list of commands)" +
                "\n'list' (shows the list of tasks you have on your list)" +
                "\n'todo (task description)' (adds a To Do task to your list)" +
                "\n'deadline (task description) /by (DD/MM/YYYY TIME)' (adds a Deadline task to your list)" +
                "\n'event (task description) /from (DD/MM/YYYY TIME) /to (DD/MM/YYYY TIME)' (adds an Event task to your list)" +
                "\n'done (valid index)' (checks a task on your list)" +
                "\n'delete (valid index)' (removes a task from your list)" +
                "\n'find (keyword)' (searches for all tasks in your list containing the keyword)" +
                "\n'contact add /name (name) /number (number) /address (address)' (name or address can be omitted)" +
                "\n'contact delete (index)' (removes contact)" +
                "\n'contact edit (index) (/name OR /number OR /address) (desired change)' (edits the contact)" +
                "\n'contact list' (lists all contacts)" +
                "\n'bye' (exits Duke)" +
                "\nDo omit the parentheses in the actual command." +
                "\nI pray that I will be of use to you.";
        return str;
    }

    /**
     * Returns string of the tasks that have been found to be given to GUI.
     *
     * @param taskList Task list that only contains found tasks.
     * @return String of found tasks to be given to GUI.
     */
    public String getFoundTaskList(TaskList taskList) {
        String str = "Here are the tasks that fit your search criteria:\n";
        for (int i = 1; i <= taskList.getSize(); i++) {
            str += String.format("%d. ", i) +
                    taskList.getTask(i - 1).toString() + "\n";
        }
        str += "I can only pray that I have been of use to you.";
        return str;
    }

    /**
     * Gets string of contact.
     *
     * @param contact Contact.
     * @return String of contact.
     */
    private String getContact(Contact contact) {
        return contact.toString();
    }

    /**
     * Gets the add contact message.
     *
     * @param contact Contact to add.
     * @return String of Add Contact message.
     */
    public String getAddContact(Contact contact) {
        String str = "I have added a new companion to your list of allies:\n" +
                getContact(contact) +
                "\nKeep your friends close, and your enemies closer.";
        return str;
    }

    /**
     * Gets the string when a contact list is to be listed.
     *
     * @param contactList Contact list to be listed.
     * @return String of contacts in the list.
     */
    public String getContactList(ContactList contactList) {
        String str = "Here are your reliable comrades:\n";
        for (int i = 1; i <= contactList.getSize(); i++) {
            str += String.format("%d. ", i) +
                    contactList.getContact(i - 1).toString() +
                    "\n";
        }
        str += "Do not fear to call upon their friendship.";
        return str;
    }

    /**
     * Gets the string when a contact is to be deleted.
     *
     * @param contact Contact to be deleted.
     * @return String of delete contact command.
     */
    public String getDeleteContact(Contact contact) {
        String str = "Friends turn to foes in a blink of an eye. I shall remove:\n" +
                getContact(contact) +
                "\nI pray for your safety.";
        return str;
    }

    public String getEditContact(Contact contact) {
        String str = "Your mistake has been forgiven. Do check if this is the correct contact:\n" +
                getContact(contact) +
                "\nFeel free to make as many mistakes as you please. I am here to resolve all.";
        return str;
    }
}