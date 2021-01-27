import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {
    // todo: try this https://github.com/se-edu/addressbook-level3/blob/master/src/main/java/seedu/address/logic/parser/ParserUtil.java
    // why do these methods have to be static??

    // used for delete and done commands
    static int parseIntParameter(String command) {
        return Integer.parseInt(command.split(" ")[1]);
    }

    static String parseParameter(String command, String regex, int index) {
        return command.split(regex, 2)[index].trim();
    }

    static LocalDateTime parseDeadlineParameter(String dateTime) {
        LocalDateTime deadline = LocalDateTime.parse(dateTime.trim(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        return deadline;
    }

    static int parseEventParameter(String command) {
        return Integer.parseInt(command.split(" ")[1]);
    }
}
