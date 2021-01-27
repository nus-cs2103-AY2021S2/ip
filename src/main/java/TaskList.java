import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public void listTask() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            Task currTask = taskList.get(i);
            System.out.println(i + 1 + "." + currTask);
        }
    }

    public void doneTask(String index) throws DukeException {
        if (index.length() == 0) {
            throw new DukeException("☹ OOPS!!! The index of the task is missing.");
        }
        int doneIndex;
        try {
            doneIndex = Integer.parseInt(index.substring(1));
        } catch (NumberFormatException error) {
            throw new DukeException("Index of task must be an integer");
        }
        if (this.taskList.size() >= doneIndex) {
            Task currTask = this.taskList.get(doneIndex - 1);
            currTask.completeTask();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + currTask);
        } else {
            System.out.println("Task not found");
        }
        Storage.update(this.taskList);
    }

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

    public void addToDo(String taskName) {
        ToDo newTask = new ToDo(taskName);
        this.taskList.add(newTask);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + newTask);
        System.out.println(this);
        Storage.update(this.taskList);
    }

    public void addEvent(String taskName, LocalDate date) {
        Event newTask = new Event(taskName, date);
        this.taskList.add(newTask);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + newTask);
        System.out.println(this);
        Storage.update(this.taskList);
    }

    public void addDeadline(String taskName, LocalDate date) {
        Deadline newTask = new Deadline(taskName, date);
        this.taskList.add(newTask);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + newTask);
        System.out.println(this);
        Storage.update(this.taskList);
    }

    @Override
    public String toString() {
        if (this.taskList.size() == 1) {
            return "Now you have " + this.taskList.size() + " task in the list";
        } else {
            return "Now you have " + this.taskList.size() + " tasks in the list";
        }
    }
}