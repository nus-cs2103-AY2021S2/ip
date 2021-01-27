package duke.storage;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.TaskList;
import duke.tasks.Todo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileReading {

    /**
     * Retrieves and loads list of task stored into task list.
     * @param file the file that task information retrieved from
     * @return task retrieved task list
     * @throws IOException
     */
    public static TaskList loadTask(File file) throws IOException {
        FileReader fr = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fr);
        TaskList taskList = new TaskList();
        String input = bufferedReader.readLine();
        while(input != null) {
            String[] starr = input.split("\\s\\|\\s");
            if(starr.length == 3) {
                Todo curr = new Todo(starr[2]);
                if(starr[1].equals("1")) {
                    curr.markAsDone();
                }
                taskList.addItem(curr);
            } else {
                if(starr[0].equals("D")) {
                    Deadline curr = new Deadline(starr[2], starr[3]);
                    if(starr[1].equals("1")) {
                        curr.markAsDone();
                    }
                    taskList.addItem(curr);
                } else if(starr[0].equals("E")) {
                    Event curr = new Event(starr[2], starr[3]);
                    if(starr[1].equals("1")) {
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
