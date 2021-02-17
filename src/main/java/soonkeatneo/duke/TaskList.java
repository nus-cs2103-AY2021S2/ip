package soonkeatneo.duke;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import soonkeatneo.duke.task.Deadline;
import soonkeatneo.duke.task.Event;
import soonkeatneo.duke.task.Task;
import soonkeatneo.duke.task.Todo;

/**
 * Stores and manages the list of {@Task} created.
 * @author Soon Keat Neo
 * @version CS2103T AY20/21 Sem 2 iP
 */
public class TaskList {
    private static List<Task> taskList;

    /**
     * Creates a new task list without reading from existing storage.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Overloaded constructor creating a new task list reading from existing {@Storage}.
     * @param storage Storage object to be read from
     */
    public TaskList(Storage storage) {
        this.taskList = new ArrayList<>();
        initializeTasks(storage.read());
    }

    /**
     * Prints a list of {@Task} matching the given string.
     * @param inputString the String to search for
     */
    public String find(String inputString) {
        List<Task> newList = new ArrayList<>();
        boolean hasPrintedTasks = false;
        int counter = 0;
        String message = "";
        for (int i = 0; i < this.taskList.size(); i++) {
            Task task = this.taskList.get(i);
            if (task.getDescription().toLowerCase().contains(inputString.toLowerCase())) {
                if (!hasPrintedTasks) {
                    message += "Here are the list of matching tasks: \n";
                    hasPrintedTasks = true;
                }
                message += (counter + 1) + ". " + task.toString() + "\n";
                counter += 1;
            }
        }
        if (!hasPrintedTasks) {
            message += "There are no matching tasks!";
        }
        return message;
    }

    /**
     * Adds a new {@Task} to the task list.
     * @param task Given task to be added to the task list
     */
    public String addTask(Task task) {
        taskList.add(task);
        String message = "Wakarimashita! Task added to list:\n";
        message += task.toString();
        message += "\nThe size of your task list is now: " + this.getSize();
        return message;
    }

    /**
     * Mark the specified task as complete.
     * @param inputString User input string
     */
    public String completeTask(String inputString, Storage storage) {
        String message = "";
        try {
            int taskId = Integer.parseInt(String.valueOf(inputString.split(" ")[1])) - 1;
            Task doneTask = taskList.get(taskId).setDone();
            String typeOfTask = doneTask.getType();
            String completionOfTask = (doneTask.getDone() ? "1" : "0");
            String descriptionOfTask = doneTask.getDescription().strip();
            LocalDate date = LocalDate.now();
            if (doneTask instanceof Event) {
                date = ((Event) doneTask).getDate();
            } else if (doneTask instanceof Deadline) {
                date = ((Deadline) doneTask).getDate();
            }
            String oldString = typeOfTask + " | " + "0" + " | " + descriptionOfTask;
            String newString = typeOfTask + " | " + completionOfTask + " | " + descriptionOfTask;
            if (doneTask instanceof Event || doneTask instanceof Deadline) {
                oldString += " | " + date.toString();
                newString += " | " + date.toString();
            }
            storage.deleteReplaceTaskFromDisk(oldString, newString);
            message += "Great~! Task completed:\n";
            message += doneTask.toString();
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskException();
        }
        return message;
    }

    /**
     * Clears all tasks from the list.
     * @param storage Storage object to remove from disk
     * @return String representing output of success
     */
    public String clear(Storage storage) {
        List<Task> temp = new ArrayList<>();
        for (Task task : taskList) {
            temp.add(task);
        }
        for (Task task : temp) {
            String stringDesc = "delete " + (taskList.indexOf(task) + 1);
            deleteTask(stringDesc, storage);
        }
        return "Successfully cleared all tasks from the task list!";
    }

    /**
     * Delete the specified task from the list.
     * @param inputString User input string
     * @param storage Storage object to remove from disk
     */
    public String deleteTask(String inputString, Storage storage) {
        String message = "";
        try {
            int taskId = Integer.parseInt(String.valueOf(inputString.split(" ")[1])) - 1;
            Task deletedTask = taskList.remove(taskId);
            String typeOfTask = deletedTask.getType();
            String completionOfTask = (deletedTask.getDone() ? "1" : "0");
            String descriptionOfTask = deletedTask.getDescription().strip();
            String oldString = typeOfTask + " | " + completionOfTask + " | " + descriptionOfTask;
            if (deletedTask.getType() == "E") {
                Event eventTask = (Event) deletedTask;
                oldString += " | " + eventTask.getDate();
            } else if (deletedTask.getType() == "D") {
                Deadline deadlineTask = (Deadline) deletedTask;
                oldString += " | " + deadlineTask.getDate();
            }
            storage.deleteReplaceTaskFromDisk(oldString, "");
            message += "Okie! I've deleted the task from your list:\n";
            message += deletedTask.toString();
            message += "\nThe size of your task list is now: " + taskList.size();
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskException();
        }
        return message;
    }

    /**
     * Prints the list of tasks in the list, including the status.
     */
    public String print() {
        if (taskList.size() < 1) {
            return "There are no tasks in your list! :c";
        }
        String message = "Tasks in your list are~: \n";
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            message += (i + 1) + "." + task + "\n";
        }
        return message;
    }

    /**
     * Gets the current size of the task list.
     * @return An integer representing the number of items in the list
     */
    public int getSize() {
        return taskList.size();
    }

    /**
     * Initializes the task list from the data file.
     * @param fileContents The contents of the file in a given List
     */
    public void initializeTasks(List<String> fileContents) {
        for (int i = 0; i < fileContents.size(); i++) {
            String line = fileContents.get(i);
            if (line.length() < 1) {
                continue;
            }
            String[] lineArray = line.split("\\|");
            boolean isDone = false;
            if (lineArray[1].strip().charAt(0) == '1') {
                isDone = true;
            }
            if (lineArray[0].strip().charAt(0) == 'T') {
                // todo
                Todo newTodo = new Todo(lineArray[2].strip());
                if (isDone) {
                    newTodo.setDone();
                }
                taskList.add(newTodo);
            } else if (lineArray[0].strip().charAt(0) == 'D') {
                // deadline
                Deadline newDeadline = new Deadline(lineArray[2].strip(), lineArray[3].strip());
                if (isDone) {
                    newDeadline.setDone();
                }
                taskList.add(newDeadline);
            } else if (lineArray[0].strip().charAt(0) == 'E') {
                // event
                Event newEvent = new Event(lineArray[2].strip(), lineArray[3].strip());
                if (isDone) {
                    newEvent.setDone();
                }
                taskList.add(newEvent);
            }
        }

    }
}
