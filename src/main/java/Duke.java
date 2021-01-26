import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Duke {
	private TaskList tasks;
	private Ui ui;
	private boolean isActive;
	
	public Duke() {
        this.tasks = new TaskList();
        this.ui = new Ui();
        this.isActive = true;
    }
	
    public static void main(String[] args) {
		Duke duke = new Duke();
		duke.run();
	}

	public void run() {
		// display welcome sequence
		ui.welcome();

		load();

        // get user input
        String userInput = ui.readCommand();

        // loop until the user exits
        while (isActive) {
            try {
                // display list
                if (userInput.toLowerCase().equals("list")) {
                    tasks.display();
                // finish a task
                } else if (userInput.toLowerCase().matches("^(do(ne)?|finish(ed)?|completed?) \\d+$")) {
                    String[] bits = userInput.split(" ");
                    int idx = Integer.parseInt(bits[1]);
                    if (idx < 1 || idx > tasks.size()) {
                        throw new DukeException("Oops! That doesn't appear to be a valid task number.");
                    } else {
                        finishTask(tasks.get(idx));
                    }
				// manually remove task
                } else if (userInput.toLowerCase().matches("^(delete|remove) \\d+$")) {
                    String[] bits = userInput.split(" ");
                    int idx = Integer.parseInt(bits[1]);
                    if (idx < 1 || idx > tasks.size()) {
                        throw new DukeException("Oops! That doesn't appear to be a valid task number.");
                    } else {
                        deleteTask(idx);
                    }
                // add task to list
                } else if (userInput.toLowerCase().matches("^(todo|deadline|event)( .+)?$")) {
					addTask(parseTask(userInput), true);
				} else if (userInput.toLowerCase().equals("bye")) {
                	isActive = false;
                } else {
                    throw new DukeException("I don't understand that command!");
                }
            } catch (Exception e) {
                ui.borderPrint(e.getMessage());
            }

			// get user input
			userInput = ui.readCommand();
        }
		
		// exit sequence
		ui.quit();
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
			tasks.clear();
			saveTasks();
			String errorMsg = "Looks like the save data's been corrupted.\n"
					+ "Please avoid manually editing this file!\n"
					+ "For now, I've cleared the save data.";
			ui.borderPrint(errorMsg);
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
				if (taskParts[0].startsWith("[isDone]")) {
					taskParts[0] = taskParts[0].split("\\[isDone\\]")[1].trim();
					isDone = true;
				}
				try {
					LocalDate datetime = LocalDate.parse(taskParts[1], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
					newTask = new Deadline(taskParts[0], datetime);
				} catch (Exception e) {
					throw new DukeException("Looks like your date's formatted incorrectly! Try this: dd/mm/yyyy");
				}
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
			String msg = String.format("I've added this task: %s\nYou now have %d items on your todo list.",
					task.toString(),
					tasks.size());
			ui.borderPrint(msg);
		}
		saveTasks();
	}
	
	private void finishTask(Task task) throws DukeException {
		if (task.isDone()) {
			throw new DukeException("That task's already done!");
		} else {
			task.finish();
			String msg = String.format("Congrats! The following task has been marked as done:\n  %s",
					task.toString());
			ui.borderPrint(msg);
		}
		saveTasks();
	}
	
	private void deleteTask(int idx) throws DukeException {
		String msg = String.format("Removed task: %s\nYou now have %d items on your todo list.",
				tasks.remove(idx).toString(),
				tasks.size());
		saveTasks();
	}

	private void saveTasks() {
		try {
			FileWriter saveWriter = new FileWriter("savedata.txt");
			StringBuilder saveStringBuilder = new StringBuilder();
			for (int i = 1; i <= tasks.size(); i++) {
				saveStringBuilder.append(tasks.get(i).getSaveString());
			}
			saveWriter.write(saveStringBuilder.toString());
			saveWriter.close();
		} catch (Exception e) {
			String errorMsg = "Save file not found!\n" +
					"Please don't manually edit the save file.";
			ui.borderPrint(errorMsg);
		}
	}
}
