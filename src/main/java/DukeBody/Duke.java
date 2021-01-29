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

            this.ui.brace();
            try {
                switch (command) {
                    case "bye":
                        break user_active;
                    
                    case "list":
                        for (int i = 0; i < this.tasks.size(); ++ i) {
                            this.ui.print(i + 1 + ". " + this.tasks.get(i).taskInformation(
                                    Ui.outputFormat));
                        }

                        this.ui.print("You have " + this.tasks.outstandingTasks().size() 
                                + " undone tasks.");
                        break;

                    case "undone":
                        ArrayList<Task> undoneTasks = this.tasks.outstandingTasks();

                        for (int i = 0; i < undoneTasks.size(); ++ i) {
                            this.ui.print(i + 1 + ". " + undoneTasks.get(i).taskInformation(
                                Ui.outputFormat));
                        }

                        break;
                    
                    case "done":
                        int taskIndex = scanner.nextInt();
                        this.tasks.markAsDone(taskIndex);
                        this.ui.markedAsDone(this.tasks.get(taskIndex));
                        break;
                    
                    case "todo":
                        this.tasks.add(new ToDo(scanner.nextLine()));
                        this.ui.addedTask(this.tasks);
                        break;
                    
                    case "event":
                        this.tasks.add(Parser.parseNewTaskCommand(
                                scanner.nextLine().trim(), "/at"));

                        this.ui.addedTask(this.tasks);
                        break;

                    case "deadline":
                        this.tasks.add(Parser.parseNewTaskCommand(
                                scanner.nextLine().trim(), "/by"));
                        
                        this.ui.addedTask(this.tasks);
                        break;
                    
                    case "delete":
                        this.ui.removedTask(this.tasks, this.tasks.remove(
                                scanner.nextInt() - 1));

                        break;
                    default:
                        this.ui.confuzzled();
                    }
            } catch (DateTimeParseException E) {
                this.ui.print("! Input datetime must be of format yyyy-MM-dd HHmm");
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