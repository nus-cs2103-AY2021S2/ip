package yoda.command;

import yoda.storage.Storage;
import yoda.task.Deadline;
import yoda.task.Event;
import yoda.task.Task;
import yoda.task.TaskList;
import yoda.task.ToDo;
import yoda.ui.Ui;

/**
 * AddCommand class that handles adding tasks to TaskList and is a child of Command class.
 */
public class AddCommand extends Command {
    /**
     * Creates an AddCommand object.
     * @param details Details of AddCommand object.
     */
    public AddCommand(String[] details) {
        super(details);
        commandType = CommandType.valueOf(details[0]);
    }

    /**
     * Makes the task requested by the user.
     * @return Task requested by the user.
     */
    public Task makeTask() {
        switch(commandType) {
        case TODO:
            return new ToDo(details[1]);
        case EVENT:
            String[] eventDetails = details[1].split(" /at ");
            return new Event(eventDetails[0], eventDetails[1]);
        case DEADLINE:
            String[] deadlineDetails = details[1].split(" /by ");
            return new Deadline(deadlineDetails[0], deadlineDetails[1]);
        default:
            return new ToDo("This task should not have appeared!");
        }
    }

    /**
     * Adds a specific type of task to the TaskList based on the command issued by the user.
     * @param taskList TaskList associated with the AddCommand being executed.
     * @param ui Ui associated with the AddCommand being executed.
     * @param storage Storage associated with the AddCommand being executed.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task task = makeTask();
        taskList.addTask(task);
        storage.write(taskList);
        System.out.println("Got it! Added this task to the list!\n" + task);
    }
}
