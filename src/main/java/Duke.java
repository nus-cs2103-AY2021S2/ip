package main.java;

import main.java.entity.*;
import main.java.exceptions.IllegalInputFormatException;
import main.java.exceptions.TaskDoesNotExistException;
import main.java.exceptions.UnrecognizableInputException;

import java.util.ArrayList;
import java.util.*;

public class Duke {
    public static void main(String[] args) throws Exception {
        TaskManager tm = new TaskManager();
        tm.greeting();
        Scanner sc = new Scanner(System.in);
        String input;
        while (sc.hasNextLine()) {
            input = sc.nextLine();
            try {
                if (input.toLowerCase().equals("bye")) {
                    break;
                }
                if (input.trim().toLowerCase().equals("list")) {
                    tm.list();
                    continue;
                }
                if (input.toLowerCase().split(" ")[0].equals("done")) {
                    tm.done(input);
                    continue;
                }
                if (input.toLowerCase().split(" ")[0].equals("delete")) {
                    tm.deleteTask(input);
                    continue;
                }
                if (input.toLowerCase().split(" ")[0].equals("todo")) {
                    tm.addTodo(input);
                    continue;
                }
                if (input.toLowerCase().split(" ")[0].equals("deadline")) {
                    tm.addDeadline(input);
                    continue;
                }
                if (input.toLowerCase().split(" ")[0].equals("event")) {
                    tm.addEvent(input);
                    continue;
                }
                throw new UnrecognizableInputException();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Bye. Until next time!");
    }
}
