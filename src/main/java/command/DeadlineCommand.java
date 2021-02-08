package command;

import java.time.LocalDateTime;

import exception.MikeCommandExecutionException;
import mike.TaskList;
import task.DeadlineTask;

public class DeadlineCommand extends AddCommand {

    private final LocalDateTime deadline;
    /**
     * Constructor for the DeadlineCommand object that includes a LocalDateTime object to represent the deadline of
     * the task
     * @param taskDescription Description of the task to be added
     * @param deadline Deadline of the task to be added
     */
    public DeadlineCommand(String taskDescription, LocalDateTime deadline) {
        super(taskDescription);
        this.deadline = deadline;
    }

    /**
     * Execute command and adds deadline task to the provided taskList
     * @param taskList TaskList object to add the task to
     * @return TaskList object after adding task to the list
     */
    @Override
    public TaskList runCommand(TaskList taskList) throws MikeCommandExecutionException {
        this.taskList = taskList;
        this.taskToAdd = new DeadlineTask(this.taskDescription, this.deadline);
        if (this.checkIfDuplicate(taskToAdd)) {
            throw new MikeCommandExecutionException("Deadline Command", " ☹ OOPS!!! Task is a duplicate!");
        }
        taskList.addTaskToList(taskToAdd);
        return taskList;
    }
}
