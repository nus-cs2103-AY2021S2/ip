package mainclasses;

import Helper.SortByDateTime;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class TaskList {
    private List<Task> newStorage;

    public TaskList(List<Task> initialisedStorage) {
        this.newStorage = initialisedStorage;
    }


    /**
     * Prints the task in a list format, includes description, task type and whether task is completed
     * @param num int index of task in the list
     * @param task the task to be printed out
     */
    public String printTask(int num, Task task) {
        if (task.getDone()) {
            return num + 1 + "." + "[" + task.type + "]" + "[X] " + this.newStorage.get(num).getDescription();
        } else {
            return num + 1 + "." + "[" + task.type + "]" + "[ ] " + this.newStorage.get(num).getDescription();
        }
    }

    public String printTaskFromExternalList(List<Task> tasks) {
        String newString = "";
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDone()) {
                newString += i + 1 + "." + "[" + tasks.get(i).type + "]" + "[X] " + tasks.get(i).getDescription() + "\n";
            } else {
                newString += i + 1 + "." + "[" + tasks.get(i).type + "]" + "[ ] " + tasks.get(i).getDescription() + "\n";
            }
        }
        return newString;
    }
    /**
     * Prints the task which includes description, task type and whether task is completed
     * @param task the task to be printed out
     */
    public String printTaskWithNoNum(Task task) {
        return "[" + task.type + "]" + "[ ] " + task.getDescription();
    }

    /**
     * Adds a task to the list of task in storage
     * @param userInput the user input in text string format
     */
    public String addTask(String userInput) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("Hmm");
        Task task;
        String[] input = userInput.split(" ");
        if (input[0].equals("todo")) {
            String description = userInput.substring(userInput.indexOf("todo") + 5);
            task = new Todo(description);
        } else if (input[0].equals("deadline")) {
            String description = userInput.substring(userInput.indexOf("deadline") + 9, userInput.indexOf("/by") - 1);
            String deadlineDate = userInput.substring(userInput.indexOf("/by") + 4, userInput.indexOf("/by") + 14);
            LocalDate date = LocalDate.parse(deadlineDate, formatter);
            String deadLineTime = userInput.substring(userInput.indexOf("/by") + 15);
            LocalTime time = LocalTime.parse(deadLineTime, timeFormatter);
            task = new Deadline(description, date, time);
        } else {
            String description = userInput.substring(userInput.indexOf("event") + 6, userInput.indexOf("/at") - 1);
            String eventDate = userInput.substring(userInput.indexOf("/at") + 4, userInput.indexOf("/at") + 14);
            LocalDate date = LocalDate.parse(eventDate, formatter);
            String eventTime = userInput.substring(userInput.indexOf("/at") + 15);
            LocalTime time = LocalTime.parse(eventTime, timeFormatter);
            task = new Event(description, date, time);
        }
        newStorage.add(task);
        return ("Got it. I've added this task: \n") + printTaskWithNoNum(task) + "\n"
                + ("Now you have " + this.newStorage.size() + " tasks in the list.");
    }

    /**
     * List out of the task that are in the task list storage
     * @param tasks List of tasks
     */
    public String listTask(List<Task> tasks) {
        String newListString = "";
        for (int i = 0; i < tasks.size(); i++) {
            newListString += (printTask(i, tasks.get(i)) + "\n");
        }
        return ("Here are the tasks in your list: \n") + newListString;
    }

    /**
     * Set a specific task as completed based on the index of task in task list storage
     * @param value int index of the task in list
     */
    public String setTaskAsDone(int value) {
        this.newStorage.get(value).setDone(true);
        return ("Nice! I've marked this task as done: \n") + printTask(value, newStorage.get(value));
    }

    /**
     * Deletes a task based on string input of user
     * @param userInput user input in string text
     */
    public String deleteTask(String userInput) {
        String[] inputBreakdown = userInput.split(" ");
        int taskToBeDeleted = Integer.valueOf(inputBreakdown[1]) - 1;
        Task task = this.newStorage.get(taskToBeDeleted);
        this.newStorage.remove(taskToBeDeleted);
        return ("Noted. I've removed this task: \n") + printTaskWithNoNum(task) + "\n"
                + ("Now you have " + this.newStorage.size() + " tasks in the list.");
    }

    public List<Task> getNewStorage() {
        return newStorage;
    }

    /**
     * Finds Tasks which description contains a certain keyword
     * @param keyWord This is the keyword used to filter the list of tasks
     */
    public String findTasksWithKeyword(String keyWord) {
        List<Task> tasks = this.getNewStorage();
        String newStringLine = "Here are the matching tasks in your list:" + "\n";
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDescription().contains(keyWord)) {
                newStringLine += (printTask(i, tasks.get(i)) + "\n");
            }
        }
        return newStringLine;
    }

    public String sortTask(String taskType, String sortBy) {
        TaskEnum taskTypeEnum = TaskEnum.valueOf(taskType);
        List<Task> tasksToBeSorted = new ArrayList<>();
        for (Task task : this.getNewStorage()) {
            if (task.getType() == taskTypeEnum) {
                tasksToBeSorted.add(task);
            }
        }
        String newStringLine = "Here are the tasks sorted by " + sortBy + "\n";
        if (sortBy.equals("date")) {
            tasksToBeSorted = sortByDateTime(tasksToBeSorted);
        }
        newStringLine += printTaskFromExternalList(tasksToBeSorted);
        return newStringLine;
    }

    private List<Task> sortByDateTime(List<Task> tasks) {
        List<Task> taskList = new ArrayList<>();
        Collections.sort(tasks, new SortByDateTime());
        taskList = tasks;
        return taskList;
    }

    public void setNewStorage(List<Task> newStorage) {
        this.newStorage = newStorage;
    }
}
