import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {
    private String filePath;

    public FileManager(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> loadFile() throws DukeException {
        File tasksListFile = new File(filePath);
        ArrayList<Task> tasksList = new ArrayList<>();

        try {
            Scanner sc = new Scanner(tasksListFile);

            while (sc.hasNextLine()) {
                String savedTask = sc.nextLine();
                String[] savedTaskDetails = savedTask.split(" | ");
                String savedTaskType = savedTaskDetails[0];
                String savedTaskDescription = savedTaskDetails[2];
                boolean isTaskDone = (Integer.parseInt(savedTaskDetails[1]) == 1);
                Task taskToLoad;

                switch (savedTaskType) {
                case "T":
                    taskToLoad = new ToDo(savedTaskDescription);
                    break;
                case "D":
                    taskToLoad = new Deadline(savedTaskDescription, savedTaskDetails[3]);
                    break;
                case "E":
                    taskToLoad = new Event(savedTaskDescription, savedTaskDetails[3]);
                    break;
                default:
                    throw new DukeException("☹ OOPS!!! There are issues in loading the saved file of the tasks list.");
                }

                if (isTaskDone) {
                    taskToLoad.markAsDone();
                }

                tasksList.add(taskToLoad);
            }

            return tasksList;
        } catch (IOException e){
            throw new DukeException("☹ OOPS!!! There is no saved file of the tasks list in this directory.");
        }
    }

    public void saveFile(ArrayList<Task> tasksList) throws DukeException {
        try {
            File tasksListFile = new File(filePath);
            FileWriter fw = new FileWriter(tasksListFile);
            BufferedWriter bw = new BufferedWriter(fw);

            for (Task task : tasksList) {
                bw.write(task.saveFormat());
                bw.newLine();
            }

            bw.close();
            fw.close();
        } catch (IOException e) {
            throw new DukeException("☹ OOPS!!! Tasks list cannot be saved.");
        }
    }
}
