import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * A class which contains list of user interfaces.
 */
public class Ui {
    /**
     * Say bye when the user logouts.
     */
    public void bye() throws IOException {
        System.out.println("    ____________________________________________________________\n     " +
                "Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________\n");
        Storage.save();
    }

    /**
     * Welcome the user when login.
     */
    public void greet() {
        System.out.println("    ____________________________________________________________\n     " +
                " ____        _        \n" +
                "     |  _ \\ _   _| | _____ \n" +
                "     | | | | | | | |/ / _ \\\n" +
                "     | |_| | |_| |   <  __/\n" +
                "     |____/ \\__,_|_|\\_\\___|\n\n     " +
                "Hello! I'm Duke :P");
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
            throw new DukeException("\n    ____________________________________________________________\n" +
                    "     ☹ OOPS!!! The description of a todo cannot be empty.\n" +
                    "    ____________________________________________________________");
        }
    }

    /**
     * Add Event object of the given description to the list.
     *
     * @param desc Description of the event.
     * @param at   Date of the event.
     * @param time Time of the event.
     * @throws DukeException If an invalid command is given by the user. It also happens when there's lack of
     *                       information when task is created such as no description, date and time.
     */
    public void event(String desc, LocalDate at, LocalTime time) throws DukeException {
        if (desc.equals("")) {
            throw new DukeException("\n    ____________________________________________________________\n" +
                    "     ☹ OOPS!!! The description, date & time of an event cannot be empty.\n" +
                    "    ____________________________________________________________");
        }
        if (at == null) {
            throw new DukeException("\n    ____________________________________________________________\n" +
                    "     ☹ OOPS!!! The date & time of an event cannot be empty.\n" +
                    "    ____________________________________________________________");
        }
        if (time == null) {
            throw new DukeException("\n    ____________________________________________________________\n" +
                    "     ☹ OOPS!!! The time of an event cannot be empty.\n" +
                    "    ____________________________________________________________");
        }
        TaskList.addTask(new Event(desc, at, time));
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
            throw new DukeException("\n    ____________________________________________________________\n" +
                    "     ☹ OOPS!!! The description, date & time of a deadline cannot be empty.\n" +
                    "    ____________________________________________________________");
        }
        if (by == null) {
            throw new DukeException("\n    ____________________________________________________________\n" +
                    "     ☹ OOPS!!! The date & time of a deadline cannot be empty.\n" +
                    "    ____________________________________________________________");
        }
        if (time == null) {
            throw new DukeException("\n    ____________________________________________________________\n" +
                    "     ☹ OOPS!!! The time of a deadline cannot be empty.\n" +
                    "    ____________________________________________________________");
        }
        TaskList.addTask(new Deadline(desc, by, time));
    }
}
