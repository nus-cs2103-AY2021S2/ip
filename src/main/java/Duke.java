import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;


public class Duke {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        List<Task> list = new ArrayList<>();

        greet();
        commandList(list);
    }

    //Duke greets the user.
    public static void greet() {
        System.out.println("\t____________________________________________________________\n"
                        + "\tHello! I'm Duke\n\tWhat can I do for you?\n"
                        + "\t____________________________________________________________\n");
    }

    //Echos the input back to the user.
    public static void echo() {
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            System.out.println("\t____________________________________________________________\n"
                    + "\t" + input + "\n"
                    + "\t____________________________________________________________\n");
            input = sc.nextLine();
        }
        System.out.println("\t____________________________________________________________\n"
                + "\tBye. Hope to see you again soon!\n"
                + "\t____________________________________________________________\n"
        );
    }

    //Adds the input in a list and echo it back to the user.
    //Prints the list if input is "list"
    //Mark task as done if input is "done" with task number.
    public static void commandList(List<Task> list) {
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                showList(list);
            } else {
                try {
                    String[] check = input.split(" ");
                    if (check[0].equals("done")) {
                        markTaskDone(list, check);
                    } else if (check[0].equals("delete")) {
                        deleteFromList(list, check);
                    } else {
                        addToList(list, input, check);
                    }
                } catch (TaskNotFoundException e) {
                    System.out.println("\t____________________________________________________________\n"
                                    + "\tTask not in list.\n"
                                    + "\t____________________________________________________________\n");
                } catch (CommandNotValidException e) {
                    System.out.println("\t____________________________________________________________\n"
                                    + "\tCommand not valid. Please use \"todo\", \"deadline\"\n"
                                    + "\tor \"event\" followed by task description to add new tasks.\n"
                                    + "\tPlease use \"list\" to view your list of tasks.\n"
                                    + "\tPlease use \"done\" followed by index to mark completed tasks.\n"
                                    + "\tPlease use \"delete\" followed by index to delete tasks.\n"
                                    + "\tPlease use \"bye\" to exit.\n"
                                    + "\t____________________________________________________________\n");
                } catch (DescriptionNotFoundException e) {
                    System.out.println("\t____________________________________________________________\n"
                                    + "\tPlease provide description for your task.\n"
                                    + "\t____________________________________________________________\n");
                } catch (DateNotFoundException e) {
                    System.out.println("\t____________________________________________________________\n"
                                    + "\tPlease enter a valid deadline (after \"/by\") for Deadline Tasks\n"
                                    + "\tor time (after \"/at\") for Event Tasks.\n"
                                    + "\t____________________________________________________________\n");
                } catch (InvalidTaskSelectionException e) {
                    System.out.println("\t____________________________________________________________\n"
                                    + "\tPlease enter task number after command.\n"
                                    + "\t____________________________________________________________\n");
                }
            }
            input = sc.nextLine();
        }
        System.out.println("\t____________________________________________________________\n"
                + "\tBye. Hope to see you again soon!\n"
                + "\t____________________________________________________________\n");
    }

    //Prints out the items in the list.
    public static void showList(List<Task> list) {
        if (list.size() == 0) {
            System.out.println("\t____________________________________________________________\n"
                        + "\tThere are no items in your list.\n"
                        + "\t____________________________________________________________\n");
        } else {
            System.out.println("\t____________________________________________________________\n"
                    + "\tHere are the tasks in your list:");
            for (int i = 0; i < list.size(); i++) {
                System.out.println("\t" + (i + 1) + "." + list.get(i).toString());
            }
            System.out.println("\t____________________________________________________________\n");
        }
    }

    //Check if string is number
    public static boolean isNumber(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    //Mark numbered task in list as done.
    public static void markTaskDone(List<Task> list, String[] check)
            throws TaskNotFoundException, InvalidTaskSelectionException {
        if (check.length == 1 || !isNumber(check[1])) {
            throw new InvalidTaskSelectionException();
        }
        int num = Integer.parseInt(check[1]);
        if (num > 0 && num <= list.size()) {
            list.get(num - 1).markAsDone();
            System.out.println("\t____________________________________________________________\n"
                    + "\tNice! I've marked this task as done:\n\t\t"
                    + list.get(num - 1).toString() + "\n"
                    + "\t____________________________________________________________\n");

        } else {
            throw new TaskNotFoundException();
        }
    }

    //Add different type of tasks in list.
    public static void addToList(List<Task> list, String input, String[] check)
            throws CommandNotValidException, DescriptionNotFoundException, DateNotFoundException {
        Task temp;
        String description;
        String date;

        if (check[0].equals("todo") || check[0].equals("deadline") || check[0].equals("event")) {
            if (check.length == 1) {
                throw new DescriptionNotFoundException();
            }
            if (check[0].equals("todo")) {
                description = input.substring(5);
                temp = new TodoTask(description);
            } else if (check[0].equals("deadline")) {
                int index = input.lastIndexOf("/by");
                if (index == -1) {
                    throw new DateNotFoundException();
                }
                description = input.substring(9, index - 1);
                date = input.substring(index + 3).stripLeading().stripTrailing();
                if (date.isEmpty()) {
                    throw new DateNotFoundException();
                }
                temp = new DeadlineTask(description, date);
            } else {
                int index = input.lastIndexOf("/at");
                if (index == -1) {
                    throw new DateNotFoundException();
                }
                description = input.substring(6, index - 1);
                date = input.substring(index + 3).stripLeading().stripTrailing();
                if (date.isEmpty()) {
                    throw new DateNotFoundException();
                }
                temp = new EventTask(description, date);
            }
        } else {
            throw new CommandNotValidException();
        }
        list.add(temp);
        System.out.println("\t____________________________________________________________\n"
                + "\tGot it. I've added this task:\n"
                + "\t   " + temp.toString() + "\n"
                + "\tNow you have " + list.size() + " tasks in the list.\n"
                + "\t____________________________________________________________\n");
    }

    //Delete task from list
    public static void deleteFromList(List<Task> list, String[] check)
            throws InvalidTaskSelectionException, TaskNotFoundException{
        if (check.length == 1 || !isNumber(check[1])) {
            throw new InvalidTaskSelectionException();
        }
        int num = Integer.parseInt(check[1]);
        if (num > 0 && num <= list.size()) {
            Task deleted = list.get(num - 1);
            list.remove(num - 1);
            System.out.println("\t____________________________________________________________\n"
                    + "\tNoted. I've removed this task:\n"
                    + "\t   " + deleted.toString() + "\n"
                    + "\tNow you have " + list.size() + " tasks in the list.\n"
                    + "\t____________________________________________________________\n");

        } else {
            throw new TaskNotFoundException();
        }
    }
}
