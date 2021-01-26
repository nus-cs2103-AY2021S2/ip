import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

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

		load();

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
                    addTask(parseTask(userInput), true);
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

	private void load() {
		try {
			File savefile = new File("savedata.txt");
			Scanner saveReader = new Scanner(savefile);
			while (saveReader.hasNextLine()) {
				String savedata = saveReader.nextLine();
				addTask(parseTask(savedata), false);
			}
			saveReader.close();
		} catch (FileNotFoundException e) {
			tasks.clear();
			// create file if it doesn't exist
			File saveFile = new File("savedata.txt");
			try {
				saveFile.createNewFile();
			} catch (Exception err) {
				System.out.println(err.getMessage());
			}
		} catch (DukeException e) {
			String errorMsg = "Looks like the save data's been corrupted.\n"
					+ "Please avoid manually editing this file!\n"
					+ "For now, I've cleared the save data.";
			System.out.println(errorMsg);
			tasks.clear();
			saveTasks();
		}
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
		boolean isDone = false;
		if (taskString.startsWith("todo")) {
			desc = taskString.split("todo")[1].trim();
			if (desc.startsWith("[isDone]")) {
				desc = desc.split("\\[isDone\\]")[1].trim();
				isDone = true;
			}
			newTask = new Todo(desc);
		} else if (taskString.startsWith("event")) {
			desc = taskString.split("event")[1].trim();
			String[] taskParts = desc.split(" /on ");
			if (taskParts.length == 1) {
				throw new DukeException("Oops! Usage: event [desc] /on [date]");
			} else {
				if (taskParts[0].startsWith("[isDone]")) {
					taskParts[0] = taskParts[0].split("\\[isDone\\]")[1].trim();
					isDone = true;
				}
				newTask = new Event(taskParts[0], taskParts[1]);
			}
		} else {
			desc = taskString.split("deadline")[1].trim();
			String[] taskParts = desc.split(" /by ");
			if (taskParts.length == 1) {
				throw new DukeException("Oops! Usage: deadline [desc] /by [date]");
			} else {
				if (taskParts[0].startsWith("[isDone]")) {
					taskParts[0] = taskParts[0].split("\\[isDone\\]")[1].trim();
					isDone = true;
				}
				newTask = new Deadline(taskParts[0], taskParts[1]);
			}
		}
		if (isDone) {
			newTask.finish();
		}
		return newTask;
	}
	
	private void addTask(Task task, boolean isVerbose) {
		tasks.add(task);
		if (isVerbose) {
			System.out.println("I've added this task: " + task.toString());
			System.out.printf("You now have %d items on your todo list.\n", tasks.size());
		}
		saveTasks();
	}
	
	private void finishTask(Task task) throws DukeException {
		if (task.isDone()) {
			throw new DukeException("That task's already done!");
		} else {
			task.finish();
			System.out.println("Congrats! The following task has been marked as done:");
			System.out.println("  " + task.toString());
		}
		saveTasks();
	}
	
	private void deleteTask(int idx) {
		System.out.println("Removed task: " + tasks.remove(idx).toString());
		System.out.printf("You now have %d items on your todo list.\n", tasks.size());
		saveTasks();
	}

	private void saveTasks() {
		try {
			FileWriter saveWriter = new FileWriter("savedata.txt");
			StringBuilder saveStringBuilder = new StringBuilder();
			for (int i = 0; i < tasks.size(); i++) {
				saveStringBuilder.append(tasks.get(i).getSaveString());
			}
			saveWriter.write(saveStringBuilder.toString());
			saveWriter.close();
		} catch (Exception e) {
			String errorMsg = "Save file not found!\n" +
					"Please don't manually edit the save file.";
			System.out.println(errorMsg);
		}
	}
}
