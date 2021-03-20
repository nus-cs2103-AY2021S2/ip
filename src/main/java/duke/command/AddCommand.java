package duke.command;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;

import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

public class AddCommand extends Command {
    private String fullCommand;

    public AddCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    public String execute(TaskList tasks, Storage storage) throws IOException {
        ArrayList<Task> taskList = tasks.getTasks();
        int indexOfSpace = fullCommand.indexOf(" ");
        String taskType = fullCommand.split(" ")[0];
        Task taskToAdd = null;
        if(fullCommand.equals("todo")) {
            return "OOPS!!! The name of the task cannot be empty. ";
        }
        if(fullCommand.equals("event")) {
            return "OOPS!!! The name and date of the task cannot be empty. (Format: event + name + /at + date[YYYY-MM-DD])";
        }
        if(fullCommand.equals("deadline")) {
            return "OOPS!!! The name and date of the task cannot be empty. (Format: deadline + name + /by + date[YYYY-MM-DD])";
        }
        if (taskType.equals("todo")) {
            String toDoName = fullCommand.substring(indexOfSpace + 1).trim();
            taskToAdd = new ToDo(toDoName);
        } else if (taskType.equals("deadline")) {
            int indexOfDivider = fullCommand.indexOf("/by");
            if(indexOfDivider == -1) {
                return "OOPS!!! Please follow the format (Format: deadline + name + /by + date[YYYY-MM-DD])";
            }
            String taskName = fullCommand.substring(indexOfSpace + 1, indexOfDivider).trim();
            if(taskName.equals("")) {
                return "OOPS!!! The name of the task cannot be empty. (Format: deadline + name + /by + date[YYYY-MM-DD])";
            }
            try {
                taskToAdd = CommandToTask(fullCommand, taskType);
            } catch (StringIndexOutOfBoundsException | NumberFormatException e) {
                return "OOPS!!! Please follow the format for date (Format: date[YYYY-MM-DD])";
            }
        } else if (taskType.equals("event")) {
            int indexOfDivider = fullCommand.indexOf("/at");
            if(indexOfDivider == -1) {
                return "OOPS!!! Please follow the format (Format: event + name + /at + date[YYYY-MM-DD])";
            }
            String taskName = fullCommand.substring(indexOfSpace + 1, indexOfDivider).trim();
            if(taskName.equals("")) {
                return "OOPS!!! The name of the task cannot be empty. (Format: event + name + /at + date[YYYY-MM-DD])";
            }
            try {
                taskToAdd = CommandToTask(fullCommand, taskType);
            } catch (StringIndexOutOfBoundsException | NumberFormatException e) {
                return "OOPS!!! Please follow the format for date (Format: date[YYYY-MM-DD])";
            }

        }
        taskList.add(taskToAdd);
        storage.saveTasksToFile(taskList);
        String output = "Got it. I have added this task : \n";
        output += taskToAdd.toString() + "\n";
        output += String.format("Now you have %d tasks in the list.", taskList.size()) ;
        return output;
    }
    

    public Task CommandToTask(String fullCommand, String taskType) {
            int indexOfSpace = fullCommand.indexOf(" ");
            Task taskToAdd = null;
            int indexOfDivider = fullCommand.indexOf("/");
            String taskName = fullCommand.substring(indexOfSpace + 1, indexOfDivider).trim();
            String dateString = fullCommand.substring(indexOfDivider + 4);
            LocalDate date = getLocalDateFromString(dateString);
            if (taskType.equals("deadline")) {
                taskToAdd = new Deadline(taskName, date);
            } else {
                assert taskType.equals("event");
                taskToAdd = new Event(taskName, date);
            }
            return taskToAdd;

    }

    public LocalDate getLocalDateFromString(String dateString) {
        int IndexOfFirstDivider = dateString.indexOf('-');
        int year = Integer.valueOf(dateString.substring(0, IndexOfFirstDivider));
        String monthAndDay = dateString.substring(IndexOfFirstDivider + 1);
        int IndexOfSecondDivider = monthAndDay.indexOf('-');
        int mon = Integer.valueOf(monthAndDay.substring(0, IndexOfSecondDivider));
        int day = Integer.valueOf(monthAndDay.substring(IndexOfSecondDivider + 1));
        LocalDate date = LocalDate.of(year, mon, day);
        return date;
    }




}
