import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FileInput {
    public FileInput() {

    }

    public void loadFile(String directoryPath, String path) {
        loadDirectory(directoryPath);
        File file = new File(path);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadDirectory(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
    }

    public List<Task> initialiseStorage(File file) {
        List<Task> list = new ArrayList<>();
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while (true) {
                String value = bufferedReader.readLine();
                if (value == null) {
                    break;
                }
                String[] values = value.split(" \\| ");
                String type = values[0];
                String done = values[1];
                String description = values[2];
                String date = null;
                if (values.length == 4) {
                    date = values[3];
                } else {
                    date = null;
                }
                Task task = setUpTask(type, done, description, date);
                list.add(task);

            }
            bufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;

    }

    private Task setUpTask(String type, String done, String description, String dateString) {
        LocalDate date = LocalDate.of(0,1,1);
        LocalTime time = LocalTime.of(0,0,0);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("H:mm");
        if (dateString != null) {
            String[] dateTime = dateString.split(" ");
            date = LocalDate.parse(dateTime[0], dateFormatter);
            time = LocalTime.parse(dateTime[1], timeFormatter);
        }
        Task task = new Task();
        switch(type) {
            case "T":
                task = new Todo(description);
                break;
            case "E":
                task = new Event(description, date, time);
                break;
            case "D":
                task = new Deadline(description, date, time);
                break;
        }

        if (done == "1") {
            task.setDone(true);
        } else {
            task.setDone(false);
        }

        return task;
    }

}
