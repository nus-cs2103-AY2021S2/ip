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

    public AddCommand(TaskType type, String task) {
        this.type = type;
        this.task = task;
        this.datetime = null;
    }

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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        boolean isInsert = false;
        if (tasks.size() >= 100) {
            System.out.println("\tSorry. The database is full!");
        } else {
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
            }
        }


        if (isInsert) {
            System.out.println("\tGot it. I've added this task: ");
            System.out.printf("\tTask added: %s\n", tasks.getTaskDescription(tasks.size() - 1));
            System.out.printf("\tNow you have %d task%s in the list.\n", tasks.size(), tasks.size() == 1 ? "" : "s");
        }
    }
}
