package duke.command;

import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.Ui;
import duke.Storage;
import duke.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;

public class DoneCommand extends Command {
    public static final boolean IS_EXIT = false;
    protected String input;

    public DoneCommand(String input) {
        super(IS_EXIT);
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] array = input.split(" ");
        if (array.length == 1) {
            throw new DukeException("☹ OOPS!!! I don't know which task to mark as done.");
        }
        if(tasks.getSize() == 0) {
            throw new DukeException("☹ OOPS!!! There are no tasks to be marked as done.");
        }
        if(Integer.parseInt(array[1]) > tasks.getSize()) {
            throw new DukeException("☹ OOPS!!! There is no such task to be marked as done.");
        }

        int idx = Integer.parseInt(array[1]) - 1;
        Task t = tasks.getTask(idx);
        t.markAsDone();
        if(t.getType().equals("D")) {
            Deadline d = (Deadline) t;
            storage.editDataInFile(idx + 1, t.getType(), "1", t.getDescription(),
                    d.getBy(), tasks.getSize());
        } else if (t.getType().equals("T")) {
            storage.editDataInFile(idx + 1, t.getType(), "1", t.getDescription(),
                    "", tasks.getSize());

        } else {
            Event e = (Event) t;
            storage.editDataInFile(idx + 1, t.getType(), "1", t.getDescription(),
                    e.getDate(), tasks.getSize());

        }
        System.out.println("    Nice! I've marked this task as done:\n  " + t);
    }
}
