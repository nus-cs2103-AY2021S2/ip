package duke.command;

import duke.DukeException;
import duke.TaskList;

public class DoneCommand extends Command {

    /**
     * Instantiates a new DoneCommand object.
     * @param commandSplit user command split by spaces.
     */
    public DoneCommand(String[] commandSplit) {
        super(commandSplit);
        assert commandSplit.length > 1 && commandSplit[0].equals("done")
                && commandSplit[1].chars().allMatch(Character::isDigit)
                : "Must have done keyword and integer as argument";
    }

    /**
     * Marks the user defined task as done.
     * @param list the task list.
     * @throws DukeException if failed to mark task as done.
     */
    @Override
    public String execute(TaskList list) throws DukeException {
        try {
            int taskNumber = Integer.parseInt(commandSplit[1]);
            return list.done(taskNumber);
        } catch (Exception e) {
            throw new DukeException("Please enter a valid task number to mark a task as done.");
        }
    }

}
