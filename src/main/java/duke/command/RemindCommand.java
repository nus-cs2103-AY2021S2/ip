package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RemindCommand extends Command{
    private static final int DEFAULT_NUM_DAYS = 3;
    private final int numDays;

    public RemindCommand() {
        super();
        this.numDays = DEFAULT_NUM_DAYS;
    }

    public RemindCommand(int numDays) {
        super();
        this.numDays = numDays;
    }

    boolean isUpcoming(Task task){
        boolean validTaskType = task.getTaskType().equals("DEADLINE") ||
                task.getTaskType().equals("EVENT");

        if (validTaskType) {
            LocalDateTime taskTime;
            switch (task.getTaskType()) {
            case ("DEADLINE"):
                taskTime = ((Deadline) task).getEndTime();
                break;
            case ("EVENT"):
                taskTime = ((Event) task).getEventTime();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + task.getTaskType());
            }
            LocalDateTime now = LocalDateTime.now();
            Duration timeDiff = Duration.between(now, taskTime);
            boolean inBounds = (timeDiff.toDays() < this.numDays) && (timeDiff.toDays() > 0);
            return  inBounds && validTaskType;
        } else {
            return false;
        }


    };

    private TaskList findUpcomingTasks(TaskList taskList) throws IllegalStateException{
        Stream<Task> fullTaskStream = new ArrayList<>(taskList.getTaskList()).stream();

        Function<Task, Boolean> isUpcomingFunc = this::isUpcoming;

        List<Task> upcomingTasks = fullTaskStream.filter(isUpcomingFunc::apply)
                .collect(Collectors.toList());
        return new TaskList(upcomingTasks);
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws IOException, DukeCommandException {
        TaskList upcomingTasks = findUpcomingTasks(taskList);
        return ui.showUpcomingTasks(upcomingTasks);
    }
}
