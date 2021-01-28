package seedu.io;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Processes user input commands.
 */
public class Parser {
    // todo: try this https://github.com/se-edu/addressbook-level3/blob/master/src/main/java/seedu/address/logic/parser/ParserUtil.java
    // why do these methods have to be static??

    /**
     * Returns {@code int} index parameter of 'delete' and 'done' user commands.
     *
     * @param command user input.
     */
    public static int parseIntParameter(String command) {
        return Integer.parseInt(command.split(" ")[1]);
    }

    /**
     * Returns {@code String} parameters of 'todo', 'deadline' and 'event' user commands.
     *
     * @param command user input.
     * @param regex expression to split the {@code command} by.
     * @param index index of the {@code Task}.
     */
    public static String parseParameter(String command, String regex, int index) {
        return command.split(regex, 2)[index].trim();
    }

    /**
     * Returns {@code LocalDateTime} parameters of 'deadline' and 'event' user commands.
     *
     * @param dateTime user input.
     */
    // used to get LocalDateTime parameters for deadline and event commands
    public static LocalDateTime parseDateTimeParameter(String dateTime) {
        return LocalDateTime.parse(dateTime.trim(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}
