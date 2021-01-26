import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Duke {
	private static String DIVIDER = "____________________________________________________________";
	
	private Scanner scanner;
	private ArrayList<Task> tasks;
	
	public Duke() {
        this.scanner = new Scanner(System.in);
        this.tasks = new ArrayList<>();
    }
	
    public static void main(String[] args) {
		Duke duke = new Duke();
		duke.run();
	}

	public void run() {
		// display welcome sequence
		start();

        // get user input
        String userInput = scanner.nextLine();

        // loop until the user exits
        while (!userInput.toLowerCase().equals("bye")) {
            System.out.println(DIVIDER);
            try {
                // display list
                if (userInput.toLowerCase().equals("list")) {
                    displayList();
                // finish a task
                } else if (userInput.toLowerCase().matches("^(do(ne)?|finish(ed)?|completed?) \\d+$")) {
                    String[] bits = userInput.split(" ");
                    int idx = Integer.parseInt(bits[1]) - 1; // zero-indexed task index
                    if (idx < 0 || idx >= tasks.size()) {
                        throw new DukeException("Oops! That doesn't appear to be a valid task number.");
                    } else {
                        finishTask(tasks.get(idx));
                    }
				// manually remove task
                } else if (userInput.toLowerCase().matches("^(delete|remove) \\d+$")) {
                    String[] bits = userInput.split(" ");
                    int idx = Integer.parseInt(bits[1]) - 1; // zero-indexed task index
                    if (idx < 0 || idx >= tasks.size()) {
                        throw new DukeException("Oops! That doesn't appear to be a valid task number.");
                    } else {
                        deleteTask(idx);
                    }
                // add task to list
                } else if (userInput.toLowerCase().matches("^(todo|deadline|event)( .+)?$")) {
                    addTask(parseTask(userInput));
                } else {
                    throw new DukeException("I don't understand that command!");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            System.out.println(DIVIDER);

            // get next input
            userInput = scanner.nextLine();
        }
		
		// exit sequence
		quit();
    }
	
	private void start() {
		System.out.println(DIVIDER);
        System.out.println("Welcome to Duke!");
        System.out.println("What can I do for you?");
        System.out.println(DIVIDER);
	}
	
	private void quit() {
		System.out.println(DIVIDER);
        System.out.println("Bye! Hope to see you again :)");
        System.out.println(DIVIDER);
	}
	
	private void displayList() {
		for (int i = 0; i < tasks.size(); i++) {
			String item = String.valueOf(i + 1) + ". " + tasks.get(i).toString();
			System.out.println(item);
		}
	}
	
	private Task parseTask(String taskString) throws DukeException {
		Task newTask;
		String desc;
		if (taskString.startsWith("todo")) {
			desc = taskString.split("todo")[1].trim();
			newTask = new Todo(desc);
		} else if (taskString.startsWith("event")) {
			desc = taskString.split("event")[1].trim();
			String[] taskParts = desc.split(" /on ");
			if (taskParts.length == 1) {
				throw new DukeException("Oops! Usage: event [desc] /on [date]");
			} else {
				try {
					LocalDate datetime = LocalDate.parse(taskParts[1], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
					newTask = new Event(taskParts[0], datetime);
				} catch (Exception e) {
					throw new DukeException("Looks like your date's formatted incorrectly! Try this: dd/mm/yyyy");
				}
			}
		} else {
			desc = taskString.split("deadline")[1].trim();
			String[] taskParts = desc.split(" /by ");
			if (taskParts.length == 1) {
				throw new DukeException("Oops! Usage: deadline [desc] /by [date]");
			} else {
				try {
					LocalDate datetime = LocalDate.parse(taskParts[1], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
					newTask = new Deadline(taskParts[0], datetime);
				} catch (Exception e) {
					throw new DukeException("Looks like your date's formatted incorrectly! Try this: dd/mm/yyyy");
				}
			}
		}
		return newTask;
	}
	
	private void addTask(Task task) {
		tasks.add(task);
		System.out.println("I've added this task: " + task.toString());
		System.out.printf("You now have %d items on your todo list.\n", tasks.size());
	}
	
	private void finishTask(Task task) throws DukeException {
		if (task.isDone()) {
			throw new DukeException("That task's already done!");
		} else {
			task.finish();
			System.out.println("Congrats! The following task has been marked as done:");
			System.out.println("  " + task.toString());
		}
	}
	
	private void deleteTask(int idx) {
		System.out.println("Removed task: " + tasks.remove(idx).toString());
		System.out.printf("You now have %d items on your todo list.\n", tasks.size());
	}
}
