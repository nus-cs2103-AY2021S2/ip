package main.java;
import main.java.entity.*;
import main.java.exceptions.IllegalInputFormatException;
import main.java.exceptions.TaskDoesNotExistException;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    static private List<Task> list;
    static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    static final String greetings = "Dear user, welcome to the world of duke!";
    static final String prefix = "    ";
    static final String emptyList = prefix + "Currently there is nothing on your list.";
    static final String ADDED = prefix + "Got it. I've added this task:";
    static final String markAsDone = prefix + "Nice! I've mark this task as done:";
    static final String removeTask = prefix + "Noted, I've removed this task:";

    public TaskManager() {
        list = new ArrayList<>();
    }

    public void greeting() {
        System.out.println("This is \n" + logo);
        System.out.println(greetings);
    }

    public void addTodo(String input) throws IllegalInputFormatException {
        String todoName = input.substring(5);
        if (todoName.trim().length() == 0) {
            throw new IllegalInputFormatException();
        }
        Task task = new Todo(todoName);
        list.add(task);
        printAfterAdd(task);
    }

    public void addEvent(String input) throws IllegalInputFormatException {
        try {
            String[] arr = input.split(" /");
            String name = arr[0].substring(6);
            String[] arr2 = arr[1].split(" ", 2);
            Task task;
            try {
                LocalDate timeDate = LocalDate.parse(arr2[1]);
                task = new Event(name, arr2[1], timeDate, arr2[0]);
            } catch (DateTimeParseException e) {
                task = new Event(name, arr2[1], arr2[0]);
            }
            list.add(task);
            printAfterAdd(task);
        } catch (Exception e) {
            throw new IllegalInputFormatException();
        }
    }

    public void addDeadline(String input) throws IllegalInputFormatException {
        try {
            String[] arr = input.split(" /");
            String name = arr[0].substring(9);
            String[] arr2 = arr[1].split(" ", 2);
            Task task;
            try {
                LocalDate deadlineDate = LocalDate.parse(arr2[1]);
                task = new Deadline(name, arr2[1], deadlineDate, arr2[0]);
            } catch (DateTimeParseException e) {
                task = new Deadline(name, arr2[1], arr2[0]);
            }
            list.add(task);
            printAfterAdd(task);
        } catch (Exception e) {
            throw new IllegalInputFormatException();
        }
    }

    public void printAfterAdd(Task task) {
        System.out.println(ADDED);
        System.out.println(prefix + task);
        printListSize();
    }

    public void printListSize() {
        System.out.println(prefix + "Now you have " + list.size() + " tasks in the list.");
    }

    public void done(String input) throws Exception{
        int index;
        try {
            index = Integer.parseInt(input.split(" ")[1]) - 1;
        } catch (Exception e) {
            throw new IllegalInputFormatException();
        }
        if (index < 0 || index >= list.size()) {
            throw new TaskDoesNotExistException();
        }
        Task task = list.get(index);
        task.markAsDone();
        System.out.println(markAsDone);
        System.out.println(prefix + task);
    }

    public void deleteTask(String input) throws Exception {
        int index;
        try {
            index = Integer.parseInt(input.split(" ")[1]) - 1;
        } catch (Exception e) {
            throw new IllegalInputFormatException();
        }
        if (index < 0 || index >= list.size()) {
            throw new TaskDoesNotExistException();
        }
        Task task = list.remove(index);
        System.out.println(removeTask);
        System.out.println(prefix + task);
        printListSize();
    }

    public void list() {
        if (this.isEmpty()) {
            System.out.println(emptyList);
        } else {
            for (int i = 0; i < list.size(); i++) {
                System.out.println("    " + (i + 1) + ". " + list.get(i));
            }
        }
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }
}
