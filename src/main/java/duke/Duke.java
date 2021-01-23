package duke;

import duke.main.Deadline;
import duke.main.DukeException;
import duke.main.Task;
import duke.main.Todo;
import duke.main.Event;
import duke.main.Deadline;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

import duke.command.CommandOption;

/**
 * Main class for project duke.
 * Takes in an user command (within the exclusive list) and react accordingly.
 */
public class Duke {
    protected static final String[] greet = {
            " ____        _        ",
            "|  _ \\ _   _| | _____ ",
            "| | | | | | | |/ / _ \\",
            "| |_| | |_| |   <  __/",
            "|____/ \\__,_|_|\\_\\___|\n",
            "Greetings! I'm Your Personal Assistant Duke:)",
            "What can I do for you today?"
    };

    protected static final String[] exit = {
            "Bye. Nice to meet you and hope to see you again soon!"
    };

    protected static final String border =
            "    ____________________________________________________________" +
                    "_______________\n";

    protected static final String indent = "     ";

    protected static ArrayList<Task> tasks = new ArrayList<>();

    protected static Path dataFilePath =
            Paths.get("src", "main", "java", "duke", "data", "duke.txt");

    protected static Path dataFolderPath =
            Paths.get("src", "main", "java", "duke", "data");


    public static void main(String[] args) {
        init();
        Scanner sc = new Scanner(System.in);
        String message = sc.nextLine();
        while(!message.equalsIgnoreCase("bye")) {
            printReply(message);
            message = sc.nextLine();
        }
        System.out.println(formatMessage(exit));
    }

    private static void init() {
        System.out.println(formatMessage(greet));
        try {
            storageExistOrCreate();
            tasks = loadData(dataFilePath);
        } catch (DukeException e) {
            System.out.println(formatMessage(new String[] {e.getMessage()}));
            System.exit(-1);
        }
    }

    /**
     * Print the corresponding reply based on user input.
     * @param message first (String) word from user input
     */
    private static void printReply(String message) {
        System.out.print(border);
        try {
            parseMessage(message);
        }
        catch (DukeException dukeExp) {
            System.out.println(indent + dukeExp.getMessage());
        }
        System.out.println(border);
    }

    /**
     * 1. Takes in the first word from user input and carries out relevant actions based on
     *      the word by printing out corresponding replies.
     * 2. A command is NOT case sensitive.
     *      For example, "LIST"/"list"/"List" will have the same effect.
     * 3. However, no additional whitespaces should be entered.
     *      For example, "LIST "/"list "/"List " will not work.
     * 4. Disclaimer: the idea of using .valueOf and convert to UpperCase is inspired
     *      based on discussion of #Issue 14 in forum.
     *      Credit to @samuelfangjw who mentioned it first.
     * @param message user input
     * @throws IllegalArgumentException thrown if user enters an invalid command
     * @throws DukeException thrown if user enters a valid command but invalid related information
     */
    private static void parseMessage(String message)
            throws DukeException{
        String[] msgArray = message.split(" ", 2);
        String commandWord = msgArray[0];
        String otherInfo = null;
        if (msgArray.length > 1) {
            otherInfo = msgArray[1];
        }

        try {
            CommandOption command = CommandOption.valueOf(commandWord.toUpperCase(Locale.ROOT));
            switch (command) {
            case LIST:
                printList();
                break;
            case DONE:
                completeTask(otherInfo);
                saveData(dataFilePath, tasks);
                break;
            case TODO:
                if (otherInfo == null) {
                    throw new DukeException("Please provide a description when creating todo.");
                }
                addTask(new Todo(otherInfo));
                saveData(dataFilePath, tasks);
                break;
            case EVENT:
                String[] temp = otherInfo.split("/at", 2);
                String description = temp[0].strip();
                String at = temp[1].strip();
                if (description.equals("") || at.equals("")) {
                    throw new DukeException("Please provide a description or an at period" +
                            " when creating event.");
                }
                addTask(new Event(description, at));
                saveData(dataFilePath, tasks);
                break;
            case DEADLINE:
                temp = otherInfo.split("/by", 2);
                description = temp[0].strip();
                String by = temp[1].strip();
                if (description.equals("") || by.equals("")) {
                    throw new DukeException("Please provide a description or a by date " +
                            "when creating deadline.");
                }
                addTask(new Deadline(description, by));
                saveData(dataFilePath, tasks);
                break;
            case DELETE:
                deleteTask(otherInfo);
                saveData(dataFilePath, tasks);
                break;
            }
        }
        catch (IllegalArgumentException e) {
            throw new DukeException("I do not understand this command.");
        }
        catch (NullPointerException e) {
            throw new DukeException("Please provide the relevant information " +
                    "when creating a task.");
        }
        catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please use /by when creating deadline or " +
                    "/at when creating event.");
        }
    }

    /**
     * Returns the formatted message (specifically, greet and bye) to be printed.
     * @param messages an array of strings, main body of the message to be formatted.
     * @return the formatted message (specifically, greet and bye) to be printed.
     */
    private static String formatMessage(String[] messages) {
        StringBuilder res = new StringBuilder(border);
        for (String message : messages) {
            res.append(indent).append(message).append("\n");
        }
        res.append(border);
        return res.toString();
    }

    private static void printList() {
        StringBuilder res = new StringBuilder();
        if (tasks.isEmpty()) {
            res.append(indent)
                    .append("Hi! Your todo list is currently empty.\n");
        } else {
            res.append(indent)
                    .append("Hi! This is your todo list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                res.append(indent)
                        .append(" ")
                        .append(i + 1)
                        .append(".")
                        .append(task.toString())
                        .append("\n");
            }
        }
        System.out.print(res.toString());
    }


    private static void addTask(Task newTask) {
        tasks.add(newTask);

        String res = indent + "Roger that! Added new task:\n" +
                indent + " " + newTask.toString() + "\n" +
                indent + "Now you have " + tasks.size() + " " +
                (tasks.size() > 1 ? "tasks" : "task") +
                " in the list.";
        System.out.println(res);
    }

    /**
     * Complete the task with the given index and print the confirmation message.
     *
     * One possible error is handled. Namely, it is:
     *      1. the task has been completed;
     * @param taskIndex taskIndex from user input, in String.
     * @throws DukeException when an invalid taskIndex is entered
     */
    private static void completeTask(String taskIndex) throws DukeException {
        int index = verifyTaskIndex(taskIndex);
        Task task = tasks.get(index);

        if (!task.markAsDone()) {
            throw new DukeException("Task with the given index has been completed.");
        }

        String res = indent +
                "Wonderful! You have completed this task:\n" +
                indent + "  " + task.toString();
        System.out.println(res);
    }

    /**
     * Remove the task with the given index and print the confirmation message.
     * @param taskIndex taskIndex from user input, in String.
     * @throws DukeException when an invalid taskIndex is entered
     */
    private static void deleteTask(String taskIndex) throws DukeException {
        int index = verifyTaskIndex(taskIndex);
        Task task = tasks.remove(index);

        String res = indent +
                "On your command! I have removed this task:\n" +
                indent + "  " + task.toString() + "\n" +
                indent + "Now you have " + tasks.size() + " " +
                (tasks.size() > 1 ? "tasks" : "task") +
                " in the list.";
        System.out.println(res);
    }

    /**
     * Verify if the given taskIndex is valid.
     * Three possible errors are handled. Namely, they are:
     *      1. no taskIndex;
     *      2. taskIndex is not an integer;
     *      3. taskIndex is out of bound;
     * @param taskIndex taskIndex from user input, in String.
     * @return index in int if it is valid
     * @throws DukeException if invalid index is provided
     */
    private static int verifyTaskIndex(String taskIndex) throws DukeException {
        int index;
        if (taskIndex == null) {
            throw new DukeException("Please enter a task index.");
        }

        try{
            index = Integer.parseInt(taskIndex) - 1;
        }
        catch (NumberFormatException e) {
            throw new DukeException("Task index entered is not an integer.");
        }

        if (index >= tasks.size()) {
            throw new DukeException("Task with the given index does not exist.");
        }

        return index;
    }

    /**
     *
     * Note: may need to check if task is null.
     * @param filePath
     * @return
     * @throws DukeException
     */
    protected static ArrayList<Task> loadData(Path filePath) throws DukeException {
        ArrayList<Task> res = new ArrayList<>();
        try {
            BufferedReader br = Files.newBufferedReader(filePath);
            String line;
            while((line = br.readLine()) != null) {
                Task task = stringToTask(line);
                if(task != null) {
                    res.add(task);
                }
            }
        } catch (IOException e) {
            throw new DukeException("error when loading Data."
                    + System.lineSeparator()
                    + indent + " " + e);
        }
        return res;
    }

    private static Task stringToTask(String taskInfo) {
        String[] taskInfoArr = taskInfo.split("\\|");
        String type = taskInfoArr[0].strip();
        boolean isDone = taskInfoArr[1].strip().equals("1");
        String description = taskInfoArr[2].strip();
        Task res = null;
        switch(type) {
        case "T":
            res = new Todo(description, isDone);
            break;
        case "E":
            String at = taskInfoArr[3].strip();
            res = new Event(description, isDone, at);
        case "D":
            String by = taskInfoArr[3].strip();
            res = new Deadline(description, isDone, by);
        }
        return res;
    }

    protected static void saveData(Path filePath, ArrayList<Task> tasks) throws DukeException {
        try {
            ArrayList<String> tasksInfoToStore = new ArrayList<>();
            for(Task task : tasks) {
                tasksInfoToStore.add(task.infoToStore());
            }
            Files.write(filePath, tasksInfoToStore);
        } catch (IOException e) {
            throw new DukeException("error in saving data."
                    + System.lineSeparator()
                    + indent + " " + e);
        }
    }

    protected static void storageExistOrCreate()
            throws DukeException{
        try {
            if (Files.notExists(dataFilePath)) {
                if (Files.notExists(dataFolderPath)) {
                    dataFolderPath = Files.createDirectories(dataFolderPath);
                }
                dataFilePath = Files.createFile(dataFilePath);
            }
        } catch (IOException e) {
            throw new DukeException("Unable to access data stored, access rights issue."
                    + System.lineSeparator()
                    + indent + " " + e);
        }
    }
}
