import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Class parser makes sense of the user commands
 *
 * @version 28 Jan 2021
 * @author Zhang Peng
 */
public class Parser {
    public Parser() {
    }

    /**
     * This is the the method for making sense of the user commands
     * @param arrayList takes in the arrayList
     * @param path specifies the path to the file
     * @param scanner takes in the a scanner to scan
     * @return Nothing.
     */
    public void makingSenseOfUserCommand(ArrayList<Task> arrayList, String path, Scanner scanner) {
        String input;
        while (scanner.hasNextLine()) {
            input = scanner.nextLine();
            try {
                if (!(input.contains("todo") || input.contains("event") || input.contains("deadline")
                        || input.contains("list") || input.contains("done") || input.contains("delete") || input.contains("find"))) {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                } else if (input.equals("todo") || input.equals("event") || input.equals("deadline")
                        || input.equals("done") || input.equals("delete") || input.equals("find")) {
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                }                                // can add in more exceptions
            } catch (DukeException e) {
                System.out.println("--------------------------");
                System.out.println(e.getMessage());
                System.out.println("--------------------------");
            }
            if (input.equals("bye")) {
                System.out.println("--------------------------");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("--------------------------");

            } else if (input.equals("list")) {
                System.out.println("--------------------------");
                System.out.println("Here are the tasks in your list:");
                for (Task m : arrayList) {
                    System.out.print(m.index + ". " + m);
                    System.out.print("\n");
                }
                System.out.println("--------------------------");

            } else if (input.contains("done") && !(input.equals("done"))) {
                int x = Integer.parseInt(input.substring(5));
                try {
                    if (x > arrayList.size()) {
                        throw new DukeException("☹ OOPS!!! Sorry item no found :-(");
                    } else {
                        System.out.println("--------------------------");
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(arrayList.get(x - 1).markAsDone().getStatusIcon() + " "
                                + arrayList.get(x - 1).description);
                        arrayList.set(x - 1, arrayList.get(x - 1).markAsDone());
                        System.out.println("--------------------------");
                    }

                } catch (DukeException e) {
                    System.out.println("--------------------------");
                    System.out.println(e.getMessage());
                    System.out.println("--------------------------");
                }

            } else if (input.contains("todo") && !(input.equals("todo"))) {
                System.out.println("--------------------------");
                System.out.println("Got it. I've added this task: ");
                Task task = new Todo(input.substring(5));
                new TaskList().addToList(arrayList, task);
                System.out.println("Now you have " + arrayList.size() + " task(s) in the list");
                System.out.println("--------------------------");


            } else if (input.contains("deadline") && !(input.equals("deadline"))) {
                System.out.println("--------------------------");
                System.out.println("Got it. I've added this task: ");
                String[] parts = input.split("/",2);
                String part1 = parts[0];
                String part2 = parts[1];

                //e.g. deadline return book /by 02/12/2019
                if (part2.contains("/")) {
                    String dateString = part2.substring(3);
                    String temp = dateString.substring(6)+"-"+dateString.substring(3,5) + "-" +dateString.substring(0,2);
                    LocalDate xx = LocalDate.parse(temp);
                    String f = xx.format(DateTimeFormatter.ofPattern("MMM d yyyy"));

                    Task task = new Deadline(part1.substring(9), f);
                    new TaskList().addToList(arrayList, task);
                } else {
                    Task task = new Deadline(part1.substring(9), part2.substring(3));
                    new TaskList().addToList(arrayList, task);
                }

                System.out.println("Now you have " + arrayList.size() + " task(s) in the list");
                System.out.println("--------------------------");

            } else if (input.contains("event") && !(input.equals("event"))) {
                System.out.println("--------------------------");
                System.out.println("Got it. I've added this task: ");
                String[] parts = input.split("/",2);
                String part1 = parts[0];
                String part2 = parts[1];

                // e.g. event project meeting /at 02/12/2019 2-4pm
                if (part2.contains("/")) {
                    String dateString = part2.substring(3,13);
                    String timeString = part2.substring(13);
                    String temp = dateString.substring(6) + "-" + dateString.substring(3, 5) + "-" + dateString.substring(0, 2);
                    LocalDate xx = LocalDate.parse(temp);
                    String f = xx.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + timeString ;

                    Task task = new Event(part1.substring(6), f);
                    new TaskList().addToList(arrayList, task);
                } else {
                    Task task = new Event(part1.substring(6), part2.substring(3));
                    new TaskList().addToList(arrayList, task);
                }
                System.out.println("Now you have " + arrayList.size() + " task(s) in the list");
                System.out.println("--------------------------");

            }  else if (input.contains("delete") && !(input.equals("delete"))) {

                int deletedNumber = Integer.parseInt(input.substring(7));
                try {
                    if (deletedNumber > arrayList.size()) {
                        throw new DukeException("☹ OOPS!!! Sorry item no found :-(");
                    } else {
                        System.out.println("--------------------------");
                        System.out.println("Noted. I've removed this task: ");
                        new TaskList().deleteFromList(arrayList, deletedNumber);
                        System.out.println("Now you have " + arrayList.size() + " task(s) in the list");
                        System.out.println("--------------------------");
                    }
                } catch (DukeException e) {
                    System.out.println("--------------------------");
                    System.out.println(e.getMessage());
                    System.out.println("--------------------------");
                }

            } else if (input.contains("find") && !(input.equals("find"))) {
                System.out.println("--------------------------");
                System.out.println("Here are the matching tasks in your list:");

                ArrayList<Task> tt = new ArrayList<>();

                int counterOne = 1;

                for (Task ttt :arrayList) {
                    if (ttt.description.contains(input.substring(5))) {
                        ttt.index = counterOne;
                        tt.add(ttt);
                        counterOne++;
                    }
                }

                for (Task m : tt) {
                    System.out.print(m.index + ". " + m);
                    System.out.print("\n");
                }
                System.out.println("--------------------------");
            }
            int counterTwo = 1;
            for (Task s : arrayList) {
                s.index = counterTwo;
                counterTwo++;
            }
            new Storage().savingFile(arrayList, path);
        }
    }
}
