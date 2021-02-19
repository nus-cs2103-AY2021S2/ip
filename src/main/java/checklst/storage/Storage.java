package checklst.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import checklst.exception.ChecklstException;
import checklst.task.Deadline;
import checklst.task.Event;
import checklst.task.TaskList;
import checklst.task.Todo;

/**
 * The Storage class stores and processes the saved commands.
 */
public class Storage {

    private List<String> exportList = new ArrayList<>();

    /**
     * Saves the command history to the save file.
     * @throws ChecklstException Exception when unable to save or write to file.
     */
    public void saveToFile(TaskList taskList) throws ChecklstException {
        File history = new File("./data/checklst.txt");
        history.getParentFile().mkdirs();

        assert history.canWrite() : "Can't Write to file!";

        try {
            history.createNewFile();
        } catch (IOException | SecurityException e) {
            throw new ChecklstException("Unable to make file!");
        }

        try (PrintStream out = new PrintStream(new FileOutputStream("./data/checklst.txt"))) {
            exportList = taskList.getTaskList().stream().map(x -> x.export()).collect(Collectors.toList());
            out.print(this.exportList.toString().replace("[", "").replace("]", "").replace(", ", "\n"));
        } catch (FileNotFoundException e) {
            throw new ChecklstException("File not found! Please create a file at path ./data/checklst.txt");
        }
    }

    /**
     * Processes history commands and run the relevant Task parser.
     * @param taskList TaskList to add Tasks to.
     * @throws ChecklstException Exception if strings are corrupted.
     */
    public void parseHistory(TaskList taskList) throws ChecklstException {
        try {
            String[] history = Files.readString(Paths.get("./data/checklst.txt")).split("\n");
            for (String task: history) {
                if (task.isBlank()) {
                    continue;
                }

                if (task.charAt(0) == 'T') {
                    taskList.add(Todo.parseTodo(task));
                } else if (task.charAt(0) == 'D') {
                    taskList.add(Deadline.parseDeadline(task));
                } else if (task.charAt(0) == 'E') {
                    taskList.add(Event.parseEvent(task));
                } else {
                    throw new ChecklstException("History corrupted!");
                }
            }
        } catch (InvalidPathException | IOException e) {
            throw new ChecklstException("No history found... Initializing from blank state!");
        }

    }

}
