package dukebody;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.Scanner;
import java.util.InputMismatchException;

import dukegui.MainApp;
import dukegui.MainWindow;
import javafx.application.Application;
import duketask.Deadline;
import duketask.Event;
import duketask.ToDo;

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
     * @param input  the user alias used to setup the user profile.
     */
    public void userSetup (String input) {
        try {
            String[] commandBreakdown = Parser.parseSubcommand(input, "/read_from:");
            username = commandBreakdown[0];
            connection.changePath(commandBreakdown[1]);
            mainWindow.dukeOutput("HENLO " + username + ", reading tasks from "
                    + commandBreakdown[1] + " ...");
        } catch (InvalidPathException e) {
            mainWindow.dukeOutput("An invalid path was given to Doge! Connecting to default database ...");
        } catch (Duke.ExpectedSubcommandException e) {
            username = input;
            mainWindow.dukeOutput("HENLO " + username + ", reading tasks from secret database...");
        }

        try {
            tasks = connection.queryTasks(username);
            mainWindow.dukeOutput("... very success! what can doge do for you now?");
        } catch (DataBase.LegacyDataException e) {
            mainWindow.dukeOutput("doge could not read userdata! previous tasks go brrrrrrrr...");
            mainWindow.dukeOutput("sorry hooman, what can doge do for you now?");
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
        String[] commandBreakdown;
        Scanner scanner;
        int taskNumber;

        try {
            switch (subcommands[0].trim().toLowerCase()) {
                case "bye":
                    connection.updateTasks(username, tasks);
                    mainWindow.dukeOutput("bye hooman, come back soon!");
                    mainWindow.exit();
                    break;

                case "list":
                    if (tasks.size() > 0) {
                        mainWindow.dukeOutput("hooman has the following tasks:");
                        outputTasks(tasks);
                    } else{
                        mainWindow.dukeOutput("hooman has no tasks... why tho");
                    }
                    break;

                case "find":
                    if (subcommands.length == 1) {
                        throw new Duke.ExpectedSubcommandException("<search_text>");
                    }

                    mainWindow.dukeOutput("sniff... sniff... I've found these tasks hooman!");
                    outputTasks(tasks.getMatchedTasks(subcommands[1].trim()));
                    break;

                case "undone":
                    TaskList undoneTasks = tasks.getUndoneTasks();
                    if (undoneTasks.size() > 0) {
                        mainWindow.dukeOutput("These are undone... so concern...");
                        outputTasks(undoneTasks);
                    } else {
                        mainWindow.dukeOutput("No more tasks! very speed. much wow");
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
                    mainWindow.dukeOutput("marked the task as done! yip yip.");
                    mainWindow.dukeOutput(tasks.get(taskNumber).getTaskInformation(
                            mainWindow.getDateFormat()));
                    break;

                case "delete": // Fallthrough
                case "remove":
                    if (subcommands.length == 1) {
                         throw new Duke.ExpectedSubcommandException("<task_number>");
                    }

                    scanner = new Scanner(subcommands[1]);
                    taskNumber = scanner.nextInt() - 1;
                    scanner.close();

                    mainWindow.dukeOutput("removed the task! begone!");
                    mainWindow.dukeOutput(tasks.remove(taskNumber).getTaskInformation(
                            mainWindow.getDateFormat()));
                    break;

                case "todo":
                    tasks.add(new ToDo((subcommands.length == 1) ? "" : subcommands[1].trim()));
                    outputNewTask();
                    break;

                case "deadline":
                    commandBreakdown = Parser.parseSubcommand(subcommands[1], "/by");
                    tasks.add(new Deadline(commandBreakdown[0], Parser.parseDateTime(
                            commandBreakdown[1])));

                    outputNewTask();
                    break;

                case "event":
                    commandBreakdown = Parser.parseSubcommand(subcommands[1], "/at");
                    tasks.add(new Event(commandBreakdown[0], Parser.parseDateTime(
                            commandBreakdown[1])));

                    outputNewTask();
                    break;

                default:
                    throw new Duke.UnrecognisedCommandException(subcommands[0]);
            }
        } catch (InputMismatchException e) {
            mainWindow.dukeOutput("oh no, task number must be an integer!");
        } catch (IndexOutOfBoundsException e) {
            mainWindow.dukeOutput("no such task number exists hooman... ?");
        } catch (IOException e) {
            mainWindow.dukeOutput("spectacular failure! doge cannot save tasks...");
        } catch (Exception e) {
            mainWindow.dukeOutput(e.getMessage());
        }
    }

    /**
     * Responds to users the most recent task in the tasklist.
     */
    private void outputNewTask () {
        mainWindow.dukeOutput("added the task for hooman!");
        mainWindow.dukeOutput(tasks.get(tasks.size() - 1).getTaskInformation(
                mainWindow.getDateFormat()));
    }

    /**
     * Formats a list of tasks to output in the window dialog box.
     * @param tasks     the list of tasks.
     * @return          the formatted string to output.
     */
    private void outputTasks (TaskList tasks) {
        for (int i = 0; i < tasks.size(); ++ i) {
            mainWindow.dukeOutput(i + 1 + ". " + tasks.get(i).getTaskInformation(
                    mainWindow.getDateFormat()));
        }
    }

    /**
     * Launches the main application
     * @param args  variable arguments from CMD
     */
    public static void main(String[] args) {
        Application.launch(MainApp.class, args);
    }
}
