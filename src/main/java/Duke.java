import helper.DukeException;
import helper.Storage;
import helper.TaskList;
import helper.Ui;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.time.LocalDate;


public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String greeting = "Hello! I'm Duke\n" + "Please enter list below";
        dukePrint(greeting);
        new Duke("duke.txt").loop();
    }


    public static void dukePrint(String s) {
        System.out.println("\n----------------------");
        System.out.println(s);
        System.out.println("----------------------\n");
    }

    public static void dukePrint(List<Task> ls) {
        System.out.println("\n----------------------");
        for (int i = 0 ; i < ls.size(); i++) {
            System.out.print(i+1);
            System.out.println(". " + ls.get(i) );
        }
        System.out.println("----------------------\n");
    }

    public void loop() {
        Scanner input = new Scanner(System.in);
        boolean end = false;
        boolean change = false;

        while(!end) {
            change = false;
            String s = input.nextLine();
            if (s.equals("bye")) {
                end = true;
                dukePrint("Bye bye! See you later!");
            } else if (s.equals("list")) {
                dukePrint(tasks.getTaskList());
            } else if (s.matches("done \\d+")) {
                s = s.replaceAll("done ","");
                int index = Integer.parseInt(s) - 1;
                try {
                    tasks.get(index).setDone(true);
                    dukePrint("Good! We finished task: " + s + ". " + tasks.get(index));
                    change = true;
                } catch (Exception e) {
                    dukePrint("Uhhh.... Our list starts at 1..." +
                            " and ends at " + (tasks.size() + 1));
                }
            } else if (s.matches("delete \\d+")) {
                s = s.replaceAll("delete ","");
                int index = Integer.parseInt(s) - 1;
                try {
                    dukePrint("OK! We removed task: " + s + ". " + tasks.get(index));
                    tasks.remove(index);
                    change = true;
                } catch (Exception e) {
                    dukePrint("Uhhh.... Our list starts at 1..." +
                            " and ends at " + (tasks.size() + 1));
                }
            } else if (s.matches("deadline .+?( /by ).*")) {
                s = s.replaceAll("deadline ","");
                String[] deadlineInfo = s.split(" /by ");
                try {
                    LocalDate d1 = LocalDate.parse(deadlineInfo[1]);
                    Deadline t = new Deadline(deadlineInfo[0],d1);
                    tasks.add(t);
                    dukePrint("added new deadline: " + t);
                    change = true;
                }
                catch (Exception e) {
                    dukePrint("task.Deadline must have something after \" /by \"!");
                }
            } else if (s.matches("event .+?( /at ).*")) {
                s = s.replaceAll("event ","");
                String[] eventInfo = s.split(" /at ");
                try {
                    LocalDate d1 = LocalDate.parse(eventInfo[1]);
                    Event t = new Event(eventInfo[0],d1);
                    tasks.add(t);
                    dukePrint("added new event: " + t);
                    change = true;
                }
                catch (Exception e) {
                    dukePrint("task.Event must have something after \" /at \"!");
                }
            } else if (s.matches("todo .*")) {
                s = s.replaceAll("todo ","");
                Todo t = new Todo(s);
                tasks.add(t);
                dukePrint("added new todo: " + t);
                change = true;
            } else {
//                throw new helper.DukeException("I don't get it");
//                task.Task t = new task.Task(s);
//                tasks.add(t);
                dukePrint("Please use todo, deadline, or event!");
            }
        }
    }
}
