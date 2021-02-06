package duke.command;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import duke.exception.DukeCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

/** An executable command to find a task by keyword and/or date of occurrence */
public class FindCommand extends Command {
    /** Keyword to search tasks with */
    private String keyword = "";
    /** Date to search tasks with */
    private LocalDate targetDate = null;

    /**
     * Constructor for a FindCommand
     *
     * @param keyword A String to check against task's description
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Constructor for a FindCommand
     *
     * @param keyword A String to check against task's description
     * @param date A date to filter the tasks with
     */
    public FindCommand(String keyword, LocalDate date) {
        this.keyword = keyword;
        this.targetDate = date;
    }

    private boolean isMatch(Task task) {
        boolean isSearchingWithDate = targetDate != null;
        boolean isToDo = task.getTypeSymbol().equals("T");
        boolean isDeadline = task.getTypeSymbol().equals("D");
        boolean isEvent = task.getTypeSymbol().equals("E");

        if (isSearchingWithDate && isToDo) {
            return false;
        } else if (isSearchingWithDate && isDeadline) {
            LocalDate dueDate = ((Deadline) task).getDateTime().toLocalDate();
            return targetDate.equals(dueDate);
        } else if (isSearchingWithDate && isEvent) {
            LocalDate startDate = ((Event) task).getStartDateTime().toLocalDate();
            LocalDate endDate = ((Event) task).getEndDateTime().toLocalDate();
            boolean isInRange = targetDate.isEqual(startDate)
                            || targetDate.isEqual(endDate)
                            || (targetDate.isAfter(startDate) && targetDate.isBefore(endDate));
            return isInRange;
        }

        return task.getDesc().contains(keyword);
    }

    /**
     * Executes the Find command to search tasks that matches the keyword and occurs on the target date, then returns
     * a response message
     *
     * @throws DukeCommandException if tasks cannot be retrieved
     */
    @Override
    public String execute() throws DukeCommandException {
        List<Task> tasksFound = taskManager.getTasks().stream()
                .filter(task -> isMatch(task))
                .collect(Collectors.toList());
        return ui.constructFoundMessage(tasksFound);
    }
}
