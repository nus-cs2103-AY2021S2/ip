package bearbear.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import bearbear.tasks.Deadline;
import bearbear.tasks.Event;
import bearbear.tasks.TaskList;
import bearbear.tasks.Todo;

public class FileReading {

    /**
     * Retrieves and loads list of task stored into task list.
     * @param file The file that task information retrieved from.
     * @return Task retrieved task list.
     * @throws IOException If error occurs while reading task data from file.
     */
    public static TaskList loadTask(File file) throws IOException {
        FileReader fr = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fr);
        TaskList taskList = new TaskList();
        String input = bufferedReader.readLine();

        while (input != null) {
            String[] starr = input.split("\\s\\|\\s");
            if (starr.length == 3) {
                Todo curr = new Todo(starr[2]);
                if (starr[1].equals("1")) {
                    curr.markAsDone();
                }
                taskList.addItem(curr);
            } else {
                if (starr[0].equals("D")) {
                    Deadline curr = new Deadline(starr[2], starr[3]);
                    if (starr[1].equals("1")) {
                        curr.markAsDone();
                    }
                    taskList.addItem(curr);
                } else if (starr[0].equals("E")) {
                    Event curr = new Event(starr[2], starr[3]);
                    if (starr[1].equals("1")) {
                        curr.markAsDone();
                    }
                    taskList.addItem(curr);
                }
            }
            input = bufferedReader.readLine();
        }
        return taskList;
    }
}
