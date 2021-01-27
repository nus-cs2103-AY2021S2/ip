package surrealchat.task;

import surrealchat.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Handles intermediary operations between TaskManagement and other classes.
 */
public class TaskParser {
    protected TaskManagement taskManagement;
    protected static final int TASK_UNDONE = 0;

    /**
     * Creates new TaskParser object.
     */
    public TaskParser() {
        this.taskManagement = new TaskManagement(new ArrayList<Task>());
    }

    /**
     * Parses isDone int into boolean.
     * @param isDone Integer indicating whether task is completed or not.
     * @return true if isDone == 1, false if isDone == 0.
     */
    public boolean parseIsDoneInt(int isDone) {
        switch(isDone) {
        case 0:
            return false;
        case 1:
            return true;
        default:
            throw new InputMismatchException("Int has to be 0 or 1. Not stonks!");
        }
    }

    /**
     * Converts tasks in file string format to Task objects.
     * @param fileLines List of tasks in file string format.
     * @return Pair of a "fileTasksAdded" string and a list of Task objects.
     */
    public Pair<String, List<Task>> parseFileLines(List<String> fileLines) {
        for (int i = 0; i < fileLines.size(); i++) {
            //Parse strings
            String[] taskComponents = fileLines.get(i).split("/split/");
            String taskType = taskComponents[0];
            int taskDone = Integer.valueOf(taskComponents[1]);
            String description = taskComponents[2];

            //Convert to Task objects
            switch(taskType) {
            case "T":
                this.addToDo(description, taskDone);
                break;
            case "D":
                this.addDeadline(description, taskDone);
                break;
            case "E":
                this.addEvent(description, taskDone);
                break;
            default:
                throw new InputMismatchException("The task type scanned from file is invalid. Not Stonks!");
            }
        }
        //Obtain list for printing
        List<Task> taskList = this.taskManagement.getTaskList();
        return new Pair<String, List<Task>>("fileTasksAdded", taskList);
    }

    /**
     * Returns total number of tasks.
     * @return Total number of tasks.
     */
    public int getNumberOfTasks() {
        return this.taskManagement.getTaskList().size();
    }

    /**
     * Converts a task from user input into Task objects.
     * @param command Type of task.
     * @param taskDescription
     * @return Pair consisting of a "userTaskAdded" string and another pair of the added Task object and total number of tasks.
     */
    public Pair<String, Pair<Task, Integer>> parseUserTaskInput(String command, String taskDescription) {
        if (taskDescription.isEmpty()) {
            throw new InputMismatchException("Empty " + command + " task description. Not stonks!");
        } else {
            Task addedTask;

            //Convert to Task object
            switch(command) {
            case "todo":
                addedTask = this.addToDo(taskDescription, this.TASK_UNDONE);
                break;
            case "deadline":
                addedTask = this.addDeadline(taskDescription, this.TASK_UNDONE);
                break;
            case "event":
                addedTask = this.addEvent(taskDescription, this.TASK_UNDONE);
                break;
            default:
                throw new InputMismatchException("Somehow, a wrong command was entered. " +
                        "Command has to be task type. Not stonks!");
            }

            //Output pair for printing
            int numberOfTasks = this.getNumberOfTasks();
            return new Pair<String, Pair<Task, Integer>>("userTaskAdded",
                    new Pair<Task, Integer>(addedTask, numberOfTasks));
        }

    }

    private ToDoTask addToDo(String taskDescription, int isDone) {
        if (taskDescription.isEmpty()) {
            throw new NoSuchElementException("Empty todo task description. Not stonks!");
        } else {
            ToDoTask newTask = new ToDoTask(taskDescription.trim(), this.parseIsDoneInt(isDone));
            this.taskManagement.addTask(newTask);
            return newTask;
        }
    }

    private LocalDate parseDate(String dateString) {
        try {
            return LocalDate.parse(dateString);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Input date format is incorrect. Not stonks!");
        }
    }

    private DeadlineTask addDeadline(String taskDescription, int isDone) {
        if (taskDescription.isEmpty()) {
            throw new NoSuchElementException("Empty deadline task description. Not stonks!");
        } else {
            //Split the description into description and deadline
            String[] descriptionSplitArray = taskDescription.split("/by");
            try {
                LocalDate deadlineDate = this.parseDate(descriptionSplitArray[1].trim());

                //Create Deadline task
                DeadlineTask newTask = new DeadlineTask(descriptionSplitArray[0].trim(),
                        deadlineDate, this.parseIsDoneInt(isDone));
                this.taskManagement.addTask(newTask);
                return newTask;
            } catch (ArrayIndexOutOfBoundsException e) { //Happens if split does not occur
                throw new ArrayIndexOutOfBoundsException("Wrong formatting. Did you forget to put '/by'? Not stonks!");
            }
        }
    }

    private EventTask addEvent(String taskDescription, int isDone) {
        if (taskDescription.isEmpty()) {
            throw new NoSuchElementException("Empty event task description. Not stonks!");
        } else {
            //Split the description into description and event
            String[] descriptionSplitArray = taskDescription.split("/at");
            try {
                LocalDate eventDate = this.parseDate(descriptionSplitArray[1].trim());

                //Create Event task
                EventTask newTask = new EventTask(descriptionSplitArray[0].trim(),
                        eventDate, this.parseIsDoneInt(isDone));
                this.taskManagement.addTask(newTask);
                return newTask;
            } catch (ArrayIndexOutOfBoundsException e) { //Happens if split does not occur
                throw new ArrayIndexOutOfBoundsException("Wrong formatting. Did you forget to put '/at'? Not stonks!");
            }

        }
    }

    private boolean checkInvalidTaskNumber(int taskNumber) {
        return ((taskNumber <= 0) || (taskNumber > this.taskManagement.getTaskList().size()));
    }

    /**
     * Edits the description of a task.
     * @param rawDescription A raw user input with both task number and new description.
     * @return Pair of "editDescript" string and the edited task.
     */
    public Pair<String, Task> editDescription(String rawDescription) {
        if (rawDescription.isEmpty()) {
            throw new NoSuchElementException("Did you forget to add the task number and new description? Not stonks!");
        } else {
            //Split the description into task number and description
            String[] descriptionSplitArray = rawDescription.split("/edit");
            try {
                int taskNumber = Integer.valueOf(descriptionSplitArray[0].trim());
                String newDescription = descriptionSplitArray[1].trim();
                //Edit task description
                if (this.checkInvalidTaskNumber(taskNumber)) {
                    throw new IllegalArgumentException("Invalid task number. Not stonks!");
                } else if (newDescription.isEmpty()) {
                    throw new IllegalArgumentException("No description provided for editing. Not stonks!");
                } else {
                    Task editedTask = this.taskManagement.editDescription(taskNumber, newDescription);
                    return new Pair<String, Task>("editDescript", editedTask);
                }
            } catch (NumberFormatException e) { //Can happen if clean split does not occur.
                throw new NumberFormatException(
                        "Task number not parsed. Did you forget to put '/edit'? Or did you not put a number? Not stonks!");
            }
            catch (ArrayIndexOutOfBoundsException e) { //Happens if split does not occur.
                throw new ArrayIndexOutOfBoundsException(
                        "Wrong formatting. Did you forget to put '/edit' and/or the description? Not stonks!");
            }
        }
    }

    /**
     * Marks a task designated by number as done.
     * @param taskNumber Position number of task (starting from 1) to be marked as done.
     * @return Pair of "markDone" string and the task marked as done.
     */
    public Pair<String, Task> markAsDone(int taskNumber) {
        if (this.checkInvalidTaskNumber(taskNumber)) {
            throw new IllegalArgumentException("Invalid task number. Not stonks!");
        } else {
            Task doneTask = this.taskManagement.markAsDone(taskNumber);
            return new Pair<String, Task>("markDone", doneTask);
        }
    }

    /**
     * Marks a task designated by number as undone.
     * @param taskNumber Position number of task (starting from 1) to be marked as undone.
     * @return Pair of "markunDone" string and the task marked as undone.
     */
    public Pair<String, Task> markAsUndone(int taskNumber) {
        if (this.checkInvalidTaskNumber(taskNumber)) {
            throw new IllegalArgumentException("Invalid task number. Not stonks!");
        } else {
            Task undoneTask = this.taskManagement.markAsUndone(taskNumber);
            return new Pair<String, Task>("markUndone", undoneTask);
        }
    }

    /**
     * Deletes a task from the list.
     * @param taskNumber Position number of task (starting from 1) to be deleted.
     * @return Pair of "deleteTask" string and another pair of the deleted Task and total number of tasks remaining.
     */
    public Pair<String, Pair<Task, Integer>> deleteTask(int taskNumber) {
        if (this.checkInvalidTaskNumber(taskNumber)) {
            throw new IllegalArgumentException("Invalid task number. Not stonks!");
        } else {
            Task deletedTask = this.taskManagement.deleteTask(taskNumber);
            Pair<String, Pair<Task, Integer>> deletePair = new Pair<String, Pair<Task, Integer>>("deleteTask",
                    new Pair<Task, Integer>(deletedTask, this.getNumberOfTasks()));
            return deletePair;
        }
    }

    /**
     * Searches for tasks based on whether task contains keyword. Is case-insensitive.
     * @param keyword Keyword to search for tasks.
     * @return A pair of "searchTasks" string and a list of tasks containing keyword.
     */
    public Pair<String, List<Task>> searchTasks(String keyword) {
        if (keyword.isEmpty()) {
            throw new IllegalArgumentException("No keyword given! Not stonks!");
        } else {
            List<Task> searchResults = new ArrayList<Task>();
            List<Task> allTasks = this.taskManagement.getTaskList();
            for (int i = 0; i < allTasks.size(); i++) {
                Task task = allTasks.get(i);
                if (task.toString().toLowerCase().contains(keyword.toLowerCase())) {
                    searchResults.add(task);
                }
            }
            return new Pair<String, List<Task>>("searchTasks", searchResults);
        }
    }

    /**
     * Generates list of tasks for saving into a file.
     * @return List of tasks in file string format.
     */
    public List<String> convertTasksForFile() {
        List<Task> rawTaskList = this.taskManagement.getTaskList();
        List<String> fileTaskList = new ArrayList<String>();
        for (int i = 0; i < rawTaskList.size(); i++) {
            fileTaskList.add(rawTaskList.get(i).saveTask());
        }
        return fileTaskList;
    }

    /**
     * Generates list of tasks with tag for print by UserOutput.
     * @return Pair of "printTaskList" string and list of tasks.
     */
    public Pair<String, List<Task>> sendListToPrint() {
        List<Task> rawTaskList = this.taskManagement.getTaskList();
        return new Pair<String, List<Task>>("printTaskList", rawTaskList);
    }
}
