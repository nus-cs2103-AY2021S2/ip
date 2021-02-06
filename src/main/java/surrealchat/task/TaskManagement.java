package surrealchat.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;

import surrealchat.exception.SurrealException;

/**
 * Handles storing of tasks and file loading/unloading operations.
 */
public class TaskManagement {
    protected static final String TODO_TYPE = "T";
    protected static final String DEADLINE_TYPE = "D";
    protected static final String EVENT_TYPE = "E";
    protected final List<Task> taskList; //Protect taskList from being changed to null.

    /**
     * Creates instance of TaskManagement object.
     * @param taskList List of tasks.
     */
    public TaskManagement(List<Task> taskList) {
        assert taskList != null : "Null taskList passed in! Not stonks!\n"; //Protection against null
        this.taskList = taskList;
    }

    /**
     * Returns List of tasks for further processing.
     * @return List of tasks.
     */
    public List<Task> getTaskList() {
        return taskList;
    }

    /**
     * Returns number of tasks stored.
     * @return Number of tasks stored.
     */
    public int getNumberOfTasks() {
        return getTaskList().size();
    }

    /**
     * Adds task to internal task list.
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Changes the description of a task designated by a number.
     * @param taskNumber Position number of task (starting from 1) to be edited.
     * @param newDescription New description of the task.
     * @return Task that has been edited.
     */
    public Task editDescription(int taskNumber, String newDescription) {
        Task editedTask = taskList.get(taskNumber - 1).editDescription(newDescription);
        taskList.set(taskNumber - 1, editedTask);
        return editedTask;
    }

    /**
     * Toggles a task designated by number between done and undone.
     * @param taskNumber Position number of task (starting from 1) to be marked as done/undone.
     * @return Task that has been marked as done/undone.
     */
    public Task markAsDone(int taskNumber) {
        Task doneTask = taskList.get(taskNumber - 1).markAsDone();
        taskList.set(taskNumber - 1, doneTask);
        return doneTask;
    }


    /**
     * Deletes a task from the list.
     * @param taskNumber Position number of task (starting from 1) to be deleted.
     * @return Deleted task.
     */
    public Task deleteTask(int taskNumber) {
        return taskList.remove(taskNumber - 1);
    }

    /**
     * Deletes all tasks from the list.
     * @throws SurrealException If list was empty to begin with.
     */
    public void deleteAllTasks() throws SurrealException {
        if (taskList.isEmpty()) {
            throw new SurrealException("List is already empty. Not stonks!\n");
        }
        taskList.clear();
    }

    private String spellTaskType(String taskType) {
        assert taskType != null : "Somehow there was a null taskType. Not stonks!\n";
        switch(taskType) {
        case TODO_TYPE:
            return "todo";
        case DEADLINE_TYPE:
            return "deadline";
        case EVENT_TYPE:
            return "event";
        default:
            throw new InputMismatchException("The task type in task is invalid. Not Stonks!\n");
        }
    }

    private String printFileLoadOutput(List<Task> taskList) {
        String outputString = "";
        int total = taskList.size();
        for (int i = 0; i < total; i++) {
            Task task = taskList.get(i);
            String printTaskType = spellTaskType(task.getType());
            outputString += String.format("Meme Man has added %s task from file: %s\n", printTaskType, task);
        }
        outputString += String.format("Total number of tasks loaded from file: %s\n", total);
        return outputString;
    }

    private void convertToTasks(String taskType, String description, boolean taskDone) {
        assert taskType != null : "Somehow there was a null taskType. Not stonks!\n";
        assert description != null : "Somehow, description was empty. Not stonks!\n";
        switch(taskType) {
        case TODO_TYPE:
            addToDoFromFile(description, taskDone);
            return;
        case DEADLINE_TYPE:
            addDeadlineFromFile(description, taskDone);
            return;
        case EVENT_TYPE:
            addEventFromFile(description, taskDone);
            return;
        default:
            throw new InputMismatchException("The task type scanned from file is invalid. Not Stonks!\n");
        }
    }

    /**
     * Parses lines that were loaded form file into tasks.
     * @param fileLines Lines from the loaded file.
     * @return String of tasks successfully loaded from files.
     */
    public String parseFileLines(List<String> fileLines) {
        for (int i = 0; i < fileLines.size(); i++) {
            //Parse strings
            String[] taskComponents = fileLines.get(i).split("/split/");
            String taskType = taskComponents[0];
            boolean taskDone = parseDoneFromInt(Integer.valueOf(taskComponents[1]));
            String description = taskComponents[2];

            //Convert to Task objects
            convertToTasks(taskType, description, taskDone);
        }
        //Obtain list for printing
        List<Task> taskList = getTaskList();
        return printFileLoadOutput(taskList);
    }

    private boolean parseDoneFromInt(int doneInt) {
        switch(doneInt) {
        case 1:
            return true;
        case 0:
            return false;
        default:
            throw new InputMismatchException(
                    "doneInt is not correct. Check the file to see if doneInt is 0 or 1. Not stonks!\n");
        }
    }

    private ToDoTask addToDoFromFile(String taskDescription, boolean isDone) {
        if (taskDescription.isEmpty()) {
            throw new NoSuchElementException("Empty todo task description. Not stonks!\n");
        }

        ToDoTask newTask = ToDoTask.loadToDoTaskFromFile(taskDescription.trim(), isDone);
        addTask(newTask);
        return newTask;
    }

    private LocalDateTime parseDate(String dateString) {
        try {
            return LocalDateTime.parse(dateString);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Input date time format is incorrect. Not stonks!\n");
        }
    }

    private DeadlineTask addDeadlineFromFile(String taskDescription, boolean isDone) {
        if (taskDescription.isEmpty()) {
            throw new NoSuchElementException("Empty deadline task description. Not stonks!\n");
        }

        //Split the description into description and deadline
        String[] descriptionSplitArray = taskDescription.split("/by");
        try {
            LocalDateTime deadlineDateTime = parseDate(descriptionSplitArray[1].trim());

            //Create Deadline task
            DeadlineTask newTask = DeadlineTask.loadDeadlineTaskFromFile(descriptionSplitArray[0].trim(),
                    deadlineDateTime, isDone);
            addTask(newTask);
            return newTask;
        } catch (ArrayIndexOutOfBoundsException e) { //Happens if split does not occur
            throw new ArrayIndexOutOfBoundsException("Wrong formatting. Did you forget to put '/by'? Not stonks!\n");
        }
    }

    private EventTask addEventFromFile(String taskDescription, boolean isDone) {
        if (taskDescription.isEmpty()) {
            throw new NoSuchElementException("Empty event task description. Not stonks!\n");
        }

        //Split the description into description and event
        String[] descriptionSplitArray = taskDescription.split("/at");
        try {
            LocalDateTime eventDateTime = parseDate(descriptionSplitArray[1].trim());

            //Create Event task
            EventTask newTask = EventTask.loadEventTaskFromFile(descriptionSplitArray[0].trim(),
                    eventDateTime, isDone);
            addTask(newTask);
            return newTask;
        } catch (ArrayIndexOutOfBoundsException e) { //Happens if split does not occur
            throw new ArrayIndexOutOfBoundsException("Wrong formatting. Did you forget to put '/at'? Not stonks!\n");
        }
    }

    /**
     * Generates list of tasks for saving into a file.
     * @return List of tasks in file string format.
     */
    public List<String> convertTasksForFile() {
        List<Task> rawTaskList = getTaskList();
        List<String> fileTaskList = new ArrayList<String>();
        for (int i = 0; i < rawTaskList.size(); i++) {
            fileTaskList.add(rawTaskList.get(i).saveTask());
        }
        return fileTaskList;
    }

    /**
     * Converts list of tasks into string form for printing.
     * @return List of tasks in print string format.
     * @throws SurrealException If list is empty.
     */
    public String listOutTasks() throws SurrealException {
        List<Task> rawTaskList = getTaskList();
        if (rawTaskList.isEmpty()) {
            throw new SurrealException("I have nothing to print. Not stonks!\n");
        }
        String outputTasks = "";
        for (int i = 1; i <= rawTaskList.size(); i++) {
            outputTasks += String.format("%d. %s\n", i, rawTaskList.get(i - 1));
        }
        return outputTasks;
    }
}
