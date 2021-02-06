package duke.command;

import duke.DukeException;
import duke.StringParser;
import duke.TaskList;

/**
 * Command type find.
 */
public class FindCommand extends Command {

    private static final int LENGTH_FIND = "find ".length();

    private static final String ERROR_EMPTY_ARGUMENT = "Invalid argument: Argument field cannot be empty.";
    private static final String ERROR_EMPTY_CONTENT = "Invalid argument: Content field is blank.";
    private static final String MESSAGE_OBJECT_NOT_FOUND = "No result found";

    private final String command;

    /**
     * Constructs a find command.
     *
     * @param command Input string.
     */
    public FindCommand(String command) {
        this.command = command;
    }

    /**
     * Execute and print a find command.
     *
     * @param list Passes TaskList in case of reading and writing to the list.
     * @throws DukeException When encounter an error in command argument.
     */
    @Override
    public String executeAndPrint(TaskList list) throws DukeException {
        if (command.length() <= LENGTH_FIND) {
            throw new DukeException(ERROR_EMPTY_ARGUMENT);
        }

        String subStr = command.substring(LENGTH_FIND);
        if (StringParser.isBlank(subStr)) {
            throw new DukeException(ERROR_EMPTY_CONTENT);
        }

        TaskList tempList = new TaskList();
        for (int i = 0; i < list.getSize(); i++) {
            if (list.getJob(i).getDescription().contains(subStr)) {
                tempList.addJob(list.getJob(i));
            }
        }
        if (tempList.getSize() == 0) {
            return MESSAGE_OBJECT_NOT_FOUND + "\n";
        } else {
            return "Here are the matching tasks in the list:\n"
                    + tempList.formatList();
        }
    }

}
