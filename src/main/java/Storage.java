import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private String filePath;


    Storage(String filePath) throws IOException {
        String parentPath = filePath.split("tasks.txt")[0];
        if(!Files.exists(Paths.get(parentPath))){
            Files.createDirectories(Paths.get(parentPath));
        }
        if(!Files.exists(Paths.get(filePath))){
            Files.createFile(Paths.get(filePath));
        }
        this.filePath = filePath;
    }

    public ArrayList<Task> getTaskList() throws IOException{
        ArrayList<Task> taskList = new ArrayList<>();
        List<String> fileLines = Files.readAllLines(Paths.get(filePath));
        for (int i = 0; i < fileLines.size(); i++) {
            String line = fileLines.get(i);
            String[] userTask = line.split(" \\| ");
            String eventType = userTask[0];
            Task taskInList;
            try {
                switch (eventType){
                    case("[T]"):
                        taskInList = new ToDo(userTask[2]);
                        taskList.add(taskInList);
                        break;
                    case("[E]"):
                        String eventDuration = Parser.parseDate(userTask[3].split("at: ")[1]);
                        String eventDetail = userTask[2];
                        taskInList = new Event(eventDuration, eventDetail);
                        taskList.add(taskInList);
                        break;
                    case("[D]"):
                        String deadline = Parser.parseDate(userTask[3].split("by: ")[1]);
                        String deadlineDetail = userTask[2];
                        taskInList = new Deadline(deadline, deadlineDetail);
                        taskList.add(taskInList);
                        break;
                }
            }
            catch (Exception e){
                System.err.println(e.getMessage());
            }
        }
        return taskList;
    }
    public void writeData(List<Task> data) {
        String stringOfData = "";
        for(int i =0; i<data.size(); i++){
            if(i == data.size()-1){
                stringOfData += data.get(i).toString();
            }
            else{
                stringOfData += data.get(i).toString() + "\n";
            }
        }
        try {
            Files.writeString(Paths.get(this.filePath), stringOfData);
        } catch (IOException e) {
            System.err.println(e);
        }
    }

}