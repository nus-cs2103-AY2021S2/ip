import java.util.Scanner;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {

    private static final String FILE_DIR = "data/";
    private static final String FILE_PATH = FILE_DIR + "duke_data.txt";

    public static TaskList taskList = new TaskList();

    public static void printMessage(String message) {
        String s = "     ";
        String line = "    _____________________________________"
                + "__________________________________\n";
        System.out.println(line);
        message = message.replace("\n", "\n" + s);
        System.out.println(s + message);
        System.out.println(line);
    }

    public static void printWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String welcomeMessage = "Hello from \n" + logo + "\n"
                + "What can I do for you?";
        printMessage(welcomeMessage);
    }

    public static void printGoodbyeMessage() {
        printMessage("Bye. Hope to see you again soon!");
    }

    public static void doTask(String userDescription) {

    }

    public static void doCommand(String userCommand, String userDesc) {
        int taskNum;
        String stringToPrint = "";
        switch (userCommand) {
        case "list":
            stringToPrint = taskList.listTask();
            break;
        case "todo": //Fallthrough
        case "deadline": //Fallthrough
        case "event":
            stringToPrint = taskList.addTask(userCommand, userDesc);
            break;
        case "done":
            taskNum = Integer.parseInt(userDesc);
            stringToPrint = taskList.doTask(taskNum);
            break;
        case "delete":
            taskNum = Integer.parseInt(userDesc);
            stringToPrint = taskList.deleteTask(taskNum);
            break;
        default:
            stringToPrint = "Oops, the command '" + userCommand + "' is not recognised.\n";
        }
        printMessage(stringToPrint);
    }

    public static void saveFile() {
        String stringToWrite = "";
        String taskIsDone, taskType, taskName, taskByAt;
        Task t;
        int i = 0;
        while (i < taskList.numTask) {
            t = taskList.tasks[i];
            taskIsDone = t.getIsDone() ? "1" : "0";
            taskType = "";
            taskName = t.getDescription();
            taskByAt = "";
            if (t instanceof Deadline) {
                taskType = "deadline ";
                taskByAt = "/by " + ((Deadline) t).getBy();
            } else if (t instanceof Event) {
                taskType = "event ";
                taskByAt = "/at " + ((Event) t).getAt();
            } else {
                taskType = "todo ";
            }
            stringToWrite += taskIsDone + "," + taskType + taskName + taskByAt + "\n";
            i++;
        }

        try {
            FileWriter taskFileWriter = new FileWriter(FILE_PATH);
            taskFileWriter.write(stringToWrite);
            taskFileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadFile() {
        try {
            File taskDir = new File(FILE_DIR);
            if (!taskDir.isDirectory()) {
                taskDir.mkdir();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        File taskFile;

        try {
            taskFile = new File(FILE_PATH);
            if (taskFile.exists() == false) {
                taskFile.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String lineString, lineIsDone, lineRemaining, lineCommand, lineDesc;
        try {
            taskFile = new File(FILE_PATH);
            Scanner taskFileScanner = new Scanner(taskFile);
            int i = 0;
            while (taskFileScanner.hasNextLine()) {
                lineString = taskFileScanner.nextLine();
                lineIsDone = new SplitString(lineString, ",").getFirstString();
                lineRemaining = new SplitString(lineString, ",").getSecondString();
                lineCommand = new SplitString(lineRemaining, " ").getFirstString();
                lineDesc = new SplitString(lineRemaining, " ").getSecondString();

                taskList.addTask(lineCommand, lineDesc);
                if (String.valueOf(lineIsDone).equals("1") == true) {
                    taskList.tasks[i].markAsDone();
                }
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        loadFile();
        printWelcomeMessage();

        Scanner scan = new Scanner(System.in);
        String userInput = scan.nextLine();
        String userCommand = new SplitString(userInput, " ").getFirstString().toLowerCase();
        String userDesc = new SplitString(userInput, " ").getSecondString();

        while (String.valueOf(userCommand).equals("bye") == false) {
            doCommand(userCommand, userDesc);
            saveFile();

            userInput = scan.nextLine();
            userCommand = new SplitString(userInput, " ").getFirstString().toLowerCase();
            userDesc = new SplitString(userInput, " ").getSecondString();
        }

        scan.close();
        printGoodbyeMessage();
    }
}
