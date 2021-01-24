package duke.command;

import duke.exception.DukeCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class FindCommand extends Command {
    private String keyword = "";
    private LocalDate targetDate = null;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    public FindCommand(String keyword, LocalDate date) {
        this.keyword = keyword;
        this.targetDate = date;
    }

    @Override
    public void execute() throws DukeCommandException {
        List<Task> tasksFound = taskManager.getTasks().stream()
                .filter(task -> {
                    if(task.getDesc().contains(keyword)) {
                        if(targetDate != null) {
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

        ui.printFoundMsg(tasksFound);
    }
}
