package duke.command;

import java.util.ArrayList;
import java.util.stream.IntStream;

import duke.DukeException;
import duke.Helper;
import duke.TaskList;

public class DeleteCommand extends Command {

    /**
     * Instantiates a new DeleteCommand object.
     * @param commandSplit user command split by spaces.
     */
    public DeleteCommand(String[] commandSplit) {
        super(commandSplit);
        assert commandSplit[0].equals("delete") : "Must have delete keyword";
    }

    private static boolean isValidTaskNumber(String taskNumber, int listLength) {
        boolean isInteger = Helper.isInteger(taskNumber);
        if (!isInteger) {
            return false;
        }
        int integerIndex = Integer.parseInt(taskNumber);
        return integerIndex > 0 && integerIndex <= listLength;
    }

    /**
     * Deletes the user specified task from the task list.
     * @param list the task list.
     * @throws DukeException if failed to remove task from task list.
     */
    @Override
    public String execute(TaskList list) throws DukeException {
        if (commandSplit.length == 1) {
            throw new DukeException("Please enter the task number of the task to delete.");
        }

        //Check if user entered valid task numbers.
        boolean isValidTaskNumbers = IntStream.range(1, commandSplit.length)
                .allMatch(index -> isValidTaskNumber(commandSplit[index], list.size()));
        if (!isValidTaskNumbers) {
            throw new DukeException("Please enter correct task numbers of tasks to be deleted.");
        }


        //Collate the task numbers of tasks to be deleted.
        ArrayList<Integer> taskNumbersToDelete = new ArrayList<>();
        for (int i = 1; i < commandSplit.length; i++) {
            int taskNumber = Integer.parseInt(commandSplit[i]);
            taskNumbersToDelete.add(taskNumber);
        }

        return list.remove(taskNumbersToDelete);

    }
}
