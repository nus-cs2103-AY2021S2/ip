import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors

public class Duke {
    public static void main(String[] args) {
        String logo = "  __  __  ___  ___   ___   ___   _____ __  __ \n" +
                " |  \\/  |/ _ \\|   \\ / _ \\ / __| |_   _|  \\/  |\n" +
                " | |\\/| | (_) | |) | (_) | (__    | | | |\\/| |\n" +
                " |_|  |_|\\___/|___/ \\___/ \\___|   |_| |_|  |_|\n" +
                "                                              ";
        System.out.println("---------------------------------------\n");
        System.out.println(logo);
        System.out.println("It is I, MODOC_TM... \n(Mechanized Organism Designed Only for Computing and Task Management) \n" );
        System.out.println("Feed me the commands I so desire...");
        System.out.println("---------------------------------------" );

        Scanner scan = new Scanner(System.in);
        String input = " ";
        int c = 0;

//        Initialize Task container
        ArrayList<Task> tasks = new ArrayList<>();
        int taskNumber = 0;


        while(!input.equals("bye")) {
            input = scan.nextLine().trim();
            String command = input.split(" ")[0];

            switch (command) {
                case "bye":
                    saveTasks(tasks);
                    System.out.println("\n---------------------------------------" );
                    System.out.println("Bye. MODOC_TM Shutting Down...");
                    System.out.println("---------------------------------------" );
                    break;

                case "list":
                    System.out.println("\n---------------------------------------" );
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(String.valueOf(i+1) + "." + tasks.get(i).toString());
                    }
                    System.out.println("---------------------------------------" );
                    break;

                case "done":
//                    Possible Error: index provided is out of bounds (NullPointerException)
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;
                    tasks.get(index).markDone();

                    System.out.println("\n---------------------------------------" );
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("[" + tasks.get(index).getStatusIcon() + "] " + tasks.get(index).name.trim());
                    System.out.println("---------------------------------------" );
                    break;

                case "todo":
                    try {
                        String name = getTodoName(input);
                        Todo todo = new Todo(name);
                        tasks.add(todo);

                        System.out.println("\n---------------------------------------" );
                        System.out.println("Got it. I've added this task:");
                        System.out.println(todo.toString());
                        System.out.println("Now you have " + tasks.size() + " tasks in the list");
                        System.out.println("---------------------------------------" );
                    } catch(DukeException e) {
                        e.printError("Come On Fella! Your ToDo description cannot be empty!");
                    }
                    break;

                case "deadline":
                    try{
                        String name = getEventOrDeadlineName(input);
                        String by = getEventOrDeadlineAttribute(input);
//                        /by String, /by
                        LocalDate date = LocalDate.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                        Deadline deadline = new Deadline(name, date);
                        tasks.add(deadline);

                        System.out.println("\n---------------------------------------" );
                        System.out.println("Got it. I've added this task:");
                        System.out.println(deadline.toString());
                        System.out.println("Now you have " + tasks.size() + " tasks in the list");
                        System.out.println("---------------------------------------" );
                    } catch (DukeException e) {
                        e.printError("Hmm... You are either lacking a name or /by details!");
                    }
                    break;

                case "event":
                    try {
                        String name = getEventOrDeadlineName(input);
                        String at = getEventOrDeadlineAttribute(input);

                        Event event = new Event(name,at);
                        tasks.add(event);

                        System.out.println("\n---------------------------------------" );
                        System.out.println("Got it. I've added this task:");
                        System.out.println(event.toString());
                        System.out.println("Now you have " + tasks.size() + " tasks in the list");
                        System.out.println("---------------------------------------" );
                    } catch (DukeException e) {
                        e.printError("Hmm... You are either lacking a name or /at details!");
                    }
                    break;

                case "delete":
                    int i = Integer.parseInt(input.split(" ")[1]) - 1;

                    System.out.println("\n---------------------------------------" );
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(tasks.get(i).toString());
                    tasks.remove(i);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list");
                    System.out.println("---------------------------------------" );
                    break;

                default:
//                    Does not exactly throw an exception*
                    System.out.println("\n---------------------------------------" );
                    System.out.println("Hol'up, I don't know what that means :-(");
                    System.out.println("---------------------------------------" );
                    break;
            }
        }
    }

    public static String getTodoName(String input) throws DukeException {
        try {
            String name = input.split(" ",2)[1].trim();
            return name;
        } catch (Exception e) {
            throw new DukeException();
        }
    }

    public static String getEventOrDeadlineName(String input) throws DukeException {
        try {
            String name = input.split("/")[0].split(" ",2)[1].trim();
            return name;
        } catch (Exception e) {
            throw new DukeException();
        }
    }

    public static String getEventOrDeadlineAttribute(String byDate) throws DukeException {
        try {
            String atBy = byDate.split("/")[1].split(" ",2)[1].trim();
            return atBy;
        } catch (Exception e) {
            throw new DukeException();
        }
    }

    public static void saveTasks(ArrayList<Task> tasks) {
        String currDir = System.getProperty("user.dir");
        String expectedDir = currDir + "/data";


        try {
//            Creates directory if doesn't exist
            Files.createDirectories(Paths.get(expectedDir));
            FileWriter writer = new FileWriter(expectedDir + "/modoc_tm.txt");

            for (Task task:tasks) {
                String result;

                Class taskType = task.getClass();
                boolean taskStatus = task.done;
                String description = task.name;

                if (taskType.equals(Event.class)) {
                    result = "E" + " | " +  (taskStatus ? "1" : "0") + " | " + description + " | " + ((Event) task).at;
                } else if (taskType.equals(Deadline.class)) {
                    result = "D" + " | " +  (taskStatus ? "1" : "0") + " | " + description + " | " + ((Deadline) task).by;
                } else {
                    result = "T" + " | " +  (taskStatus ? "1" : "0") + " | " + description;
                }

                writer.write(result + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("saveTask error");
            e.printStackTrace();
        }
    }

    public static ArrayList<Task> fetchTasks() {
        ArrayList<Task> result = new ArrayList<>();
        String currDir = System.getProperty("user.dir");
        String expectedDir = currDir + "/data/modoc_tm.txt";

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(expectedDir));
            String lineRead = reader.readLine();
            while (lineRead != null) {
                char taskType = lineRead.charAt(0);
                String[] data = lineRead.split("/");
                Boolean isDone = (Integer.parseInt(data[1]) == 1 ? true : false);
                if (taskType == 'E') {
                    Event event = new Event(data[2], data[3], isDone);
                    result.add(event);
                } else if (taskType == 'D') {
                    String unparsedDate = data[3];
                    LocalDate date = LocalDate.parse(unparsedDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    Deadline deadline = new Deadline(data[2], date, isDone);
                    result.add(deadline);
                } else {
                    Todo todo = new Todo(data[2], isDone);
                    result.add(todo);
                }
                lineRead = reader.readLine();
            }
        } catch (IOException e) {
            return result;
        }
        return result;
    }
}
