import java.time.LocalDate;

public class AddCommand extends Command {

    public AddCommand(String command, String description) {
        super.command = command;
        super.description = description;
        super.date = "";
    }

    public AddCommand(String command, String description, String date) {
        super.command = command;
        super.description = description;
        super.date = date;
    }

    private void todoProcess(String taskName, TaskList tasks) {
        Task task = new ToDo(taskName);
        tasks.add(task);
        output = "Got it. I've added this task: \n\t  "
                + task.toString() + getRemainingTasks(tasks);
    }

    private void eventProcess(String description, String date, TaskList tasks) throws DukeException {
        if (!Utility.isValidDate(date)) {
            throw new DukeException(DukeExceptionType.INVALID_DATE_FORMAT);
        }
        Task task = new Event(description, LocalDate.parse(date));
        tasks.add(task);
        output = "Got it. I've added this task: \n\t  "
                + task.toString() + getRemainingTasks(tasks);
    }

    private void deadlineProcess(String description, String date, TaskList tasks) throws DukeException {
        if (!Utility.isValidDate(date)) {
            throw new DukeException(DukeExceptionType.INVALID_DATE_FORMAT);
        }
        Task task = new Deadline(description, LocalDate.parse(date));
        tasks.add(task);
        output = "Got it. I've added this task: \n\t  "
                + task.toString() + getRemainingTasks(tasks);
    }

    private String getRemainingTasks(TaskList tasks) {
        return "\n\tNow you have " + tasks.size() + " tasks in the list.";
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        switch (command) {
        case "todo":
            todoProcess(description, tasks);
            break;
        case "event":
            eventProcess(description, date, tasks);
            break;
        case "deadline":
            deadlineProcess(description, date, tasks);
            break;
        }
        storage.save(tasks);
        ui.response(output);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
