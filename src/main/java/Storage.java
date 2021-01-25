import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String fileDirectory;
    private final String pathDirectory;

    public Storage(String fileDirectory) {
        this.fileDirectory = fileDirectory;
        this.pathDirectory = fileDirectory.replaceFirst("/Duke.txt", "");
    }

    public File fileConfiguration() throws DukeException {
        File dataDirectory = new File(this.pathDirectory);
        File dataFile = new File(this.fileDirectory);

        try {
            if(!(Files.isDirectory(Paths.get(this.pathDirectory)))) {
                // Handles folder does not exist case
                dataDirectory.mkdir();
                dataFile.createNewFile();
            } else if(!dataFile.exists()) {
                // Handles file does not exist
                dataFile.createNewFile();
            }
            return dataFile;
        } catch (IOException ex) {
            throw new DukeException(ExceptionType.INVALID_FILE_CONFIGURATION, "");
        }
    }

    public void saveData(TaskList taskList) throws DukeException {
        System.out.println(taskList.get(0));
        try {
            File dataFile = fileConfiguration();
            FileWriter fileWriter = new FileWriter(dataFile, false);

            for(int index = 0; index < taskList.size(); index++) {
                Task currTask = taskList.get(index);
                fileWriter.write(currTask.formatTask() + System.lineSeparator());
            }
            fileWriter.close();
        } catch (IOException ex) {
            throw new DukeException(ExceptionType.SAVING_ERROR, "");
        }
    }

    public ArrayList<Task> loadData() throws DukeException {
        ArrayList<Task> taskList = new ArrayList<>();

        try {
            File dataFile = fileConfiguration();
            Scanner sc = new Scanner(dataFile);
            while (sc.hasNext()) {
                String[] taskDetails = sc.nextLine().split("[|]");
                String taskType = taskDetails[0];
                Task newTask = null;

                switch (taskType) {
                case "T":
                    newTask = new ToDo(taskDetails[2]);
                    break;
                case "E":
                    newTask = new Event(taskDetails[2], LocalDate.parse(taskDetails[3]));
                    break;
                case "D":
                    newTask = new Deadline(taskDetails[2], LocalDate.parse(taskDetails[3]));
                    break;
                default:
                    break;
                }
                if (taskType.equals("T") || taskType.equals("E") || taskType.equals("D")) {
                    if (taskDetails[1].equals("1")) {
                        newTask.markAsDone();
                    }
                    taskList.add(newTask);
                }
            }
            return taskList;
        } catch (IOException ex) {
            throw new DukeException(ExceptionType.LOADING_ERROR, "");
        }
    }
}
