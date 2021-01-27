package main.java;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

/**
 * The class that contains the task list. It has operations to add/delete
 * tasks in the list.
 */
class TaskList {
    private ArrayList<Task> taskList;

    /**
     * If there is no existing data on the local hard disk, Dukebot will startup with an
     * empty task list. If there is an existing text file, then Dukebok will copy over the
     * text file into the task list.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
        File file = new File("./data/duke.txt");
        try {
            if (file.exists()) {
                Storage.convert(file, this.taskList);
            }
        } catch (IOException exception) {
            System.out.println(exception);
        }
    }

    /**
     * Iterates over the task list and prints out all the tasks.
     */
    public void listTask() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            main.java.Task currTask = taskList.get(i);
            System.out.println(i + 1 + "." + currTask);
        }
    }

    /**
     * Set the task at the particular index as done.
     *
     * @param index the index of the task which is completed
     * @throws DukeException is thrown when the index given is invalid
     */
    protected void doneTask(int index) throws DukeException {
        if (this.taskList.size() >= index) {
            Task currTask = this.taskList.get(index - 1);
            currTask.completeTask();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + currTask);
        } else {
            System.out.println("Task not found");
        }
        Storage.update(this.taskList);
    }

    /**
     * Deletes the task at the particular index.
     *
     * @param index the index of the task to be deleted
     */
    public void delete(int index) {
        int deleteIndex = index;
        if (this.taskList.size() >= deleteIndex) {
            System.out.println("Noted. I've removed this task:");
            System.out.println("  " + this.taskList.remove(deleteIndex - 1));
            System.out.println(this);
        } else {
            System.out.println("Task not found");
        }
        Storage.update(this.taskList);
    }

    /**
     * Adds a new todo task to the task list.
     *
     * @param taskName the name of the todo task
     */
    public void addToDo(String taskName) {
        ToDo newTask = new ToDo(taskName);
        this.taskList.add(newTask);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + newTask);
        System.out.println(this);
        Storage.update(this.taskList);
    }

    /**
     * Adds a new event task to the task list.
     *
     * @param taskName the name of the event task
     * @param date the date in which the event takes place
     */
    public void addEvent(String taskName, LocalDate date) {
        Event newTask = new Event(taskName, date);
        this.taskList.add(newTask);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + newTask);
        System.out.println(this);
        Storage.update(this.taskList);
    }

    /**
     * Adds a new deadline task to the task list.
     *
     * @param taskName the name of the deadline task
     * @param date the date in which the deadline is due
     */
    public void addDeadline(String taskName, LocalDate date) {
        Deadline newTask = new Deadline(taskName, date);
        this.taskList.add(newTask);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + newTask);
        System.out.println(this);
        Storage.update(this.taskList);
    }

    /**
     * Displays the number of tasks in the list in a special format.
     *
     * @return a string that shows the number of tasks in the list
     */
    @Override
    public String toString() {
        if (this.taskList.size() == 1) {
            return "Now you have " + this.taskList.size() + " task in the list";
        } else {
            return "Now you have " + this.taskList.size() + " tasks in the list";
        }
    }
}