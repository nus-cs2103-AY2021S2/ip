package duck.operation;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import duck.task.Deadline;
import duck.task.Event;
import duck.task.TaskList;
import duck.task.Todo;

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
     * execute the done command
     *
     * @param tasks   the task list used to  record various tasks
     * @param gui     deals with interactions with the user
     * @param storage deals with loading tasks from the file and saving tasks in the file
     * @return after executing, get the reply which will show in chat dot
     */

    private String executeDone(TaskList tasks, Gui gui, Storage storage) {
        try {
            Integer noOfTask = Integer.parseInt(description) - 1;
            tasks.getTask(noOfTask).markAsDone();
            storage.updateFile(tasks);
            return gui.getDoneReply(noOfTask, tasks);
        } catch (ArrayIndexOutOfBoundsException e) {
            return gui.getErrorReply("error_done_empty");
        } catch (NumberFormatException e) {
            return gui.getErrorReply("error_done_no_meaning");
        } catch (IndexOutOfBoundsException e) {
            return gui.getErrorReply("error_done_non_existed_task");
        } catch (IOException e) {
            e.printStackTrace();
            return gui.getErrorReply("error_IO");
        }
    }

    /**
     * execute the delete command
     *
     * @param tasks   the task list used to  record various tasks
     * @param gui     deals with interactions with the user
     * @param storage deals with loading tasks from the file and saving tasks in the file
     * @return after executing, get the reply which will show in chat dot
     */
    private String executeDelete(TaskList tasks, Gui gui, Storage storage) {
        try {
            Integer noOfTask = Integer.parseInt(description) - 1;
            tasks.getTask(noOfTask);
            String replyString = gui.getDeleteReply(noOfTask, tasks);
            tasks.removeTask(noOfTask);
            storage.updateFile(tasks);
            return replyString;
        } catch (ArrayIndexOutOfBoundsException e) {
            return gui.getErrorReply("error_delete_empty");
        } catch (NumberFormatException e) {
            return gui.getErrorReply("error_delete_no_meaning");
        } catch (NullPointerException e) {
            return gui.getErrorReply("error_delete_non_existed_task");
        } catch (IndexOutOfBoundsException e) {
            return gui.getErrorReply("error_delete_non_existed_task");
        } catch (IOException e) {
            e.printStackTrace();
            return gui.getErrorReply("error_IO");
        }
    }

    /**
     * execute the date command
     *
     * @param tasks the task list used to  record various tasks
     * @param gui   deals with interactions with the user
     * @return after executing, get the reply which will show in chat dot
     */
    private String executeDate(TaskList tasks, Gui gui) {
        try {
            Integer noOfTask = Integer.parseInt(description) - 1;
            tasks.getTask(noOfTask);
            return gui.getDateReply(noOfTask, tasks);
        } catch (ArrayIndexOutOfBoundsException e) {
            return gui.getErrorReply("error_date_empty");
        } catch (NumberFormatException e) {
            return gui.getErrorReply("error_date_no_meaning");
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            return gui.getErrorReply("error_date_non_existed_task");
        }
    }

    /**
     * execute the todo command
     *
     * @param tasks   the task list used to  record various tasks
     * @param gui     deals with interactions with the user
     * @param storage deals with loading tasks from the file and saving tasks in the file
     * @return after executing, get the reply which will show in chat dot
     */
    private String executeTodo(TaskList tasks, Gui gui, Storage storage) throws IOException {
        try {
            description.equals(null);
        } catch (NullPointerException e) {
            return gui.getErrorReply("error_todo_empty");
        }
        tasks.addTask(new Todo(description));
        storage.updateFile(tasks);
        return gui.getCommandReply(description, tasks);
    }

    /**
     * execute the deadline command
     *
     * @param tasks   the task list used to  record various tasks
     * @param gui     deals with interactions with the user
     * @param storage deals with loading tasks from the file and saving tasks in the file
     * @return after executing, get the reply which will show in chat dot
     */
    private String executeDeadline(TaskList tasks, Gui gui, Storage storage) {
        String[] descriptionSplit;
        try {
            descriptionSplit = description.split("/by");
        } catch (NullPointerException e) {
            return gui.getErrorReply("error_deadline_empty");
        }
        try {
            tasks.addTask(new Deadline(descriptionSplit[0].trim(), descriptionSplit[1].trim()));
            storage.updateFile(tasks);
            return gui.getCommandReply(command, tasks);
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            return gui.getErrorReply("error_deadline_by");
        } catch (IOException e) {
            return gui.getErrorReply("error_IO");
        }
    }

    /**
     * execute the event command
     *
     * @param tasks   the task list used to  record various tasks
     * @param gui     deals with interactions with the user
     * @param storage deals with loading tasks from the file and saving tasks in the file
     * @return after executing, get the reply which will show in chat dot
     */
    private String executeEvent(TaskList tasks, Gui gui, Storage storage) {
        String[] descriptionSplit;
        try {
            descriptionSplit = description.split("/at");
        } catch (NullPointerException e) {
            return gui.getErrorReply("error_event_empty");
        }
        try {
            tasks.addTask(new Event(descriptionSplit[0].trim(), descriptionSplit[1].trim()));
            storage.updateFile(tasks);
            return gui.getCommandReply(command, tasks);
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            return gui.getErrorReply("error_event_at");
        } catch (IOException e) {
            return gui.getErrorReply("error_IO");
        }
    }

    /**
     * execute the find command
     *
     * @param tasks the task list used to  record various tasks
     * @param gui   deals with interactions with the user
     * @return after executing, get the reply which will show in chat dot
     */
    private String executeFind(TaskList tasks, Gui gui) {
        try {
            String[] result = tasks.findTask(description);
            return gui.getFindReply(result);
        } catch (NullPointerException e) {
            return gui.getErrorReply("find_empty");
        }
    }

    /**
     * execute the schedule command
     *
     * @param tasks the task list used to  record various tasks
     * @param gui   deals with interactions with the user
     * @return after executing, get the reply which will show in chat dot
     */
    private String executeSchedule(TaskList tasks, Gui gui) {
        ArrayList<ArrayList<String>> scheduleTask = tasks.scheduleTask();
        return gui.getScheduleReply(scheduleTask);
    }


    /**
     * execute the command
     *
     * @param tasks   the task list used to  record various tasks
     * @param gui     deals with interactions with the user
     * @param storage deals with loading tasks from the file and saving tasks in the file
     * @return after executing, get the reply which will show in chat dot
     * @throws IOException
     */
    public String execute(TaskList tasks, Gui gui, Storage storage) throws IOException {
        assert (!command.equals(null));
        switch (command) {
        case "bye":
        case "list":
        case "hello":
            return gui.getCommandReply(command, tasks);
        case "done":
            return executeDone(tasks, gui, storage);
        case "delete":
            return executeDelete(tasks, gui, storage);
        case "date":
            return executeDate(tasks, gui);
        case "todo":
            return executeTodo(tasks, gui, storage);
        case "deadline":
            return executeDeadline(tasks, gui, storage);
        case "event":
            return executeEvent(tasks, gui, storage);
        case "find":
            return executeFind(tasks, gui);
        case "schedule":
            return executeSchedule(tasks, gui);
        default:
            return gui.getErrorReply("error_no_meaning");
        }
    }
}
