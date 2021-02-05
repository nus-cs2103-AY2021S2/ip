import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Storage {
    String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }
    public void save(List<Task> tasks) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        for(Task task : tasks) {
            writer.write(convertToFileFormat(task)+'\n');
        }
        writer.close();
    }
    private Task convertToTaskFormat(String line) throws EmptyArgumentException, BadDateException {
        String[] arr = line.split(Pattern.quote(" | "));
        Task task;
        if(arr[0].charAt(0) == 'T') task = new ToDos(arr[2].trim());
        else if(arr[0].charAt(0) == 'E') task = new Event(arr[2].trim());
        else task = new Deadline(arr[2]);
        if(arr[1].trim().charAt(0) == '1') task.isCompleted = true;
        else task.isCompleted = false;
        return task;
    }
    private String convertToFileFormat(Task task) {
        String fileString = "";
        if(task instanceof ToDos) fileString += "T | ";
        else if(task instanceof Deadline) fileString += "D | ";
        else if(task instanceof Event) fileString += "E | ";
        if(task.isCompleted) fileString += "1 | ";
        else fileString += "0 | ";
        fileString += task.description;
        return fileString;
    }
    public List<Task> load() throws IOException, EmptyArgumentException {
        List<Task> tasks = new ArrayList<>();
        try {
            File file = new File(filePath);
            Scanner sc = new Scanner(file);
            while(sc.hasNext()) {
                String line = sc.nextLine();
                Task task = convertToTaskFormat(line);
                tasks.add(task);
            }
        }
        catch(FileNotFoundException | BadDateException e) {
            System.out.println(e.getMessage());
        }
        return tasks;
    }

}
