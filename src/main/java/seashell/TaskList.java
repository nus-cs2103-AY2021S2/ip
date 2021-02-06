package seashell;

import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void listTasks() throws SeashellException {
        if (this.taskList.isEmpty()) {
            throw new SeashellException("OOPS!!! Task list is currently still empty!");
        } else {
            for (int i = 1; i <= this.taskList.size(); i++) {
                Task t = this.taskList.get(i - 1);
                System.out.println(i + ". " + t);
            }
        }
    }

    public TaskList setDone(String command, SaveHandler saveHandler) throws SeashellException {
        String num = command.substring(5);
        int index = Integer.parseInt(num);
        if (this.taskList.size() < index) {
            throw new SeashellException("OOPS!!! Task does not exist!");
        } else {
            ArrayList<Task> updatedList = new ArrayList<>(this.taskList);
            Task updated = updatedList.get(index - 1).setDone();
            updatedList.set(index - 1, updated);
            System.out.println("Nice! I've marked this task as done: \n" + updated);
            saveHandler.updateSaveFile(updatedList);
            return new TaskList(updatedList);
        }
    }

    public TaskList delete(String command, SaveHandler saveHandler) throws SeashellException {
        String num = command.substring(7);
        int index = Integer.parseInt(num);
        if (taskList.size() < index) {
            throw new SeashellException("OOPS!!! Task does not exist!");
        } else {
            ArrayList<Task> updatedList = new ArrayList<>(this.taskList);
            Task toRemove = updatedList.remove(index - 1);
            System.out.println("Noted. I have removed " + toRemove);
            System.out.println("You now have " + updatedList.size() + " items in the task list");
            saveHandler.updateSaveFile(updatedList);
            return new TaskList(updatedList);
        }
    }

    public TaskList createTodo(String command, SaveHandler saveHandler) throws SeashellException {
        String taskName = command.substring(4).stripLeading();
        if (taskName.equals("")) {
            throw new SeashellException("OOPS!!! The description of a task cannot be empty.");
        } else {
            Todo newTask = new Todo(taskName);
            ArrayList<Task> updatedList = new ArrayList<>(this.taskList);
            updatedList.add(newTask);
            System.out.println("Added " + newTask + " to the task list!");
            System.out.println("You now have " + taskList.size() + " items in the task list");
            saveHandler.addTaskToSaveFile(newTask);
            return new TaskList(updatedList);
        }
    }

    public TaskList createDeadline(String command, SaveHandler saveHandler) throws SeashellException {
        String taskName = command.substring(8).stripLeading();
        if (taskName.equals("")) {
            throw new SeashellException("OOPS!!! The description of a task cannot be empty.");
        } else if (command.indexOf("/by") == -1) {
            throw new SeashellException("OOPS!!! The syntax of adding a deadline should be [name] /by [date/time]");
        } else {
            taskName = command.substring(8, command.indexOf("/by") - 1).stripLeading();
            String by = command.substring(command.indexOf("/by") + 4);
            Deadline newTask = new Deadline(taskName, by);
            ArrayList<Task> updatedList = new ArrayList<>(this.taskList);
            updatedList.add(newTask);
            System.out.println("Added " + newTask + " to the task list!");
            System.out.println("You now have " + updatedList.size() + " items in the task list");
            saveHandler.addTaskToSaveFile(newTask);
            return new TaskList(updatedList);
        }
    }

    public TaskList createEvent(String command, SaveHandler saveHandler) throws SeashellException {
        String taskName = command.substring(5).stripLeading();
        if (taskName.equals("")) {
            throw new SeashellException("OOPS!!! The description of a task cannot be empty.");
        } else if (command.indexOf("/at") == -1) {
            throw new SeashellException("OOPS!!! The syntax of adding an event should be [name] /at [date/time]");
        } else {
            taskName = command.substring(5, command.indexOf("/at") - 1).stripLeading();
            String at = command.substring(command.indexOf("/at") + 4);
            Event newTask = new Event(taskName, at);
            ArrayList<Task> updatedList = new ArrayList<>(this.taskList);
            updatedList.add(newTask);
            System.out.println("Added " + newTask + " to the task list!");
            System.out.println("You now have " + updatedList.size() + " items in the task list");
            saveHandler.addTaskToSaveFile(newTask);
            return new TaskList(updatedList);
        }
    }

    public TaskList clear(SaveHandler saveHandler) throws SeashellException {
        if (this.taskList.isEmpty()) {
            throw new SeashellException("OOPS!!! Task list is already empty!");
        } else {
            saveHandler.clearSaveFile();
            return new TaskList(new ArrayList<>());
        }
    }
}
