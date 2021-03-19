package duke;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Storage {
    public String filePath;
    public UserInterface UI;

    public Storage(String filePath) throws IOException {
        this.filePath = filePath;
        this.UI = new UserInterface();
    }

    /*
     * Writes the current contents of the task list to a file.
     */
    public void exportTasks() throws IOException {
        String output = "";
        for (Task t : TaskList.taskList) {
            output += (t.toString() + "\n");
        }
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(this.filePath));
            bw.write(output);
            bw.close();
        } catch (FileNotFoundException e) {
            File data = new File("./src/main/DATA");
            data.mkdirs();
            File duke = new File(data, "duke.txt");
            duke.createNewFile();
            BufferedWriter bw = new BufferedWriter(new FileWriter(this.filePath));
            bw.write(output);
            bw.close();
        }
    }

    /*
     * Reads tasks from save file into task list.
     */
    public void importTasks() throws IOException, ParseException {
        File data = new File("./src/main/DATA/duke.txt");
        if (data.exists()) {
            BufferedReader input = new BufferedReader(new FileReader(this.filePath));
            String content = input.readLine();
            while (content != null) {
                String[] fileInput = content.split(" ", 2);
                String header = fileInput[0];
                String type = header.substring(0, 3);
                if ("[T]".equals(type)) {
                    String description = fileInput[1];
                    importTodo(header, description);
                } else if ("[D]".equals(type)) {
                    String[] details = fileInput[1].split("/", 2);
                    importDeadline(header, details);
                } else {
                    String[] details = fileInput[1].split("/", 2);
                    importEvent(header, details);
                }
                content = input.readLine();
            }
            input.close();
        }
    }

    /*
     * Processes tasks of type todo from save file
     */
    public void importTodo(String header, String description) {
        ToDo newToDo = new ToDo(description);
        if (header.length() > 5) {
            newToDo.completeTask();
        }
        TaskList.taskList.add(newToDo);
    }

    /*
     * Processes tasks of type deadline from save file
     */
    public void importDeadline(String header, String[] details) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("'by' EEE MMM dd HH:mm:ss z yyyy");
        Date by = dateFormat.parse(details[1]);
        Deadline newDeadline = new Deadline(details[0], by);
        if (header.length() > 5) {
            newDeadline.completeTask();
        }
        TaskList.taskList.add(newDeadline);
    }

    /*
     * Processes tasks of type event from save file
     */
    public void importEvent(String header, String[] details) throws ParseException {
        SimpleDateFormat startDateFormat = new SimpleDateFormat("'from' EEE MMM dd HH:mm:ss z yyyy ");
        SimpleDateFormat endDateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
        String[] timings = details[1].split("to ", 2);
        Date start = startDateFormat.parse(timings[0]);
        Date end = endDateFormat.parse(timings[1]);
        Event newEvent = new Event(details[0], start, end);
        if (header.length() > 5) {
            newEvent.completeTask();
        }
        TaskList.taskList.add(newEvent);
    }
}