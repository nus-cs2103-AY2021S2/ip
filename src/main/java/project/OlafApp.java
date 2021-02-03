package project;

import project.common.PrintText;
import project.io.Parser;
import project.io.Ui;
import project.storage.Storage;
import project.task.Deadline;
import project.task.Event;
import project.task.Task;
import project.task.TaskList;
import project.task.Todo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;

/**
 * Handles application logic.
 */
public class OlafApp {
    private TaskList taskList;
    private Ui ui;
    private Storage storage;
    private boolean isActive;

    /**
     * Creates an instance of {@code OlafApp}.
     *
     * @param tasks All the tasks stored.
     * @param ui A {@code Ui} instance to handle test output.
     * @param storage A {@code Storage} instance to handle saving and loading tasks from local file.
     */
    OlafApp(TaskList tasks, Ui ui, Storage storage) {
        this.taskList = tasks;
        this.ui = ui;
        this.storage = storage;
        this.isActive = true;
    }

    /**
     * Executes each session of the application.
     *
     * @param command
     */
    public String run(String command) {
        while (isActive) {
            // is while loop needed here??
            assert command != null;

            if (command.equalsIgnoreCase("bye")) {
                this.stop();
            } else if (command.equalsIgnoreCase("list")) {
                // todo: use try catch here
                if (taskList.hasTasks()) {
                    return ui.showList(taskList);
                } else {
                    return PrintText.EMPTY_TASKLIST_ERROR.toString();
                }
            } else if (command.toLowerCase().startsWith("find")) {
                String expression = Parser.parseParameter(command, " ", 1);
                TaskList matches = taskList.findTasks(expression);

                if (matches.hasTasks()) {
                    // todo: index of matches List should follow original taskList
                    String output = "Here are the tasks that match your search:\n\n" + matches.toString();
                    return ui.showFormatResponse(output);
                } else {
                    return ui.showFormatResponse("No tasks match your search...");
                }
            } else if (command.toLowerCase().startsWith("done")) {
                try {
                    int id = Parser.parseIntParameter(command);
                    taskList.markTaskAsDone(id);

                    storage.saveData(taskList);

                    return ui.showDoneSuccess(id, taskList.getTask(id), taskList.getTotalNumberOfTasksUndone());
                } catch (ArrayIndexOutOfBoundsException e) {
                    return ui.showFormatError(PrintText.DONE_FORMAT);
                } catch (IndexOutOfBoundsException | NumberFormatException e) {
                    return ui.showInvalidIndexError();
                }
            } else if (command.toLowerCase().startsWith("delete")) {
                try {
                    int id = Parser.parseIntParameter(command);
                    Task deleted = taskList.deleteTask(id);

                    storage.saveData(taskList);

                    return ui.showDeleteSuccess(id, deleted, taskList.getTotalNumberOfTasks());
                } catch (ArrayIndexOutOfBoundsException e) {
                    return ui.showFormatError(PrintText.DELETE_FORMAT);
                } catch (IndexOutOfBoundsException | NumberFormatException e) {
                    return ui.showInvalidIndexError();
                }
            } else if (command.toLowerCase().startsWith("todo")) {
                try {
                    String expression = Parser.parseParameter(command, " ", 1);
                    Todo newTodo = new Todo(expression);
                    taskList.addTask(newTodo);

                    storage.saveData(taskList);

                    return ui.showNewTaskAddedSuccess(taskList.getTotalNumberOfTasks(),
                            newTodo, taskList.getTotalNumberOfTasksUndone());
                } catch (IndexOutOfBoundsException e) {
                    return ui.showFormatError(PrintText.TODO_FORMAT);
                }
            } else if (command.toLowerCase().startsWith("deadline")) {
                try {
                    String expression = Parser.parseParameter(command, " ", 1);
                    String description = Parser.parseParameter(expression, "/by", 0);

                    String dateTime = Parser.parseParameter(expression, "/by", 1);
                    LocalDateTime deadline = Parser.parseDateTimeParameter(dateTime);

                    Deadline newDeadline = new Deadline(description, deadline);
                    taskList.addTask(newDeadline);

                    storage.saveData(taskList);

                    return ui.showNewTaskAddedSuccess(taskList.getTotalNumberOfTasks(),
                            newDeadline, taskList.getTotalNumberOfTasksUndone());
                } catch (Exception e) {
                    // catches both ParseException and IndexOutOfBounds exception
                    return ui.showFormatError(PrintText.DEADLINE_FORMAT);
                }
            } else if (command.toLowerCase().startsWith("event")) {
                try {
                    String expression = Parser.parseParameter(command, " ", 1);
                    String description = Parser.parseParameter(expression, "/at", 0);

                    String dateTime = Parser.parseParameter(expression, "/at", 1);
                    String start = Parser.parseParameter(dateTime, " to ", 0);
                    String end = Parser.parseParameter(dateTime, " to ", 1);

                    LocalDateTime startDateTime = Parser.parseDateTimeParameter(start);
                    LocalDateTime endDateTime = Parser.parseDateTimeParameter(end);

                    Event newEvent = new Event(description, startDateTime, endDateTime);
                    taskList.addTask(newEvent);

                    storage.saveData(taskList);

                    return ui.showNewTaskAddedSuccess(taskList.getTotalNumberOfTasks(),
                            newEvent, taskList.getTotalNumberOfTasksUndone());
                } catch (Exception e) {
                    // catches both ParseException and IndexOutOfBounds exception
                    return ui.showFormatError(PrintText.EVENT_FORMAT);
                }
            } else if (command.equalsIgnoreCase("help")) {
                return ui.showFormatResponse(PrintText.HELP);
            } else {
                return ui.showFormatResponse("  Hmm sorry I don't understand :(\n"
                        + "  Type 'help' to find out how you can talk to me!\n");
            }
        }
        return "could you repeat that?";
    }

    private void stop() {
        isActive = false;
    }
}
