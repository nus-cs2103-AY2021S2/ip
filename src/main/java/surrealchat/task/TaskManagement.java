package surrealchat.task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Handles storing of tasks and file loading/unloading operations.
 */
public class TaskManagement {
    protected List<Task> taskList;

    /**
     * Creates instance of TaskManagement object.
     * @param taskList List of tasks.
     */
    public TaskManagement(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Returns List of tasks for further processing.
     * @return List of tasks.
     */
    public List<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Returns number of tasks stored.
     * @return Number of tasks stored.
     */
    public int getNumberOfTasks() {
        return this.getTaskList().size();
    }

    /**
     * Adds task to internal task list.
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Changes the description of a task designated by a number.
     * @param taskNumber Position number of task (starting from 1) to be edited.
     * @param newDescription New description of the task.
     * @return Task that has been edited.
     */
    public Task editDescription(int taskNumber, String newDescription) {
        Task editedTask = this.taskList.get(taskNumber - 1).editDescription(newDescription);
        this.taskList.set(taskNumber - 1, editedTask);
        return editedTask;
    }

    /**
     * Toggles a task designated by number between done and undone.
     * @param taskNumber Position number of task (starting from 1) to be marked as done/undone.
     * @return Task that has been marked as done/undone.
     */
    public Task markAsDone(int taskNumber) {
        Task doneTask = this.taskList.get(taskNumber - 1).markAsDone();
        this.taskList.set(taskNumber - 1, doneTask);
        return doneTask;
    }


    /**
     * Deletes a task from the list.
     * @param taskNumber Position number of task (starting from 1) to be deleted.
     * @return Deleted task.
     */
    public Task deleteTask(int taskNumber) {
        return this.taskList.remove(taskNumber - 1);
    }

    private String spellTaskType(String taskType) {
        switch(taskType) {
        case "T":
            return "todo";
        case "D":
            return "deadline";
        case "E":
            return "event";
        default:
            throw new InputMismatchException("The task type in task is invalid. Not Stonks!");
        }
    }

    private String printFileLoadOutput(List<Task> taskList) {
        String outputString = "";
        int total = taskList.size();
        for (int i = 0; i < total; i++) {
            Task task = taskList.get(i);
            String printTaskType = this.spellTaskType(task.getType());
            outputString += String.format("Meme Man has added %s task from file: %s\n", printTaskType, task);
        }
        outputString += String.format("Total number of tasks loaded from file: %s\n", total);
        return outputString;
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
            boolean taskDone = this.parseDoneFromInt(Integer.valueOf(taskComponents[1]));
            String description = taskComponents[2];

            //Convert to Task objects
            switch(taskType) {
            case "T":
                this.addToDoFromFile(description, taskDone);
                break;
            case "D":
                this.addDeadlineFromFile(description, taskDone);
                break;
            case "E":
                this.addEventFromFile(description, taskDone);
                break;
            default:
                throw new InputMismatchException("The task type scanned from file is invalid. Not Stonks!");
            }
        }
        //Obtain list for printing
        List<Task> taskList = this.getTaskList();
        return this.printFileLoadOutput(taskList);
    }

    private boolean parseDoneFromInt(int doneInt) {
        switch(doneInt) {
        case 1:
            return true;
        case 0:
            return false;
        default:
            throw new InputMismatchException(
                    "doneInt is not correct. Check the file to see if doneInt is 0 or 1. Not stonks!");
        }
    }

    private ToDoTask addToDoFromFile(String taskDescription, boolean isDone) {
        if (taskDescription.isEmpty()) {
            throw new NoSuchElementException("Empty todo task description. Not stonks!");
        }

        ToDoTask newTask = ToDoTask.createNewToDoTask(taskDescription.trim());
        this.addTask(newTask);
        return newTask;
    }

    private LocalDate parseDate(String dateString) {
        try {
            return LocalDate.parse(dateString);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Input date format is incorrect. Not stonks!");
        }
    }

    private DeadlineTask addDeadlineFromFile(String taskDescription, boolean isDone) {
        if (taskDescription.isEmpty()) {
            throw new NoSuchElementException("Empty deadline task description. Not stonks!");
        }

        //Split the description into description and deadline
        String[] descriptionSplitArray = taskDescription.split("/by");
        try {
            LocalDate deadlineDate = this.parseDate(descriptionSplitArray[1].trim());

            //Create Deadline task
            DeadlineTask newTask = DeadlineTask.loadDeadlineTaskFromFile(descriptionSplitArray[0].trim(),
                    deadlineDate, isDone);
            this.addTask(newTask);
            return newTask;
        } catch (ArrayIndexOutOfBoundsException e) { //Happens if split does not occur
            throw new ArrayIndexOutOfBoundsException("Wrong formatting. Did you forget to put '/by'? Not stonks!");
        }
    }

    private EventTask addEventFromFile(String taskDescription, boolean isDone) {
        if (taskDescription.isEmpty()) {
            throw new NoSuchElementException("Empty event task description. Not stonks!");
        }

        //Split the description into description and event
        String[] descriptionSplitArray = taskDescription.split("/at");
        try {
            LocalDate eventDate = this.parseDate(descriptionSplitArray[1].trim());

            //Create Event task
            EventTask newTask = EventTask.loadEventTaskFromFile(descriptionSplitArray[0].trim(),
                    eventDate, isDone);
            this.addTask(newTask);
            return newTask;
        } catch (ArrayIndexOutOfBoundsException e) { //Happens if split does not occur
            throw new ArrayIndexOutOfBoundsException("Wrong formatting. Did you forget to put '/at'? Not stonks!");
        }
    }

    /**
     * Generates list of tasks for saving into a file.
     * @return List of tasks in file string format.
     */
    public List<String> convertTasksForFile() {
        List<Task> rawTaskList = this.getTaskList();
        List<String> fileTaskList = new ArrayList<String>();
        for (int i = 0; i < rawTaskList.size(); i++) {
            fileTaskList.add(rawTaskList.get(i).saveTask());
        }
        return fileTaskList;
    }

    /**
     * Converts list of tasks into string form for printing.
     * @return List of tasks in print string format.
     */
    public String listOutTasks() {
        List<Task> rawTaskList = this.getTaskList();
        if (rawTaskList.isEmpty()) {
            throw new NoSuchElementException("I have nothing to print. Not stonks!");
        }
        String outputTasks = "";
        for (int i = 1; i <= rawTaskList.size(); i++) {
            outputTasks += String.format("%d. %s\n", i, rawTaskList.get(i - 1));
        }
        return outputTasks;
    }
}
