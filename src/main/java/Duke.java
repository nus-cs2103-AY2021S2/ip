import java.io.IOException;

/**
 * The main class where the duke is run
 */
public class Duke {
    private UI ui;
    private Tasks taskList;
    private Storage storage;

    public Duke(String path) throws IOException {
        ui = new UI();
        storage = new Storage(path);
        try {
            taskList = storage.load();
        } catch (Exception e) {
            taskList = new Tasks();
        }
        ui.greetings();
    }

    //Uses the UI and runs against various conditions
    public void process() throws DukeException {
        String command;
        while (true) {
            command = ui.getCommand();
            String[] executable = new String[100];
            executable = command.split(" ");

            if (command.equals("bye")) {
                ui.goodbye();
                storage.saveTask(taskList);
                break;
            } else if (command.equals("list")) {
                taskList.printTasks();
            } else if (executable.length > 1) {
                if (executable[0].equals("done")) {
                    taskList.markAsDone(Integer.parseInt(executable[1]));
                } else if (executable[0].equals("delete")) {
                    taskList.DeleteTask(Integer.parseInt(executable[1]));
                } else if (executable[0].equals("todo")) {
                    taskList.addTask(new TodoTask(command));
                } else if (executable[0].equals("deadline")) {
                    taskList.addTask(new DeadlineTask(command));
                } else if (executable[0].equals("event")) {
                    taskList.addTask(new EventTask(command));
                }
            } else {
                if (executable[0].equals("todo") || executable[0].equals("deadline")
                        || executable[0].equals("event")) {
                    throw new DukeException("Oops!!! Incomplete command :(");
                } else {
                    throw new DukeException("Oops!!! Invalid Input :(");
                }
            }

        }
    }

    //Main method where duke is initialized
    public static void main(String[] args) throws DukeException, IOException {
        Duke duke = new Duke("data/duke.txt");
        duke.process();
    }
}
