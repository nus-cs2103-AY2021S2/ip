package com.tjtanjin.ip;

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

/**
 * The Storage class handles the saving/loading of tasks from hard disk.
 */
public class StorageHandler {

    private JSONArray taskList = new JSONArray();
    private final ArrayList<Task> tasks = new ArrayList<>();
    private final String path;

    public StorageHandler(String path) {
        this.path = path;
    }

    //solution below adapted from https://howtodoinjava.com/java/library/json-simple-read-write-json-examples/
    /**
     * Loads saved tasks from hard disk.
     */
    @SuppressWarnings("unchecked")
    public ArrayList<Task> loadTasks() {

        File tasksFile = new File(this.path);
        if (!tasksFile.getParentFile().isDirectory()) {
            try {
                if (!tasksFile.getParentFile().mkdirs()) {
                    throw new DukeException();
                }
            } catch (DukeException e) {
                Ui.showError("Unable to create storage directory.");
            }
        }
        if (!tasksFile.exists()) {
            try {
                if (!tasksFile.createNewFile()) {
                    throw new DukeException();
                }
            } catch (IOException | DukeException e) {
                Ui.showError("Unable to create storage file.");
            }
        }

        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(this.path)) {
            //read JSON file
            Object obj = jsonParser.parse(reader);

            taskList = (JSONArray) obj;

            //load each task
            taskList.forEach(emp -> parseTaskGroup((JSONObject) emp));
        } catch (IOException | ParseException e) {
            System.out.println("No saved task found.");
        }
        return tasks;
    }

    /**
     * Retrieve individual task information and repopulate them into taskList.
     * @param task task to retrieve information for
     */
    private void parseTaskGroup(JSONObject task) {
        JSONObject taskDetails = (JSONObject) task.get("task");

        String taskName = (String) taskDetails.get("taskName");

        String status = (String) taskDetails.get("status");

        String type = (String) taskDetails.get("type");

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
     * @param index index of task being saved
     * @param saveType type of save operation (new task, modify existing task or deleting task)
     * @param taskName name of task
     * @param status status of task (incomplete or complete)
     * @param type type of task (todo, deadline or event)
     * @param taskDates array of dates (defaults to first element for deadline end date)
     */
    @SuppressWarnings("unchecked")
    public void saveTask(int index, String saveType, String taskName,
            String status, String type, LocalDate[] taskDates) {

        JSONObject taskDetails = new JSONObject();
        taskDetails.put("taskName", taskName);
        taskDetails.put("status", status);
        taskDetails.put("type", type);

        //convert datetime to string
        if (taskDates[0] != null) {
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

        JSONObject taskGroup = new JSONObject();
        taskGroup.put("task", taskDetails);

        //add each task to list
        if (saveType.equals("NEW")) {
            taskList.add(taskGroup);
        } else if (saveType.equals("DONE")) {
            taskList.set(index, taskGroup);
        } else {
            taskList.remove(index);
        }

        //write JSON file
        try (FileWriter file = new FileWriter(this.path)) {

            file.write(taskList.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
