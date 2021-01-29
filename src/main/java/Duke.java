/**
 * The main class where the duke is run
 */

public class Duke {
    private Ui ui;
    private TaskList taskList;
    private Storage storage;

    public Duke(String path) {
        ui = new Ui();
        storage = new Storage(path);
        try {
            taskList = storage.load();
        } catch (Exception e) {
            taskList = new TaskList();
        }
        ui.greetings();
    }

    //Uses the UI and runs against various conditions
    public void process() throws DukeException {
        String command;
        while (true) {
            command = ui.getCommand();
            Parser p = new Parser(command);
            p.parse();
            String TaskType = p.getTaskType();
        //    String[] executable = new String[100];
        //    executable = command.split(" ");
            if (command.equals("bye")) {
                ui.goodbye();
                storage.saveTask(taskList);
                break;
            } else if (command.equals("list")) {
                taskList.printTasks();
            } else if (p.getCommandLength() > 1) {
                if (p.getTaskType().equals("done")) {
                    taskList.markAsDone(Integer.parseInt(p.getIndex()));
                } else if (p.getTaskType().equals("delete")) {
                    taskList.DeleteTask(Integer.parseInt(p.getIndex()));
                } else if (p.getTaskType().equals("find")) {
                    taskList.findTask(p.getTaskName());
                } else if (p.getTaskType().equals("todo")) {
                    taskList.addTask(new TodoTask(command));
                } else if (p.getTaskType().equals("deadline")) {
                    taskList.addTask(new DeadlineTask(command));
                } else if (p.getTaskType().equals("event")) {
                    taskList.addTask(new EventTask(command));
                }
            } else {
                if (p.getTaskType().equals("todo") || p.getTaskType().equals("deadline")
                        || p.getTaskType().equals("event")) {
                    throw new DukeException("Oops!!! Incomplete command :(");
                } else {
                    throw new DukeException("Oops!!! Invalid Input :(");
                }
            }
        }
    }

    //Main method where duke is initialized
    public static void main(String[] args) throws DukeException {
        Duke duke = new Duke("data/duke.txt");
        duke.process();
    }
}
