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

    public static void handleInput() {
        while (sc.hasNextLine()){
            String command = sc.nextLine();
            if (command.equals(Command.BYE.getAction())){
                printBye();
                break;
            } else if (command.equals(Command.LIST.getAction())){
                listingTasks(list);
            } else if (command.equals(Command.DONE.getAction()) || command.equals(Command.DELETE.getAction())){
                handleIndexOutOfBoundTask(command);
            } else if (command.startsWith(Command.DONE.getAction() + " ")){
                try{
                    int doneIndex = Integer.parseInt(command.substring(5));
                    if (doneIndex > list.size() || doneIndex <= 0){
                        handleIndexOutOfBoundTask("done");
                    } else {
                        Task task = list.get(doneIndex - 1);
                        markingDoneTask(task);
                    }
                } catch (NumberFormatException e){
                    handleIndexOutOfBoundTask("done");
                }
            } else if (command.startsWith(Command.DELETE.getAction() + " ")){
                try{
                    int deleteIndex = Integer.parseInt(command.substring(7));
                    if (deleteIndex > list.size() || deleteIndex <= 0){
                        handleIndexOutOfBoundTask("delete");
                    } else {
                        removeTask(list, deleteIndex - 1);
                    }
                } catch (NumberFormatException e){
                    handleIndexOutOfBoundTask("delete");
                }
            } else {
                try {
                    addTask(command);
                } catch (NoSuchCommandException e){
                    System.out.println(e.getMessage());
                } catch (EmptyTaskException e){
                    System.out.println(e.getMessage());
                } catch (InvalidTask e){
                    System.out.println(e.getMessage());
                } finally {
                    System.out.println(LINES);
                    System.out.println();
                }
            }
        }
    }

    public static void printBye(){
        System.out.println(LINES);
        System.out.println(BYE);
        System.out.println(LINES);
        System.out.println();
    }

    public static void addTask(String command) throws NoSuchCommandException, EmptyTaskException, InvalidTask{
        System.out.println(LINES);
        if (command.equals(Command.TODO.getAction()) || command.equals(Command.DEADLINE.getAction()) || command.equals(Command.EVENT.getAction())){
            throw new EmptyTaskException(command);
        } else if (command.startsWith(Command.TODO.getAction() + " ")){
            String description = command.substring(5);
            if (description.isEmpty()){
                throw new EmptyTaskException("todo");
            }
            Task task = new Todo(description);
            list.add(task);
            System.out.println("Got it. I've added this task: ");
            System.out.println("  " + task);
            System.out.println("Now you have " + list.size() + " tasks in the list.");
        } else if (command.startsWith(Command.DEADLINE.getAction() + " ")){
            String content = command.substring(9);
            if (content.isEmpty()){
                throw new EmptyTaskException("deadline");
            }
            int byIndex = content.indexOf("/by");
            if (byIndex == -1){
                throw new InvalidTask("deadline");
            } else {
                String description = content.substring(0, byIndex - 1);
                String by = content.substring(byIndex + 4);
                Task task = new Deadline(description, by);
                list.add(task);
                System.out.println("Got it. I've added this task: ");
                System.out.println("  " + task);
                System.out.println("Now you have " + list.size() + " tasks in the list.");
            }
        } else if (command.startsWith(Command.EVENT.getAction() + " ")){
            String content = command.substring(6);
            if (content.isEmpty()){
                throw new EmptyTaskException("event");
            }
            int atIndex = content.indexOf("/at");
            if (atIndex == -1){
                throw new InvalidTask("event");
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
            throw new NoSuchCommandException();
        }
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
        System.out.println("  " + task.toString());
        System.out.println(LINES);
        System.out.println();
    }

    public static void removeTask(ArrayList<Task> list, int index){
        Task task = list.remove(index);
        System.out.println(LINES);
        System.out.println("Noted. I've removed this task: ");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + list.size() + " tasks in the list.");
        System.out.println(LINES);
        System.out.println();
    }

    public static void handleIndexOutOfBoundTask(String type){
        System.out.println(LINES);
        System.out.println(type + " command should be followed by a number between 1 and " + list.size() + ".");
        System.out.println(LINES);
        System.out.println();
    }
}
