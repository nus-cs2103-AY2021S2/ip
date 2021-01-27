import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String path) {
        storage = new Storage(path);
        ui = new Ui("Sonia");

        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            tasks = new TaskList(new ArrayList<>());
        }
    }

    public void executeCommand(Command c) {
        try {
            if (c.type == CommandType.ADD_TODO) {
                if (c.args.size() != 1) {
                    throw new DukeInvalidArgumentException();
                }

                tasks.addTodo(c.args.get(0));
                ui.echo(ui.ADD_TASK);
            } else if (c.type == CommandType.ADD_DEADLINE) {
                if (c.args.size() != 2) {
                    throw new DukeInvalidArgumentException();
                }

                tasks.addDeadline(c.args.get(0), c.args.get(1));
                ui.echo(ui.ADD_TASK);
            } else if (c.type == CommandType.ADD_EVENT) {
                if (c.args.size() != 2) {
                    throw new DukeInvalidArgumentException();
                }

                tasks.addEvent(c.args.get(0), c.args.get(1));
                ui.echo(ui.ADD_TASK);
            } else if (c.type == CommandType.COMPLETE_TASK) {
                int id = Integer.parseInt(c.args.get(0));
                tasks.completeTask(id);
                ui.echo(ui.COMPLETE_TASK);
            } else if (c.type == CommandType.DELETE_TASK) {
                int id = Integer.parseInt(c.args.get(0));
                tasks.deleteTask(id);
                ui.echo(ui.DELETE_TASK);
            } else if (c.type == CommandType.FIND_TASKS) {
                ui.echo(ui.FIND_TASKS);
                tasks.findTasks(c.args.get(0));
            } else if (c.type == CommandType.LIST_TASKS) {
                ui.echo(ui.SHOW_TASKS);
                tasks.showTasks();
            } else if (c.type == CommandType.TERMINATE) {
                this.terminate();
            } else {
                throw new DukeInvalidCommandException();
            }
        } catch (DukeInvalidArgumentException e) {
            ui.echo(ui.INVALID_ARGUMENT);
        } catch (DukeInvalidCommandException e) {
            ui.echo(ui.INVALID_COMMAND);
        }
    }

    public void terminate() {
        storage.save(tasks);
        ui.closing();
        System.exit(0);
    }

    public static void main(String[] args) {
        Duke sonia = new Duke("./data.txt");
        sonia.ui.greeting();
        System.out.print("You: ");

        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            Command c = sonia.ui.prompt(sc);
            sonia.executeCommand(c);
            System.out.print("You: ");
        }

    }
}
