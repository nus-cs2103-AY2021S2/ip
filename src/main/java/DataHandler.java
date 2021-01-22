import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataHandler {

    public static ArrayList<Task> tasks = new ArrayList<>();
    public static JSONArray taskList = new JSONArray();

    //Solution below adapted from https://howtodoinjava.com/java/library/json-simple-read-write-json-examples/
    @SuppressWarnings("unchecked")
    public static ArrayList<Task>  loadData() {

        File tasksFile = new File("./data/tasks.json");
        if (!tasksFile.exists()) {
            try {
                if (!tasksFile.createNewFile()) {
                    throw new DukeException("Error");
                }
            } catch (IOException | DukeException e) {
                System.out.println("Error");
            }
        }

        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("./data/tasks.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            taskList = (JSONArray) obj;
            System.out.println(taskList);

            //load each task
            taskList.forEach( emp -> parseTaskGroup( (JSONObject) emp ) );

        } catch (IOException | ParseException e) {
            System.out.println("No saved task found.");
        }

        return tasks;
    }

    private static void parseTaskGroup(JSONObject task)
    {
        JSONObject taskDetails = (JSONObject) task.get("task");

        int id = Integer.parseInt((String) taskDetails.get("id"));

        String taskName = (String) taskDetails.get("taskName");

        String status = (String) taskDetails.get("status");

        String type = (String) taskDetails.get("type");

        if (type.equals("T")) {
            tasks.add(new ToDo(id, taskName, status));
        } else if (type.equals("D")) {
            String endDate = (String) taskDetails.get("endDate");
            tasks.add(new Deadline(id, taskName, status, LocalDate.parse(endDate)));
        } else {
            String startEndDate = (String) taskDetails.get("startEndDate");
            tasks.add(new Event(id, taskName, status, LocalDate.parse(startEndDate)));
        }
    }

    //Solution below adapted from https://howtodoinjava.com/java/library/json-simple-read-write-json-examples/
    @SuppressWarnings("unchecked")
    public static void saveData(String id, String taskName, String status, String type, LocalDate dueDate) {

        JSONObject taskDetails = new JSONObject();
        taskDetails.put("id", id);
        taskDetails.put("taskName", taskName);
        taskDetails.put("status", status);
        taskDetails.put("type", type);

        //convert datetime to string
        if (dueDate != null) {
            String dueDateStr = dueDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            if (type.equals("D")) {
                taskDetails.put("endDate", dueDateStr);
            } else if (type.equals("E")) {
                taskDetails.put("startEndDate", dueDateStr);
            }
        }

        JSONObject taskGroup = new JSONObject();
        taskGroup.put("task", taskDetails);

        //Add each task to list
        taskList.add(taskGroup);

        //Write JSON file
        try (FileWriter file = new FileWriter("./data/tasks.json")) {

            file.write(taskList.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}