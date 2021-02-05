package blarb;

import java.util.Arrays;

/**
 * {@code Command} is an enumeration of the possible commands.
 */
enum Command {
    DONE("done"), TODO("todo"), DEADLINE("deadline"), EVENT("event"),
    LIST("list"), DELETE("delete"), BYE("bye"), FIND("find"), UNKNOWN("");

    private final String command;

    Command(String command) {
        this.command = command;
    }

    /**
     * Reads in string and transforms to command.
     *
     * @param string String form of command.
     * @return The command
     */
    public static Command command(String string) {
        return Arrays.stream(Command.values())
                .filter(x -> x.command.equals(string.toLowerCase()))
                .findAny()
                .orElse(UNKNOWN);
    }

}
