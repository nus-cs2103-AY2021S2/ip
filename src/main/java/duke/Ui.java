package duke;

import java.time.LocalDate;

class Ui {

    /**
     * displays the welcome message
     */
    static String welcome() {
        return "    ____________________________________________________________\n\n"

            + "      Hello! I'm Duke\n"
            + "      What can I do for you?\n"
            + "    ____________________________________________________________\n\n";

    }

    /**
     * displays the message when an invalid input is given
     */
    static String invalidInput() {
        return "    ____________________________________________________________\n"

            + "      ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n"
            + "    ____________________________________________________________\n\n";

    }

    /**
     * displays the message when the task has an empty description
     * @param s name of task
     */
    static String emptyDescription(String s) {
        return "    ____________________________________________________________\n\n"

            + "      ☹ OOPS!!! The description of a " + s + " cannot be empty.\n"
            + "    ____________________________________________________________\n\n";

    }

    /**
     * displays the message when the task is stored
     * @param t task that was stored
     * @param n the number of tasks in the list
     */
    static String store(Task t, int n) {
        return "    ____________________________________________________________\n\n"

            + "      Got it. I've added this task: \n"
            + "      " + t + "\n"
            + "      now you have " + n + " tasks in the list.\n"
            + "    ____________________________________________________________\n\n";

    }

    /**
     * displays the message when the tasks are listed
     * @param t the given TaskList
     */
    static String list(TaskList t) {
        return "    ____________________________________________________________\n\n"

            + "      Here are the tasks in your list:\n"
            + t.toString()
            + "    ____________________________________________________________\n\n";

    }

    /**
     * displays the message when the task is marked as done
     * @param t the Task to be marked as done
     */
    static String done(Task t) {
        return "    ____________________________________________________________\n\n"

            + "      Nice! I've marked this task as done:\n"
            + "      " + t + "\n"
            + "    ____________________________________________________________\n\n";

    }

    /**
     * displays the message when the task is to be deleted
     * @param t the Task to be deleted
     * @param n the size of TaskList after deletion
     */
    static String delete(Task t, int n) {
        return "    ____________________________________________________________\n\n"

            + "      Noted. I've removed this task:\n"
            + "      " + t + "\n"
            + "      now you have " + n + " tasks in the list.\n"
            + "    ____________________________________________________________\n\n";

    }

    /**
     * displays the message showing the Tasks on the given day
     * @param t the given TaskList
     * @param s the day that is given in yyy-mm-dd format (e.g. 2021-01-31)
     */
    static String tasksOnDay(TaskList t, String s) {
        LocalDate day = LocalDate.parse(s);
        return "    ____________________________________________________________\n\n"

            + "      Here are the tasks on " + day.toString() + ":\n"
            + t.tasksOnDay(s)
            + "    ____________________________________________________________\n\n";

    }

    static String find(TaskList t, String s) {
        return "    ____________________________________________________________\n\n"

            + "      Here are the matching tasks in your list:\n"
            + t.find(s) + "\n"
            + "    ____________________________________________________________\n\n";

    }

    static String clear() {
        return "    ____________________________________________________________\n\n"

            + "      The list has been cleared\n"
            + "    ____________________________________________________________\n\n";

    }

    static String bye() {
        return "    ____________________________________________________________\n\n"

            + "      Bye. Hope to see you again soon!\n"
            + "    ____________________________________________________________\n\n";

    }
}
