package checklst.storage;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import checklst.exception.ChecklstException;

/**
 * The Storage class stores and processes the saved commands.
 */
public class Storage {

    private final ArrayList<String> commandHistory = new ArrayList<>();

    /**
     * Adds the given command to the history list.
     * @param command Command to be saved.
     */
    public void addCommand(String[] command) {
        this.commandHistory.add(String.join(" ", command));
    }

    /**
     * Removes the latest command from the history list. Used when there is an
     * errorneous command detected.
     */
    public void removeLastCommand() {
        this.commandHistory.remove(this.commandHistory.size() - 1);
    }

    /**
     * Saves the command history to the save file.
     * @throws ChecklstException Exception when the file is not found, prompts user to create it.
     */
    public void saveToFile() throws ChecklstException {
        try (PrintStream out = new PrintStream(new FileOutputStream("./data/checklst.txt"))) {
            out.print(this.commandHistory.toString().replace("[", "").replace("]", "").replace(", ", "\n"));
        } catch (FileNotFoundException e) {
            throw new ChecklstException("File not found! Please create a file at path ./data/checklst.txt");
        }
    }

    protected ArrayList<String> getCommandHistory() {
        return this.commandHistory;
    }

}
