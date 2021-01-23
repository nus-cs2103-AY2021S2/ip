import java.time.LocalDate;

/**
 * Entry point for handling logic and execution of add command.
 */
public class AddCommand {

    /**
     * Forwards the add task operation to TaskList.
     * @param taskType type of task (todo, deadline or event)
     * @param taskName name of task
     * @param taskDate due date for task (null for todo)
     */
    public static void execute(String taskType, String taskName, LocalDate taskDate) {
        TaskList.addTask(taskType, taskName, taskDate);
        Storage.saveTask(TaskList.tasks.size(), "NEW", taskName, "incomplete", taskType.toUpperCase(), taskDate);
    }
}
