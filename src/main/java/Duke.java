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
        while (true){
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
                    addTask(command);
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
        list.add(new Task(command));
        System.out.println("added: " + command);
        System.out.println(LINES);
        System.out.println();
    }

    public static void listingTasks(ArrayList<Task> list) {
        System.out.println(LINES);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            System.out.println((i + 1) + ". [" + task.getStatusIcon() + "] " + task.getDescription());
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
        System.out.println("No such task in the list");
        System.out.println(LINES);
        System.out.println();
    }
}
