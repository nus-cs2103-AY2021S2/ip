package dukebody;

import java.io.IOException;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.time.format.DateTimeParseException;

import duketask.Task;
import dukegui.DukeApp;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Duke {
    public static class ExpectedSubcommandException extends Exception  {
        public ExpectedSubcommandException (String expectedSubcommand) {
            super("doge cannot find subcommand: " + expectedSubcommand
                    + "! very missing...");
        }
    }

    public static class UnrecognisedCommandException extends Exception  {
        public UnrecognisedCommandException (String command) {
            super("?? doge confused very. no understand: " + command);
        }
    }

    String username = "";
    DukeApp app;
    TaskList tasks;
    DataBase connection = new DataBase();

    // accessors
    public boolean hasSetupUser() {
        return username.length() > 0;
    }

    // mutators
    public void syncDukeApp (DukeApp app) {
        this.app = app;
    }

    public void userSetup (String name) {
        username = name;
        app.dukeOutput("HenLo " + username + ", reading tasks from secret database...");
        try {
            tasks = connection.queryTasks(username);
            app.dukeOutput("... very success! what can doge do for you now?");
        } catch (DataBase.LegacyDataException e) {
            app.dukeOutput("doge could not read userdata! previous tasks go brrrrrrrr...\n"
                    + "sorry hooman, what can doge do for you now?");
        }
    }

    public void respondToCommand (String command) {
        String[] subcommands = command.split(" ", 2);
        StringBuilder output = new StringBuilder();
        Scanner scanner;
        int taskNumber;

        try {
            switch (subcommands[0].trim()) {
                case "bye":
                    connection.updateTasks(username, tasks);
                    output.append("bye hooman, come back soon!");
                    break;

                case "list":
                    if (tasks.size() > 0) {
                        output.append("hooman has the following tasks:\n");
                        output.append(outputTasks(tasks));
                    } else{
                        output.append("hooman has no tasks... why tho");
                    }
                    break;

                case "find":
                    output.append("sniff... sniff... I've found these tasks hooman!\n");

                    if (subcommands.length == 1) {
                        throw new Duke.ExpectedSubcommandException("<search_text>");
                    }

                    output.append(outputTasks(tasks.matchedTasks(subcommands[1].trim())));
                    break;

                case "undone":
                    TaskList undone = tasks.undoneTasks();
                    if (undone.size() > 0) {
                        output.append("These are undone... so concern...\n");
                        output.append(outputTasks(undone));
                    } else {
                        output.append("No more tasks! very speed. much wow");
                    }

                    break;

                case "done":
                    if (subcommands.length == 1) {
                        throw new Duke.ExpectedSubcommandException("<task_number>");
                    }

                    scanner = new Scanner(subcommands[1]);
                    taskNumber = scanner.nextInt() - 1;
                    scanner.close();

                    tasks.get(taskNumber).markAsDone();
                    output.append("marked the task as done! yip yip.\n");
                    output.append(tasks.get(taskNumber).taskInformation(
                            app.getDateformat()));
                    break;

                case "delete":
                case "remove":
                    if (subcommands.length == 1) {
                        throw new Duke.ExpectedSubcommandException("<task_number>");
                    }

                    scanner = new Scanner(subcommands[1]);
                    taskNumber = scanner.nextInt() - 1;
                    scanner.close();

                    Task removed = tasks.get(taskNumber);
                    output.append("removed the task! begone!\n");
                    output.append(tasks.remove(taskNumber).taskInformation(
                            app.getDateformat()));
                    break;

                default:
                    if (subcommands.length == 1) {
                        throw new Duke.UnrecognisedCommandException(subcommands[0]);
                    } else {
                        tasks.add(Parser.parseNewCommand(subcommands[0].trim(), subcommands[1].trim()));
                        output.append("added the task for hooman!\n");
                        output.append(tasks.get(tasks.size() - 1).taskInformation(
                                app.getDateformat()));
                    }
            }
        } catch (InputMismatchException e) {
            output.append("oh no, task number must be an integer!");
        } catch (IndexOutOfBoundsException e) {
            output.append("no such task number exists hooman... ?");
        } catch (IOException e) {
            output.append("spectacular failure! doge cannot save tasks...");
        } catch (Exception e) {
            output.append(e.getMessage());
        }

        app.dukeOutput(output.toString());
    }

    private String outputTasks (TaskList tasks) {
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < tasks.size(); ++ i) {
            output.append(i + 1);
            output.append(". " + tasks.get(i).taskInformation(app.getDateformat())
                    + "\n");
        }

        return output.toString();
    }

    public static void main(String[] args) {
        Application.launch(DukeApp.class, args);
    }
}
