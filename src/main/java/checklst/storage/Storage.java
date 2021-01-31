package checklst.storage;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import checklst.exception.ChecklstException;

public class Storage {
    
    private final ArrayList<String> commandHistory;

    public Storage() {
        this.commandHistory = new ArrayList<>();
    }

    public void addCommand(String[] command) {
        this.commandHistory.add(String.join(" ", command));
    }

    public void removeLastCommand() {
        this.commandHistory.remove(this.commandHistory.size() - 1);
    }

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
