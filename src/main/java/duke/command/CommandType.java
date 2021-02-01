package duke.command;

/**
 * Enumerates all possible command types accepted by the program. If user input does not correspond to any of the
 * commands provided, it will be defined as an INVALID command type.
 */
public enum CommandType {
    TODO,
    EVENT,
    DEADLINE,
    DONE,
    LIST,
    DELETE,
    FIND,
    INVALID,
    BYE
}
