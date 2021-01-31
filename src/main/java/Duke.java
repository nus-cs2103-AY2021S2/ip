import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) throws TodoException, DeadlineException, EventException, UnknownException {
        Scanner sc = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        while (true) {
            String command = sc.nextLine();
            if (command.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(i + 1 + "." + tasks.get(i).toString());
                }
            } else if (command.contains("todo")) {
                String[] line = command.split(" ");
                int length = line.length;
                if (length == 1) {
                    throw new TodoException("Oops! The description for Todo cannot be empty.");
                }
                System.out.println("Got it. I've added this task:");
                String description = "";
                String date = "";
                String time = "";
                boolean isDesc = true;
                boolean isDate = true;
                for (int i = 1; i < length; i++) {
                    if (line[i].equals("/at")) {
                        isDesc = false;
                    } else if (isDesc) {
                        if (i + 1 == length) {
                            description += line[i];
                        } else {
                            description += line[i] + " ";
                        }
                    } else if (isDate) {
                        date += line[i];
                        isDate = false;
                    } else {
                        time += line[i];
                    }
                }
                Todo todo = new Todo(description, date, time);
                tasks.add(todo);
                System.out.println(todo.toString());
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else if (command.contains("deadline")) {
                String[] line = command.split(" ");
                int length = line.length;
                if (length == 1) {
                    throw new DeadlineException("Oops! The description for Deadline cannot be empty.");
                }
                System.out.println("Got it. I've added this task:");
                String description = "";
                String date = "";
                String time = "";
                boolean isDesc = true;
                boolean isDate = true;
                for (int i = 1; i < length; i++) {
                    if (line[i].equals("/by")) {
                        isDesc = false;
                    } else if (isDesc) {
                        if (i + 1 == length) {
                            description += line[i];
                        } else {
                            description += line[i] + " ";
                        }
                    } else if (isDate) {
                        date += line[i];
                        isDate = false;
                    } else {
                        time += line[i];
                    }
                }
                Deadline deadline = new Deadline(description, date, time);
                tasks.add(deadline);
                System.out.println(deadline.toString());
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else if (command.contains("event")) {
                String[] line = command.split(" ");
                int length = line.length;
                if (length == 1) {
                    throw new EventException("Oops! The description for Event cannot be empty.");
                }
                System.out.println("Got it. I've added this task:");
                String description = "";
                String date = "";
                String time = "";
                boolean isDesc = true;
                boolean isDate = true;
                for (int i = 1; i < length; i++) {
                    if (line[i].equals("/at")) {
                        isDesc = false;
                    } else if (isDesc) {
                        if (i + 1 == length) {
                            description += line[i];
                        } else {
                            description += line[i] + " ";
                        }
                    } else if (isDate) {
                        date += line[i];
                        isDate = false;
                    } else {
                        time += line[i];
                    }
                }
                Event event = new Event(description, date, time);
                tasks.add(event);
                System.out.println(event.toString());
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else if (command.contains("done")) {
                String[] line = command.split(" ");
                int number = Integer.parseInt(line[1]) - 1;
                tasks.get(number).markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(tasks.get(number).toString());
            } else if (command.contains("delete")) {
                System.out.println("Noted. I've removed this task:");
                String[] line = command.split(" ");
                int number = Integer.parseInt(line[1]) - 1;
                Task removedTask = tasks.remove(number);
                System.out.println(removedTask.toString());
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else if (command.equals("bye")) {
                System.out.println("Bye! Hope to see you again soon!");
                break;
            } else {
                throw new UnknownException("Oops! I'm sorry, but I don't know what that means!");
            }
        }
        System.exit(0);
    }
}