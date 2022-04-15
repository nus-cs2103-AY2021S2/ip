package rick;

import rick.exceptions.RickException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private String filePath;
    private File file;
    private Ui ui;

    public Storage(String filePath, Ui ui) throws IOException {
        this.filePath = filePath;
        this.ui = ui;
        file = new File(this.filePath);
        if(!file.exists()) {
            File parentDirectory = file.getParentFile();
            if(!parentDirectory.exists()) {
                parentDirectory.mkdir();
            }
            file.createNewFile();
        }
    }

    public void save(TaskList taskList) throws IOException {
        StringBuilder data = new StringBuilder();
        FileWriter writer = new FileWriter(filePath);
        for(int i = 0; i < taskList.getSize(); i++) {
            data.append(taskList.getTask(i));
            if(i < taskList.getSize() - 1) {
                data.append("\n");
            }
        }
        writer.write(data.toString());
        writer.close();
    }

    public List<Task> load() throws IOException {
        List<Task> data = new ArrayList<>();
        Scanner fileReader = new Scanner(file);
        while(fileReader.hasNextLine()) {
            String[] taskInfo = fileReader.nextLine().split(" \\| ");
            Task currentTask;
            switch(taskInfo[0]) {
                case "T":
                    currentTask = new Todo(taskInfo[2], taskInfo[1].equals("\u2713"));
                    data.add(currentTask);
                    break;
                case "D":
                    String deadlineDate = taskInfo[3].replaceAll(" ", "-");
                    currentTask = new Deadline(taskInfo[2], LocalDate.parse(deadlineDate, DateTimeFormatter.ofPattern("dd-MMM-yyyy")), taskInfo[1].equals("\u2713"));
                    data.add(currentTask);
                    break;
                case "E":
                    String eventDate = taskInfo[3].replaceAll(" ", "-");
                    currentTask = new Event(taskInfo[2], LocalDate.parse(eventDate, DateTimeFormatter.ofPattern("dd-MMM-yyyy")), taskInfo[1].equals("\u2713"));
                    data.add(currentTask);
                    break;
                default:
                    assert false : taskInfo[0];
                    break;
            }
        }
        return data;
    }
}
