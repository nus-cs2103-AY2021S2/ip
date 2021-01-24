package duke.command;

import duke.DukeException;
import duke.StringParser;
import duke.TaskList;

/**
 * Command type find.
 */
public class FindCommand extends Command {

    private final String command;

    /**
     * Find command builder.
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
     * @param length For printer to call newLiner, make Duke looks nice.
     * @throws DukeException When encounter an error in command argument.
     */
    @Override
    public void executeAndPrint(TaskList list, int length) throws DukeException {
        if (command.length() <= 5) {
            throw new DukeException("Invalid argument: Argument field cannot be empty.");
        } else {
            String subStr = command.substring(5);
            if (StringParser.isBlank(subStr)) {
                throw new DukeException("Invalid argument: Content field is blank");
            } else {
                TaskList tempList = new TaskList();
                for (int i = 0; i < list.getSize(); i++) {
                    if (list.getJob(i).getDescription().contains(subStr)) {
                        tempList.addJob(list.getJob(i));
                    }
                }
                if (tempList.getSize() == 0) {
                    System.out.print("No result found\n");
                } else {
                    System.out.print("Here are the matching tasks in the list:\n"
                            + tempList.formatList(length));
                }
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
