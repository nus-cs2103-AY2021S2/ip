import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {

    public static Command parse(String string) throws DukeException {

        String action = "", info = "", time = "", temp = "";
        Command command = null;

        if (string.contains(" ")){
            String[] str = string.split(" ", 2);
            action = str[0];
            temp = str[1];
            if (temp.contains(" /")) {
                str = temp.split(" /", 2);
                info = str[0];
                str = str[1].split(" ", 2);
                LocalDate date = LocalDate.parse(str[1]);
                time = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            } else {
                info = temp;
            }
        } else {
            action = string;
        }

        if (action.equals("todo") || action.equals("deadline") || action.equals("event")) {
            command = new AddCommand(action, info, time);
        } else if (action.equals("done")) {
            command = new DoneCommand(action, info, time);
        } else if (action.equals("delete")) {
            command = new DeleteCommand(action, info, time);
        } else if (action.equals("bye")) {
            command = new ExitCommand(action, info, time);
        } else if (action.equals("list")) {
            command = new ListCommand(action, info, time);
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        return command;
    }

}
