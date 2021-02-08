package duke.commands;

import duke.exceptions.DukeExceptionIllegalArgument;
import duke.parser.UserInputTokenSet;
import duke.storage.FileLoader;
import duke.tasks.Event;
import duke.tasks.Todo;
import duke.tasks.Deadline;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

/**
 * List command.
 *
 * If 'toSort' is provided during construction, the list view will be
 * sorted in order of Todo < Event (chronological) < Deadline (chronological).
 */
public class DukeCommandList extends DukeCommand {

    protected boolean toSort;

    public DukeCommandList(UserInputTokenSet tokenSet) {
        toSort = tokenSet.contains("sort");
    }

    @Override
    public void execute(TaskList tasks, Ui ui, FileLoader loader) throws DukeExceptionIllegalArgument {
        if (tasks.size() == 0) {
            ui.showMessage("There are no tasks in your list.");
        } else if (toSort) {
            ArrayList<Task> view = new ArrayList<>();
            for (int i = 0; i < tasks.size(); i++) {
                view.add(tasks.getTask(i));
            }

            // Sort in order: Todo < Event (chronological) < Deadline (chronological)
            view.sort((t1, t2) -> {
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
            });
            ArrayList<String> lines = new ArrayList<>();
            for (Task task: view) {
                lines.add(" - " + task.toString());
            }
            ui.showMessage("Here are the tasks in your list, in sorted order:", lines);

        } else {
            ArrayList<String> lines = new ArrayList<>();
            for (int i = 0; i < tasks.size(); i++) {
                lines.add((i+1) + ". " + tasks.getTask(i));
            }
            ui.showMessage("Here are the tasks in your list:", lines);
        }
    }
}
