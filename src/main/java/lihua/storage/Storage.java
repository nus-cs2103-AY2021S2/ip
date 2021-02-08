package lihua.storage;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import lihua.tasks.Deadline;
import lihua.tasks.Event;
import lihua.tasks.Task;
import lihua.tasks.Tasks;
import lihua.tasks.ToDo;
import org.json.simple.parser.ParseException;

public class Storage {
    /** Default path in which the task data is stored */
    public static final String DATA_PATH = "data/lihua.json";

    /**
     * Loads the tasks from hard disk.
     *
     * @return The task list object, generated from data extracted from hard disk.
     */
    public Tasks load() {
        Tasks tasks = new Tasks();
        try {
            // create a file in the path, in case the file does not exist
            boolean isTheFileOriginallyExist = createFileRecursively();
            if (!isTheFileOriginallyExist) {
                return tasks;
            }

            JSONParser parser = new JSONParser();
            Reader reader = new FileReader(DATA_PATH);
            JSONArray jsonArray = (JSONArray) parser.parse(reader);

            populateTasks(jsonArray, tasks);
        } catch (IOException | ParseException | DateTimeParseException e) {
            System.err.println("Storage issues encountered. Task cannot be stored.");
        }
        return tasks;
    }

    private void populateTasks(JSONArray jsonArray, Tasks tasks) throws ParseException, DateTimeParseException {
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            String type = (String) jsonObject.get("type");
            String description = (String) jsonObject.get("description");
            boolean isDone = (boolean) jsonObject.get("isDone");

            Task t = null;
            if (type.equals("todo")) {
                t = new ToDo(description);
            } else {
                LocalDate time = LocalDate.parse((String) jsonObject.get("time"));
                if (type.equals("deadline")) {
                    t = new Deadline(description, time);
                } else if (type.equals("event")) {
                    t = new Event(description, time);
                }
            }
            assert t != null;
            t.setDone(isDone);
            tasks.addTask(t);
        }
    }

    /** Returns true if the file previously exists */
    private boolean createFileRecursively() throws IOException {
        File fileChecker = new File(DATA_PATH);
        if (!fileChecker.exists()) {
            fileChecker.getParentFile().mkdir();
            fileChecker.createNewFile();
            return false;
        } else {
            return true;
        }
    }

    /**
     * Saves the tasks configuration to hard disk. The new data will overwrite old data.
     *
     * @param tasks The tasks configuration to be saved in hard disk.
     */
    public void saveTasks(Tasks tasks) {
        JSONArray jsonArray = tasks.getJsonArray();
        try {
            createFile();
            writeToFile(jsonArray);
        } catch (IOException e) {
            System.err.println("Storage issues encountered. Task cannot be stored.");
        }
    }

    private void createFile() throws IOException {
        File fileToCreate = new File(DATA_PATH);
        fileToCreate.getParentFile().mkdir();
        fileToCreate.createNewFile();
    }

    private void writeToFile(JSONArray jsonArray) throws IOException {
        FileWriter fileWriter = new FileWriter(DATA_PATH);
        fileWriter.write(jsonArray.toJSONString());
        fileWriter.close();
    }
}
