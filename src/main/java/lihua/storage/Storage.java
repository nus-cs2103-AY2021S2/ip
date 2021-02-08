package lihua.storage;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalDate;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import lihua.tasks.Deadline;
import lihua.tasks.Event;
import lihua.tasks.Task;
import lihua.tasks.Tasks;
import lihua.tasks.ToDo;

public class Storage {
    /** Default path in which the task data is stored */
    public static final String DATA_PATH = "data/lihua.json";

    /**
     * Initializes an storage instance.
     */
    public Storage() {
    }

    /**
     * Loads the tasks from hard disk.
     *
     * @return The task list object, generated from data extracted from hard disk.
     */
    public Tasks load() {
        Tasks tasks = new Tasks();
        try {
            // will create a file in the path, in case the file does not exist
            File fileChecker = new File(DATA_PATH);
            if (!fileChecker.exists()) {
                fileChecker.getParentFile().mkdir();
                fileChecker.createNewFile();
                return tasks;
            }

            JSONParser parser = new JSONParser();
            Reader reader = new FileReader(DATA_PATH);
            JSONArray jsonArray = (JSONArray) parser.parse(reader);

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
        } catch (Exception e) {
            System.out.println("Something bad happens, cannot load data. :')");
        }
        return tasks;
    }

    /**
     * Saves the tasks configuration to hard disk. The new data will overwrite old data.
     *
     * @param tasks The tasks configuration to be saved in hard disk.
     */
    public void saveTasks(Tasks tasks) {
        JSONArray jsonArray = tasks.getJsonArray();

        try {
            File fileToCreate = new File(DATA_PATH);
            fileToCreate.getParentFile().mkdir();
            fileToCreate.createNewFile();

            FileWriter fileWriter = new FileWriter(DATA_PATH);
            fileWriter.write(jsonArray.toJSONString());
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Sorry, this task cannot be saved right now. :')");
        }
    }
}
