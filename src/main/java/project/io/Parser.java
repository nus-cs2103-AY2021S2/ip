package project.io;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Processes the user commands.
 */
public class Parser {
    // todo: implement this instead https://github.com/se-edu/addressbook-level3/blob/master
    //  /src/main/java/seedu/address/logic/parser/ParserUtil.java

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
     * Returns a {@code LocalDateTime} parameter from 'deadline' and 'event' userInput.
     * This reads the date and time in the expected user input format.
     *
     * @param dateTime The user command specifying the date and time.
     */
    public static LocalDateTime parseInputDateTime(String dateTime) {
        return LocalDateTime.parse(dateTime.trim(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    /**
     * Returns a {@code LocalDateTime} parameter from saved deadlines and events.
     * This reads the date and time in the output and saved format .
     *
     * @param dateTime The user command specifying the date and time.
     */
    public static LocalDateTime parseOutputDateTime(String dateTime) {
        String[] s = dateTime.trim().split("\\)");
        return LocalDateTime.parse(s[0],
                DateTimeFormatter.ofPattern("d MMM yyyy HH:mm"));
    }
}
