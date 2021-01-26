
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class ListUpdater {
    public static ArrayList<Task> update(ArrayList<Task> list, String path) throws IOException {
        File file = new File(path);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String task = br.readLine();
        while (task != null) {
            Task taskObject = null;
            String[] taskInfo = task.split(" , ");
            if (taskInfo[0].equals("T")) {
                taskObject = new ToDo(taskInfo[2], "T");
                taskObject.isDone = taskInfo[1].equals("1") ?  true :  false;
            } else if (taskInfo[0].equals("E")) {
                Date date = new Date(taskInfo[3]);
                taskObject = new Event(taskInfo[2], date, taskInfo[4], "E");
                taskObject.isDone = taskInfo[1].equals("1") ?  true :  false;
            } else if (taskInfo[0].equals("D")) {
                Date date = new Date(taskInfo[3]);
                taskObject = new Deadline(taskInfo[2], date, "D");
                taskObject.isDone = taskInfo[1].equals("1") ?  true :  false;
            }
            list.add(taskObject);
            task = br.readLine();
        }
        return list;
    }
}
