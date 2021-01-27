package duke.storage;
import duke.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Storage {
    private boolean existFile;
    private String filePath;
    private boolean existDir;
    private String dirPath;


    public Storage(String filePath, String dirPath){
        this.filePath = filePath;
        this.dirPath = dirPath;
    }

    public TaskList readTasks(TaskList taskList) throws FileNotFoundException {
        File f = new File(dirPath);
        if ( ! f.exists()){
            existDir = f.mkdir();
        }
        try{
            f = new File(filePath);
            if ( ! f.exists()){
                existFile = f.createNewFile();
            }
        } catch (IOException e){
            throw new FileNotFoundException();
        }


        Scanner s = new Scanner(f);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        while (s.hasNext()) {
            String info = s.nextLine();
            boolean status = Integer.parseInt(info.substring(0,1)) == 1;
            String type = info.substring(3,4);

            if (type.equals("T")){
                String name = info.substring(5);
                ToDo todo = new ToDo(name,status);
                taskList.addTasks(todo);
            } else if(type.equals("D")){
                int endNameIndex = info.indexOf("(");
                int endTimeIndex = info.length() - 1;
                String name = info.substring(5 , endNameIndex - 1);
                String by = info.substring(endNameIndex + 5, endTimeIndex);
                LocalDateTime byTime = LocalDateTime.parse(by,df);
                Deadline deadline = new Deadline(name, byTime, status);
                taskList.addTasks(deadline);
            } else if(type.equals("E")){
                int endNameIndex = info.indexOf("(");
                int endTimeIndex = info.length() - 1;
                String name = info.substring(5 , endNameIndex - 1);
                String at = info.substring(endNameIndex + 5, endTimeIndex);
                LocalDateTime atTime = LocalDateTime.parse(at,df);
                Event event = new Event(name, atTime, status);
                taskList.addTasks(event);
            }
        }
        return taskList;
    }


    public void saveTasks(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(filePath, false);

        StringBuilder builder = new StringBuilder();
        if (taskList.getNumOfTasks() == 0) {
            String textToAppend = builder.toString();
            fw.write(textToAppend);
            fw.close();
        } else{
            for (Task task:taskList.getTasks()){
                int status = task.getStatus() ? 1 : 0;
                builder.append(status);
                builder.append("|");
                String taskName = task.toString();
                builder.append(taskName);
                builder.append("\n");
            }
            String textToAppend = builder.toString();
            fw.write(textToAppend);
            fw.close();
        }
    }
}
