import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static java.lang.Integer.parseInt;


public class Duke {
    public static void main(String[] args) throws FileNotFoundException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String hello = "What can I do for you?\n";
        String goodbye = "Bye. Hope to see you again soon!\n";
        String SAVE_FILE_PATH = "./data/";
        String SAVE_FILE_NAME = "data.txt";
        Storage storage;

        storage = new Storage(SAVE_FILE_PATH,SAVE_FILE_NAME);
        ArrayList<String> input;
        ArrayList<Task> tasks;
        try {
            input = storage.readFile();
            tasks = readInput(input);
        }
        catch (FileNotFoundException e){
            throw new FileNotFoundException("No File Detected");
        }
     //   catch (IndexOutOfBoundsException e){
     //       tasks = new ArrayList<Task>();
     //   }
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello from\n" + logo);
        System.out.println("Duke: " + hello);
        String cmd = sc.nextLine();
        String[] pre = cmd.split("\\s+");
        while(!cmd.equals("bye")) {
            int n;
            switch (pre[0]) {
                case "list" :
                    printTasks(tasks);
                    storage.writeTasks(tasks);
                    break;
                case "done" :
                    n = parseInt(pre[1]) - 1;
                    doneTask(tasks, n);
                    storage.writeTasks(tasks);
                    break;
                case "delete" :
                    n = parseInt(pre[1]) - 1;
                    deleteTask(tasks, n);
                    storage.writeTasks(tasks);
                    break;
                case "deadline" :
                    addDeadline(tasks, cmd);
                    storage.writeTasks(tasks);
                    break;
                case "event" :
                    addEvent(tasks, cmd);
                    storage.writeTasks(tasks);
                    break;
                case "todo" :
                    addToDo(tasks, pre);
                    storage.writeTasks(tasks);
                    break;
                default :
                    addError();
            }
            cmd = sc.nextLine();
            pre = cmd.split("\\s+");
        }
        System.out.println("Duke: " + goodbye);
    }

    private static void deleteTask(ArrayList<Task> tasks, int n) {
        try {
            Task t = tasks.get(n);
            System.out.println("Noted. I've removed this task: ");
            tasks.remove(n);
            System.out.println(t);
            int size = tasks.size();
            printTotalTasks(size);
        }
        catch(IndexOutOfBoundsException e){
            System.out.println("Wrong task ID");
        }
    }

    private static void printTotalTasks(int size) {
        if(size != 1) {
            System.out.printf("Now you have %d tasks in the list.\n", size);
        }
        else{
            System.out.printf("Now you have %d task in the list.\n", size);
        }
    }

    private static void doneTask(ArrayList<Task> tasks, int n) {
        try {
            tasks.set(n, tasks.get(n).finish());
            System.out.println("Nice! I've marked this task as done: \n");
            System.out.println(tasks.get(n));
        }
        catch(IndexOutOfBoundsException e){
            System.out.println("Wrong task ID");
        }
    }

    private static void addError() {
        System.out.println("Command not understood");
    }

    private static void addToDo(ArrayList<Task> tasks, String[] pre) {
        String s = "";
        for (int i = 1; i < pre.length; i++) {
            s = s + pre[i] + " ";
        }
        if (!s.equals("")) {
            tasks.add(new Todo(s,false));
            System.out.println("Got it. I've added this task:");
            System.out.println(tasks.get(tasks.size() - 1));
            printTotalTasks(tasks.size());
        }
        else {
            System.out.println("Please add a description for todo.");
        }
    }

    private static void addEvent(ArrayList<Task> tasks, String cmd) {
        String[] pre2 = cmd.split("/at");
        try {
            String s = pre2[0];
            String[] dates = pre2[1].split("\\s+");
            System.out.println("Got it. I've added this task:");
            LocalDate startDate = LocalDate.parse(dates[1],DateTimeFormatter.ofPattern("MM/dd/yyyy"));
            LocalTime startTime = LocalTime.parse(dates[2],DateTimeFormatter.ofPattern("HH:mm"));
            LocalDate endDate = LocalDate.parse(dates[3],DateTimeFormatter.ofPattern("MM/dd/yyyy"));
            LocalTime endTime = LocalTime.parse(dates[4],DateTimeFormatter.ofPattern("HH:mm"));
            tasks.add(new Event(s.substring(6), false, startDate,startTime,endDate,endTime));
            System.out.println(tasks.get(tasks.size() - 1));
            printTotalTasks(tasks.size());
        }
        catch (ArrayIndexOutOfBoundsException  e){
            System.out.println("Please enter a description for event");
        }
    }

    private static void addDeadline(ArrayList<Task> tasks, String cmd) {
        String[] pre2 = cmd.split("/by");
        try {
            String s = pre2[0];
            String[] dates = pre2[1].split("\\s+");
            LocalDate deadlineDate = LocalDate.parse(dates[1], DateTimeFormatter.ofPattern("MM/dd/yyyy"));
            LocalTime deadlineTime = LocalTime.parse(dates[2], DateTimeFormatter.ofPattern("HH:mm"));
            System.out.println("Got it. I've added this task:");
            tasks.add(new Deadline(s.substring(9), false, deadlineDate,deadlineTime));
            System.out.println(tasks.get(tasks.size() - 1));
            printTotalTasks(tasks.size());
        }
        catch (ArrayIndexOutOfBoundsException  e){
            System.out.println("Please enter a description for deadline");
        }
    }

    private static void printTasks(ArrayList<Task> tasks) {
        System.out.println("Here are the tasks in your list:\n");
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println(i + ". " + tasks.get(i-1));
        }

    }

    private static ArrayList<Task> readInput(ArrayList<String> strings){
        ArrayList<Task> tasks = new ArrayList<>();
        for(String i: strings){
            String desc = "";
            String missions = "";
            String[] texts = i.split("\\s+");
            String[] time = i.split("[|]");
            String content = time[0];
            String[] text = content.split("\\s+");
            for (int j = 1; j < texts.length ; j++) {
                desc = desc + texts[j] + " ";
            }
            for (int j = 1; j < text.length ; j++) {
                missions = missions + texts[j] + " ";
            }
            boolean status = (texts[0].charAt(4) == 'X');
            switch(texts[0].charAt(1)){
                case 'D':
                    String[] times = time[1].split("\\s+");
                    Task new_deadline = new Deadline(missions,status
                            ,LocalDate.parse(times[1],DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                            ,LocalTime.parse(times[2],DateTimeFormatter.ofPattern("HH:mm")));
                    tasks.add(new_deadline);
                    break;
                case 'T':
                    Task new_todo = new Todo(desc,status);
                    tasks.add(new_todo);
                    break;
                case 'E':
                    String[] startEnd = time[1].split("[~]");
                    String[] start = startEnd[0].split("\\s+");
                    String[] end = startEnd[1].split("\\s+");
                    Task new_event = new Event(missions,status,
                            LocalDate.parse(start[1],DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                            LocalTime.parse(start[2],DateTimeFormatter.ofPattern("HH:mm")),
                            LocalDate.parse(end[1],DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                            LocalTime.parse(end[2],DateTimeFormatter.ofPattern("HH:mm")));
                    tasks.add(new_event);
                    break;
            }
        }
    return tasks;
    }

}