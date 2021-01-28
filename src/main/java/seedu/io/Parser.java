package seedu.io;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {
    // todo: try this https://github.com/se-edu/addressbook-level3/blob/master/src/main/java/seedu/address/logic/parser/ParserUtil.java
    // why do these methods have to be static??

    // used to get int index parameter of delete and done commands
    public static int parseIntParameter(String command) {
        return Integer.parseInt(command.split(" ")[1]);
    }

    // used to get String parameters for todo, deadline and event commands
    public static String parseParameter(String command, String regex, int index) {
        return command.split(regex, 2)[index].trim();
    }

    // used to get LocalDateTime parameters for deadline and event commands
    public static LocalDateTime parseDateTimeParameter(String dateTime) {
        return LocalDateTime.parse(dateTime.trim(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}
