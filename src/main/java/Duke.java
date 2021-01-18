import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duke {
    private static List<Task> tasks = new ArrayList<>();
    public static void main(String[] args) {
        String input;
        String splitInput[];
        Scanner scanner = new Scanner(System.in);
        
        printMessage("Hey! It's PAson, ready to help :)\nHow can I help you today?");
        while(scanner.hasNext()) {
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
            }
        }
    }

    public static void printMessage(String message) {
        System.out.println("____________________________________________________________");
        System.out.println(message);
        System.out.println("____________________________________________________________");
    }

    public static void doneTask(int index) {
        tasks.get(index - 1).markAsDone();
        printMessage("Good job! I've marked this task as done:\n"+tasks.get(index - 1));
    }

    public static void listTasks() {
        if(tasks.size() == 0) {
            printMessage("There are no tasks in your list. Time to add some!");
        } else {
            System.out.println("____________________________________________________________");
            System.out.println("Here are the tasks in your list:");
            for(int i = 0; i < tasks.size(); i++) {
                System.out.println((i+1)+". " + tasks.get(i));
            }
            System.out.println("____________________________________________________________");
        }
    }

    public static void addToDo(String input) {
        Pattern p = Pattern.compile("(todo) ([\\w ]*)");
        Matcher m = p.matcher(input);
        if(!m.find()) {
            printMessage("You've entered an invalid format.");
            return;
        }
        ToDo newToDo = new ToDo(m.group(2));
        tasks.add(newToDo);
        printMessage("Done! I've added a new todo:\n\t" + newToDo + "\nNow there are " + tasks.size() + " tasks in your list.");
    }

    public static void addDeadline(String input) {
        Pattern p = Pattern.compile("(deadline) ([\\w ]*)( \\/by )([\\w- ]*)");
        Matcher m = p.matcher(input);
        if(!m.find()) {
            printMessage("You've entered an invalid format.");
            return;
        }
        Deadline newDeadline = new Deadline(m.group(2), m.group(4));
        tasks.add(newDeadline);
        printMessage("Done! I've added a new deadline:\n\t" + newDeadline + "\nNow there are " + tasks.size() + " tasks in your list.");
    }

    public static void addEvent(String input) {
        Pattern p = Pattern.compile("(event) ([\\w ]*)( \\/at )([\\w-]*) ([\\w- ]*)");
        Matcher m = p.matcher(input);
        if(!m.find()) {
            printMessage("You've entered an invalid format.");
            return;
        }
        Event newEvent = new Event(m.group(2), m.group(4), m.group(5));
        tasks.add(newEvent);
        printMessage("Done! I've added a new event:\n\t" + newEvent + "\nNow there are " + tasks.size() + " tasks in your list.");
    }
}
