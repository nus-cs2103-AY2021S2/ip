import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello, I am Duke, your personal Assistant. How may I help you today?:");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine().trim();

        // Initialise list
        List<Task> tasks = new ArrayList<>();
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
                            String output = String.format("%s. %s", i, tasks.get(i - 1).toString());
                            System.out.println(output);
                        }
                        break;
                    case "done":
                        doneCommand(tasks, inputArr);
                        break;
                    case "todo":
                        toDoCommand(inputArr, tasks, numOfItems, input);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(tasks.get(numOfItems).toString());
                        numOfItems++;
                        System.out.println("Now you have " + numOfItems + " tasks in the list.");
                        break;
                    case "deadline":
                        deadlineCommand(inputArr, tasks, numOfItems, input);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(tasks.get(numOfItems).toString());
                        numOfItems++;
                        System.out.println("Now you have " + numOfItems + " tasks in the list.");
                        break;
                    case "event":
                        eventCommand(inputArr, tasks, numOfItems, input);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(tasks.get(numOfItems).toString());
                        numOfItems++;
                        System.out.println("Now you have " + numOfItems + " tasks in the list.");
                        break;
                    case "delete":
                        System.out.println("Noted. I've removed this task: ");
                        System.out.println(tasks.get(Integer.parseInt(inputArr[1]) - 1));
                        deleteCommand(inputArr, tasks);
                        numOfItems--;
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

    public static void doneCommand(List<Task>tasks, String[] inputArr ){
        System.out.println("Nice! I've marked this task as done:");
        Task doneTask = tasks.get(Integer.parseInt(inputArr[1]) - 1);
        doneTask.markAsDone();
        System.out.println(tasks.get(Integer.parseInt(inputArr[1]) - 1).toString());
    }

    public static void toDoCommand(String[] inputArr, List<Task> tasks, int numOfItems, String input)
            throws DukeMissingInputException {
        String description = "";
        if (input.equals("todo")) {
            throw new DukeMissingInputException("OOPS!!! The description of a todo cannot be empty.");
        } else {
            for (int i = 1; i < inputArr.length; i++) {
                description += (inputArr[i] + " ");
            }
        }
        description = description.trim();
        tasks.add(new Todo(description)) ;
        tasks.get(numOfItems).toString();
    }

    public static void deadlineCommand(String[] inputArr, List<Task> tasks, int numOfItems, String input)
            throws DukeMissingInputException {
        String description = "";
        String deadline = "";
        boolean foundBy = false;
        if (input.equals("deadline")) {
            throw new DukeMissingInputException("OOPS!!! The description of a deadline cannot be empty.");
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
        tasks.add(new Deadline(description, deadline));
    }

    public static void eventCommand(String[] inputArr, List<Task> tasks, int numOfItems, String input)
            throws DukeMissingInputException {
        String description = "";
        String time = "";
        boolean foundAt = false;
        if (input.equals("event")) {
            throw new DukeMissingInputException("OOPS!!! The description of an event cannot be empty.");
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
        tasks.add(new Event(description, time));
    }

    public static void deleteCommand(String[] inputArr, List<Task> tasks){
        tasks.remove(Integer.parseInt(inputArr[1]) - 1);
    }
}