import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

/**
 * Handles logic of SurrealChat.
 */
public class SurrealChat {
    protected static final String TASK_FILE_PATH = "tasks.txt";
    protected final TaskManagement taskManagement;
    protected final FileManagement fileManagement;

    private SurrealChat(TaskManagement taskManagement,
                        FileManagement fileManagement) {
        this.taskManagement = taskManagement;
        this.fileManagement = fileManagement;
        this.loadFile();
    }

    /**
     * Creates new SurrealChat instance.
     * @param filePath Path of file for save/load.
     * @return SurrealChat instance.
     */
    public static SurrealChat initSurrealChat(File filePath) {
        TaskManagement taskManagement = new TaskManagement(new ArrayList<Task>());
        FileManagement fileManagement = new FileManagement(filePath);
        return new SurrealChat(taskManagement, fileManagement);
    }

    private String[] splitString(String inputString) {
        return inputString.split(" ");
    }

    /**
     * Generates output to be printed based on what command is executed.
     * @param inputString The entire command, inclusive of arguments if any.
     * @return Output to be printed.
     */
    public String commandLogic(String inputString) {
        String[] separatedWords = this.splitString(inputString);
        String userCommand = separatedWords[0];
        String restOfInput = "";

        //Reorganise the remainder of input.
        for (int i = 1; i < separatedWords.length; i++) {
            restOfInput += separatedWords[i];
        }
        restOfInput = restOfInput.trim();

        //Generate output
        switch(userCommand) {
        case "help":
            return HelpMode.displayHelp(restOfInput);
        case "list":
            Command listCommand = new ListCommand();
            String outputList = listCommand.execute(this.taskManagement);
            return outputList;
        case "todo":
            Command addToDoCommand = new ToDoCommand(restOfInput);
            String outputString = addToDoCommand.execute(this.taskManagement);
            return outputString;
        case "deadline":
            Command addDeadlineCommand = new DeadlineCommand(restOfInput);
            outputString = addDeadlineCommand.execute(this.taskManagement);
            return outputString;
        case "event":
            Command addEventCommand = new EventCommand(restOfInput);
            outputString = addEventCommand.execute(this.taskManagement);
            return outputString;
        case "edit":
            Command editCommand = new EditCommand(restOfInput);
            outputString = editCommand.execute(this.taskManagement);
            return outputString;
        case "done":
            Command doneCommand = new DoneCommand(restOfInput);
            outputString = doneCommand.execute(this.taskManagement);
            return outputString;
        case "delete":
            Command deleteCommand = new DeleteCommand(restOfInput);
            outputString = deleteCommand.execute(this.taskManagement);
            return outputString;
        case "find":
            Command findCommand = new FindCommand(restOfInput);
            outputString = findCommand.execute(this.taskManagement);
            return outputString;
        case "orang":
            EasterEgg orangEasterEgg = new OrangEasterEgg();
            outputString = orangEasterEgg.execute();
            return outputString;
        case "vegetal":
            EasterEgg vegetalEasterEgg = new VegetalEasterEgg();
            outputString = vegetalEasterEgg.execute();
            return outputString;
        default:
            return "Command not recognised. Not stonks!\n";
        }
    }

    /**
     * Loads the tasks from file.
     */
    public void loadFile() {
        try {
            List<String> fileLines = this.fileManagement.loadTaskFile();
            this.taskManagement.parseFileLines(fileLines);
        } catch (IOException e) {
            return;
        }
    }

    /**
     * Saves the tasks into file.
     * @return A string indicating that tasks are being saved.
     */
    public String saveFile() {
        List<String> fileTaskList = this.taskManagement.convertTasksForFile();
        this.fileManagement.saveTasksToFile(fileTaskList);
        return "Saving tasks now...\n";
    }
}
