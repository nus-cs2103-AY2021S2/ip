import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Justin {
    public static void main(String[] args) {
        
        /*
         * Justin is a chatbot that help users plan and organise tasks
         * Justin stands for JUSt a TImetable(New) : JUSTIN
         *
         * Justin is able to create and manage new tasks
         *   1) Mark tasks off as done
         *   2) Set tasks as To Do's - <keyword> <name>
         *   3) Set tasks as Deadline's - keyword  : <keyword> <name> /by <day>
         *   4) Set tasks as Event's - keyword : <keyword> <name> /at <day time>
         *   5) Supports deletion of completed tasks with command delete <int>
         *
         * Justin also supports viewing the entire tasks list with the command list - keyword : <keyword>
         * To end off the session user can input bye to terminate program - keyword : <keyword>
         *
         * @author Goh Wei Kiat aka github : mrweikiat
         * @version CS2103T AY20/21 Semester 2, Individual Project 'IP'
         */

        String logoNew = "     ,--.                    ,--.   ,--.          \n" +
                "     |  | ,--.,--.  ,---.  ,-'  '-. `--' ,--,--,  \n" +
                ",--. |  | |  ||  | (  .-'  '-.  .-' ,--. |      \\ \n" +
                "|  '-'  / '  ''  ' .-'  `)   |  |   |  | |  ||  |  \n" +
                " `-----'   `----'  `----'    `--'   `--' `--''--'  \n";


        Scanner sc = new Scanner(System.in);

        // Starting line for UI
        System.out.println(logoNew);
        printLineBreaker();
        System.out.println("Hello I'm Justin");
        System.out.println("What can I do for you?");
        System.out.println();
        System.out.println("To add a todo: use command todo<space>taskName");
        System.out.println("To add a deadline: use command deadline<space>taskName<space>/by<space>YYYY-MM-DD");
        System.out.println("To add a event: use command event<space>taskName<space>/at<space>YYYY-MM-DD<space>HH:MM");
        System.out.println();
        printLineBreaker();

        // Condition for Duke to stop
        boolean terminate = false;

        // create LinkedList to store information of user inputs
        //ArrayList<Task> tasks = new ArrayList<>();

        // method to load file from Duke.txt

        String userDir = System.getProperty("user.dir");
        String filePath = userDir + File.separator + "data" + File.separator + "duke.txt";
        File file = new File(filePath);
        // load any existing list into the array list

        ArrayList<Task> tasks = Justin.loadFile(filePath);


        //Duke will keep repeating until command given "Bye"
        while (!terminate) {

            String text = sc.nextLine();

            try {

                validate(text);

                if (text.equals("bye")) {

                    printLineBreaker();
                    System.out.println("Bye. Hope to see you again soon!");
                    printLineBreaker();

                    // create method to save content of list into duke.txt
                    saveFile(tasks, filePath);

                    terminate = true; // terminates Duke

                } else if (text.equals("list")) {

                    // print all the tasks stored currently in the list
                    printLineBreaker();
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(i+1 + ". " + tasks.get(i).toString());
                    }
                    printLineBreaker();

                } else if (text.contains("done")) {

                    String num = text.substring(5); // take out the int value of the task to be completed
                    int listNum = Integer.parseInt(num); // changes to int
                    Task hold = tasks.get(listNum-1);
                    hold.markAsDone();

                    // format
                    printLineBreaker();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(tasks.get(listNum-1).toString());
                    printLineBreaker();

                } else if (text.contains("deadline")) {

                    // insert code for deadline
                    String newText = text.substring(9); // remove deadline from the string text
                    //System.out.println(newText); // for debugging

                    // set delimiter to take out the description of the deadline
                    String description = newText.substring(0, newText.indexOf("/")-1);
                    //System.out.println(description); // for debugging

                    // set delimiter to take out date of the deadline
                    String date = newText.substring(newText.indexOf("/")+4);
                    //System.out.println(date); // for debugging

                    printLineBreaker();
                    System.out.println("Got it. I've added this task:");

                    boolean ifExist = false; // checking if there is an instance of a default tasks

                    for (int i = 0; i < tasks.size(); i++) {
                        if (tasks.get(i).description.equals(description)) { // meaning this is the task we want to change
                            Deadline dl = new Deadline(description, date);
                            if (tasks.get(i).isDone) {
                                dl.markAsDone();
                            }

                            printLineBreaker();
                            System.out.println("Got it. I've added this task:");
                            tasks.set(i, dl);// insert into the list
                            System.out.println(" " + dl.toString());
                            System.out.println("Now you have " + tasks.size() + " tasks in the list");
                            printLineBreaker();

                            ifExist = true;
                        }
                    }

                    if (!ifExist) {
                        Deadline dl = new Deadline(description, date);
                        printLineBreaker();
                        System.out.println("Got it. I've added this task:");
                        tasks.add(dl);
                        System.out.println(" " + dl.toString());
                        System.out.println("Now you have " + tasks.size() + " tasks in the list");
                        printLineBreaker();

                    }

                } else if (text.contains("todo")) {
                    String description = text.substring(text.indexOf(" ")+1); // take out the item from the text
                    //System.out.println(description); // for debugging

                    boolean ifExist = false; // checking if there is an instance of a default tasks

                    for (int i = 0; i < tasks.size(); i++) { // there is an instance of the item in list
                        if (tasks.get(i).description.equals(description)) {
                            Todo td = new Todo(description);
                            // bringing over information from superclass
                            if (tasks.get(i).isDone) {
                                td.markAsDone();
                            }
                            tasks.set(i, td); // insert into list
                            // formatting
                            printLineBreaker();
                            System.out.println("Got it. I've added this task:");
                            System.out.println(" " + td.toString());
                            System.out.println("Now you have " + tasks.size() + " tasks in the list");
                            printLineBreaker();
                            ifExist = true;
                        }
                    }

                    if (!ifExist) {
                        // no instance of new task in exisiting list, must create new one
                        Todo td = new Todo(description);
                        tasks.add(td);
                        // formatting
                        printLineBreaker();
                        System.out.println("Got it. I've added this task:");
                        System.out.println(" " + td.toString());
                        System.out.println("Now you have " + tasks.size() + " tasks in the list");
                        printLineBreaker();
                    }


                } else if (text.contains("event")) {

                    String newText = text.substring(text.indexOf(" ")+1); // removing the event to get description
                    System.out.println(newText); // for debugging

                    // set delimiter to obtain the description and the at
                    String description = newText.substring(0, newText.indexOf("/")-1);
                    String date = newText.substring(newText.indexOf("/")+4);

                    // splitting the date and time respectively

                    //System.out.println(date1);
                    //System.out.println(time);

                    //System.out.println(description + " " + date); // for debugging

                    boolean ifExist = false;

                    for (int i = 0; i < tasks.size(); i++) {
                        if(tasks.get(i).description.equals(description)) {
                            Event e = new Event(description, date);
                            // bringing over info from superclass
                            if (tasks.get(i).isDone) {
                                e.markAsDone();
                            }

                            tasks.set(i, e); // insert into list

                            //formatting
                            printLineBreaker();
                            System.out.println("Got it. I've added this task:");
                            System.out.println(" " + e.toString());
                            System.out.println("Now you have " + tasks.size() + " tasks in the list");
                            printLineBreaker();
                            ifExist = true;
                        }
                    }

                    if (!ifExist) {
                        Event e = new Event(description, date);
                        tasks.add(e);
                        //formatting
                        printLineBreaker();
                        System.out.println("Got it. I've added this task:");
                        System.out.println(" " + e.toString());
                        System.out.println("Now you have " + tasks.size() + " tasks in the list");
                        printLineBreaker();
                    }
                }
                else if (text.contains("delete")) {

                    String num = text.substring(7); // take out the int value of the task to be completed

                    tasks = delete(tasks, num);

                }
                // adding of tasks
                else {
                    printLineBreaker();
                    System.out.println("added: " + text);
                    printLineBreaker();
                    // create new instance of task and add to the list
                    Task holder = new Task(text);
                    tasks.add(holder); // position corresponds to item number
                }
            } catch (Exception m) {
                printLineBreaker();
                System.out.println(m.getMessage());
                printLineBreaker();
            }
        }

        sc.close();

    }

    static void validate(String text) throws JustinException {
        if (text.length() < 5 && text.contains("todo") ) { // case 1
            throw new JustinException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        else if (text.contains("blah")) { // case 2
            throw new JustinException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        } else if (text.length() < 10 && text.contains("deadline")) { // case 3
            throw new JustinException("☹ OOPS!!! The description of a deadline cannot be empty.");
        } else if (text.length() < 6 && text.contains("event")) { // case 4
            throw new JustinException("☹ OOPS!!! The description of a event cannot be empty.");
        }
    }


    public static void printLineBreaker() {

        for (int i = 0; i < 50; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    public static ArrayList<Task> delete(ArrayList<Task> tasks, String num) throws JustinException {
        try {
            int listNum = Integer.parseInt(num); // changes to int
            System.out.println(listNum);
            Task newTask = tasks.remove(listNum-1); // delete the entry of choice
            //format
            printLineBreaker();
            System.out.println("Noted. I've removed this task:");
            System.out.println(" " + newTask.toString());
            System.out.println("Now you have " + tasks.size() + " tasks in the list");
            printLineBreaker();
            return tasks;
        } catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("Error deleting" + e.getMessage());
            throw new JustinException("OOPS!!! Cannot delete what you don't have!");
        }
    }


    public static ArrayList<Task> loadFile(String filePath)  {

        ArrayList<Task> holder = new ArrayList<>();
        File file = new File(filePath);

        if (file.exists()) {
            try {
                for (String line : Files.readAllLines(Paths.get(filePath))) {

                    //System.out.println(line); // for debugging

                    // create an linkedlist to store the split lines
                    String[] splits = line.split("\\|", 5);

                    // splits[0] is the holder
                    // T is t0do
                    // E is event
                    // D is deadline
                    // else just a vanilla task

                    if (splits[0].equals("T")) {
                        Todo td = new Todo(splits[2]);
                        if (splits[1].equals("1")) {
                            td.markAsDone();
                        }
                        holder.add(td);
                    } else if (splits[0].equals("D")) {
                       Deadline dl = new Deadline(splits[2], splits[3]);
                       if (splits[1].equals("1")) {
                           dl.markAsDone();
                       }
                       holder.add(dl);
                    } else if (splits[0].equals("E")) {
                        Event e = new Event(splits[2], splits[3]);
                        if (splits[1].equals("1")) {
                            e.markAsDone();
                        }
                        holder.add(e);
                    }
                    else {
                        Task t = new Task(splits[1]);
                        holder.add(t);
                    }


                }
            } catch (IOException e) {
                System.out.println("file not found");
            }
        } else {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
                System.out.println("New file created");
            } catch (IOException e) {
                System.out.println("Error creating file");
            }

        }

        return holder;

    }

    // method to save contents of list onto justin.txt
    public static void saveFile(ArrayList<Task> task, String filePath) {

        try {

            StringBuffer sb = new StringBuffer(""); // create empty sb to store content of list

            for (int i = 0; i < task.size(); i++) {

                String holder = ""; // to hold content of current line to be stored into sb

                if (task.get(i) instanceof Todo) { // is a t0do class
                    if (task.get(i).isDone) {
                        holder = "T" + "|" + "1" + "|" + task.get(i).description;
                    } else {
                        holder = "T" + "|" + "0" + "|" + task.get(i).description;
                    }
                } else if (task.get(i) instanceof Deadline) { // is a deadline class
                    if (task.get(i).isDone) {
                        holder = "D" + "|" + "1" + "|" + task.get(i).description + "|" + ((Deadline) task.get(i)).by;
                    } else {
                        holder = "D" + "|" + "0" + "|" + task.get(i).description + "|" + ((Deadline) task.get(i)).by;
                    }
                } else if (task.get(i) instanceof Event) {
                    if (task.get(i).isDone) {
                        holder = "E" + "|" + "1" + "|" + task.get(i).description + "|" + ((Event) task.get(i)).dateTime;
                    } else {
                        holder = "E" + "|" + "1" + "|" + task.get(i).description + "|" + ((Event) task.get(i)).dateTime;
                    }
                } else {
                    // vanilla event
                    if (task.get(i).isDone) {
                        holder = "1" + "|" + task.get(i).description;
                    } else {
                        holder = "1" + "|" + task.get(i).description;
                    }

                }

                if (i < task.size()) {
                    sb.append((holder + "\n"));
                } else {
                    sb.append(holder);
                }

            }

            // write content of sb into file

            FileWriter myWriter = new FileWriter(new File(filePath));
            myWriter.write(sb.toString());
            myWriter.close();

        } catch(IOException e) {
            System.out.println("Unable to write to file justin.txt");
        }
    }
}