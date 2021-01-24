import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {

    public static void readTasks(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String info = s.nextLine();
            boolean status = Integer.valueOf(info.substring(0,1)) == 1;
            String type = info.substring(3,4);

            if (type.equals("T")){
                String name = info.substring(5);
                new ToDo(name,status);
            }
            else if(type.equals("D")){
                int endNameIndex = info.indexOf("(");
                int endTimeIndex = info.length() - 1;
                String name = info.substring(5 , endNameIndex - 1);
                String by = info.substring(endNameIndex + 5, endTimeIndex);
                new Deadline(name, by, status);
            }
            else if(type.equals("E")){
                int endNameIndex = info.indexOf("(");
                int endTimeIndex = info.length() - 1;
                String name = info.substring(5 , endNameIndex - 1);
                String at = info.substring(endNameIndex + 5, endTimeIndex);
                new Event(name, at, status);
            }

        }
    }

    public static void saveTasks(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath, false); // create a FileWriter in append mode
        StringBuilder builder = new StringBuilder();
        if (Task.getNumOfTasks() == 0) {
            String textToAppend = builder.toString();
            fw.write(textToAppend);
            fw.close();
        }
        else{
            for (Task task:Task.getTasks()){
                int status = task.getStatus() ? 1 : 0;
                builder.append(status);
                builder.append("|");
                String taskName = task.getTaskName();
                builder.append(taskName);
                builder.append("\n");
            }
            String textToAppend = builder.toString();
            fw.write(textToAppend);
            fw.close();
        }

    }
}
