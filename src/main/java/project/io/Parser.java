package project.io;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Processes the user commands.
 */
public class Parser {
    // todo: try this https://github.com/se-edu/addressbook-level3/blob/master/src/main/java/seedu/address/logic/parser/ParserUtil.java
    // why do these methods have to be static??

    /**
     * Returns {@code int} index parameter of 'delete' and 'done' commands.
     *
     * @param command The user command.
     */
    public static int parseIntParameter(String command) {
        return Integer.parseInt(command.split(" ")[1]);
    }

    /**
     * Returns a {@code String} parameter of 'todo', 'deadline' and 'event' commands.
     *
     * @param command The user command.
     * @param regex The regex pattern to split the command by.
     * @param index The index of the resultant array to return.
     */
    public static String parseParameter(String command, String regex, int index) {
        return command.split(regex, 2)[index].trim();
    }

    /**
     * Returns a {@code LocalDateTime} parameter from 'deadline' and 'event' commands.
     *
     * @param dateTime The user command specifying the date and time.
     */
    public static LocalDateTime parseDateTimeParameter(String dateTime) {
        return LocalDateTime.parse(dateTime.trim(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}
