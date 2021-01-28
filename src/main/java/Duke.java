import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DukeList list = new DukeList();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String bound = "____________________________________________________________";
        String topBound = "____________________________________________________________\n";
        String bottomBound = "\n____________________________________________________________\n";

        System.out.println(topBound + logo + "\nHello! I'm Duke\n" + "What can I do for you?\n" + bottomBound);

        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            String[] arr = input.split("/");
            if (input.equals("bye")) {
                System.out.println(topBound + "Good bye! Stay calm and keep coding o7." + bottomBound);
                sc.close();
                break;
            } else if (input.equals("list")) {
                System.out.println(bound);
                System.out.println("Here are the tasks in your list:");
                list.printAll();
                System.out.println(topBound);
            } else if (arr[0].startsWith("done")) {
                try {
                    String[] doneArr = arr[0].split(" ");
                    int x = Integer.parseInt(doneArr[1]) - 1;
                    list.done(x);
                    System.out.println(topBound + "Good job! I've marked this task as done:\n" + "  " + list.get(x)
                            + bottomBound);
                } catch (IndexOutOfBoundsException e) {
                    // Task number x does not exist
                    System.out.println(topBound + "Uh oh! Task does not exist and can not be completed!" + bottomBound);
                } catch (NumberFormatException e) {
                    // parameter for done is not an Integer
                    System.out.println(topBound + "Uh oh! done must be followed by an Integer ie. done 1.\nTry again!"
                        + bottomBound);
                }
            } else if (arr[0].startsWith("todo")) {
                try {
                    String taskName = input.replaceFirst("todo", "").stripLeading();
                    if (taskName.equals("")) {
                        throw new DukeException("todo");
                    }
                    ToDos curr = new ToDos(taskName);
                    list.add(curr);
                    System.out.println(topBound + "Got it! I've added this task:\n" + "  " + curr
                            + "\nNow you have " + list.noOfTasks() + " tasks in the list." + bottomBound);
                } catch (DukeException e) {
                    // command came without a description
                    System.out.println(topBound + e.getMessage() + bottomBound);
                }
            } else if (arr[0].startsWith("deadline")) {
                try {
                    String taskName = arr[0].replaceFirst("deadline", "").stripLeading()
                            .stripTrailing();
                    if (taskName.equals("")) {
                        throw new DukeException("deadline");
                    }
                    LocalDate d = LocalDate.parse(arr[1].replaceFirst("by", "").stripLeading());
                    Deadlines curr = new Deadlines(taskName, d);
                    list.add(curr);
                    System.out.println(topBound + "Got it! I've added this task:\n" + "  " + curr
                            + "\nNow you have " + list.noOfTasks() + " tasks in the list." + bottomBound);
                } catch (DukeException e) {
                    // command came without a description
                    System.out.println(topBound + e.getMessage() + bottomBound);
                } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
                    // command came without proper date time format
                    System.out.println(topBound
                            + "Uh Oh! event must include '/at YYYY-MM-DD'.\nTry again!" + bottomBound);
                }
            } else if (arr[0].startsWith("event")) {
                try {
                    String taskName = arr[0].replaceFirst("event", "").stripLeading().stripTrailing();
                    if (taskName.equals("")) {
                        throw new DukeException("event");
                    }
                    LocalDate d = LocalDate.parse(arr[1].replaceFirst("at", "").stripLeading());
                    Events curr = new Events(taskName, d);
                    list.add(curr);
                    System.out.println(topBound + "Got it! I've added this task:\n" + "  " + curr
                            + "\nNow you have " + list.noOfTasks() + " tasks in the list." + bottomBound);
                } catch (DukeException e) {
                    // command came without a description
                    System.out.println(topBound + e.getMessage() + bottomBound);
                } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
                    // command came without proper date time format
                    System.out.println(topBound
                            + "Uh Oh! event must include '/at YYYY-MM-DD'.\nTry again!" + bottomBound);
                }
            } else if (arr[0].startsWith("delete")) {
                try {
                    String str = arr[0].replaceFirst("delete", "").stripLeading();
                    int x = Integer.parseInt(str) - 1;
                    Task curr = list.get(x);
                    list.delete(x);
                    System.out.println(topBound + "Got it! I've removed this task:\n" + " " + curr + "\n" + "You have "
                            + list.noOfTasks() + " tasks left in the list." + bottomBound);
                } catch (IndexOutOfBoundsException e) {
                    // task number x does not exist
                    System.out.println(topBound + "Uh Oh! Task does not exist and cannot be deleted!" + bottomBound);
                } catch (NumberFormatException e) {
                    // parameter for delete is not an Integer
                    System.out.println(topBound
                            + "Uh Oh! delete must be followed by an Integer, ie. delete 1.\nTry again!" + bottomBound);
                }
            } else if (arr[0].startsWith("show")) {
                try {
                    LocalDate day = LocalDate.parse(arr[0].replaceFirst("show", "").stripLeading());
                    System.out.println(topBound + "Here are your tasks on " +
                            day.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ":");
                    list.showTaskOnDay(day);
                    System.out.println(bound);
                } catch (DateTimeParseException e) {
                    System.out.println(topBound
                            + "Uh Oh! show must be followed by a date in YYYY-MM-DD format.\nTry again!" + bottomBound);
                }
            } else {
                // unknown command
                System.out.println(topBound + "Uh Oh! I'm sorry, but I don't know what that means." + bottomBound);
            }
        }
    }
}
