import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import surrealchat.command.Command;
import surrealchat.command.DeadlineCommand;
import surrealchat.command.DeleteCommand;
import surrealchat.command.DoneCommand;
import surrealchat.command.EditCommand;
import surrealchat.command.EventCommand;
import surrealchat.command.FindCommand;
import surrealchat.command.ListCommand;
import surrealchat.command.ToDoCommand;
import surrealchat.easteregg.EasterEgg;
import surrealchat.easteregg.OrangEasterEgg;
import surrealchat.easteregg.VegetalEasterEgg;
import surrealchat.file.FileManagement;
import surrealchat.help.HelpMode;
import surrealchat.task.Task;
import surrealchat.task.TaskManagement;
import surrealchat.user.UserInput;
import surrealchat.user.UserOutput;

/**
 * Handles logic of SurrealChat.
 */
public class SurrealChat {
    protected static final String TASK_FILE_PATH = "tasks.txt";
    protected final UserInput userInput;
    protected final TaskManagement taskManagement;
    protected final FileManagement fileManagement;
    protected final UserOutput userOutput;

    private SurrealChat(UserInput userInput, TaskManagement taskManagement,
                        FileManagement fileManagement, UserOutput userOutput) {
        this.userInput = userInput;
        this.taskManagement = taskManagement;
        this.fileManagement = fileManagement;
        this.userOutput = userOutput;
    }

    /**
     * Creates new SurrealChat instance.
     * @param filePath Path of file for save/load.
     * @param isVerbose Flag to determine whether to print verbose output.
     * @return SurrealChat instance.
     */
    public static SurrealChat initSurrealChat(File filePath, boolean isVerbose) {
        UserInput userInput = new UserInput(new Scanner(System.in));
        TaskManagement taskManagement = new TaskManagement(new ArrayList<Task>());
        FileManagement fileManagement = new FileManagement(filePath);
        UserOutput userOutput = new UserOutput(isVerbose);
        return new SurrealChat(userInput, taskManagement, fileManagement, userOutput);
    }

    private void initialGreeting() {
        this.userOutput.printInitialGreeting();
    }

    private void exitProgram() {
        this.userOutput.printExitProgram();
    }

    private boolean commandLogic(boolean maintainLoop, String userCommand) {
        switch(userCommand) {
        case "help":
            String command = this.userInput.getInputDescription();
            this.userOutput.printOutput(HelpMode.displayHelp(command));
            break;
        case "bye":
            this.userInput.checkExcessArguments();
            maintainLoop = false; //Break out of infinite loop
            break;
        case "list":
            this.userInput.checkExcessArguments();
            Command listCommand = new ListCommand();
            String outputList = listCommand.execute(this.taskManagement);
            this.userOutput.printOutput(outputList);
            break;
        case "todo":
            String description = this.userInput.getInputDescription();
            Command addToDoCommand = new ToDoCommand(description);
            String outputString = addToDoCommand.execute(this.taskManagement);
            this.userOutput.printOutput(outputString);
            break;
        case "deadline":
            description = this.userInput.getInputDescription();
            Command addDeadlineCommand = new DeadlineCommand(description);
            outputString = addDeadlineCommand.execute(this.taskManagement);
            this.userOutput.printOutput(outputString);
            break;
        case "event":
            description = this.userInput.getInputDescription();
            Command addEventCommand = new EventCommand(description);
            outputString = addEventCommand.execute(this.taskManagement);
            this.userOutput.printOutput(outputString);
            break;
        case "edit":
            description = this.userInput.getInputDescription(); //Get raw form
            Command editCommand = new EditCommand(description);
            outputString = editCommand.execute(this.taskManagement);
            this.userOutput.printOutput(outputString);
            break;
        case "done":
            String taskNumberString = this.userInput.getInputDescription(); //Get raw form
            Command doneCommand = new DoneCommand(taskNumberString);
            outputString = doneCommand.execute(this.taskManagement);
            this.userOutput.printOutput(outputString);
            break;
        case "delete":
            taskNumberString = this.userInput.getInputDescription(); //Get raw form
            Command deleteCommand = new DeleteCommand(taskNumberString);
            outputString = deleteCommand.execute(this.taskManagement);
            this.userOutput.printOutput(outputString);
            break;
        case "find":
            String keyword = this.userInput.getInputDescription();
            Command findCommand = new FindCommand(keyword);
            outputString = findCommand.execute(this.taskManagement);
            this.userOutput.printOutput(outputString);
            break;
        case "orang":
            this.userInput.checkExcessArguments();
            EasterEgg orangEasterEgg = new OrangEasterEgg();
            outputString = orangEasterEgg.execute();
            this.userOutput.printEasterEggOutput(outputString);
            break;
        case "vegetal":
            this.userInput.checkExcessArguments();
            EasterEgg vegetalEasterEgg = new VegetalEasterEgg();
            outputString = vegetalEasterEgg.execute();
            this.userOutput.printEasterEggOutput(outputString);
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
        boolean isVerbose = true;
        SurrealChat surrealChat = SurrealChat.initSurrealChat(new File(SurrealChat.TASK_FILE_PATH), isVerbose);
        surrealChat.initialGreeting();

        //Try to read from file
        try {
            List<String> fileLines = surrealChat.fileManagement.loadTaskFile();
            String loadString = surrealChat.taskManagement.parseFileLines(fileLines);
            surrealChat.userOutput.printVerbose(loadString);
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
        List<String> fileTaskList = surrealChat.taskManagement.convertTasksForFile();
        surrealChat.fileManagement.saveTasksToFile(fileTaskList);
        surrealChat.userInput.closeScanner();
        surrealChat.exitProgram();
    }
}
