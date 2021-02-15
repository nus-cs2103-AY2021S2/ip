package duke.command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import duke.CallbackFunction;
import duke.DukeException;
import duke.Helper;
import duke.TaskList;
import duke.task.Task;
import javafx.util.Pair;

public class ListCommand extends Command {

    /**
     * Instantiates a new ListCommand object.
     * @param commandSplit user command split by spaces.
     */
    public ListCommand(String[] commandSplit) {
        super(commandSplit);
        assert commandSplit.length >= 1 && commandSplit[0].equals("list") : "Must have list keyword";
    }

    private LocalDate parseDate(String date) throws DukeException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[dd/MM/yyyy][yyyy-MM-dd][MMM dd yyyy]");
            return LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            throw new DukeException("Incorrect date format. " + e.getMessage());
        }
    }

    /**
     * Lists out all tasks in the task list if user did not provide a date.
     * Else, lists out only tasks that are marked with the given date.
     * @param list the task list.
     * @throws DukeException if an incorrect date format is provided.
     */
    @Override
    public Pair<String, CallbackFunction> execute(TaskList list) throws DukeException {
        //Check if command includes an optional date argument
        boolean hasDate = commandSplit.length > 1;
        if (hasDate) {
            String enteredDate = Helper.join(this.commandSplit, 1, this.commandSplit.length - 1);
            LocalDate queryDate = parseDate(enteredDate);
            //Filter Tasks from TaskList against the date provided
            ArrayList<Task> matchedTasks = list.filter(x -> !x.getDate().equals(LocalDate.MIN)
                    && x.getDate().equals(queryDate));
            //Map each filtered Task with Task.toString()
            List<String> taskStrings = matchedTasks.stream().map(Task::toString).collect(Collectors.toList());
            String response = "Found the following tasks on " + queryDate.toString() + ":"
                    + System.lineSeparator() + System.lineSeparator() + Helper.formatStrings(taskStrings);
            return new Pair<>(response, CallbackFunction.empty());

        } else {
            return new Pair<>(list.toString(), CallbackFunction.empty());
        }
    }
}
