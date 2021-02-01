package duke.command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import duke.DukeException;
import duke.Helper;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class ListCommand extends Command {

    public ListCommand(String[] commandSplit) {
        super(commandSplit);
    }

    /**
     * Lists out all tasks in the task list if user did not provide a date.
     * Else, lists out only tasks that are marked with the given date.
     * @param list the task list.
     * @throws DukeException if an incorrect date format is provided.
     */
    @Override
    public void execute(TaskList list) throws DukeException {
        //Check if command includes an optional date argument
        boolean hasDate = commandSplit.length > 1;
        if (hasDate) {
            String enteredDate = Helper.join(this.commandSplit, 1, this.commandSplit.length - 1);
            LocalDate queryDate;
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[dd/MM/yyyy][yyyy-MM-dd][MMM dd yyyy]");
                queryDate = LocalDate.parse(enteredDate, formatter);
            } catch (DateTimeParseException e) {
                throw new DukeException("Incorrect date format. " + e.getMessage());
            }
            ArrayList<Task> matchedTasks = list.filter(x -> !x.getDate().equals(LocalDate.MIN)
                    && x.getDate().equals(queryDate));
            Ui.printWithStyle(matchedTasks.stream().map(Task::toString).collect(Collectors.toList()));
        } else {
            list.printList();
        }
    }
}
