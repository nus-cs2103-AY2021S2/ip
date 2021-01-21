import java.util.*;

public class Duke {

    static void level1() {
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            System.out.println(input);
            input = sc.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }


    static void level2() {
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        List<String> lst = new ArrayList<>();

        while (!input.equals("bye")) {
            if (!input.equals("list")) {
                lst.add(input);
                System.out.println("added: " + input);
            } else {
                for (int i = 1; i <= lst.size(); i++) {
                    System.out.println(i + ". " + lst.get(i-1));
                }
            }
            input = sc.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");

    }


    static void level3() {
        List<Task> lst = new ArrayList<>();
        lst.add(new Task("read book"));
        lst.add(new Task("return book"));
        lst.add(new Task("buy bread"));
        lst.get(0).markAsDone();

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String[] inputArray = input.split(" ");

        while (true) {
            if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");

                for (int i = 1; i <= lst.size(); i++) {
                    System.out.println(i + "." + lst.get(i - 1));
                }
            } else if (inputArray[0].equals("done")) {
                System.out.println("Nice! I've marked this task as done:");
                lst.get(Integer.parseInt(inputArray[1]) - 1).markAsDone();
                System.out.println("  " + lst.get(Integer.parseInt(inputArray[1]) - 1));
            }

            input = sc.nextLine();
            inputArray = input.split(" ");
        }
    }

    static void level4() throws DukeException {
        List<Task> lst = new ArrayList<>();
        lst.add(new ToDoTask("read book"));
        lst.add(new DeadlineTask("return book", "June 6th"));
        lst.add(new EventTask("project meeting", "Aug 6th 2-4pm"));
        lst.add(new ToDoTask("join sports club"));
        lst.get(0).markAsDone();
        lst.get(3).markAsDone();

        Scanner sc = new Scanner(System.in);
        String input;
        String[] inputArray;

        while (sc.hasNextLine()) {
            input = sc.nextLine();
            inputArray = input.split(" ");
            if (inputArray[0].equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i <= lst.size(); i++) {
                    System.out.println(i + "." + lst.get(i - 1));
                }
            } else if (inputArray[0].equals("todo") || inputArray[0].equals("deadline") || inputArray[0].equals("event")) { //adding a task
                Task task = new Task("");
                String[] inputArr1;
                String[] inputArr2;
                if (inputArray[0].equals("todo")) {
                    if (inputArray.length == 1) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    inputArr1 = input.split("todo ");
                    task = new ToDoTask(inputArr1[1]);
                } else if (inputArray[0].equals("deadline")) {
                    if (inputArray.length == 1) {
                        throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                    inputArr1 = input.split("deadline ");
                    inputArr2 = inputArr1[1].split(" /by ");
                    task = new DeadlineTask(inputArr2[0], inputArr2[1]);
                } else if (inputArray[0].equals("event")) {
                    if (inputArray.length == 1) {
                        throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                    inputArr1 = input.split("event ");
                    inputArr2 = inputArr1[1].split(" /at ");
                    task = new EventTask(inputArr2[0], inputArr2[1]);
                }
                lst.add(task);
                System.out.println("Got it. I've added this task: ");
                System.out.println("  " + task);
                System.out.println("Now you have " + lst.size() + " tasks in the list.");
            } else {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }

        }


    }




    public static void main(String[] args) {

        try {
            String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
            System.out.println("Hello from\n" + logo);

            level4();
        } catch (DukeException e) {
            System.out.println(e);
        }

    }
}
