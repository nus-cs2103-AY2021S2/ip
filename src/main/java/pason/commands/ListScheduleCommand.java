package pason.commands;

import pason.storage.Storage;
import pason.tasks.Deadline;
import pason.tasks.Event;
import pason.tasks.Task;
import pason.tasks.TaskList;

public class ListScheduleCommand extends Command {
    private final String filter;
    /**
     * Initialises the AddCommand.
     */
    public ListScheduleCommand(String command, String filter) {
        super(command);
        this.filter = filter;
    }

    /**
     * Executes the command.
     */
    public CommandResult execute(TaskList tasks, Storage storage) {
        assert tasks != null : "TaskList cannot be null.";
        if (tasks.getTasks().size() == 0) {
            return new CommandResult("There are no tasks in your list. Time to add some!",
                    CommandResultType.CHAT_PASON);
        }
        int matchingResults = 0;
        String output = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < tasks.getTasks().size(); i++) {
            Task task = tasks.getTasks().get(i);
            if (matchesDate(task, this.filter)) {
                System.out.println("matches");
                output += (i + 1) + ". " + tasks.getTasks().get(i) + "\n";
                matchingResults++;
            }
        }
        if (matchingResults == 0) {
            return new CommandResult("There are no matching tasks for this date.",
                    CommandResultType.CHAT_PASON);
        }
        return new CommandResult(output, CommandResultType.CHAT_PASON);
    }

    /**
     * Extracts the Task date, if any.
     */
    public String extractDate(Task task) {
        if (task instanceof Event) {
            Event event = (Event) task;
            return event.getDate();
        } else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            return deadline.getDate();
        } else {
            return null;
        }
    }

    /**
     * Checks if task date matches filter.
     */
    public boolean matchesDate(Task task, String filter) {
        String extractDate = extractDate(task);
        if (extractDate != null && extractDate.equals(filter)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isExit() {
        return false;
    }
}
