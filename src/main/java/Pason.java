import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Pason {
    private static List<Task> tasks = new ArrayList<>();
    public static void main(String[] args) {
        String input;
        String splitInput[];
        Scanner scanner = new Scanner(System.in);
        
        printMessage("Hey! It's PAson, ready to help :)\nHow can I help you today?");
        while(scanner.hasNext()) {
            try {
                input = scanner.nextLine();
                splitInput = input.split(" ");
                String command = splitInput[0].toLowerCase();
                switch (command) {
                case "bye":
                    printMessage("Bye! I shall go rest now. PAge me when you need me!");
                    return;
                case "list":
                    listTasks();
                    break;
                case "done":
                    doneTask(Integer.parseInt(splitInput[1]));
                    break;
                case "delete":
                    deleteTask(Integer.parseInt(splitInput[1]));
                    break;
                case "todo":
                    addToDo(input);
                    break;
                case "deadline":
                    addDeadline(input);
                    break;
                case "event":
                    addEvent(input);
                    break;
                default:
                    throw new PasonException("I can't help you with this command yet. Sorry!");
                }
            } catch(Exception e) {
                printMessage("Oops! " + e.getMessage());
            }
        }
    }

    public static void printMessage(String message) {
        System.out.println("____________________________________________________________");
        System.out.println(message);
        System.out.println("____________________________________________________________");
    }

    public static void doneTask(int index) {
        try {
            if(tasks.get(index - 1).isDone()) {
                throw new PasonException("You've already marked this task as done.");
            }
            tasks.get(index - 1).markAsDone();
            printMessage("Good job! I've marked this task as done:\n" + tasks.get(index - 1));
        } catch(IndexOutOfBoundsException e) {
            printMessage("Oops! We couldn't find this task. Please enter the correct task number.");
        } catch(Exception e) {
            printMessage("Oops! " + e.getMessage());
        }
    }

    public static void listTasks() {
        if(tasks.size() == 0) {
            printMessage("There are no tasks in your list. Time to add some!");
        } else {
            System.out.println("____________________________________________________________");
            System.out.println("Here are the tasks in your list:");
            for(int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1)+". " + tasks.get(i));
            }
            System.out.println("____________________________________________________________");
        }
    }

    public static void deleteTask(int index) {
        index = index - 1;
        try {
            if(index > tasks.size() - 1 || index < 0) {
                throw new PasonException("You've entered an invalid task number.");
            } else {
                printMessage("Okay! I've removed this task:\n\t" + tasks.get(index) + "\nNow there are " + (tasks.size() - 1) + " tasks in your list.");
                tasks.remove(index);
            }
        } catch(Exception e) {
            printMessage("Oops! " + e.getMessage());
        }
    }

    public static void addToDo(String input) {
        try {
            Pattern p = Pattern.compile("(todo) ([\\w ]*)");
            Matcher m = p.matcher(input);
            if(!m.find()) {
                throw new PasonException("Please include a description for your todo task.");
            }
            ToDo newToDo = new ToDo(m.group(2));
            tasks.add(newToDo);
            printMessage("Done! I've added a new todo:\n\t" + newToDo + "\nNow there are " + tasks.size() + " tasks in your list.");
        } catch(Exception e) {
            printMessage("Oops! " + e.getMessage());
        }
    }

    public static void addDeadline(String input) {
        try {
            String[] splitInput;
            splitInput = input.substring(8).trim().split(" /by ");
            if(splitInput.length == 2) {
                Deadline newDeadline = new Deadline(splitInput[0], splitInput[1]);
                tasks.add(newDeadline);
                printMessage("Done! I've added a new deadline:\n\t" + newDeadline + "\nNow there are " + tasks.size() + " tasks in your list.");
            } else if(splitInput.length == 1) {
                throw new PasonException("Please enter a by date for '" + splitInput[0] + "'");
            } else if(splitInput.length == 0) {
                throw new PasonException("Please enter a description followed by the date in the format: deadline <description> /by <when>");
            } else {
                throw new PasonException("You've entered an invalid format. Please use: deadline <description> /by <when>");
            }
        } catch(PasonException e) {
            printMessage("Oops! " + e.getMessage());
        }
    }

    public static void addEvent(String input) {
        try {
            String[] splitInput;
            splitInput = input.substring(5).trim().split(" /at ");
            if(splitInput.length == 2) {
                Event newEvent = new Event(splitInput[0], splitInput[1]);
                tasks.add(newEvent);
                printMessage("Done! I've added a new event:\n\t" + newEvent + "\nNow there are " + tasks.size() + " tasks in your list.");
            } else if(splitInput.length == 1) {
                System.out.println(splitInput[0]);
                throw new PasonException("Please enter a by date for '" + splitInput[0] + "'");
            } else if(splitInput.length == 0) {
                throw new PasonException("Please enter a description followed by the date in the format: event <description> /at <when>");
            } else {
                throw new PasonException("You've entered an invalid format. Please use: event <description> /at <when>");
            }
        } catch(PasonException e) {
            printMessage("Oops! " + e.getMessage());
        }
    }
}
