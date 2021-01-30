package yoda.command;

import yoda.task.Task;
import yoda.task.ToDo;
import yoda.task.Deadline;
import yoda.task.Event;
import yoda.task.TaskList;
import yoda.ui.Ui;
import yoda.storage.Storage;

public class AddCommand extends Command{

    public AddCommand(String[] details) {
        super(details);
        taskType = Input.valueOf(details[0]);
    }

    public Task makeTask() {
        String[] splitInfo = details[1].split(" ", 3);
        switch(taskType) {
        case TODO:
            return new ToDo(details[1]);
        case EVENT:
            return new Event(splitInfo[0], splitInfo[2]);
        case DEADLINE:
            return new Deadline(splitInfo[0], splitInfo[2]);
        default:
            return new ToDo("This task should not have appeared!");
        }
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task task = makeTask();
        taskList.addTask(task);
        storage.write(taskList);
        System.out.println("Got it! Added this task to the list!\n" + task);
    }
}
