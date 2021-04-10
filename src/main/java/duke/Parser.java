package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import duke.commands.Commands;
import duke.commands.Deadline;
import duke.commands.Event;
import duke.commands.Task;
import duke.commands.ToDo;
import duke.exception.*;


/**
 * Represents a parser that makes sense of user input by reformatting data, making objects etc.
 * Processes commands and returns parsed information
 */
public class Parser {


    /**
     * Processes a "todo" command and returns a ToDo object.
     *
     * @param command command passed in by the user.
     * @return ToDo object.
     * @throws InvalidToDoCommandException if todo command used incorrectly.
     * @see Deadline
     */
    public ToDo parseAddTodo(String command, TaskList userList) throws InvalidToDoCommandException {
        int taskDetailOffset = 5;
        if (command.length() <= taskDetailOffset) {
            throw new InvalidToDoCommandException();
        }
        String toDoTaskDetail = command.substring(taskDetailOffset);
        if (toDoTaskDetail.startsWith(" ")) {
            throw new InvalidToDoCommandException();
        } else {
            ToDo newTodo = new ToDo(toDoTaskDetail);
            userList.addTask(newTodo);
            return newTodo;
        }
    }

    /**
     * Processes a "deadline" command and returns a Deadline object.
     *
     * @param command command passed in by the user.
     * @return Deadline object.
     * @throws InvalidDeadlineCommandException if deadline command used incorrectly.
     * @see Deadline
     */
    public Deadline parseAddDeadline(String command, TaskList userList) throws InvalidDeadlineCommandException {
        String commandAndDetail = command.split(" /by ")[0];
        if (!command.contains(" /by ") || commandAndDetail.length() < "deadline ".length()) {
            throw new InvalidDeadlineCommandException();
        } else {
            Deadline newDeadline = createDeadlineTask(command);
            userList.addTask(newDeadline);
            return newDeadline;
        }
    }

    /**
     * Processes a "event" command and returns an Event object.
     *
     * @param command command passed in by the user.
     * @return Event object.
     * @throws InvalidEventCommandException if event command used incorrectly.
     * @see Event
     */
    public Event parseAddEvent(String command, TaskList userList) throws InvalidEventCommandException {
        String commandAndDetail = command.split(" /at ")[0];
        if (!command.contains(" /at ") || commandAndDetail.length() < "event ".length()) {
            throw new InvalidEventCommandException();
        } else {
            Event newEvent = this.createEventTask(command);
            userList.addTask(newEvent);
            return newEvent;
        }
    }

    /**
     * Processes a "delete" command and returns the index of the Task to be deleted from Task List.
     *
     * @param command command passed in by the user.
     * @return Index of Task to be deleted.
     */
    public Task parseDeleteCommand(String command, TaskList userList) throws InvalidDeleteCommandException {
        if (command.length() <= "delete ".length()) {
            throw new InvalidDeleteCommandException();
        }
        int taskNumToBeDeleted = Integer.parseInt(command.split(" ")[1]);
        int indexNum = taskNumToBeDeleted - 1;
        int userListSize = userList.getTaskListSize();
        if (taskNumToBeDeleted > userListSize) {
            throw new InvalidDeleteCommandException();
        } else {
            Task deletedTask = userList.removeTask(indexNum);
            deletedTask.markAsDone();
            return deletedTask;
        }
    }

    /**
     * Processes a "find" command and returns a TaskList object containing all Tasks that match the string searched.
     *
     * @param command  command passed in by the user.
     * @param userList TaskList of the user.
     * @return TaskList containing tasks for which the keywords are matched.
     * @throws InvalidFindCommandException if find command in incorrect format.
     */
    public TaskList parseFindCommand(String command, TaskList userList) throws InvalidFindCommandException {
        if (command.length() < 5) {
            throw new InvalidFindCommandException();
        } else {
            String keywords = command.substring(5);
            ArrayList<Task> results = new ArrayList<>();
            for (Task task : userList.getTaskList()) {
                if (task.getTaskDetail().contains(keywords)) {
                    results.add(task);
                }
            }
            return new TaskList(results);
        }
    }

    public Task parseDoneCommand(int taskNumber, TaskList userList) throws InvalidDoneCommandException {
        int userListLength = userList.getTaskListSize();
        if (taskNumber > userListLength || taskNumber < 1) {
            throw new InvalidDoneCommandException();
        } else {
            Task doneTask = userList.getTask(taskNumber - 1);
            doneTask.markAsDone();
            return doneTask;
        }
    }

    private ToDo createTodoTask (String command) {
        int taskDetailOffset = 5;
        String taskDetail = command.substring(taskDetailOffset);
        ToDo toDoTask = new ToDo(taskDetail);
        return toDoTask;
    }

    private Event createEventTask (String command) throws InvalidEventCommandException {
        String[] eventTimeAndTask = command.split(" /at ");
        try {
            LocalDate eventDate = LocalDate.parse(eventTimeAndTask[1]);

            int eventDetailStartIndexOffset = 6;
            String eventDetails = eventTimeAndTask[0].substring(eventDetailStartIndexOffset);

            return new Event(eventDate, eventDetails);
        } catch (DateTimeParseException e) {
            throw new InvalidEventCommandException();
        }
    }

    private Deadline createDeadlineTask (String command) throws InvalidDeadlineCommandException {
        String[] deadlineAndTask = command.split(" /by ");
        try {
            LocalDate date = LocalDate.parse(deadlineAndTask[1]);
            int deadlineDetailStartIndexOffset = 9;
            return new Deadline(date, deadlineAndTask[0].substring(deadlineDetailStartIndexOffset));
        } catch (DateTimeParseException e) {
            throw new InvalidDeadlineCommandException();
        }
    }

    public boolean parseUndoCommand (String lastCommand, Task deletedTask, TaskList userList) throws DukeException {
        String keyWord = lastCommand.split(" ")[0];
        String keyWordToCompare = keyWord.toUpperCase();

        boolean isUndoSuccessful = false;
        int lastTaskIndex = userList.getTaskListSize() - 1;

        if (keyWord.equals("")) {
            isUndoSuccessful = false;
        } else {
            switch (Commands.valueOf(keyWordToCompare)) {
            case TODO:
                ToDo toDoTask = createTodoTask(lastCommand);
                if (userList.checkTaskPresent(toDoTask)) {
                    userList.removeTask(lastTaskIndex);
                    isUndoSuccessful = true;
                }
                break;
            case DEADLINE:
                Deadline deadlineTask = createDeadlineTask(lastCommand);
                if (userList.checkTaskPresent(deadlineTask)) {
                    userList.removeTask(lastTaskIndex);
                    isUndoSuccessful = true;
                }
                break;
            case EVENT:
                Event eventTask = createEventTask(lastCommand);
                if (userList.checkTaskPresent(eventTask)) {
                    userList.removeTask(lastTaskIndex);
                    isUndoSuccessful = true;
                }
                break;
            case DONE:
                int taskNumber = Integer.parseInt(lastCommand.split(" ")[1]);
                Task taskToMarkUndone = userList.getTask(taskNumber - 1);
                taskToMarkUndone.markAsUndone();
                isUndoSuccessful = true;
                break;
            case DELETE:
                if (deletedTask instanceof ToDo || deletedTask instanceof Event || deletedTask instanceof Deadline) {
                    userList.addTask(deletedTask);
                    deletedTask.markAsUndone();
                    isUndoSuccessful = true;
                }
                break;
            default:
                // do nothing
            }
        }
        return isUndoSuccessful;
    }
}
