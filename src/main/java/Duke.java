import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello, I am Duke, your personal Assistant. How may I help you today?:");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine().trim();

        // Initialise list
        Task[] tasks = new Task[100];
        int numOfItems = 0;
        String[] command = {"list", "bye", "todo", "deadline", "event"};

        // User input
        while (!input.equals("bye")) {
            String[] inputArr = input.split(" ");
            try {
                switch(inputArr[0]) {
                    case "list":
                        System.out.println("Here are the tasks on your list:");
                        for (int i = 1; i <= numOfItems; i++) {
                            String output = String.format("%s. %s", i, tasks[i - 1].toString());
                            System.out.println(output);
                        }
                        break;
                    case "done":
                        doneCommand(tasks, inputArr);
                        break;
                    case "todo":
                        toDoCommand(inputArr, tasks, numOfItems, input);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(tasks[numOfItems].toString());
                        numOfItems++;
                        System.out.println("Now you have " + numOfItems + " tasks in the list.");
                        break;
                    case "deadline":
                        deadlineCommand(inputArr, tasks, numOfItems, input);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(tasks[numOfItems].toString());
                        numOfItems++;
                        System.out.println("Now you have " + numOfItems + " tasks in the list.");
                        break;
                    case "event":
                        eventCommand(inputArr, tasks, numOfItems, input);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(tasks[numOfItems].toString());
                        numOfItems++;
                        System.out.println("Now you have " + numOfItems + " tasks in the list.");
                        break;
                    default:
                        throw new DukeWrongInputException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeMissingInputException e) {
                System.out.println(e.toString());
            } catch (DukeWrongInputException e) {
                System.out.println(e.toString());
            }
            input = sc.nextLine().trim();
        }
        byeCommand();
        sc.close();
    }

    public static void byeCommand(){
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void doneCommand(Task[] list, String[] inputArr ){
        System.out.println("Nice! I've marked this task as done:");
        list[Integer.parseInt(inputArr[1]) - 1].markAsDone();
        System.out.println(list[Integer.parseInt(inputArr[1]) - 1].toString());
    }

    public static void toDoCommand(String[] inputArr, Task[] list, int numOfItems, String input) throws DukeMissingInputException {
        String description = "";
        if (input.equals("todo")) {
            throw new DukeMissingInputException("OOPS!!! The description of a event cannot be empty.");
        } else {
            for (int i = 1; i < inputArr.length; i++) {
                description += (inputArr[i] + " ");
            }
        }
        description = description.trim();
        list[numOfItems] = new Todo(description);
        list[numOfItems].toString();
    }

    public static void deadlineCommand(String[] inputArr, Task[] list, int numOfItems, String input) throws DukeMissingInputException {
        String description = "";
        String deadline = "";
        boolean foundBy = false;
        if (input.equals("deadline")) {
            throw new DukeMissingInputException("OOPS!!! The description of a event cannot be empty.");
        } else {
            for (int i = 1; i < inputArr.length; i++) {
                if (inputArr[i].equals("/by")) {
                    foundBy = true;
                    continue;
                }
                if (foundBy) {
                    deadline += (inputArr[i] + " ");
                } else {
                    description += (inputArr[i] + " ");
                }
            }
        }
        deadline = deadline.trim();
        list[numOfItems] = new Deadline(description, deadline);
    }

    public static void eventCommand(String[] inputArr, Task[] list, int numOfItems, String input) throws DukeMissingInputException {
        String description = "";
        String time = "";
        boolean foundAt = false;
        if (input.equals("event")) {
            throw new DukeMissingInputException("OOPS!!! The description of a event cannot be empty.");
        } else {
            for (int i = 1; i < inputArr.length; i++) {
                if (inputArr[i].equals("/at")){
                    foundAt = true;
                    continue;
                }
                if (foundAt) {
                    time += (inputArr[i] + " ");
                } else {
                    description += (inputArr[i] + " ");
                }
            }
        }
        time = time.trim();
        list[numOfItems] = new Event(description, time);
    }
}