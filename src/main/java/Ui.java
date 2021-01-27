import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * A class which contains list of user interfaces.
 */
public class Ui {
    /**
     * Finds task and print out the details of the tasks that is related to the keyword provided.
     *
     * @param keyword Parts of the description of the task that a user wants to find.
     */
    public void find(String keyword) {
        String msg = "";
        int num = 1;
        for (int i = 0; i < TaskList.tasks.size(); i++) {
            Task task = TaskList.tasks.get(i);
            String word = "";
            for (int j = 0; j < task.description.length(); j++) {
                if (task.description.charAt(j) == ' ') {
                    if (word.equals(keyword)) {
                        msg += "     " + num + "." + task + "\n";
                        num++;
                    }
                    word = "";
                    continue;
                } else {
                    word += task.description.charAt(j);
                    if (j == task.description.length() - 1) {
                        if (word.equals(keyword)) {
                            msg += "     " + num + "." + task + "\n";
                            num++;
                        }
                    }
                }
            }
        }
        if (num == 1) {
            msg = "    ____________________________________________________________\n"
                    + "     You have no matching tasks in your list :(\n"
                    + "    ____________________________________________________________";
        } else {
            msg = "    ____________________________________________________________\n"
                    + "     Here are the matching tasks in your list:\n"
                    + msg + "    ____________________________________________________________";
        }
        System.out.println(msg);
    }

    /**
     * Say bye when the user logouts.
     */
    public void bye() throws IOException {
        System.out.println("    ____________________________________________________________\n     "
                + "Bye. Hope to see you again soon!\n"
                + "    ____________________________________________________________");
        Storage.save();
    }

    /**
     * Welcome the user when login.
     */
    public void greet() {
        System.out.println("    ____________________________________________________________\n     "
                + " ____        _        \n"
                + "     |  _ \\ _   _| | _____ \n"
                + "     | | | | | | | |/ / _ \\\n"
                + "     | |_| | |_| |   <  __/\n"
                + "     |____/ \\__,_|_|\\_\\___|\n\n     "
                + "Hello! I'm Duke :P");
    }

    /**
     * Add ToDo object of the given description to the list.
     *
     * @param desc Description of what to be done.
     * @throws DukeException If an invalid command is given by the user. It also happens when there's lack of
     *                       information when task is created such as no description, date and time.
     */
    public void todo(String desc) throws DukeException {
        if (desc.length() > 0) {
            TaskList.addTask(new ToDo(desc));
        } else {
            throw new DukeException("\n    ____________________________________________________________\n"
                    + "     ☹ OOPS!!! The description of a todo cannot be empty.\n"
                    + "    ____________________________________________________________");
        }
    }

    /**
     * Add Event object of the given description to the list.
     *
     * @param desc  Description of the event.
     * @param at    Date of the event.
     * @param start Start time of the event.
     * @param end End time of the event.
     * @throws DukeException If an invalid command is given by the user. It also happens when there's lack of
     *                       information when task is created such as no description, date and time.
     */
    public void event(String desc, LocalDate at, LocalTime start, LocalTime end) throws DukeException {
        if (desc.equals("")) {
            throw new DukeException("\n    ____________________________________________________________\n"
                    + "     ☹ OOPS!!! The description, date, start and end time of an event cannot be empty.\n"
                    + "    ____________________________________________________________");
        }
        if (at == null) {
            throw new DukeException("\n    ____________________________________________________________\n"
                    + "     ☹ OOPS!!! The date & time of an event cannot be empty.\n"
                    + "    ____________________________________________________________");
        }
        if (start == null) {
            throw new DukeException("\n    ____________________________________________________________\n"
                    + "     ☹ OOPS!!! The start and end time of an event cannot be empty.\n"
                    + "    ____________________________________________________________");
        }
        if (end == null) {
            throw new DukeException("\n    ____________________________________________________________\n"
                    + "     ☹ OOPS!!! The end time of an event cannot be empty.\n"
                    + "    ____________________________________________________________");
        }
        TaskList.addTask(new Event(desc, at, start, end));
    }

    /**
     * Add DeadLine object of the given description to the list.
     *
     * @param desc Description of the deadline.
     * @param by   Date of the deadline.
     * @param time Time of the deadline.
     * @throws DukeException If an invalid command is given by the user. It also happens when there's lack of
     *                       information when task is created such as no description, date and time.
     */
    public void deadline(String desc, LocalDate by, LocalTime time) throws DukeException {
        if (desc.equals("")) {
            throw new DukeException("\n    ____________________________________________________________\n"
                    + "     ☹ OOPS!!! The description, date & time of a deadline cannot be empty.\n"
                    + "    ____________________________________________________________");
        }
        if (by == null) {
            throw new DukeException("\n    ____________________________________________________________\n"
                    + "     ☹ OOPS!!! The date & time of a deadline cannot be empty.\n"
                    + "    ____________________________________________________________");
        }
        if (time == null) {
            throw new DukeException("\n    ____________________________________________________________\n"
                    + "     ☹ OOPS!!! The time of a deadline cannot be empty.\n"
                    + "    ____________________________________________________________");
        }
        TaskList.addTask(new Deadline(desc, by, time));
    }
}
