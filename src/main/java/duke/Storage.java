package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

//Save tasks to and load tasks from file
public class Storage {

    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;

    }


    public void saveTasks(ArrayList<Task> list) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        String fileContent = "";
        for (Task task: list) {
            int num;
            String description = task.getDescription();
            char type = task.type();
            if (!task.getDoneStatus()) {
                num = 0;
            }
            else {
                num = 1;
            }
            if (type == 'T') {
                fileContent += type + " ; " + num + " ; " + description + "\n";
            }
            else if (type == 'D') {
                Deadline deadline = (Deadline) task;
                fileContent += type + " ; " + num + " ; " + description +  " ; " + deadline.getBy() + "\n";
            }
            else { //type 'E'
                Event event = (Event) task;
                fileContent += type + " ; " + num + " ; " + description +  " ; " + event.getAt() + "\n";
            }
        }
        fw.write(fileContent);
        fw.close();
    }

    public ArrayList<Task> loadTasks() {
        ArrayList<Task> list = new ArrayList<>();
        File savedTasksFile = null;
        try {
            savedTasksFile = new File(this.filePath);
            if (!savedTasksFile.exists()) {
                if (!savedTasksFile.getParentFile().exists()) {
                    File parentDir = savedTasksFile.getParentFile();
                    parentDir.mkdir();
                }
                savedTasksFile.createNewFile();
            }
            Scanner s = new Scanner(savedTasksFile);
            while (s.hasNextLine()) {
                String line = s.nextLine();
                String[] taskArr = line.split(" ; ", 4);
                char type = taskArr[0].charAt(0);
                String num = taskArr[1];
                String description = taskArr[2];
                if (type == 'T') {
                    Todo todo = new Todo(description);
                    if (num.equals("1")) {
                        todo.done();
                    }
                    list.add(todo);
                } else if (type == 'D') {
                    String when = taskArr[3];
                    Deadline deadline = new Deadline(description, when);
                    if (num.equals("1")) {
                        deadline.done();
                    }
                    list.add(deadline);
                } else { //type 'E'
                    String when = taskArr[3];
                    Event event = new Event(description, when);
                    if (num.equals("1")) {
                        event.done();
                    }
                    list.add(event);
                }
            }
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}



