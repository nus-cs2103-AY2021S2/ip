package main.java.duke.maincomponents;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;

import main.java.duke.exceptions.DukeException;
import main.java.duke.exceptions.TaskException;
import main.java.duke.task.Deadline;
import main.java.duke.task.Event;
import main.java.duke.task.Task;
import main.java.duke.task.ToDo;


public class Storage {
    private String pathOfDataFile;

    public Storage(String filePath, String folderName) throws DukeException {
        pathOfDataFile = filePath;
        try {
            File folder = new File(folderName);
            if (folder.mkdir()) {
                //System.out.println("Folder created: " + folder.getName());
            } else {
                //System.out.println("Folder already exists.");
            }
            File dataFile = new File(filePath);
            if (dataFile.createNewFile()) {
                //System.out.println("File created: " + dataFile.getName());
            } else {
                //System.out.println("File already exists.");
            }
        } catch (IOException e) {
            throw new DukeException("Error occured during file or folder creation");
        }
    }

    public ArrayList<Task> load() throws DukeException {
        try {
            File dukeData = new File(pathOfDataFile);
            Scanner dataReader = new Scanner(dukeData);
            int tempNumberOfTasks = 0;
            ArrayList<Task> tempArray = new ArrayList<>();

            while (dataReader.hasNextLine()) {
                String data = dataReader.nextLine();
                String[] arr = data.split("\\|");

                String taskSymbol = arr[0];
                int doneInt = Integer.valueOf(arr[1]);
                String description = arr[2];

                if (taskSymbol.equals("T")) {
                    tempArray.add(new ToDo(description, doneInt));
                    tempNumberOfTasks++;
                } else if (taskSymbol.equals("D")) {
                    try {
                        tempArray.add(new Deadline(description, arr[3], doneInt));
                    } catch (TaskException e) {
                        throw new DukeException("Data Corrupted, failed to restore data.");
                    }
                    tempNumberOfTasks++;
                } else if (taskSymbol.equals("E")) {
                    tempArray.add(new Event(description, arr[3], doneInt));
                    tempNumberOfTasks++;
                }
            }
            dataReader.close();

            return tempArray;
        } catch (FileNotFoundException e) {
            throw new DukeException(pathOfDataFile + " not found, failed to restore data.");
        }
    }

    public void saveToFile(ArrayList<Task> taskArray) throws DukeException {
        try {
            Writer fileWriter = new FileWriter("data/dukedata.txt", false);
            for (int i = 0; i < taskArray.size(); i++) {
                String line = taskArray.get(i).toSaveFormat();
                fileWriter.write(line + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeException("Error could not save task list.");
        }
    }
}
