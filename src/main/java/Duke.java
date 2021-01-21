import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static final String LINES = "____________________________________________________________";
    public static final String GREETING = "Hello! I'm Duke\nWhat can I do for you?";
    public static final String BYE = "Bye. Hope to see you again soon!";

    public static ArrayList<Task> list;
    public static Scanner sc;

    public static void main(String[] args) {
        File fileName = new File("data/duke.txt");
        sc = new Scanner(System.in);
        ArrayList<Task> tasks = readDataFromFile(fileName);
        list = new ArrayList<>(tasks);
        printWelcome();
        handleInput();
        writeToFile(fileName);
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
            if (command.equalsIgnoreCase(Command.BYE.getAction())){
                printBye();
                break;
            } else if (command.equalsIgnoreCase(Command.LIST.getAction())){
                listingTasks(list);
            } else if (command.equalsIgnoreCase(Command.DONE.getAction()) ||
                    command.equalsIgnoreCase(Command.DELETE.getAction())){
                handleIndexOutOfBoundTask(command);
            } else if (command.toLowerCase().startsWith(Command.DONE.getAction() + " ")){
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
            } else if (command.toLowerCase().startsWith(Command.DELETE.getAction() + " ")){
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
        if (command.equalsIgnoreCase(Command.TODO.getAction()) ||
                command.equalsIgnoreCase(Command.DEADLINE.getAction()) ||
                command.equalsIgnoreCase(Command.EVENT.getAction())){
            throw new EmptyTaskException(command);
        } else if (command.toLowerCase().startsWith(Command.TODO.getAction() + " ")){
            String description = command.substring(5);
            if (description.isEmpty()){
                throw new EmptyTaskException("todo");
            }
            Task task = new Todo(description);
            list.add(task);
            System.out.println("Got it. I've added this task: ");
            System.out.println("  " + task);
            System.out.println("Now you have " + list.size() + " tasks in the list.");
        } else if (command.toLowerCase().startsWith(Command.DEADLINE.getAction() + " ")){
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
                DateTimeProcessor processor = new DateTimeProcessor(by);
                String time = processor.getFullDateTime();
                if (time.equals("Invalid format for date and time.")){
                    System.out.println(time);
                    System.out.println("Your date (and time) should have format yyyy-mm-dd (HH-MM)");
                    System.out.println("For example: 2019-10-15 or 2019-10-15 1800");
                } else {
                    Task task = new Deadline(description, time);
                    list.add(task);
                    System.out.println("Got it. I've added this task: ");
                    System.out.println("  " + task);
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                }
            }
        } else if (command.toLowerCase().startsWith(Command.EVENT.getAction() + " ")){
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
                DateTimeProcessor processor = new DateTimeProcessor(at);
                String time = processor.getFullDateTime();
                if (time.equals("Invalid format for date and time.")){
                    System.out.println(time);
                    System.out.println("Your date (and time) should have format yyyy-mm-dd (HH-MM)");
                    System.out.println("For example: 2019-10-15 or 2019-10-15 1800");
                } else {
                    Task task = new Event(description, time);
                    list.add(task);
                    System.out.println("Got it. I've added this task: ");
                    System.out.println("  " + task);
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                }
            }
        } else {
            throw new NoSuchCommandException();
        }
    }

    public static void listingTasks(ArrayList<Task> list) {
        System.out.println(LINES);
        if (list.isEmpty()){
            System.out.println("There is no task in your list.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < list.size(); i++) {
                Task task = list.get(i);
                System.out.println((i + 1) + ". " + task);
            }
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

    public static ArrayList<Task> readDataFromFile(File f){
        try {
            Scanner sc = new Scanner(f);
            ArrayList<Task> tasks = new ArrayList<>();
            while (sc.hasNextLine()){
                String currLine = sc.nextLine();
                String[] information = currLine.split("\\|");
                switch (information[0].charAt(0)) {
                    case 'T': {
                        Task newTask = new Todo(information[2]);
                        if (information[1].trim().equals("1")) newTask.markAsDone();
                        tasks.add(newTask);
                        break;
                    }
                    case 'D': {
                        Task newTask = new Deadline(information[2], information[3]);
                        if (information[1].trim().equals("1")) newTask.markAsDone();
                        tasks.add(newTask);
                        break;
                    }
                    case 'E': {
                        Task newTask = new Event(information[2], information[3]);
                        if (information[1].trim().equals("1")) newTask.markAsDone();
                        tasks.add(newTask);
                        break;
                    }
                }
            }
            return tasks;
        } catch (FileNotFoundException e) {
            System.out.println("File not found " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static void writeToFile(File f){
        try {
            FileWriter fw = new FileWriter(f);
            for (Task task : list){
                String content = task.getType() + " | " + task.getStatusNumber() + " | " + task.getDescription() + task.getTime();
                fw.write(content + '\n');
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Unable to write to file " + e.getMessage());
        }
    }
}
