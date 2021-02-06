package checklst.storage;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import checklst.exception.ChecklstException;
import checklst.task.TaskList;

/**
 * The Storage class stores and processes the saved commands.
 */
public class Storage {

    private List<String> exportList = new ArrayList<>();

    /**
     * Saves the command history to the save file.
     * @throws ChecklstException Exception when the file is not found, prompts user to create it.
     */
    public void saveToFile(TaskList taskList) throws ChecklstException {
        try (PrintStream out = new PrintStream(new FileOutputStream("./data/checklst.txt"))) {
            exportList = taskList.getTaskList().stream().map(x -> x.export()).collect(Collectors.toList());
            out.print(this.exportList.toString().replace("[", "").replace("]", "").replace(", ", "\n"));
        } catch (FileNotFoundException e) {
            throw new ChecklstException("File not found! Please create a file at path ./data/checklst.txt");
        }
    }

}
