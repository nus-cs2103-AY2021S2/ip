import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static final String LINES = "____________________________________________________________";
    public static final String GREETING = "Hello! I'm Duke\nWhat can I do for you?";
    public static final String BYE = "Bye. Hope to see you again soon!";

    public static ArrayList<Task> list;
    public static Scanner sc;

    public static void main(String[] args) {
        sc = new Scanner(System.in);
        list = new ArrayList<>();
        printWelcome();
        handleInput();
        sc.close();
    }

    public static void printWelcome(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(LINES);
        System.out.println(GREETING);
        System.out.println(LINES);
        System.out.println();
    }

    public static void handleInput(){
        while (sc.hasNextLine()){
            String command = sc.nextLine();
            if (command.equals("bye")){
                printBye();
                break;
            } else if (command.equals("list")){
                listingTasks(list);
            } else if (command.startsWith("done ")){
                try{
                    int doneIndex = Integer.parseInt(command.substring(5));
                    if (doneIndex > list.size() || doneIndex <= 0){
                        handleIndexOutOfBoundTask();
                    } else {
                        Task task = list.get(doneIndex - 1);
                        markingDoneTask(task);
                    }
                } catch (NumberFormatException e){ //just add a normal task
                    handleIndexOutOfBoundTask();
                }
            }
            else {
                addTask(command);
            }
        }
    }

    public static void printBye(){
        System.out.println(LINES);
        System.out.println(BYE);
        System.out.println(LINES);
        System.out.println();
    }

    public static void addTask(String command){
        System.out.println(LINES);
        if (command.startsWith("todo ")){
            String description = command.substring(5);
            Task task = new Todo(description);
            list.add(task);
            System.out.println("Got it. I've added this task: ");
            System.out.println("  " + task);
            System.out.println("Now you have " + list.size() + " tasks in the list.");
        } else if (command.startsWith("deadline ")){
            String content = command.substring(9);
            int byIndex = content.indexOf("/by");
            if (byIndex == -1){
                System.out.println("Invalid command for deadline! Try again");
            } else {
                String description = content.substring(0, byIndex - 1);
                String by = content.substring(byIndex + 4);
                Task task = new Deadline(description, by);
                list.add(task);
                System.out.println("Got it. I've added this task: ");
                System.out.println("  " + task);
                System.out.println("Now you have " + list.size() + " tasks in the list.");
            }
        } else if (command.startsWith("event ")){
            String content = command.substring(6);
            int atIndex = content.indexOf("/at");
            if (atIndex == -1){
                System.out.println("Invalid command for event! Try again");
            } else {
                String description = content.substring(0, atIndex - 1);
                String at = content.substring(atIndex + 4);
                Task task = new Event(description, at);
                list.add(task);
                System.out.println("Got it. I've added this task: ");
                System.out.println("  " + task);
                System.out.println("Now you have " + list.size() + " tasks in the list.");
            }
        } else {
            System.out.println("No such command. In order to add a new task, start a command with todo, deadline or event.");
        }
        System.out.println(LINES);
        System.out.println();
    }

    public static void listingTasks(ArrayList<Task> list) {
        System.out.println(LINES);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            System.out.println((i + 1) + ". " + task);
        }
        System.out.println(LINES);
        System.out.println();
    }

    public static void markingDoneTask(Task task){
        task.markAsDone();
        System.out.println(LINES);
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("  [" + task.getStatusIcon() + "] " + task.getDescription());
        System.out.println(LINES);
        System.out.println();
    }

    public static void handleIndexOutOfBoundTask(){
        System.out.println(LINES);
        System.out.println("Done command should be followed by a number between 1 and " + list.size() + ".");
        System.out.println(LINES);
        System.out.println();
    }
}
