package com.tjtanjin.steve.storage;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.tjtanjin.steve.tasks.Deadline;
import com.tjtanjin.steve.tasks.Event;
import com.tjtanjin.steve.tasks.Task;
import com.tjtanjin.steve.tasks.ToDo;
import com.tjtanjin.steve.ui.UiHandler;

/**
 * StorageHandler manages the saving/loading of tasks from hard disk and is managed through the
 * TaskHandler.
 */
public class StorageHandler {

    private ArrayList<Task> tasks = new ArrayList<>();
    private final String PATH;
    private JSONArray taskList = new JSONArray();

    /**
     * Constructor for StorageHandler.
     *
     * @param path path to look for or create storage file
     */
    public StorageHandler(String path) {
        this.PATH = path;
    }

    //solution below adapted from https://howtodoinjava.com/java/library/json-simple-read-write-json-examples/
    /**
     * Loads saved tasks from hard disk.
     *
     * @return array list of tasks
     */
    @SuppressWarnings("unchecked")
    public ArrayList<Task> loadTasks() {

        File tasksFile = new File(this.PATH);
        try {
            tasksFile.getParentFile().mkdirs();
            tasksFile.createNewFile();
        } catch (IOException e) {
            //special case of directly accessing controller due to critical error
            UiHandler.getFxmlLoader().<UiHandler>getController().showResponse("Terminated: Steve has no permission"
                    + "to create storage file. Please check and restart the application.");
        }

        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(this.PATH)) {
            //read JSON file
            Object obj = jsonParser.parse(reader);

            taskList = (JSONArray) obj;

            //load each task
            taskList.forEach(emp -> parseTaskGroup((JSONObject) emp));
        } catch (IOException | ParseException e) {
            //do nothing, empty file is ok just means nothing to load
        }
        return tasks;
    }

    /**
     * Retrieves individual task information and repopulate them into taskList.
     *
     * @param task task to retrieve information for
     */
    private void parseTaskGroup(JSONObject task) {
        JSONObject taskDetails = (JSONObject) task.get("task");

        String taskName = (String) taskDetails.get("taskName");

        String status = (String) taskDetails.get("status");

        String type = (String) taskDetails.get("type");

        assert taskName != null;
        assert status != null;
        assert type != null;

        if (type.equals("TODO")) {
            tasks.add(new ToDo(taskName, status));
        } else if (type.equals("DEADLINE")) {
            String endDate = (String) taskDetails.get("endDate");
            LocalDate[] taskDates = new LocalDate[1];
            taskDates[0] = LocalDate.parse(endDate);
            tasks.add(new Deadline(taskName, status, taskDates));
        } else {
            String startDate = (String) taskDetails.get("startDate");
            String endDate = (String) taskDetails.get("endDate");
            LocalDate[] taskDates = new LocalDate[2];
            taskDates[0] = LocalDate.parse(startDate);
            taskDates[1] = LocalDate.parse(endDate);
            tasks.add(new Event(taskName, status, taskDates));
        }
    }

    //solution below adapted from https://howtodoinjava.com/java/library/json-simple-read-write-json-examples/
    /**
     * Saves task into hard disk.
     *
     * @param index index of task being saved
     * @param saveType type of save operation (new task, modify existing task or deleting task)
     * @param taskName name of task
     * @param status status of task (incomplete or complete)
     * @param type type of task (todo, deadline or event)
     * @param taskDates array of dates (defaults to first element for deadline end date)
     * @return true if saving task was successful, false otherwise
     */
    @SuppressWarnings("unchecked")
    public boolean saveTask(int index, String saveType, String taskName,
            String status, String type, LocalDate[] taskDates) {

        JSONObject taskDetails = parseTaskToJson(taskName, status, type, taskDates);

        JSONObject taskGroup = new JSONObject();
        taskGroup.put("task", taskDetails);

        //add or update or delete task
        if (saveType.equals("NEW")) {
            taskList.add(taskGroup);
        } else if (saveType.equals("DONE")) {
            taskList.set(index, taskGroup);
        } else {
            taskList.remove(index);
        }

        return writeToFile();
    }

    /**
     * Reverts task list by one modification.
     */
    public boolean revertTask(ArrayList<Task> revertedTasks) {
        System.out.println(revertedTasks.size());
        this.tasks = revertedTasks;
        JSONArray taskList = new JSONArray();

        this.tasks.forEach(task -> {
            String taskName = task.getTaskName();
            String status = task.getStatus();
            String type = task.getType();
            LocalDate[] taskDates = task.getDates();
            JSONObject taskDetails = parseTaskToJson(taskName, status, type, taskDates);
            JSONObject taskGroup = new JSONObject();
            taskGroup.put("task", taskDetails);
            taskList.add(taskGroup);
        });
        this.taskList = taskList;
        return writeToFile();
    }

    /**
     * Groups task information into a json object.
     *
     * @param taskName name of task
     * @param status status of task
     * @param type type of task
     * @param taskDates due dates of task
     * @return json object containing task information
     */
    public JSONObject parseTaskToJson(String taskName, String status, String type, LocalDate[] taskDates) {
        JSONObject taskDetails = new JSONObject();
        taskDetails.put("taskName", taskName);
        taskDetails.put("status", status);
        taskDetails.put("type", type);

        //convert datetime to string
        if (taskDates != null) {
            if (type.equals("DEADLINE")) {
                String dueDateStr = taskDates[0].format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                taskDetails.put("endDate", dueDateStr);
            } else if (type.equals("EVENT")) {
                String startDateStr = taskDates[0].format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                String endDateStr = taskDates[1].format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                taskDetails.put("startDate", startDateStr);
                taskDetails.put("endDate", endDateStr);
            }
        }
        return taskDetails;
    }

    /**
     * Writes task information into json file.
     *
     * @return true or false to indicate if file was written to successfully
     */
    public boolean writeToFile() {
        try (FileWriter file = new FileWriter(this.PATH)) {

            file.write(taskList.toJSONString());
            file.flush();
            return true;

        } catch (IOException e) {
            return false;
        }
    }
}
