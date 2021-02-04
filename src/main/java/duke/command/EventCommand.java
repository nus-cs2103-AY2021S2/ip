package duke.command;

import duke.tasks.TaskList;
import duke.Ui;
import duke.Storage;
import duke.DukeException;
import duke.tasks.Event;

public class EventCommand extends Command {
    public static final boolean IS_EXIT = false;
    protected String input;

    public EventCommand(String input) {
        super(IS_EXIT);
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if(input.length() <= 6 || !input.contains("/at")) {
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty");
        }
        String[] strArr = input.split("/at ");
        String description = strArr[0].substring(6).trim();
        String date = strArr[1];
        System.out.println("Got it. I've added this task:");
        Event e = new Event(description, date);
        tasks.addTask(e);
        System.out.println(" " + e.toString());
        storage.addNewDataToFile("E", "0", e.getDescription(), e.getDate());
        if(tasks.getSize() == 1) {
            System.out.printf("Now you have %d task in the list.%n", tasks.getSize());
        } else {
            System.out.printf("Now you have %d tasks in the list.%n", tasks.getSize());
        }
    }
}
