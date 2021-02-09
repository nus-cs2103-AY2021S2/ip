package duck.operation;

import duck.task.Deadline;
import duck.task.Event;
import duck.task.TaskList;
import duck.task.Todo;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class CommandGui {
    private String command;
    private String description;

    /**
     * Initialize the Command object
     *
     * @param command     the command getting from ui.
     * @param description the description of command,includes things to do, time and so on.
     * @return Lateral location.
     */
    public CommandGui(String command, String description) {
        this.command = command;
        this.description = description;
    }

    /**
     * execute the command
     *
     * @param tasks   the task list used to  record various tasks
     * @param gui     deals with interactions with the user
     * @param storage deals with loading tasks from the file and saving tasks in the file
     * @throws IOException
     */
    public String execute(TaskList tasks, Gui gui, Storage storage) throws IOException {
        String[] descriptionSplit;
        assert (!command.equals(null));
        switch (command) {
        case "bye":
        case "list":
        case "hello":
            return gui.showCommandReply(command, tasks);
        case "done":
            try {
                Integer NoOfTask = Integer.parseInt(description) - 1;
                tasks.getTask(NoOfTask).markAsDone();
                storage.updateFile(tasks);
                return gui.showDoneReply(NoOfTask, tasks);
            } catch (ArrayIndexOutOfBoundsException e) {
                return gui.showErrorReply("error_done_empty");
            } catch (NumberFormatException e) {
                return gui.showErrorReply("error_done_no_meaning");
            } catch (IndexOutOfBoundsException e) {
                return gui.showErrorReply("error_done_non_existed_task");
            } catch (IOException e) {
                e.printStackTrace();
            }
            break;
        case "delete":
            try {
                Integer NoOfTask = Integer.parseInt(description) - 1;
                tasks.getTask(NoOfTask);
                String replyString = gui.showDeleteReply(NoOfTask, tasks);
                tasks.removeTask(NoOfTask);
                storage.updateFile(tasks);
                return replyString;
            } catch (ArrayIndexOutOfBoundsException e) {
                return gui.showErrorReply("error_delete_empty");
            } catch (NumberFormatException e) {
                return gui.showErrorReply("error_delete_no_meaning");
            } catch (NullPointerException e) {
                return gui.showErrorReply("error_delete_non_existed_task");
            } catch (IndexOutOfBoundsException e) {
                return gui.showErrorReply("error_delete_non_existed_task");
            } catch (IOException e) {
                e.printStackTrace();
            }
            break;
        case "date":
            try {
                Integer NoOfTask = Integer.parseInt(description) - 1;
                tasks.getTask(NoOfTask);
                return gui.showDateReply(NoOfTask, tasks);
            } catch (ArrayIndexOutOfBoundsException e) {
                return gui.showErrorReply("error_date_empty");
            } catch (NumberFormatException e) {
                return gui.showErrorReply("error_date_no_meaning");
            } catch (NullPointerException e) {
                return gui.showErrorReply("error_date_non_existed_task");
            } catch (IndexOutOfBoundsException e) {
                return gui.showErrorReply("error_date_non_existed_task");
            }
        case "todo":
            try {
                description.equals(null);
            } catch (NullPointerException e) {
                return gui.showErrorReply("error_todo_empty");
            }
            tasks.addTask(new Todo(description));
            storage.updateFile(tasks);
            return gui.showCommandReply(description, tasks);
        case "deadline":
            try {
                descriptionSplit = description.split("/by");
            } catch (NullPointerException e) {
                return gui.showErrorReply("error_deadline_empty");
            }
            try {
                tasks.addTask(new Deadline(descriptionSplit[0].trim(), descriptionSplit[1].trim()));
                storage.updateFile(tasks);
                return gui.showCommandReply(command, tasks);
            } catch (ArrayIndexOutOfBoundsException e) {
                return gui.showErrorReply("error_deadline_by");
            } catch (DateTimeParseException e) {
                return gui.showErrorReply("error_deadline_by");
            }
        case "event":
            try {
                descriptionSplit = description.split("/at");
            } catch (NullPointerException e) {
                return gui.showErrorReply("error_event_empty");
            }
            try {
                tasks.addTask(new Event(descriptionSplit[0].trim(), descriptionSplit[1].trim()));
                storage.updateFile(tasks);
                return gui.showCommandReply(command, tasks);
            } catch (ArrayIndexOutOfBoundsException e) {
                return gui.showErrorReply("error_event_at");
            } catch (DateTimeParseException e) {
                return gui.showErrorReply("error_event_at");
            }
        case "find":
            try {
                String[] result = tasks.findTask(description);
                return gui.showFindReply(result);
            } catch (NullPointerException e) {
                return gui.showErrorReply("find_empty");
            }
        case "schedule":
            ArrayList <ArrayList<String> > scheduleTask = tasks.scheduleTask();
            return gui.showScheduleReply(scheduleTask);
        default:
            return gui.showErrorReply("error_no_meaning");
        }

        return "error";
    }
}
