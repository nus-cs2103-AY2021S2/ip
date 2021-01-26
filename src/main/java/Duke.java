import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.LocalTime;

public class Duke {
    public static void main(String[] args) throws DukeException {

        // open FastIO to read input
        FastIO reader = new FastIO();

        // greeting for user to see
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        // read user input
        String input = reader.nextLine();

        // create list to store tasks
        ArrayList<Task> taskArrayList = new ArrayList<>();

        try {
            while (true) {
                // user exit program
                if (input.equals("bye")) {
                    reader.println("Bye. Hope to see you again soon!");
                    break;

                    // user wants to see list of tasks
                } else if (input.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    int counter = 1;
                    for (Task t : taskArrayList) {
                        System.out.println(counter + "." + t.taskStatus());
                        counter++;
                    }
                    input = reader.nextLine();

                    // user completes a task
                } else if (input.startsWith("done")) {
                    String[] split = input.split("\\s+");
                    int index = Integer.parseInt(split[1]) - 1;
                    Task to_complete = taskArrayList.get(index);
                    taskArrayList.set(index, to_complete.completeTask());

                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(to_complete.completeTask().taskStatus());

                    input = reader.nextLine();

                    // user wants to add a ToDo task
                } else if (input.startsWith("todo")) {
                    String[] split = input.split(" ");

                    // if user didn't say what the ToDo was
                    if (split.length == 1) {
                        throw new DukeException("todo");
                    }

                    String task = "";
                    for (int counter = 1; counter < split.length; counter++) {
                        if (counter == 1) {
                            task = task + split[counter];
                        } else {
                            task = task + " " + split[counter];
                        }
                    }

                    ToDo toAdd = new ToDo(task);
                    taskArrayList.add(toAdd);

                    System.out.println("Got it. I've added this task:");
                    System.out.println(toAdd.taskStatus());
                    System.out.println("Now you have " + taskArrayList.size() + " tasks in the list");

                    input = reader.nextLine();

                    // user wants to add a Deadline
                } else if (input.startsWith("deadline")) {
                    String[] split = input.split(" ");

                    // if user didn't say what the Deadline was
                    if (split.length == 1) {
                        throw new DukeException("deadline");
                    }

                    String task = "";

                    int counter;
                    outerloop:
                    for (counter = 1; counter < split.length; counter++) {
                        if (split[counter].startsWith("/")) {
                            break outerloop;
                        } else {
                            if (counter == 1) {
                                task = task + split[counter];
                            } else {
                                task = task + " " + split[counter];
                            }
                        }
                    }

                    counter++;
                    LocalDate date = LocalDate.parse(split[counter], DateTimeFormatter.ofPattern("d/MM/yyyy"));
                    counter++;
                    LocalTime time = LocalTime.parse(split[counter], DateTimeFormatter.ofPattern("HHmm"));

                    Deadline toAdd = new Deadline(task, date, time);
                    taskArrayList.add(toAdd);

                    System.out.println("Got it. I've added this task:");
                    System.out.println(toAdd.taskStatus());
                    System.out.println("Now you have " + taskArrayList.size() + " tasks in the list");

                    input = reader.nextLine();

                    // user wants to add an Event
                } else if (input.startsWith("event")) {
                    String[] split = input.split(" ");

                    // if user didn't say what the Event was
                    if (split.length == 1) {
                        throw new DukeException("event");
                    }

                    String task = "";

                    int counter;
                    outerloop:
                    for (counter = 1; counter < split.length; counter++) {
                        if (split[counter].startsWith("/")) {
                            break outerloop;
                        } else {
                            if (counter == 1) {
                                task = task + split[counter];
                            } else {
                                task = task + " " + split[counter];
                            }
                        }
                    }

                    counter++;
                    LocalDate date = LocalDate.parse(split[counter], DateTimeFormatter.ofPattern("d/MM/yyyy"));
                    counter++;
                    LocalTime time = LocalTime.parse(split[counter], DateTimeFormatter.ofPattern("HHmm"));

                    Event toAdd = new Event(task, date, time);
                    taskArrayList.add(toAdd);

                    System.out.println("Got it. I've added this task:");
                    System.out.println(toAdd.taskStatus());
                    System.out.println("Now you have " + taskArrayList.size() + " tasks in the list");

                    input = reader.nextLine();

                // user wants to delete a task from the list
                } else if (input.startsWith("delete")) {
                    String[] split = input.split(" ");
                    int index = Integer.parseInt(split[1]) - 1;

                    Task to_remove = taskArrayList.get(index);
                    taskArrayList.remove(index);

                    System.out.println("Noted. I have removed this task:");
                    System.out.println(to_remove.taskStatus());
                    System.out.println("Now you have " + taskArrayList.size() + " tasks in the list");

                    input = reader.nextLine();

                // user input is unknown
                } else {
                    throw new DukeException("unknown");
                }
            }

        // print error message
        } catch (DukeException e) {
            System.out.println(e.errorMessage());

        // close the program
        } finally {
            // close FastIO to print exit statement
            reader.close();
        }
    }
}

