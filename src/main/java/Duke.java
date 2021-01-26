import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList();
            tasks.load(storage);
            ui.output("Saved data successfully loaded.");
        } catch (IOException e) {
            tasks = new TaskList();
        }
    }

    public void run() {
        try {
            Scanner sc = new Scanner(System.in);
            ui.output("Greetings. My name is I-01B, but you may call me DUKE.");
            ui.output("What can I assist you with?");
            Parser parser = new Parser();
            while (sc.hasNext()) {
                String command = sc.nextLine();
                if (command.equals("bye")) {
                    ui.output("I hope I have been of assistance. Goodbye. C:");
                    break;
                } else {
                    Command parsedCmd = null;
                    parsedCmd = parser.parseCommand(command);
                    String argument = parsedCmd.getArguments();
                    switch (parsedCmd.getCommand()) {
                    case LIST:
                        ui.output("This is your to-do list:");
                        for (int i = 0; i < tasks.size(); i++) {
                            ui.output((i + 1) + ". " + tasks.get(i));
                        }
                        break;
                    case DONE:
                        int doneIndex = Integer.parseInt(argument) - 1;
                        Task toDo = tasks.get(doneIndex);
                        toDo.doTask();
                        storage.markDoneInFile(doneIndex);
                        ui.output("Affirmative. The following task has been marked as done: \n" + toDo);
                        break;
                    case DELETE:
                        int delIndex = Integer.parseInt(argument) - 1;
                        Task toDelete = tasks.get(Integer.parseInt(argument) - 1);
                        tasks.remove(delIndex);
                        storage.deleteFromFile(delIndex);
                        ui.output("Affirmative. The following task has been removed: \n" + toDelete);
                        break;
                    case TODO:
                        if (argument.isBlank()) {
                            String msg = "I apologize, please input description for 'todo'.";
                            DukeException exception = new DukeException(msg);
                            throw exception;
                        } else {
                            Task newTask = new Todo(argument);
                            tasks.add(newTask);
                            storage.addToFile(newTask);
                            ui.output("Added to to-do list: \n" + newTask);
                        }
                        break;
                    case DEADLINE:
                        if (argument.isBlank()) {
                            String msg = "I apologize, please input description and time for 'deadline'.";
                            DukeException exception = new DukeException(msg);
                            throw exception;
                        } else {
                            String[] split = argument.split("/by");
                            if (argument.equals(split[0])) {
                                String msg = "I apologize, please use '/by' argument to specify date for 'deadline'.";
                                DukeException exception = new DukeException(msg);
                                throw exception;
                            } else {
                                Task newTask = new Deadline(split[0].strip(), split[1].strip());
                                tasks.add(newTask);
                                storage.addToFile(newTask);
                                ui.output("Added to to-do list: \n" + newTask);
                            }
                        }
                        break;
                    case EVENT:
                        if (argument.isBlank()) {
                            String msg = "I apologize, please input description and time for 'event'.";
                            DukeException exception = new DukeException(msg);
                            throw exception;
                        } else {
                            String[] split = argument.split("/at");
                            if (argument.equals(split[0])) {
                                String msg = "I apologize, please use '/at' argument to specify time for 'event'.";
                                DukeException exception = new DukeException(msg);
                                throw exception;
                            } else {
                                Task newTask = new Event(split[0].strip(), split[1].strip());
                                tasks.add(newTask);
                                storage.addToFile(newTask);
                                ui.output("Added to to-do list: \n" + newTask);
                            }
                        }
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + parsedCmd.getCommand());
                    }

                }
            }
        } catch (DukeException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
