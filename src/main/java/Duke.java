import java.util.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Duke {
    enum Command {
        list, done, delete, todo, deadline, event, bye;
    }

    public static void main(String[] args) throws IOException, ParseException {

        List<Task> taskList = new ArrayList<Task>(100);

        String filePath = "data.txt";
        FileWriter fw = new FileWriter(filePath, true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter pw = new PrintWriter(bw);
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String readLine = "";
        while ((readLine = br.readLine()) != null) {
            String[] read = readLine.split("]", 2);
            String type = read[0].replace("[", "");
            if (type.contains("T")) {
                String sequence = read[1];
                String[] data = sequence.split("] ");
                Task newTask = new ToDos(data[1]);
                if (sequence.contains("\u2718")) {
                    newTask.markAsDone();
                }
                taskList.add(newTask);
            } else if (type.contains("D")) {
                String sequence = read[1];
                String[] data = sequence.split("] ");
                String secondData = data[1].replace("(by: ", "").replace(")", "");
                String[] seperateTime = secondData.split(" ", 2);
                SimpleDateFormat formatter = new SimpleDateFormat("MMM d yyyy", Locale.ENGLISH);
                Date date = formatter.parse(seperateTime[1]);
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                Task newTask = new Deadline(seperateTime[0], dateFormat.format(date));
                if (sequence.contains("\u2718")) {
                    newTask.markAsDone();
                }
                taskList.add(newTask);

            } else if (type.contains("E")) {
                String sequence = read[1];
                String[] data = sequence.split("] ");
                String secondData = data[1].replace("(at: ", "").replace(")", "");
                String[] seperateTime = secondData.split(" ", 2);
                SimpleDateFormat formatter = new SimpleDateFormat("MMM d yyyy", Locale.ENGLISH);
                Date date = formatter.parse(seperateTime[1]);
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Task newTask = new Events(seperateTime[0], dateFormat.format(date));
                if (sequence.contains("\u2718")) {
                   newTask.markAsDone();
                }
                taskList.add(newTask);
                
            }
        }
        

        br.close();
        
        
        // inserts correct file path separator on *nix and Windows
        // works on *nix
        // works on Windows
        
            Scanner sc = new Scanner(System.in);
            String logo = "Hello! I'm Duke\n"
                    + "What can I do for you?\n";
            System.out.println(logo);

            while (sc.hasNext()) {
                try {
                    String line = sc.nextLine();
                    String[] lineSplit = line.split(" ", 2);
                    Command command = Command.valueOf(lineSplit[0]);

                    Scanner fileScanner = new Scanner(new File(filePath));

                    if (command.equals(Command.bye)) {
                        break;
                    } else {
                        if (command.equals(Command.list)) {
                            int length = lineSplit.length;
                            if (length > 1) {
                                String[] item = lineSplit[1].split("/by ");
                                LocalDate date = LocalDate.parse(item[0]);
                                System.out.println("____________________________________________________________\n"
                                        + "Here are the tasks in your list due on "
                                        + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
                                while (fileScanner.hasNext()) {
                                    String curTask = fileScanner.nextLine();
                                    String[] string = curTask.split(": ");
                                    if (string.length > 1) {
                                        String dueDate = string[1].replace(")", "");
                                        if (dueDate.equals(date.format(DateTimeFormatter.ofPattern("MMM d yyyy")))) {
                                            System.out.println(curTask);
                                        }
                                    }
                                
                                }
                                System.out.println("____________________________________________________________");
                            } else {
                                System.out.println("____________________________________________________________\n"
                                        + "Here are the tasks in your list");
                                /*for (int i = 1; i <= taskList.size(); i++) {
                                    Task curTask = taskList.get(i - 1);
                                    System.out.println(i + "." + curTask.toString());
                                }*/
                                while (fileScanner.hasNext()) {
                                    System.out.println(fileScanner.nextLine());
                                }
                                System.out.println("____________________________________________________________");

                            }
                        } else if (command.equals(Command.done)) {
                            try {
            
            

                                FileWriter tfw = new FileWriter(filePath);
                                BufferedWriter tbw = new BufferedWriter(tfw);
                                PrintWriter tpw = new PrintWriter(tbw);

                                int index = Integer.valueOf(lineSplit[1]) - 1;
                                System.out.println(taskList.size());
                                Task curTask = taskList.get(index);
                                curTask.markAsDone();
                                System.out.println("____________________________________________________________\n"
                                        + "Nice! I've marked this task as done:\n" + curTask.toString()
                                        + "\n____________________________________________________________");

                                for (int i = 1; i <= taskList.size(); i++) {
                                    Task writeTask = taskList.get(i - 1);
                                    tpw.println(i + "." + writeTask.toString());
                                }
                                tpw.flush();
                                tpw.close();
                                
                            
                            } catch (ArrayIndexOutOfBoundsException ex) {
                                throw new DukeException("\u00a9 OOPS!!! The description of a done cannot be empty.");
                            }
                        } else if (command.equals(Command.delete)) {
                            try {
                                File oldFile = new File(filePath);
                    

                                FileWriter tfw = new FileWriter(filePath);
                                BufferedWriter tbw = new BufferedWriter(tfw);
                                PrintWriter tpw = new PrintWriter(tbw);
                                

                                int index = Integer.valueOf(lineSplit[1]) - 1;
                                Task curTask = taskList.get(index);
                                taskList.remove(index);
                                System.out.println("____________________________________________________________\n"
                                        + "Noted. I've removed this task:\n  " + curTask.toString() + "\nNow you have "
                                        + taskList.size() + " tasks in the list.\n"
                                        + "____________________________________________________________");
                                for (int i = 1; i <= taskList.size(); i++) {
                                    Task writeTask = taskList.get(i-1);
                                    tpw.println(i + "." + writeTask.toString());
                                }
                                tpw.close();
                            } catch (ArrayIndexOutOfBoundsException ex) {
                                throw new DukeException("\u00a9 OOPS!!! The description of a delete cannot be empty.");
                            } catch (IndexOutOfBoundsException ex) {
                                throw new DukeException("\u00a9 OOPS!!! There is nothing to delete at " + lineSplit[1]);
                            }
                        } else {
                            if (command.equals(Command.todo)) {
                                try {
                                    String[] item = lineSplit[1].split("/");
                                    Task newTask = new ToDos(item[0]);
                                    taskList.add(newTask);
                                    System.out.println("____________________________________________________________\n"
                                            + "Got it. I've added this task:\n  " + newTask.toString()
                                            + "\nNow you have " + taskList.size() + " tasks in the list.\n"
                                            + "____________________________________________________________");
                                    pw.println(taskList.size() + "." + newTask.toString());
                                } catch (ArrayIndexOutOfBoundsException ex) {
                                    throw new DukeException(
                                            "\u00a9 OOPS!!! The description of a todo cannot be empty.");
                                }
                            } else if (command.equals(Command.deadline)) {
                                try {
                                    String[] item = lineSplit[1].split("/by ");
                                    Task newTask = new Deadline(item[0], item[1]);
                                    taskList.add(newTask);
                                    System.out.println("____________________________________________________________\n"
                                            + "Got it. I've added this task:\n  " + newTask.toString()
                                            + "\nNow you have " + taskList.size() + " tasks in the list.\n"
                                            + "____________________________________________________________");
                                    pw.println(taskList.size() + "." + newTask.toString());
                                } catch (ArrayIndexOutOfBoundsException ex) {
                                    throw new DukeException(
                                            "\u00a9 OOPS!!! The description of a deadline cannot be empty.");
                                }
                            } else if (command.equals(Command.event)) {
                                try {
                                    String[] item = lineSplit[1].split("/at ");
                                    Task newTask = new Events(item[0], item[1]);
                                    taskList.add(newTask);
                                    System.out.println("____________________________________________________________\n"
                                            + "Got it. I've added this task:\n  " + newTask.toString()
                                            + "\nNow you have " + taskList.size() + " tasks in the list.\n"
                                            + "____________________________________________________________");
                                    pw.println(taskList.size() + "." + newTask.toString());
                                } catch (ArrayIndexOutOfBoundsException ex) {
                                    throw new DukeException(
                                            "\u00a9 OOPS!!! The description of a event cannot be empty.");
                                }
                            } else {
                                throw new DukeException(
                                        "\u00a9 OOPS!!! I'm sorry, but I don't know what that means :-(");
                            }
                            pw.flush();

                        }
                    }

                } catch (DukeException ex) {
                    System.out.println("____________________________________________________________\n"
                            + ex.getMessage() + "\n____________________________________________________________");
                } catch (IllegalArgumentException ex) {
                    System.out.println("____________________________________________________________\n"
                            + "\u00a9 OOPS!!! I'm sorry, but I don't know what that means :-("
                            + "\n____________________________________________________________");
                } catch (DateTimeParseException ex) {
                    System.out.println("____________________________________________________________\n"
                            + "\u00a9 OOPS!!! I'm sorry, but I don't know what the date means :-("
                            + "\n____________________________________________________________");
                } catch (NullPointerException ex) {
                    System.out.println("____________________________________________________________\n"
                            + "\u00a9 OOPS!!! Nothing found :-("
                            + "\n____________________________________________________________");
                }
            }
            pw.close();
            sc.close();
            System.out.println("____________________________________________________________\n"
                    + "Bye. Hope to see you again soon!"
                    + "\n____________________________________________________________");
    }
}
class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2718" : " "); //return tick or X symbols
    }

    public void markAsDone() {
        this.isDone = true;
    }

    LocalDate getTime(){
        return null;
    }

    @Override
    public String toString() {
        return "["+this.getStatusIcon()+"] "+ this.description;
    }

}
class Deadline extends Task{

    protected LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }
    @Override
    LocalDate getTime(){
        return this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
class ToDos extends Task {

    public ToDos(String description)  {
        super(description);

    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
class Events extends Task {

    protected LocalDate duration;

    public Events(String description, String duration) {
        super(description);
        this.duration = LocalDate.parse(duration);
    }
    @Override
    LocalDate getTime(){
        return this.duration;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + duration.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
class DukeException extends Exception{
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public DukeException(String message) {
        super(message);
    }

}