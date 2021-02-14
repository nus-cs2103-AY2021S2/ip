package mainclasses;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private String filePath;

    public Storage() {

    }

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads a file that is specified in String parameter. If the directory of the file
     * does not exist, the directory will be created. If the file itself does not exist
     * the file will be created
     * @param path an absolute pathway to where the file is located
     */
    public void loadFile(String path) {
        int indexOfLastSlash = path.lastIndexOf('/');
        String pathDirectory = path.substring(0, indexOfLastSlash);
        loadDirectory(pathDirectory);
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

    /**
     * Reads a file provided in the parameter and returns a list of Tasks based on the input on the file
     * @param file the file to be read from
     * @return a List of Task that is currently stored in the file
     */
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
        LocalDate date = LocalDate.of(0, 1, 1);
        LocalTime time = LocalTime.of(0, 0, 0);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("H:mm");
        if (dateString != null) {
            String[] dateTime = dateString.split(" ");
            date = LocalDate.parse(dateTime[0], dateFormatter);
            time = LocalTime.parse(dateTime[1], timeFormatter);
        }

        Task task = new Task();
        switch (type) {
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

        if (done.equals("1")) {
            task.setDone(true);
        } else {
            assert(done.equals("0"));
            task.setDone(false);
        }

        return task;
    }

    /**
     * updates the hard drive with the updated list of Task whenever the user exits the program
     * @param fileName the path directory of the file
     * @param taskList the updated List of task
     */
    public void updateHardDrive(String fileName, TaskList taskList) {
        try {
            Files.delete(Paths.get(fileName));
            loadFile(fileName);
            FileWriter fileWriter = new FileWriter(fileName);
            for (int i = 0; i < taskList.getNewStorage().size(); i++) {
                fileWriter.write(printInHardDrive(taskList.getNewStorage().get(i)));
            }
            fileWriter.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private String printInHardDrive(Task task) {
        assert(task != null);
        if (task.getDate() != null || task.getTime() != null) {
            if (task.getDone()) {
                return task.getType() + " | 1 | " + task.getOnlyDescription() + " | " + task.getDate() + " " + task.getTime() + "\n";
            } else {
                return task.getType() + " | 0 | " + task.getOnlyDescription() + " | " + task.getDate() + " " + task.getTime() + "\n";
            }
        } else {
            if (task.getDone()) {
                return task.getType() + " | 1 | " + task.getDescription() + "\n";
            } else {
                return task.getType() + " | 0 | " + task.getDescription() + "\n";
            }
        }
    }

    /**
     * Returns the file path stored in Storage instance
     * @return the file path stored in Storage
     */
    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
