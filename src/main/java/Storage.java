import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.time.LocalDate;

public class Storage {
    public static final String DATA_PATH = "data/lihua.json";

    public Storage() {
    }

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
                // either one of the cases will be entered
                // <-> t will not be null
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
                t.setDone(isDone);
                tasks.addTask(t);
            }
            // System.out.println("Successfully loaded task data :D");
        } catch (IOException e) {
            System.out.println("Something bad happens, cannot load data. :')");
        } catch (ParseException e) {
            System.out.println("Something bad happens, cannot load data. :')");
        }
        return tasks;
    }

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