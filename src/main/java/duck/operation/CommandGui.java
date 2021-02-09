package duck.operation;

import duck.task.Deadline;
import duck.task.Event;
import duck.task.TaskList;
import duck.task.Todo;

import java.io.IOException;
import java.time.format.DateTimeParseException;

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
                tasks.getTask(Integer.parseInt(description) - 1).markAsDone();
                storage.updateFile(tasks);
                return gui.showDoneReply(Integer.parseInt(description) - 1, tasks);
            } catch (ArrayIndexOutOfBoundsException e) {
                gui.showErrorReply("error_done_empty");
            } catch (NumberFormatException e) {
                gui.showErrorReply("error_done_no_meaning");
            } catch (IndexOutOfBoundsException e) {
                gui.showErrorReply("error_done_non_existed_task");
            } catch (IOException e) {
                e.printStackTrace();
            }
            break;
        case "delete":
            try {
                tasks.getTask(Integer.parseInt(description) - 1);
                String replyString = gui.showDeleteReply(Integer.parseInt(description) - 1, tasks);
                tasks.removeTask(Integer.parseInt(description) - 1);
                storage.updateFile(tasks);
                return replyString;
            } catch (ArrayIndexOutOfBoundsException e) {
                gui.showErrorReply("error_delete_empty");
            } catch (NumberFormatException e) {
                gui.showErrorReply("error_delete_no_meaning");
            } catch (NullPointerException e) {
                gui.showErrorReply("error_delete_non_existed_task");
            } catch (IndexOutOfBoundsException e) {
                gui.showErrorReply("error_delete_non_existed_task");
            } catch (IOException e) {
                e.printStackTrace();
            }
            break;
        case "date":
            try {
                tasks.getTask(Integer.parseInt(description) - 1);
                return gui.showDateReply(Integer.parseInt(description) - 1, tasks);
            } catch (ArrayIndexOutOfBoundsException e) {
                gui.showErrorReply("error_date_empty");
            } catch (NumberFormatException e) {
                gui.showErrorReply("error_date_no_meaning");
            } catch (NullPointerException e) {
                gui.showErrorReply("error_date_non_existed_task");
            } catch (IndexOutOfBoundsException e) {
                gui.showErrorReply("error_date_non_existed_task");
            }
            break;
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
        default:
            return gui.showErrorReply("error_no_meaning");
        }
        return "error";
    }


}
