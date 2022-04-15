import java.util.ArrayList;

public class Ui {


    /**
     * Greets the user when the Duke is launched.
     *
     * @return A string showing correct GUI output.
     */
    public String showWelcomeMsg() {
        return "Hello! I'm Jay!\n" + "What can I do for you?";
    }

    /**
     * Show the user that a todo task has been added.
     *
     * @param tasks The Task Arraylist containing user tasks in sequence.
     */
    public String showTodoMsg(TaskList tasks) {
        assert tasks.getSize() >= 0 : tasks.getSize();
        return "Got it. I've added this task:\n" + "    " + tasks.getTaskList().get(tasks.getSize() - 1).toString()
                + "\n" + "Now you have " + tasks.getSize() + " tasks in the list.";
    }

    /**
     * Show the user that a deadline task has been added.
     *
     * @param tasks The Task Arraylist containing user tasks in sequence.
     * @return A string showing correct GUI output.
     */
    public String showDeadlineMsg(TaskList tasks) {
        return "Got it. I've added this task:\n" + "    " + tasks.getTaskList().get(tasks.getSize() - 1).toString()
                + "\n" + "Now you have " + tasks.getSize() + " tasks in the list.";
    }

    /**
     * Show the user that a event task has been added.
     *
     * @param tasks The Task Arraylist containing user tasks in sequence.
     * @return A string showing correct GUI output.
     */
    public String showEventMsg(TaskList tasks) {
        return "Got it. I've added this task:\n" + "    " + tasks.getTaskList().get(tasks.getSize() - 1).toString()
                + "\n" + "Now you have " + tasks.getSize() + " tasks in the list.";
    }

    /**
     * Show the user that a task of choice has been deleted.
     *
     * @param taskRemoved The description of the task deleted.
     * @param totalTasks  Total number of tasks in the list.
     * @return A string showing correct GUI output.
     */
    public String showDeleteTaskMsg(String taskRemoved, int totalTasks) {
        return "Noted. I've removed this task:\n" + "    " + taskRemoved + "\n"
                + "Now you have " + totalTasks + " tasks in the list.";
    }

    /**
     * Show the user that a task has been marked done.
     *
     * @param tasks   The Task Arraylist containing user tasks in sequence.
     * @param itemNum The item number that is marked done.
     * @return A string showing correct GUI output.
     */
    public String showDoneMsg(TaskList tasks, int itemNum) {
        return "Nice! I've marked this task as done:\n" + "    " + tasks.getTaskList().get(itemNum - 1).toString();
    }

    /**
     * Show the user the list of tasks.
     *
     * @param tasks The Task Arraylist containing user tasks in sequence.
     * @return A string showing correct GUI output.
     */
    public String showTaskListMsg(TaskList tasks) {
        return "Here are the tasks in your list:" + "\n" + tasks.toString();
    }

    public String showContactListMsg(ContactList contacts) {
        return "Here are the contacts in your list:" + "\n" + contacts.toString();
    }

    /**
     * Show the user the list of tasks with matching keyword.
     *
     * @param keyword The keyword to search for match.
     * @param tasks   The Task Arraylist containing user tasks in sequence.
     * @return A string showing correct GUI output.
     */
    public String showFindMsg(String keyword, TaskList tasks) {
        ArrayList<Task> matchingTasks = tasks.search(keyword);
        TaskList matchedTasks = new TaskList(matchingTasks);
        return "Here are the matching tasks in your list:" + "\n" + matchedTasks.toString();
    }

    /**
     * Show the user that a contact has been added to the list.
     *
     * @param contacts The Contact Arraylist containing user contacts in sequence.
     * @return A string showing the correct GUI output.
     */
    public String showAddContactMsg(ContactList contacts) {
        return "Got it. I've added this contact:\n" + "    "
                + contacts.getContactList().get(contacts.getSize() - 1).toString() + "\n" + "Now you have "
                + contacts.getSize() + " contacts in the list.";
    }

    /**
     * Show the user that a contact has been deleted from the list.
     *
     * @param contactRemoved The detail of the contact deleted.
     * @param totalContacts  The total number of contacts in the list.
     * @return A string showing the correct GUI output.
     */
    public String showDeleteContactMsg(String contactRemoved, int totalContacts) {
        return "Noted. I've removed this contact:\n" + "    " + contactRemoved + "\n" + "Now you have "
                + totalContacts + " contacts in the list.";
    }

    /**
     * Show the uer that a contact has been updated.
     *
     * @param contacts The Contact Arraylist containing user contacts in sequence.
     * @return A string showing the correct GUI output.
     */
    public String showUpdateContactMsg(ContactList contacts) {
        return "Got it. I've updated this contact:\n" + "    "
                + contacts.getContactList().get(contacts.getSize() - 1).toString();
    }
}
