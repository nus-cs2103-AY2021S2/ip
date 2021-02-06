package seashell;

import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Prints out all the tasks that are currently in the task list
     * @throws SeashellException if the task list is empty
     */
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

    /**
     * Set a specified task in the task list as done
     * @param command input from user
     * @param saveHandler save handler object to update save file
     * @return updated task list after setting the specified task as done
     * @throws SeashellException if specified task index is out of bounds
     */
    public TaskList setDone(String command, SaveHandler saveHandler) throws SeashellException {
        String num = command.substring(5);
        int index = Integer.parseInt(num);
        if (this.taskList.size() < index) {
            throw new SeashellException("OOPS!!! Task index is out of bounds!");
        } else {
            ArrayList<Task> updatedList = new ArrayList<>(this.taskList);
            Task updated = updatedList.get(index - 1).setDone();
            updatedList.set(index - 1, updated);
            System.out.println("Nice! I've marked this task as done: \n" + updated);
            saveHandler.updateSaveFile(updatedList);
            return new TaskList(updatedList);
        }
    }

    /**
     * Delete a task from the task list
     * @param command input from user
     * @param saveHandler save handler object to update save file
     * @return updated task list after setting the specified task is deleted
     * @throws SeashellException if specified task index is out of bounds
     */
    public TaskList delete(String command, SaveHandler saveHandler) throws SeashellException {
        String num = command.substring(7);
        int index = Integer.parseInt(num);
        if (taskList.size() < index) {
            throw new SeashellException("OOPS!!! Task index is out of bounds!");
        } else {
            ArrayList<Task> updatedList = new ArrayList<>(this.taskList);
            Task toRemove = updatedList.remove(index - 1);
            System.out.println("Noted. I have removed " + toRemove);
            System.out.println("You now have " + updatedList.size() + " items in the task list");
            saveHandler.updateSaveFile(updatedList);
            return new TaskList(updatedList);
        }
    }

    /**
     * Create a todo task item and add it to the task list
     * @param command input from user
     * @param saveHandler save handler object to update save file
     * @return updated task list after the created todo task is added
     * @throws SeashellException if no task description is given
     */
    public TaskList createTodo(String command, SaveHandler saveHandler) throws SeashellException {
        String taskName = command.substring(4).stripLeading();
        if (taskName.equals("")) {
            throw new SeashellException("OOPS!!! The description of a task cannot be empty.");
        } else {
            Todo newTask = new Todo(taskName);
            ArrayList<Task> updatedList = new ArrayList<>(this.taskList);
            updatedList.add(newTask);
            System.out.println("Added " + newTask + " to the task list!");
            System.out.println("You now have " + updatedList.size() + " items in the task list");
            saveHandler.addTaskToSaveFile(newTask);
            return new TaskList(updatedList);
        }
    }

    /**
     * Create a deadline task item and add it to the task list
     * @param command input from user
     * @param saveHandler save handler object to update save file
     * @return updated task list after the created deadline task is added
     * @throws SeashellException if no task description is given or the syntax of the command is invalid
     */
    public TaskList createDeadline(String command, SaveHandler saveHandler) throws SeashellException {
        String taskName = command.substring(8).stripLeading();
        if (taskName.equals("")) {
            throw new SeashellException("OOPS!!! The description of a task cannot be empty.");
        } else if (command.indexOf("/by") == -1) {
            throw new SeashellException("OOPS!!! The syntax of adding a deadline should be [name] /by [yyyy-mm-dd]");
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

    /**
     * Create a event task item and add it to the task list
     * @param command input from user
     * @param saveHandler save handler object to update save file
     * @return updated task list after the created event task is added
     * @throws SeashellException if no task description is given or the syntax of the command is invalid
     */
    public TaskList createEvent(String command, SaveHandler saveHandler) throws SeashellException {
        String taskName = command.substring(5).stripLeading();
        if (taskName.equals("")) {
            throw new SeashellException("OOPS!!! The description of a task cannot be empty.");
        } else if (command.indexOf("/at") == -1) {
            throw new SeashellException("OOPS!!! The syntax of adding an event should be [name] /at [yyyy-mm-dd]");
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

    /**
     * Clear the task list
     * @param saveHandler save handler object to update save file
     * @return an empty Task list object
     * @throws SeashellException if task list is empty
     */
    public TaskList clear(SaveHandler saveHandler) throws SeashellException {
        if (this.taskList.isEmpty()) {
            throw new SeashellException("OOPS!!! Task list is already empty!");
        } else {
            saveHandler.clearSaveFile();
            return new TaskList(new ArrayList<>());
        }
    }

    /**
     * Find tasks that contain some keyword specified by user
     * @param command
     * @return a task list with tasks that contain keyword
     * @throws SeashellException if task list is empty or no tasks found
     */
    public TaskList find(String command) throws SeashellException {
        String toFind = command.substring(5);
        if (this.taskList.isEmpty()) {
            throw new SeashellException("OOPS!!! Task list is already empty!");
        } else {
            ArrayList<Task> foundList = new ArrayList<>();
            for (Task t : this.taskList) {
                if (t.getName().contains(toFind)) {
                    foundList.add(t);
                }
            }
            if (foundList.isEmpty()) {
                throw new SeashellException("No tasks with the keyword " + toFind + " was found!");
            } else {
                TaskList foundTaskList = new TaskList(foundList);
                System.out.println("Here are the matching tasks in your list:");
                foundTaskList.listTasks();
                return foundTaskList;
            }
        }
    }
}
