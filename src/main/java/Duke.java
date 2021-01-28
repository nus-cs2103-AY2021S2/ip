import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Duke {
    public TaskList tasks;
    public Storage storage;

    public Duke() {
        this.tasks = new TaskList();
        this.storage = new Storage("C:/Users/Jeremias/Documents/GitHub/ip/data/", "data.txt");
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    public void run() {
        System.out.println("Greetings, I am Mr. C \nHow may I assist you?");
        storage.loadTaskList(tasks);
        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                exit = true;
                System.out.println("Farewell sir/maam. Hope to see you again soon.");
            } else {
                executeCommand(input);
                save();
            }
        }
    }

    public void executeCommand(String input) {
        String[] command = input.split(" ");
        try {
            if (command[0].equals("todo")) {
                if (command.length == 1) {
                    throw new DukeException("Please describe the nature of your task sir/maam.");
                } else if (command[1].equals("")) {
                    throw new DukeException(("Please describe the nature of your task sir/maam."));
                } else {
                    tasks.addTask(new Todo(input));
                }
            } else if (command[0].equals("deadline")) {
                if (command.length == 1) {
                    throw new DukeException("Please describe the nature of your task sir/maam.");
                } else if (command[1].equals("")) {
                    throw new DukeException(("Please describe the nature of your task sir/maam."));
                } else {
                    String[] details = input.split(" /by ");
                    if (details.length != 2) {
                        throw new DukeException("Please specify the deadline for this task using /by.");
                    }
                    try {
                        LocalDateTime dateTime = LocalDateTime.parse(details[1], DateTimeFormatter.ofPattern("dd/M/yyyy Hmm"));
                        Deadline task = new Deadline(dateTime, details[0].substring(9));
                        tasks.addTask(task);
                    } catch (DateTimeParseException e) {
                        System.out.println(e);
                    }
                }
            } else if (command[0].equals("event")) {
                if (command.length == 1) {
                    throw new DukeException("Please describe the nature of your task sir/maam.");
                } else if (command[1].equals("")) {
                    throw new DukeException(("Please describe the nature of your task sir/maam."));
                } else {
                    String[] details = input.split(" /at ");
                    if (details.length != 2) {
                        throw new DukeException("Please specify the date of this event using /at.");
                    }
                    try {
                        LocalDateTime dateTime = LocalDateTime.parse(details[1], DateTimeFormatter.ofPattern("dd/M/yyyy Hmm"));
                        Event task = new Event(dateTime, details[0].substring(6));
                        tasks.addTask(task);
                    } catch (DateTimeParseException e) {
                        System.out.println(e);
                    }
                }
            } else if (command[0].equals("list")) {
                if (command.length == 1) {
                    tasks.printTasks();
                } else {
                    throw new DukeException("I am unable to comprehend what you have just said. I deeply regret my insufficiency.");
                }
            } else if (command[0].equals("done")) {
                if (command.length == 1) {
                    throw new DukeException("I apologise but I must ask you to specify the task you have conquered.");
                } else if (command[1].equals("")) {
                    throw new DukeException("I apologise but I must ask you to specify the task you have conquered.");
                } else if (Integer.valueOf(command[1]) < 1 || Integer.valueOf(command[1]) > tasks.size()) {
                    throw new DukeException("I apologise but there is no such index in your list of tasks sir/maam.");
                } else {
                    tasks.doneTask(Integer.valueOf(command[1]) - 1);
                }
            } else if (command[0].equals("delete")) {
                if (command.length == 1) {
                    throw new DukeException("I apologise but I must ask you to specify which task you wish to remove.");
                } else if (command[1].equals("")) {
                    throw new DukeException("I apologise but I must ask you to specify which task you wish to remove.");
                } else if (Integer.valueOf(command[1]) < 1 || Integer.valueOf(command[1]) > tasks.size()) {
                    throw new DukeException("I am incapable of deleting something that does not exist sir/maam.");
                } else {
                    tasks.deleteTask(Integer.valueOf(command[1]) - 1);
                }
            } else {
                throw new DukeException("I am unable to comprehend what you have just said. I deeply regret my insufficiency.");
            }
        } catch (DukeException e) {
            System.out.println(e);
        }
    }

    public void save() {
        String str = "";
        for (int i = 0; i < tasks.size(); i++) {
            str += tasks.getTask(i).formatToSave() + "\n";
        }
        storage.saveTaskList(str);
    }
}