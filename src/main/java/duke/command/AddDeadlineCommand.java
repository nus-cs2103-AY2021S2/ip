package duke.command;

import java.time.LocalDate;
import java.time.LocalTime;

import duke.exception.NameDuplicateException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;
import duke.util.Deadline;
import duke.exception.NoMeaningException;

public class AddDeadlineCommand extends Command {
    public AddDeadlineCommand() {}

    /**
     * Process command deadline given by user.
     * @param storage The storage to store result
     *        taskList TaskList associated to the current Duke
     *        ui The UI we use to print stuff
     *        command Sentences entered by the user
     */
    public String execute(Storage storage, TaskList taskList, Ui ui, String command) {
        try {
            String commandContent = command.substring(9);
            String[] deadlineWords = commandContent.split("/by");
            String deadlineWord = deadlineWords[0];
            String deadlineTime = deadlineWords[1];

            String[] deadlineDateHours = deadlineTime.split(" ");
            LocalDate deadlineDate = LocalDate.parse(deadlineDateHours[1]);
            LocalTime deadlineHour = LocalTime.parse(deadlineDateHours[2]);

            if (taskList.findExact(deadlineWord).size() == 0) {
                Deadline deadline = new Deadline(deadlineWord, deadlineDate, deadlineHour);
                taskList.add(deadline);
                storage.saveTasks(taskList);
                return ui.getTaskFinally(deadline, taskList.size());
            } else {
                storage.saveTasks(taskList);
                throw new NameDuplicateException("Daddy, looks like you have already add this task");
            }
        }  catch (StringIndexOutOfBoundsException e) {
            return ui.getTaskFail(new NoMeaningException(
                    "☹ OOPS!!! The description of a deadline cannot be empty.")
            );
        }  catch (NameDuplicateException e) {
            return ui.getTaskFail(e);
        }
    }
}
