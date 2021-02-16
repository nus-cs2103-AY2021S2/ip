package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.TaskType;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

public class AddCommand implements Command {
    private TaskType type;
    private String task;
    private String datetime;

    /**
     * Constructor for class AddCommand on tasks without a datetime.
     * @param   type    the task type
     * @param   task    the task description
     */
    public AddCommand(TaskType type, String task) {
        this.type = type;
        this.task = task;
        this.datetime = null;
    }

    /**
     * Constructor for class AddCommand on tasks with a datetime.
     * @param   type        the task type
     * @param   task        the task description
     * @param   datetime    the datetime
     */
    public AddCommand(TaskType type, String task, String datetime) {
        this.type = type;
        this.task = task;
        this.datetime = datetime;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String executeAndGetResponse(TaskList tasks, Ui ui, Storage storage) {
        boolean isInsert = false;
        if (tasks.size() >= 100) {
            return ui.getfullDatabaseMessage();
        }

        switch (type) {
        case TODO:
            tasks.add(new ToDo(task));
            isInsert = true;
            break;
        case EVENT:
            Event event = null;
            final String[] eventDeadline = this.datetime.split(" ", 2);
            if (eventDeadline.length == 1) {
                event = Event.create(task, eventDeadline[0].strip());
            } else if (eventDeadline.length == 2) {
                event = Event.create(task, eventDeadline[0].strip(), eventDeadline[1].strip());
            }

            if (event != null) {
                tasks.add(event);
                isInsert = true;
            }
            break;
        case DEADLINE:
            Deadline deadline = null;
            final String[] deadlineDatetime = this.datetime.split(" ", 2);
            if (deadlineDatetime.length == 1) {
                deadline = Deadline.create(task, deadlineDatetime[0].strip());
            } else if (deadlineDatetime.length == 2) {
                deadline = Deadline.create(task, deadlineDatetime[0].strip(), deadlineDatetime[1].strip());
            }

            if (deadline != null) {
                tasks.add(deadline);
                isInsert = true;
            }
            break;
        default:
            return "";
        }


        if (isInsert) {
            int numberOfTasks = tasks.size();
            return ui.getAddTaskSuccessfulMessage(tasks.getTaskDescription(tasks.size() - 1), numberOfTasks);
        }

        return "";
    }
}
