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

    public void exportTasks() throws IOException {
        String output = "";
        for(Task t : TaskList.taskList) {
            output += (t.toString() + "\n");
        } try {
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

    public void importTasks() throws IOException, ParseException {
        File data = new File("./src/main/DATA/duke.txt");
        if(data.exists()) {
            BufferedReader input = new BufferedReader(new FileReader(this.filePath));
            SimpleDateFormat dateFormat = new SimpleDateFormat("'by' EEE MMM dd HH:mm:ss z yyyy");
            SimpleDateFormat dateFormat2 = new SimpleDateFormat("'from' EEE MMM dd HH:mm:ss z yyyy ");
            SimpleDateFormat dateFormat3 = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
            String content = input.readLine();
            while (content != null) {
                String[] fileInput = content.split(" ", 2);
                if ("[T]".equals(fileInput[0].substring(0, 3))) {
                    ToDo newToDo = new ToDo(fileInput[1]);
                    if(fileInput[0].length() > 5) {
                        newToDo.completeTask();
                    }
                    TaskList.taskList.add(newToDo);
                } else if ("[D]".equals(fileInput[0].substring(0, 3))) {
                    String[] details = fileInput[1].split("/", 2);
                    Date by = dateFormat.parse(details[1]);
                    Deadline newDeadline = new Deadline(details[0], by);
                    if(fileInput[0].length() > 5) {
                        newDeadline.completeTask();
                    }
                    TaskList.taskList.add(newDeadline);
                } else if (fileInput[0].length() > 5) {
                    String[] details = fileInput[1].split("/", 2);
                    String[] timings = details[1].split("to ", 2);
                    Date start = dateFormat2.parse(timings[0]);
                    Date end = dateFormat3.parse(timings[1]);
                    Event newEvent = new Event(details[0], start, end);
                    if (fileInput[0].length() > 5) {
                        newEvent.completeTask();
                    }
                    TaskList.taskList.add(newEvent);
                }
                content = input.readLine();
            }
            input.close();
        }
    }
}
