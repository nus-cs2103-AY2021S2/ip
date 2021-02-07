package dukebody;

import java.io.IOException;
import java.util.Scanner;
import java.util.InputMismatchException;

import dukegui.MainApp;
import dukegui.MainWindow;
import javafx.application.Application;

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
    TaskList tasks;
    DataBase connection = new DataBase();

    MainWindow mainWindow;

    // accessors
    /**
     * Checks if the user profile has been setup.
     * @return  boolean whether the user profile has been setup.
     */
    public boolean hasSetupUser() {
        return username.length() > 0;
    }

    // mutators
    /**
     * Syncs duke with the main window in order to directly output
     * responses into the window dialog box.
     * @param window    the main window to syn in the application.
     */
    public void syncWindow (MainWindow window) {
        mainWindow = window;
    }

    /**
     * Setups the user profile through the username. Loads any
     * previously saved tasks under the same alias from the
     * default database directory path.
     * @param name  the user alias used to setup the user profile.
     */
    public void userSetup (String name) {
        username = name;
        mainWindow.dukeOutput("Henlo " + username + ", reading tasks from secret database...");
        try {
            tasks = connection.queryTasks(username);
            mainWindow.dukeOutput("... very success! what can doge do for you now?");
        } catch (DataBase.LegacyDataException e) {
            mainWindow.dukeOutput("doge could not read userdata! previous tasks go brrrrrrrr...\n"
                    + "sorry hooman, what can doge do for you now?");
        }
    }

    /**
     * The duke logic to response and act on the user inputs from the
     * main window by recognition of specific commands and necessary
     * subcommands.
     * @param command   the entire user input from the main window
     */
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

                    output.append(outputTasks(tasks.getMatchedTasks(subcommands[1].trim())));
                    break;

                case "undone":
                    TaskList undone = tasks.getUndoneTasks();
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
                    output.append(tasks.get(taskNumber).getTaskInformation(
                            mainWindow.getDateFormat()));
                    break;

                case "delete":
                    // Fallthrough
                case "remove":
                    if (subcommands.length == 1) {
                         throw new Duke.ExpectedSubcommandException("<task_number>");
                    }

                    scanner = new Scanner(subcommands[1]);
                    taskNumber = scanner.nextInt() - 1;
                    scanner.close();

                    output.append("removed the task! begone!\n");
                    output.append(tasks.remove(taskNumber).getTaskInformation(
                            mainWindow.getDateFormat()));
                    break;

                default:
                    if (subcommands.length == 1) {
                        throw new Duke.UnrecognisedCommandException(subcommands[0]);
                    } else {
                        tasks.add(Parser.parseNewCommand(subcommands[0].trim(), subcommands[1].trim()));
                        output.append("added the task for hooman!\n");
                        output.append(tasks.get(tasks.size() - 1).getTaskInformation(
                                mainWindow.getDateFormat()));
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

        mainWindow.dukeOutput(output.toString());
    }

    /**
     * Formats a list of tasks to output in the window dialog box.
     * @param tasks     the list of tasks.
     * @return          the formatted string to output.
     */
    private String outputTasks (TaskList tasks) {
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < tasks.size(); ++ i) {
            output.append(i + 1);
            output.append(". " + tasks.get(i).getTaskInformation(mainWindow.getDateFormat())
                    + "\n");
        }

        return output.toString();
    }

    /**
     * Launches the main application
     * @param args  variable arguments from CMD
     */
    public static void main(String[] args) {
        Application.launch(MainApp.class, args);
    }
}
