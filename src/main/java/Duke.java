import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String greeting = "Hello! I'm Duke\n" + "Please enter list below";
        dukePrint(greeting);
        loop();
    }

    private static class DukeException extends Exception {
        public DukeException(String errorMessage) {
            super(errorMessage);
        }
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

    public static void loop() {
        Scanner input = new Scanner(System.in);
        boolean end = false;

        List<Task> myList = new ArrayList<>();

        while(!end) {
            String s = input.nextLine();
            if (s.equals("bye")) {
                end = true;
                dukePrint("Bye bye! See you later!");
            } else if (s.equals("list")) {
                dukePrint(myList);
            } else if (s.matches("done \\d+")) {
                s = s.replaceAll("done ","");
                int index = Integer.parseInt(s) - 1;
                try {
                    myList.get(index).isDone = true;
                    dukePrint("Good! We finished task: " + s + ". " + myList.get(index));
                } catch (Exception e) {
                    dukePrint("Uhhh.... Our list starts at 1..." +
                            " and ends at " + (myList.size() + 1));
                }
            } else if (s.matches("delete \\d+")) {
                s = s.replaceAll("delete ","");
                int index = Integer.parseInt(s) - 1;
                try {
                    dukePrint("OK! We removed task: " + s + ". " + myList.get(index));
                    myList.remove(index);
                } catch (Exception e) {
                    dukePrint("Uhhh.... Our list starts at 1..." +
                            " and ends at " + (myList.size() + 1));
                }
            } else if (s.matches("deadline .+?( /by ).*")) {
                s = s.replaceAll("deadline ","");
                String[] deadlineInfo = s.split(" /by ");
                try {
                    LocalDate d1 = LocalDate.parse(deadlineInfo[1]);
                    Deadline t = new Deadline(deadlineInfo[0],d1);
                    myList.add(t);
                    dukePrint("added new deadline: " + t);
                }
                catch (Exception e) {
                    dukePrint("Deadline must have something after \" /by \"!");
                }
            } else if (s.matches("event .+?( /at ).*")) {
                s = s.replaceAll("event ","");
                String[] eventInfo = s.split(" /at ");
                try {
                    LocalDate d1 = LocalDate.parse(eventInfo[1]);
                    Event t = new Event(eventInfo[0],d1);
                    myList.add(t);
                    dukePrint("added new event: " + t);
                }
                catch (Exception e) {
                    dukePrint("Event must have something after \" /at \"!");
                }
            } else if (s.matches("todo .*")) {
                s = s.replaceAll("todo ","");
                Todo t = new Todo(s);
                myList.add(t);
                dukePrint("added new todo: " + t);
            } else {
//                throw new DukeException("I don't get it");
//                Task t = new Task(s);
//                myList.add(t);
                dukePrint("Please use todo, deadline, or event!");
            }
        }
    }
}
