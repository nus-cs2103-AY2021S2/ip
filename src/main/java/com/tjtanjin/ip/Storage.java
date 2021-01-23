package com.tjtanjin.ip;

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

/**
 * The Storage class handles the saving/loading of tasks from hard disk.
 */
public class Storage {

    public static ArrayList<Task> tasks = new ArrayList<>();
    public static JSONArray taskList = new JSONArray();

    //solution below adapted from https://howtodoinjava.com/java/library/json-simple-read-write-json-examples/
    /**
     * Loads saved tasks from hard disk.
     */
    @SuppressWarnings("unchecked")
    public static ArrayList<Task> loadTasks() {

        File tasksFile = new File("./data/tasks.json");
        if (!tasksFile.exists()) {
            try {
                if (!tasksFile.createNewFile()) {
                    throw new DukeException("Unable to generate task file.");
                }
            } catch (IOException | DukeException e) {
                System.out.println("IO Operation failed.");
            }
        }

        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("./data/tasks.json"))
        {
            //read JSON file
            Object obj = jsonParser.parse(reader);

            taskList = (JSONArray) obj;

            //load each task
            taskList.forEach( emp -> parseTaskGroup( (JSONObject) emp ) );

        } catch (IOException | ParseException e) {
            System.out.println("No saved task found.");
        }

        return tasks;
    }

    /**
     * Retrieve individual task information and repopulate them into taskList.
     * @param task task to retrieve information for
     */
    private static void parseTaskGroup(JSONObject task)
    {
        JSONObject taskDetails = (JSONObject) task.get("task");

        String taskName = (String) taskDetails.get("taskName");

        String status = (String) taskDetails.get("status");

        String type = (String) taskDetails.get("type");

        if (type.equals("TODO")) {
            tasks.add(new ToDo(taskName, status));
        } else if (type.equals("DEADLINE")) {
            String endDate = (String) taskDetails.get("endDate");
            tasks.add(new Deadline(taskName, status, LocalDate.parse(endDate)));
        } else {
            String startEndDate = (String) taskDetails.get("startEndDate");
            tasks.add(new Event(taskName, status, LocalDate.parse(startEndDate)));
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
     * @param dueDate due date for task (null for todo)
     */
    @SuppressWarnings("unchecked")
    public static void saveTask(int index, String saveType, String taskName, String status, String type, LocalDate dueDate) {

        JSONObject taskDetails = new JSONObject();
        taskDetails.put("taskName", taskName);
        taskDetails.put("status", status);
        taskDetails.put("type", type);

        //convert datetime to string
        if (dueDate != null) {
            String dueDateStr = dueDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            if (type.equals("DEADLINE")) {
                taskDetails.put("endDate", dueDateStr);
            } else if (type.equals("EVENT")) {
                taskDetails.put("startEndDate", dueDateStr);
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
        try (FileWriter file = new FileWriter("./data/tasks.json")) {

            file.write(taskList.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}