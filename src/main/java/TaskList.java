package main.java;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<String> taskCommands) {
        try {
            tasks = initialiseTasks(taskCommands);
        } catch (EmptyDescriptionException e) {
            tasks = new ArrayList<>();
        }
    }

    public TaskList() {
        tasks = new ArrayList<>();
    }

    private ArrayList<Task> initialiseTasks(ArrayList<String> taskCommands) throws EmptyDescriptionException {
        ArrayList<Task> tasks = new ArrayList<>();
        for (String taskCommand : taskCommands) {
            Task task;
            if (taskCommand.charAt(0) == '#') {
                taskCommand = taskCommand.substring(1);
                task = createTask(taskCommand);
                task.markAsDone();
            } else {
                task = createTask(taskCommand);
            }
            tasks.add(task);
        }
        return tasks;
    }

    private Task createTask(String taskCommand) throws EmptyDescriptionException {
        String[] arr = taskCommand.split(" ", 2);
        if (arr.length == 1) {
            throw new EmptyDescriptionException();
        }
        String type = arr[0];
        String rest = arr[1];
        Task task;
        if (type.equals("todo")) {
            task = new Todo(rest);
        } else if (type.equals("deadline")) {
            String[] temp = rest.split(" /by ");
            String description = temp[0];
            String by = temp[1];
            task = new Deadline(description, by);
        } else {
            String[] temp = rest.split(" /at ");
            String description = temp[0];
            String at = temp[1];
            task = new Event(description, at);
        }
        return task;
    }

    public void addTask(String taskCommand) throws EmptyDescriptionException {
        Task task = createTask(taskCommand);
        tasks.add(task);
    }

    public void deleteTask(int taskId) {
        tasks.remove(taskId - 1);
    }

    public ArrayList<String> getPrintableTasks() {
        ArrayList<String> printableTasks = new ArrayList<>();
        int id = 1;
        for (Task task : tasks) {
            printableTasks.add(id + ". " + task);
            id++;
        }
        return printableTasks;
    }

    public ArrayList<String> getPrintableTasksWithKeyword(String keyword) {
        ArrayList<String> printableTasks = new ArrayList<>();
        int id = 1;
        for (Task task : tasks) {
            if (task.descriptionContains(keyword)) {
                printableTasks.add(id + ". " + task);
                id++;
            }
        }
        return printableTasks;
    }

    public ArrayList<String> getWritableTasks() {
        ArrayList<String> writableTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.isDone()) {
                writableTasks.add("#" + task.getTaskCommand());
            } else {
                writableTasks.add(task.getTaskCommand());
            }
        }
        return writableTasks;
    }

    public void markTaskAsDone(int taskId) {
        tasks.get(taskId - 1).markAsDone();
    }

    public String getTaskString(int taskId) {
        return tasks.get(taskId - 1).toString();
    }

    public int getNumOfTasks() {
        return tasks.size();
    }
}
