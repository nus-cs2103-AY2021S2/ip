/**
 * Duke program maintains a taskList for user to track tasks.
 * Reads user input tasks(todo, event, deadline).
 * Can add, delete, markasDone tasks.
 *
 * @author Oh Jun Ming
 * @version 1.0
 * @Since 2020-01-17
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> tasklist = new ArrayList<>();
    private static Boolean doExit = false;

    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        Duke.initiate(s);
    }

    public static void initiate(Scanner s) {
        Duke.Greet();
        while(s.hasNextLine()){
            try {
                if (doExit.equals(true)) {
                    break;
                }
                Duke.process(s);
            } catch (EmptyDescription e) {
                Duke.output(e.toString());
            } catch (InvalidTypeofTask e) {
                Duke.output(e.toString());
            }
        }
        Duke.exit();

    }

    public static void process(Scanner s) throws InvalidTypeofTask, EmptyDescription {
        String input = s.nextLine();
        String[] arr = input.split(" ", 2);
        String typeofTask = arr[0];
        String description;

        if (typeofTask.equals("bye")) {
            doExit = true;
        } else if (typeofTask.equals("list")) {
            Duke.list();
        } else if (typeofTask.equals("done")) {
            description = arr[1];
            Duke.markAsDone(description);
        } else if (typeofTask.equals("delete")) {
            description = arr[1];
            Duke.delete(description);
        } else if (typeofTask.equals("todo") || typeofTask.equals("deadline") ||
                        typeofTask.equals("event")){
            Duke.add(arr);
        }  else {
            throw new InvalidTypeofTask();
        }
    }

    public static void add(String[] arr) throws EmptyDescription {
        String typeOfTask = arr[0];
        if (arr[1].equals("")) {
            throw new EmptyDescription(typeOfTask);
        } else {
            String msg = arr[1];
            Task newTask;
            String[] arr1;
            if (typeOfTask.equals("todo")) {
                newTask = new Todo(msg);
            } else if (typeOfTask.equals("deadline")) {
                arr1 = msg.split("/by ");
                newTask = new Deadline(arr1[0], arr1[1]);
            } else {
                arr1 = msg.split("/at ");
                newTask = new Event(arr1[0], arr1[1]);
            }
            tasklist.add(newTask);
            String instructions = Response.ADD.toString() + newTask + "\n" + Duke.status();
            Duke.output(instructions);
        }
    }

    public static void delete(String msg) {
        String[] arr = msg.split(" ",2);
        int i = Integer.parseInt(arr[0]);

        String instructions = Response.DELETE.toString() + tasklist.get(i - 1) + "\n" + Duke.status();
        tasklist.remove(i - 1);
        Duke.output(instructions);
    }

    public static void markAsDone(String msg) {
        String[] arr = msg.split(" ", 2);
        int i = Integer.parseInt(arr[0]);
        tasklist.set(i - 1, tasklist.get(i - 1).setDone());
        Duke.output(Response.DONE.toString() + tasklist.get(i - 1) + "\n");
    }

    public static void list() {
        String msg = "";
        for(int i = 0; i < tasklist.size(); i++) {
            msg += (i + 1) + "." + tasklist.get(i) + "\n";
        }
        Duke.output(Response.LIST.toString() + msg);
    }

    public static String status() {
        return "Now you have " + tasklist.size() + " tasks in the list.\n";
    }

    public static void Greet() {
        Duke.output(Response.GREET.toString());
    }

    public static void echo(String msg) {
        Duke.output(msg+ "\n");
    }

    public static void exit() {
        Duke.output(Response.EXIT.toString());
    }

    public static void output(String response) {
        System.out.println("---------------------------------------");
        System.out.println(response);
        System.out.println("---------------------------------------\n");
    }
}
