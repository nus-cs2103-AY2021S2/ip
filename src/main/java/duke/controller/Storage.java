package duke.controller;

import duke.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

import java.time.LocalDate;

/**
 * A class to handle storing data into an external CSV file.
 */
public class Storage {
    private final String fileName;

    /**
     * Constructs a Storage class with a file name.
     *
     * @param fileName Name of the file.
     */
    public Storage(String fileName){
        this.fileName = fileName;
    }

    /**
     * Saves data into the file.
     *
     * @param taskList List of Tasks.
     * @throws DukeException If an error occurs while saving data.
     */
    public void handleSave(TaskList taskList) throws DukeException {
        try {
            FileWriter csvWriter = new FileWriter(fileName);
            for (int i = 1; i <= taskList.getNumberOfTasks(); i++) {
                csvWriter.append(taskList.getTaskAtIndex(i).parseToCSVRow());
                csvWriter.append("\n");
            }
            csvWriter.flush();
            csvWriter.close();
        } catch (IOException e) {
            throw new DukeException("An error occurred while saving your changes. Please try again later.");
        }
    }

    /**
     * Loads the data into a TaskList object.
     *
     * @return A list of Tasks based on the file.
     * @throws DukeException If an error occurs while loading the data.
     */
    public TaskList handleLoad() throws DukeException {
        try {
            TaskList listOfTasks = new TaskList();
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            while(line != null) {
                String[] details = line.split(",");
                switch (details[0]){
                    case "T":
                        listOfTasks.addTask(new ToDo(details[2], Boolean.parseBoolean(details[1])));
                        break;
                    case "D":
                        LocalDate dateD = LocalDate.parse(details[3]);
                        listOfTasks.addTask(new Deadline(details[2], Boolean.parseBoolean(details[1]), dateD));
                        break;
                    case "E":
                        LocalDate dateE = LocalDate.parse(details[3]);
                        listOfTasks.addTask(new Event(details[2], Boolean.parseBoolean(details[1]), dateE));
                        break;
                }
                line = reader.readLine();
            }
            return listOfTasks;
        } catch (FileNotFoundException e) {
            System.out.println("It seems like you do not have a save file. A new one will be created.");
            makeNewFile();
            return new TaskList();
        } catch (IOException e) {
            throw new DukeException( "An error occurred while loading. Please try again later.");
        }
    }

    /**
     * Makes a new CSV file if there is no file initially.
     *
     * @throws DukeException If an error occurs while making a new file.
     */
    private void makeNewFile() throws DukeException {
        try{
            new File(fileName).createNewFile();
        } catch (IOException e) {
            throw new DukeException("An error occurred while loading. Please try again later.");
        }
    }
}
