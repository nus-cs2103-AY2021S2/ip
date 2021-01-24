import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    List<Task> tasks;
    private Storage storage;

    public Duke(String filePath) {
        storage = new Storage(filePath);
    }

    public void runDuke() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("How can I help you?");
        this.tasks = storage.load();
        scan();
    }

    public void scan() {
        Scanner s = new Scanner(System.in);
        String description = s.nextLine();
        while (!description.equals("bye")) {
            try {
                manageTask(description);
            } catch (DukeException e) {
                System.out.println("     " + e);
                System.out.println("     --------------------------------");
            } finally {
                description = s.nextLine();
            }
        }
        try {
            manageTask(description);
        } catch (DukeException e) {
            System.out.println("     " + e);
        }
        s.close();
    }

    public void manageTask(String description) throws DukeException {
        System.out.println("     --------------------------------");
        if (description.equals("list")) {
            System.out.println("     Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                String rank = String.valueOf(i + 1);
                System.out.println("     " + rank + "." + tasks.get(i));
            }
        } else if (description.equals("bye")) {
            System.out.println("     Bye.Cya");
        } else if(description.contains("delete")) {
            String taskLocation = description.substring(7);
            int taskNumber = Integer.parseInt(taskLocation);
            Task deleteTask = tasks.get(taskNumber - 1);
            tasks.remove(taskNumber - 1);
            System.out.println("     Noted. I've removed this task:");
            System.out.println("       " + deleteTask);
            String listLength = Integer.toString(tasks.size());
            System.out.println("     Now you have " + listLength + " tasks in the list!");
        } else if (description.contains("done")) {
            String taskLocation = description.substring(5);
            int taskNumber = Integer.parseInt(taskLocation);
            Task doneTask = tasks.get(taskNumber - 1);
            doneTask.markAsDone();
            System.out.println("     Noice! I've marked this task as done:");
            System.out.println("     " + doneTask);
        } else if (description.contains("todo") || description.contains("deadline")
                || description.contains("event")){
            if (description.contains("todo")){
                if (description.equals("todo")) {
                    throw new DukeException("OOPS!!! The description of a todo cannot " +
                            "be empty");
                } else {
                    System.out.println("     Got it. I've added this task:");
                    String descLocation = description.substring(5);
                    ToDo newToDo = new ToDo(descLocation);
                    tasks.add(newToDo);
                    System.out.println("       " + newToDo);
                }
            } else if (description.contains("deadline")){
                if (description.equals("deadline")) {
                    throw new DukeException("OOPS!!! The description of a deadline cannot " +
                            "be empty");
                } else {
                    System.out.println("     Got it. I've added this task:");
                    String descLocation = description.substring(9);
                    int byPosition = descLocation.indexOf("/by");
                    String desc = descLocation.substring(0, byPosition);
                    String by = descLocation.substring(byPosition + 4);
                    Deadline newDeadline = new Deadline(desc, by);
                    tasks.add(newDeadline);
                    System.out.println("       " + newDeadline);
                }
            } else if (description.contains("event")) {
                if (description.equals("event")) {
                    throw new DukeException("OOPS!!! The description of an event cannot " +
                            "be empty");
                } else {
                    System.out.println("     Got it. I've added this task:");
                    String descLocation = description.substring(6);
                    int atPosition = descLocation.indexOf("/at");
                    String desc = descLocation.substring(0, atPosition);
                    String at = descLocation.substring(atPosition + 4);
                    Event newEvent = new Event(desc, at);
                    tasks.add(newEvent);
                    System.out.println("       " + newEvent);
                }
            }
            String listLength = Integer.toString(tasks.size());
            System.out.println("     Now you have " + listLength + " tasks in the list!");
        } else {
            throw new DukeException("OOPS!!! I don't know what that means. Try again :(");
        }
        System.out.println("     --------------------------------");
    }

    public static void main(String[] args) {
        String home = System.getProperty("user.home");
        String filePath = home+"/dukeTasks.txt";
        Duke duke = new Duke(filePath);
        duke.runDuke();
    }
}
