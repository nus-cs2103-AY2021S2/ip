import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * A class that deals with making sense of the user command.
 */
public class Parser {
    /**
     * Parses the user inputs into commands that the program understands and perform actions accordingly.
     *
     * @param command User inputs.
     * @return Formatted date eg. changes 1/1/2011 to 01/01/2011.
     * @throws DukeException If an invalid command is given by the user. It also happens when there's lack of
     *                       information when task is created such as no description, date and time.
     * @throws IOException   If the named file exists but is a rather than a regular file, does not exist but
     *                       cannot be created, or cannot be opened for any other reason.
     */
    public static void parse(String command) throws DukeException, IOException {
        Ui ui = new Ui();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        if (command.equals("bye")) {
            ui.bye();
            Duke.respond = "Bye bye!";
        } else if (command.equals("list")) {
            TaskList.list();
        } else if (command.substring(0, 4).equals("todo")) {
            assert command.length() >= 6;
            ui.todo(command.substring(5));
        } else if (command.substring(0, 4).equals("done")) {
            assert command.length() >= 6;
            TaskList.done(Integer.parseInt(command.substring(5)));
        } else if (command.substring(0, 4).equals("find")) {
            assert command.length() >= 6;
            ui.find(command.substring(5));
        } else if (command.substring(0, 5).equals("event")) {
            String desc = "";
            String date = "";
            String start = "";
            String end = "";
            String localStart = "";
            String localEnd = "";
            for (int i = 6; i < command.length(); i++) {
                if (command.charAt(i) == ' ' && command.charAt(i + 1) == '/') {
                    break;
                } else {
                    desc += command.charAt(i);
                }
            }
            for (int i = desc.length() + 11; i < command.length(); i++) {
                if (command.charAt(i) == ' ') {
                    break;
                } else {
                    date += command.charAt(i);
                }
            }
            assert desc.length() > 0;
            start = command.substring(desc.length() + 12 + date.length(), desc.length() + 16 + date.length());
            end = command.substring(desc.length() + 17 + date.length(), desc.length() + 21 + date.length());
            if (desc.equals("")) {
                ui.event("", null, null, null);
            } else if (date.equals("")) {
                ui.event(desc, null, null, null);
            } else if (start.equals("")) {
                ui.event(desc, LocalDate.parse(format(date), formatter), null, null);
            } else if (end.equals("")) {
                ui.event(desc, LocalDate.parse(format(date), formatter), LocalTime.parse(localStart), null);
            } else {
                localStart += start.substring(0, 2) + ":" + start.substring(2, 4);
                localEnd += end.substring(0, 2) + ":" + end.substring(2, 4);
                ui.event(desc, LocalDate.parse(format(date), formatter), LocalTime.parse(localStart), LocalTime.parse(localEnd));
            }
        } else if (command.substring(0, 6).equals("delete")) {
            assert command.length() > 8;
            TaskList.delete(Integer.parseInt(command.substring(7)));
        } else if (command.substring(0, 8).equals("deadline")) {
            String desc = "";
            String date = "";
            String time = "";
            String localTime = "";
            for (int i = 9; i < command.length(); i++) {
                if (command.charAt(i) == ' ' && command.charAt(i + 1) == '/') {
                    break;
                } else {
                    desc += command.charAt(i);
                }
            }
            for (int i = desc.length() + 14; i < command.length(); i++) {
                if (command.charAt(i) == ' ') {
                    break;
                } else {
                    date += command.charAt(i);
                }
            }
            assert desc.length() >= 10;
            time = command.substring(desc.length() + 15 + date.length());
            localTime += time.substring(0, 2) + ":" + time.substring(2, 4);
            if (desc.equals("")) {
                ui.deadline("", null, null);
            } else if (date.equals("")) {
                ui.deadline(desc, null, null);
            } else if (time.equals("")) {
                ui.deadline(desc, LocalDate.parse(format(date), formatter), null);
            }
            ui.deadline(desc, LocalDate.parse(format(date), formatter), LocalTime.parse(localTime));
        } else if (command.length() <= 3) {
            Duke.respond = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
            throw (new DukeException("\n    ____________________________________________________________\n"
                    + "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                    + "    ____________________________________________________________"));
        } else {
            Duke.respond = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
            throw (new DukeException("\n    ____________________________________________________________\n"
                    + "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                    + "    ____________________________________________________________"));
        }
    }

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
            Duke.respond = "You have no matching tasks in your list :(\n" + msg;
            msg = "    ____________________________________________________________\n"
                    + "     You have no matching tasks in your list :(\n"
                    + "    ____________________________________________________________";
        } else {
            Duke.respond = "Here are the matching tasks in your list:\n" + msg;
            msg = "    ____________________________________________________________\n"
                    + "     Here are the matching tasks in your list:\n"
                    + msg + "    ____________________________________________________________";
        }
    }

    /**
     * Returns a formatted date of the given date string.
     *
     * @param date Date.
     * @return Formatted date eg. changes 1/1/2011 to 01/01/2011.
     */
    public static String format(String date) {
        if (date.charAt(1) == '/') {
            date = "0" + date;
        }
        if (date.charAt(4) == '/') {
            date = date.substring(0, 3) + "0" + date.substring(3);
        }
        return date;
    }
}
