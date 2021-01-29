package DukeBody;

import DukeTask.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.format.DateTimeParseException;

public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private String username;

    public Duke (String username) {
        this.ui = new Ui();
        this.ui.brace();
        this.ui.greet();

        this.username = username;
        this.storage = new Storage(this.username);

        this.ui.storageReading(this.username);
        this.tasks = this.storage.readTasks();
        
        this.ui.brace();
    }

    public void run () {
        Scanner scanner = new Scanner(System.in);

        user_active: while (true) {
            String command = scanner.next();
            int taskIndex;

            this.ui.brace();
            try {
                switch (command) {
                    case "bye":
                        break user_active;

                    case "list":
                        this.ui.listTasks(this.tasks);
                        this.ui.print("You have " + this.tasks.undoneTasks().size()
                                + " undone tasks.");
                        break;

                    case "find":
                        this.ui.print("I found these matches from your search:");
                        this.ui.listTasks(this.tasks.matchedTasks(scanner.next()));
                        break;

                    case "undone":
                        this.ui.print("Tasks that remain outstanding:");
                        this.ui.listTasks(this.tasks.undoneTasks());
                        break;

                    case "done":
                        taskIndex = scanner.nextInt() - 1;
                        this.tasks.markAsDone(taskIndex);
                        this.ui.markedAsDone(this.tasks.get(taskIndex));
                        break;

                    case "delete":
                        taskIndex = scanner.nextInt() - 1;
                        Task task = this.tasks.remove(taskIndex);
                        this.ui.removedTask(this.tasks, task);
                        break;

                    default:
                        this.tasks.add(Parser.parseNewCommand(command, scanner.nextLine()));
                        this.ui.addedTask(this.tasks);
                }
            } catch (DateTimeParseException E) {
                this.ui.print("! Input datetime must be of format yyyy-MM-dd HHmm");
            } catch (Parser.UnrecognisedCommandException E) {
                this.ui.confuzzled();
            } catch (Exception E) {
                this.ui.print(E.getMessage());
            }

            this.ui.brace();
        }

        scanner.close();

        try {
            this.storage.saveTasks(this.tasks);
        } catch (Exception E) {
            this.ui.saveTasksFailure();
        }

        this.ui.byebye();
    }

    public static void main(String[] args) {
        new Duke("A0183450J").run();
    }
}