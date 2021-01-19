package main.java.subfiles;

import java.util.ArrayList;
import main.java.exceptions.*;

public class TaskManager {
    private ArrayList<Task> tasks;

    public TaskManager() {
        tasks = new ArrayList<>();
    }

    private void addTodo(String s)
            throws EmptyDescriptionException {
        try {
            s = s.substring(5);
            tasks.add(new ToDo(s));
        } catch (StringIndexOutOfBoundsException e) {
            throw new EmptyDescriptionException("todo");
        }
    }

    private void addDeadline(String s)
            throws EmptyDescriptionException, EmptyTimeException {
        try {
            String[] sArray = s.split("/", 2);
            s = sArray[0].substring(9, sArray[0].length() - 1);
            String t = sArray[1].substring(3);
            tasks.add(new Deadline(s, t));
        } catch (StringIndexOutOfBoundsException e) {
            throw new EmptyDescriptionException("deadline");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new EmptyTimeException("deadline");
        }
    }

    private void addEvent(String s)
            throws EmptyDescriptionException, EmptyTimeException {
        try {
            String[] sArray = s.split("/", 2);
            s = sArray[0].substring(6, sArray[0].length() - 1);
            String t = sArray[1].substring(3);
            tasks.add(new Event(s, t));
        } catch (StringIndexOutOfBoundsException e) {
            throw new EmptyDescriptionException("event");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new EmptyTimeException("event");
        }
    }

    public void addTask(String s)
            throws EmptyDescriptionException, EmptyTimeException, InvalidInputException {
        String command = s.split(" ", 2)[0];

        if (command.equals("todo"))  addTodo(s);
        else if (command.equals("deadline")) addDeadline(s);
        else if (command.equals("event")) addEvent(s);
        else throw new InvalidInputException();

        System.out.println("Got it. I've added this task:");
        System.out.println(tasks.get(tasks.size() - 1).toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public void printTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i < tasks.size() + 1; i++) {
            Task task = tasks.get(i - 1);
            System.out.println(i + ". " + task.toString());
        }
    }

    public void markDone(String s)
            throws InvalidInputException, ListOutOfBoundsException {
        try {
            int index = Integer.parseInt(s) - 1;
            tasks.get(index).setDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(tasks.get(index).toString());
        } catch (NumberFormatException e) {
            throw new InvalidInputException();
        } catch (IndexOutOfBoundsException e) {
            throw new ListOutOfBoundsException(tasks.size());
        }
    }

    public void deleteTask(String s) throws InvalidInputException, ListOutOfBoundsException {
        try {
            int index = Integer.parseInt(s) - 1;
            Task t = tasks.remove(index);
            System.out.println("Noted. I've removed this task:");
            System.out.println(t.toString());
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        } catch (NumberFormatException e) {
            throw new InvalidInputException();
        } catch (IndexOutOfBoundsException e) {
            throw new ListOutOfBoundsException(tasks.size());
        }
    }
}
