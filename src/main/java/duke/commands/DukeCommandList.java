package duke.commands;

import java.util.ArrayList;
import java.util.Comparator;

import duke.parser.UserInputTokenSet;
import duke.responses.Response;
import duke.storage.FileLoader;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.Todo;

/**
 * List command.
 *
 * If 'toSort' is provided during construction, the list view will be
 * sorted in order of Todo < Event (chronological) < Deadline (chronological).
 */
public class DukeCommandList extends DukeCommand {

    protected boolean toSort;

    /** Single constructor */
    public DukeCommandList(UserInputTokenSet tokenSet) {
        toSort = tokenSet.contains("sort");
    }

    /**
     * Returns Response containing formatted string representing the list.
     *
     * @param tasks tasklist
     * @param loader loader
     * @return Response
     */
    @Override
    public Response execute(TaskList tasks, FileLoader loader) {
        if (tasks.size() == 0) {
            return Response.createResponseOk("There are no tasks in your list.");
        }

        /* Prepare response */
        ArrayList<Task> listOfTasks = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            listOfTasks.add(tasks.getTaskUnsafe(i));
        }

        /* Sort responses */
        ArrayList<String> lines = new ArrayList<>();
        if (toSort) {
            listOfTasks.sort(new TaskComparator());
            lines.add("Here are the tasks in your list, in sorted order:");
            for (Task task: listOfTasks) {
                lines.add(" - " + task.toString()); // no numbering for sorted order
            }
        } else {
            lines.add("Here are the tasks in your list:");
            for (int i = 0; i < listOfTasks.size(); i++) {
                Task task = listOfTasks.get(i);
                String line = tasks.getLeftPadding(i + 1) + (i + 1) + ". " + task.toString();
                lines.add(line);
            }
        }
        return Response.createResponseOk(lines.toArray(new String[0]));
    }
}

class TaskComparator implements Comparator<Task> {
    // See List above.
    @Override
    public int compare(Task t1, Task t2) {
        if (t1 instanceof Todo) {
            return -1;
        } else if (t2 instanceof Todo) {
            return 1;
        } else if (t1 instanceof Event) {
            if (t2 instanceof Event) {
                return ((Event) t1).getDatetime().compareTo(((Event) t2).getDatetime());
            }
            return -1;
        } else if (t2 instanceof Event) {
            return 1;
        } else if (t1 instanceof Deadline) {
            if (t2 instanceof Deadline) {
                return ((Deadline) t1).getDatetime().compareTo(((Deadline) t2).getDatetime());
            }
            return -1;
        } else {
            return 1;
        }
    }
}
