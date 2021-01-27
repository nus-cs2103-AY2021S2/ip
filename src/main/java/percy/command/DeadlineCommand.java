package percy.command;

import percy.exception.NoDescriptionException;
import percy.ui.UserInterface;
import percy.task.Deadline;
import percy.task.Task;
import percy.task.TaskList;
import percy.storage.Storage;

import java.io.IOException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class DeadlineCommand extends Command {
    public static final String COMMAND = "deadline";

    public static final String DATE_TIME_PREFIX =  " /by ";

    private String deadlineDescription;
    private LocalDate date;
    private LocalTime time;

    public static final ArrayList<String> USAGE_GUIDE = new ArrayList<String>(List.of(
            "deadline: Adds a task that needs to be done before a specific date and time.",
            "Parameters: TASK_DESCRIPTION /by DATE(yyyy-MM-dd) START_TIME(HHmm)-END_TIME(HHmm)",
            "Example: deadline return book /by 2021-01-27 1800-2000"));

    public DeadlineCommand(String deadlineDescription, LocalDate date, LocalTime time) {
        super(false);
        this.deadlineDescription = deadlineDescription;
        this.date = date;
        this.time = time;
    }

    /**
     * Executes the Todo command which creates a Todo Task.
     *
     * <p></p>Taking the TaskList and Storage object of the main Duke class as arguments, this command will create a new
     * Deadline Task which will then be added to the TaskList and Storage objects. The UI will also be used to print
     * a newTask message into the console.
     *
     * @param taskList The TaskList from the main Duke object.
     */
    public String execute(TaskList taskList, Storage storage) throws IOException { // Is task list Immutable?
        try {
            if (deadlineDescription.isEmpty()) {
                throw new NoDescriptionException("deadline");
            }
        } catch(NoDescriptionException ex) {
            UserInterface.makeMsg(ex.toString());
        }

        Task deadlineTask = new Deadline(deadlineDescription, date, time);
        taskList.addTaskToList(deadlineTask);
        storage.save(taskList);
        return UserInterface.makeAddMsg(deadlineTask, taskList);
    }
}
