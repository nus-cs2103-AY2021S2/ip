import surrealchat.Pair;
import surrealchat.task.Task;
import surrealchat.task.TaskParser;
import surrealchat.user.UserInput;
import surrealchat.user.UserOutput;
import surrealchat.files.FileManagement;

import java.util.List;
import java.util.Scanner;

import java.io.File;
import java.io.IOException;

/**
 * Handles logic of SurrealChat.
 */
public class SurrealChat {
    protected static final String TASK_FILE_PATH = "tasks.txt";
    protected UserInput userInput;
    protected TaskParser taskParser;
    protected FileManagement fileManagement;
    protected UserOutput userOutput;

    private SurrealChat(UserInput userInput, TaskParser taskParser,
                        FileManagement fileManagement, UserOutput userOutput) {
        this.userInput = userInput;
        this.taskParser = taskParser;
        this.fileManagement = fileManagement;
        this.userOutput = userOutput;
    }

    /**
     * Creates new SurrealChat instance.
     * @param filePath Path of file for save/load.
     * @param verboseFlag Flag to determine whether to print verbose output.
     * @return SurrealChat instance.
     */
    public static SurrealChat initSurrealChat(File filePath, boolean verboseFlag) {
        UserInput userInput = new UserInput(new Scanner(System.in));
        TaskParser taskParser = new TaskParser();
        FileManagement fileManagement = new FileManagement(filePath);
        UserOutput userOutput = new UserOutput(verboseFlag);
        return new SurrealChat(userInput, taskParser, fileManagement, userOutput);
    }

    private void initialGreeting() {
        this.userOutput.printInitialGreeting();
    }

    private void exitProgram() {
        this.userOutput.printExitProgram();
    }

    private void printEasterEgg(String easterEgg) {
        this.userOutput.printEasterEggOutput(easterEgg);
    }

    private boolean commandLogic(boolean maintainLoop, String userCommand) {
        switch(userCommand) {
        case "bye":
            this.userInput.checkExcessArguments();
            maintainLoop = false; //Break out of infinite loop
            break;
        case"list":
            this.userInput.checkExcessArguments();
            Pair<String, List<Task>> listPair = this.taskParser.sendListToPrint();
            this.userOutput.printOutput(listPair);
            break;
        case "todo", "deadline", "event":
            String description = this.userInput.getInputDescription();
            Pair<String, Pair<Task, Integer>> taskPair =
                    this.taskParser.parseUserTaskInput(userCommand, description);
            this.userOutput.printOutput(taskPair);
            break;
        case "edit":
            description = this.userInput.getInputDescription(); //Get raw form
            Pair<String, Task> editPair = this.taskParser.editDescription(description);
            this.userOutput.printOutput(editPair);
            break;
        case "done":
            description = this.userInput.getInputDescription(); //Get raw form
            int taskNumber = this.userInput.getInputNumber(description); //Process to obtain int
            Pair<String, Task> donePair = this.taskParser.markAsDone(taskNumber);
            this.userOutput.printOutput(donePair);
            break;
        case "undone":
            description = this.userInput.getInputDescription(); //Get raw form
            taskNumber = this.userInput.getInputNumber(description); //Process to obtain int
            Pair<String, Task> undonePair = this.taskParser.markAsUndone(taskNumber);
            this.userOutput.printOutput(undonePair);
            break;
        case "delete":
            description = this.userInput.getInputDescription(); //Get raw form
            taskNumber = this.userInput.getInputNumber(description); //Process to obtain int
            Pair<String, Pair<Task, Integer>> deletePair = this.taskParser.deleteTask(taskNumber);
            this.userOutput.printOutput(deletePair);
            break;
        case "find":
            String keyword = this.userInput.getInputDescription();
            Pair<String, List<Task>> searchPair = this.taskParser.searchTasks(keyword);
            this.userOutput.printOutput(searchPair);
            break;
        case "orang", "vegetal":
            this.userInput.checkExcessArguments();
            this.printEasterEgg(userCommand);
            break;
        default:
            this.userInput.scannerNextLine(); //Clear input line
            throw new UnsupportedOperationException("Command not recognised. Not stonks!");
        }
        return maintainLoop;
    }

    /**
     * The driver code for Meme Man chatbot.
     * @param args - Optional argument
     */
    public static void main(String[] args) {
        //Initialise SurrealChat
        boolean verboseFlag = true;
        SurrealChat surrealChat = SurrealChat.initSurrealChat(new File(SurrealChat.TASK_FILE_PATH), verboseFlag);
        surrealChat.initialGreeting();

        //Try to read from file
        try {
            List<String> fileLines = surrealChat.fileManagement.loadTaskFile();
            Pair<String, List<Task>> filePair = surrealChat.taskParser.parseFileLines(fileLines);
            surrealChat.userOutput.printOutput(filePair);
        } catch (IOException e) {
            surrealChat.userOutput.printException(e);
        }

        //Read user commands
        String userCommand;
        boolean maintainLoop = true;
        while (maintainLoop) {
            userCommand = surrealChat.userInput.getInputCommand();
            try {
                maintainLoop = surrealChat.commandLogic(maintainLoop, userCommand);
            } catch (Exception e) {
                surrealChat.userOutput.printException(e);
            }
        }

        //Save and exit
        List<String> fileTaskList = surrealChat.taskParser.convertTasksForFile();
        surrealChat.fileManagement.saveTasksToFile(fileTaskList);
        surrealChat.userInput.closeScanner();
        surrealChat.exitProgram();
    }
}
