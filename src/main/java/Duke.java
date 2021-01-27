import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String divider = "____________________________________________________________\n";
        System.out.println(divider + logo + "\nHello! I'm Duke\nWhat can I do for you?\n" + divider);

        Scanner sc = new Scanner(System.in);
        List<Task> list = new ArrayList<>();

        while(sc.hasNextLine()) {
            String input = sc.nextLine();
            String[] check = input.split(" ");
            if(input.equals("bye")) {
                System.out.println(divider + "Bye. Hope to see you again soon!\n" + divider);
                break;
            } else if(input.equals("list")) {
                System.out.println(divider + "Here are the tasks in your list:");
                for(int i = 1; i <= list.size(); i++) {
                    System.out.println("  " + i + ". " + list.get(i-1));
                }
                System.out.println(divider);
            } else if(check[0].equals("done")) {
                try {
                    checkEmptyInput(check);
                    list.get(Integer.parseInt(check[1])-1).markAsDone();
                    System.out.println(divider + "Nice! I've marked this task as done:\n  " + list.get(Integer.parseInt(check[1])-1) + "\n" + divider);
                } catch (DukeException e) {
                    System.out.println(divider + "OOPS!!! Please select an item to mark as done.\n" + divider);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(divider + "OOPS!!! Selected item does not exist.\n" + divider);
                }
            } else if(check[0].equals("delete")) {
                try {
                    checkEmptyInput(check);
                    System.out.println(divider + "Noted. I've removed this task:\n  " + list.remove(Integer.parseInt(check[1])-1) + "\nNow you have " + list.size() + " tasks in the list.\n" + divider);
                } catch (DukeException e) {
                    System.out.println(divider + "OOPS!!! Please select an item to delete.\n" + divider);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(divider + "OOPS!!! Selected item does not exist.\n" + divider);
                }
            } else if(check[0].equals("todo")) {
                try {
                    checkEmptyInput(check);
                    Todo curr = new Todo(input.substring(5,input.length()));
                    list.add(curr);
                    System.out.println(divider + "Got it. I've added this task:\n  " + curr + "\nNow you have " + list.size() + " tasks in the list.\n" + divider);
                } catch (DukeException e) {
                    System.out.println(divider + "OOPS!!! The description of a todo cannot be empty.\n" + divider);
                }
            } else if(check[0].equals("deadline")) {
                try {
                    checkEmptyInput(check);
                    String[] temp = input.substring(9, input.length()).split(" /by ");
                    Deadline curr = new Deadline(temp[0], LocalDate.parse(temp[1]));
                    list.add(curr);
                    System.out.println(divider + "Got it. I've added this task:\n  " + curr + "\nNow you have " + list.size() + " tasks in the list.\n" + divider);
                } catch(DukeException e) {
                    System.out.println(divider + "OOPS!!! The description of a deadline cannot be empty\n" + divider);
                } catch(DateTimeParseException e) {
                    System.out.println(divider + "OOPS!!! The date provided is invalid\n" + divider);
                }
            } else if(check[0].equals("event")) {
                try {
                    checkEmptyInput(check);
                    String[] temp = input.substring(6, input.length()).split(" /at ");
                    Event curr = new Event(temp[0], temp[1]);
                    list.add(curr);
                    System.out.println(divider + "Got it. I've added this task:\n  " + curr + "\nNow you have " + list.size() + " tasks in the list.\n" + divider);
                } catch (DukeException e) {
                    System.out.println(divider + "OOPS!!! The description of an event cannot be empty\n" + divider);
                }
            } else {
                System.out.println(divider + "OOPS!!! I'm sorry, but I don't know what that means :-(\n" + divider);
            }
        }
    }

    private static boolean checkEmptyInput(String[] input) throws DukeException {
        if(input.length <= 1) {
            throw new DukeException();
        }
        return true;
    }
}
