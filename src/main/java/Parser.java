import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {

    public static String parsedCommand(String command) {
        return command.split(" ")[0];
    }

    public static int parseDoneIndex(String command) {
        return Integer.parseInt(command.split(" ")[1]) - 1;
    }

    public static LocalDateTime DateTimeParser(String input) {
        return LocalDateTime.parse(input,
                DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
    }

    public static String parseTypeOfTask(String task) {
        String[] split = task.split("\\|");
        String typeOfTask = split[0].trim();
        return typeOfTask;
    }

    public static String parseCompletionStatus(String task) {
        String[] split = task.split("\\|");
        String isDone = split[1].trim();
        return isDone;
    }

    public static String parseName(String task) {
        String[] split = task.split("\\|");
        String name = split[2].trim();
        return name;
    }

    public static LocalDateTime parseTime(String task) {
        String[] split = task.split("\\|");
        String typeOfTask = split[0].trim();
        LocalDateTime time = typeOfTask.equals("T")
                             ? Parser.DateTimeParser("01-01-2001 0101")
                             : Parser.DateTimeParser(split[3].trim());
        return time;
    }

}

