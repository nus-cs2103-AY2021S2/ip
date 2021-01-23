import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StringParser {
    private static final DateTimeFormatter PRINT_FORMAT =
            DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");
    private static final DateTimeFormatter SCAN_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    public static Command parseCommand(String str, TaskList list) throws DukeException {
        int strLength = str.length();
        if (str.equalsIgnoreCase("bye")) {
            return new ExitCommand();
        } else if (str.equalsIgnoreCase("list")) {
            return new ListCommand(list);
        } else if (str.length() >= 9 && str.substring(0, 9).equalsIgnoreCase("deadline ")) {
            return new AddCommand(str, CommandType.DEADLINE);
        } else if (strLength >= 7 && str.substring(0, 7).equalsIgnoreCase("delete ")) {
            return new DeleteCommand(str);
        } else if (strLength >= 6 && str.substring(0, 6).equalsIgnoreCase("event ")) {
            return new AddCommand(str, CommandType.EVENT);
        } else if (strLength >= 5 && str.substring(0, 5).equalsIgnoreCase("todo ")) {
            return new AddCommand(str, CommandType.TODO);
        } else if (strLength >= 5 && str.substring(0, 5).equalsIgnoreCase("done ")) {
            return new DoneCommand(str);
        } else {
            throw new DukeException("Invalid command");
        }
    }

    public static Task loadData(String command) {
        if (command.charAt(0) == 'T') {
            Todo result = new Todo(command.substring(8));
            if (command.charAt(4) != '0') {
                result.markAsDone();
            }
            return result;
        } else if (command.charAt(0) == 'D') {
            int position = command.indexOf("@@@");
            Deadline result = new Deadline(command.substring(8, position - 1),
                    LocalDateTime.from(PRINT_FORMAT.parse(command.substring(position + 3))));
            if (command.charAt(4) != '0') {
                result.markAsDone();
            }
            return result;
        } else {
            int position = command.indexOf("@@@");
            Event result = new Event(command.substring(8, position - 1),
                    LocalDateTime.from(PRINT_FORMAT.parse(command.substring(position + 3))));
            if (command.charAt(4) != '0') {
                result.markAsDone();
            }
            return result;
        }
    }

    public static String saveData(Task task) {
        if (task.getSaveType() == "T") {
            return task.getSaveType() + " | " + (task.getStatus() ? "1" : "0")
                    + " | " + task.getDescription() + "\n";
        } else {
            return task.getSaveType() + " | " + (task.getStatus() ? "1" : "0")
                    + " | " + task.getDescription() + " @@@" + task.getSaveTime() + "\n";
        }
    }

    public static LocalDateTime parseTime(String str) {
        return LocalDateTime.from(SCAN_FORMAT.parse(str));
    }

    public static String newLiner(String str, int length) {
        StringBuilder resultStr = new StringBuilder();
        while (str.length() > length) {
            resultStr.append(str, 0, length - 1).append("\n");
            str = str.substring(length - 1);
        }
        return resultStr.toString() + str + "\n";
    }

    public static String underlineGenerator(int i) {
        return "_".repeat(i) + "\n";
    }

    public static boolean isBlank(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != ' ') {
                return false;
            }
        }
        return true;
    }
}
