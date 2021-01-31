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

    /**
     * Executes the Find command to search tasks that matches the keyword and occurs on the target date
     *
     * @throws DukeCommandException if tasks cannot be retrieved
     */
    @Override
    public String execute() throws DukeCommandException {
        List<Task> tasksFound = taskManager.getTasks().stream()
                .filter(task -> {
                    if (task.getDesc().contains(keyword)) {
                        if (targetDate != null) {
                            switch (task.getTypeSymbol()) {
                            case "D":
                                LocalDate due = ((Deadline) task).getDateTime().toLocalDate();
                                return targetDate.equals(due);
                            case "E":
                                LocalDate start = ((Event) task).getStartDateTime().toLocalDate();
                                LocalDate end = ((Event) task).getEndDateTime().toLocalDate();
                                return targetDate.isEqual(start) || targetDate.isEqual(end)
                                        || (targetDate.isAfter(start) && targetDate.isBefore(end));
                            default:
                                return false;
                            }
                        } else {
                            return true;
                        }
                    } else {
                        return false;
                    }
                })
                .collect(Collectors.toList());

        return ui.constructFoundMsg(tasksFound);
    }
}
