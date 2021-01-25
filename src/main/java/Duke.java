import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.time.LocalDate;


/**
 * A personal task managing chatbot project.
 */
public class Duke {
    public static ArrayList<Task> tasks;
    public static Storage storage;

    Duke(){
        storage = new Storage("data/duke.txt");
        try{
        tasks = storage.readTasksFromFile();
        }
        catch(FileNotFoundException fileException){
            tasks = new ArrayList<Task>();
        }

    }
    /**
     * main method which runs the chatbot.
     *
     * @param args empty string array.
     */
    public static void main(String[] args) {
        new Duke();

        Scanner sc = new Scanner(System.in);
        Commands.greetUser();

        while (sc.hasNext()) {

            String input = sc.nextLine();

            try {

                System.out.println("\n>>> " + input);
                if (input.equals("list")) {
                    Commands.listTasks(tasks);
                } else if (input.startsWith("done")) {
                    int index = Integer.parseInt(input.replaceAll("[^-0-9]", ""));
                    if (index > tasks.size() || index <= 0) {
                        throw new DukeException("The list item number provided is invalid");
                    }
                    Commands.markAsDone(tasks, index - 1);
                    storage.writeTasksToFile(tasks);
                } else if (input.startsWith("todo")) {
                    String description = input.replace("todo", "");
                    ToDo todo = new ToDo(description);
                    if (description.strip().equals("")) {
                        throw new DukeException("todo description cannot be empty");
                    }
                    tasks.add(todo);
                    Commands.printAddedTask(tasks, todo);
                    storage.writeTasksToFile(tasks);
                } else if (input.startsWith("deadline")) {
                    if (input.strip().equals("deadline")) {
                        throw new DukeException("deadline description cannot be empty");
                    }
                    if (!input.contains("/by")) {
                        throw new DukeException("Wrong command format: Missing `/by`");
                    }
                    if (input.split("/by").length < 2) {
                        throw new DukeException("deadline date/time cannot be empty");
                    }
                    String description = input.split("/by", 2)[0].replace("deadline", "");
                    LocalDate date = LocalDate.parse(input.split("/by", 2)[1].strip());
                    Deadline deadline = new Deadline(description, date);
                    tasks.add(deadline);
                    Commands.printAddedTask(tasks, deadline);
                    storage.writeTasksToFile(tasks);
                } else if (input.startsWith("event")) {

                    if (input.strip().equals("event")) {
                        throw new DukeException("event description cannot be empty");
                    }
                    if (!input.contains("/at")) {
                        throw new DukeException("Wrong command format: Missing `/at`");
                    }
                    if (input.split("/at").length < 2) {
                        throw new DukeException("event date/time cannot be empty");
                    }
                    String description = input.split("/at", 2)[0].replace("event", "");
                    LocalDate date = LocalDate.parse(input.split("/at", 2)[1].strip());

                    Event event = new Event(description, date);
                    tasks.add(event);
                    Commands.printAddedTask(tasks, event);
                    storage.writeTasksToFile(tasks);

                } else if (input.startsWith("delete")) {
                    int index = Integer.parseInt(input.replaceAll("[^-0-9]", ""));
                    if (index > tasks.size() || index <= 0) {
                        throw new DukeException("The list item number provided is invalid");
                    }
                    Commands.deleteTask(tasks, index - 1);
                    storage.writeTasksToFile(tasks);

                } else if (input.equals("bye")) {
                    Commands.farewellUser();
                    break;
                } else {
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }


    }
}
