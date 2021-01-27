import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class TaskData {
    protected String path;
    protected ArrayList<Task> taskList = new ArrayList<>();

    public TaskData(String path) {
        this.path = path;
    }

    public ArrayList<Task> openFile() throws DukeException{
        File file = new File(path);

        if(!file.exists()) {
            try {
                file.getParentFile().mkdir();
                file.createNewFile();
            } catch (IOException e) {
                throw new DukeException("File cannot be created.");
            }
        }

        try {
            Scanner sc = new Scanner(file);
            while(sc.hasNext()) {
                String txtLine = sc.nextLine();
                Task task = processContent(txtLine);
                taskList.add(task);
            }
            return taskList;
        } catch(FileNotFoundException e){
            throw new DukeException("File cannot be found.");
        }
    }

    //Process txt into Task
    public Task processContent (String txtLine) throws DukeException{
        String[] content = txtLine.split(" \\| ");
        String taskType = content[0];
        int taskStatus = Integer.parseInt(content[1]);
        String taskDescription = content[2];

        switch(taskType){
            case "D":
                return new Deadline(taskDescription, taskStatus, LocalDateTime.parse(content[3], DateTimeFormatter.ofPattern("HHmm, MMM dd yyyy")));
            case "E":
                return new Event(taskDescription, taskStatus, LocalDateTime.parse(content[3], DateTimeFormatter.ofPattern("HHmm, MMM dd yyyy")));
            case "T":
                return new Todo(taskDescription, taskStatus);
            default:
                throw new DukeException(taskType + " is an invalid text type. Please modify the file accordingly.");
        }
    }

    //Update taskList into txt
    public void updateFile(){
        try{
            FileWriter fw = new FileWriter(path);
            for(Task t : taskList) {
                fw.write(t.toTxt());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("File cannot be found");
        }
    }
}
