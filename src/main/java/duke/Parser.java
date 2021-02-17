package duke;

import java.time.Month;
import java.util.ArrayList;

/**
 * Represents a parser that makes sense of user input by reformatting data, making objects etc.
 * Processes commands and returns parsed information
 */
public class Parser {

    /**
     * Returns formattedDate in "yyyy-MM-DD" format from unformatted "month day yyyy".
     *
     * @param unformattedDate String in "month day year" format.
     * @return formatted date in "yyyy-MM-DD" format.
     */
    public String parseDate(String unformattedDate) {
        try {
            String[] dateArr = unformattedDate.split(" ");
            String monthString = String.format("%02d", Month.valueOf(dateArr[0].toUpperCase()).getValue());
            String day = String.format("%02d", Integer.parseInt(dateArr[1]));
            String year = dateArr[2];
            String formattedDate = year + "-" + monthString + "-" + day;
            return formattedDate;
        } catch (IllegalArgumentException e) {
            return e.getMessage() + " date input as 'month day year' format ";
        }
    }

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
        if (!command.contains(" /by ")) {
            throw new InvalidDeadlineCommandException();
        } else {
            String[] deadlineAndTask = command.split(" /by ");
            int deadlineDetailStartIndexOffset = 9;
            Deadline newDeadline = new Deadline(deadlineAndTask[1],
                    deadlineAndTask[0].substring(deadlineDetailStartIndexOffset));
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
        if (!command.contains(" /at ")) {
            throw new InvalidEventCommandException();
        } else {
            String[] eventTimeAndTask = command.split(" /at ");
            int eventDetailStartIndexOffset = 6;
            Event newEvent = new Event(eventTimeAndTask[1], eventTimeAndTask[0].substring(eventDetailStartIndexOffset));
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

    private Event createEventTask (String command) {
        String[] eventTimeAndTask = command.split(" /at ");
        int eventDetailOffset = 6;
        return new Event(eventTimeAndTask[1], eventTimeAndTask[0].substring(eventDetailOffset));
    }

    private Deadline createDeadlineTask (String command) {
        String[] deadlineAndTask = command.split(" /by ");
        int deadlineDetailStartIndexOffset = 9;
        return new Deadline(deadlineAndTask[1], deadlineAndTask[0].substring(deadlineDetailStartIndexOffset));
    }

    public boolean parseUndoCommand(String lastCommand, Task lastDeletedTask, TaskList userList) throws Exception {
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
                if (lastDeletedTask instanceof ToDo) {
                    this.parseAddTodo(lastCommand, userList);
                } else if (lastDeletedTask instanceof Event) {
                    this.parseAddEvent(lastCommand, userList);
                } else if (lastDeletedTask instanceof Deadline) {
                    this.parseAddDeadline(lastCommand, userList);
                }
                isUndoSuccessful = true;
                break;
            default:
                //do nothing
            }
        }
        return isUndoSuccessful;
    }
}
