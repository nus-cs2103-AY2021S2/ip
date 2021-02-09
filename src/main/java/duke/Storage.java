package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles saving and loading the TaskList to/from the hard disk via a save data text file.
 */
public class Storage {
    private String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Loads list of Tasks from the text file specified in the Storage object's filepath.
     *
     * @return ArrayList of the Tasks stored in the save data file.
     * @throws DukeException If an Exception occurs as a result of the stored save data being malformed.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            File savefile = new File(filepath);
            Scanner saveReader = new Scanner(savefile);

            while (saveReader.hasNextLine()) {
                String saveData = saveReader.nextLine();
                tasks.add(TaskParser.parseTask(saveData));
            }

            saveReader.close();
        } catch (FileNotFoundException e) {
            // create file if it doesn't exist
            File saveFile = new File(filepath);
            try {
                saveFile.createNewFile();
            } catch (Exception err) {
                String errorMsg = "There was an error creating the save file!\n"
                        + "You can still use this todo list, but your data won't be saved.";
                throw new DukeException(errorMsg);
            }
        } catch (DukeException e) {
            tasks.clear();

            String errorMsg = "Looks like the save data's been corrupted.\n"
                    + "Please avoid manually editing this file!\n"
                    + "For now, I've cleared the save data.";
            throw new DukeException(errorMsg);

        }
        return tasks;
    }

    /**
     * Saves list of Tasks to the save data file.
     * @param tasks The list of Tasks to be saved to the file.
     * @throws DukeException If an Exception occurs as a result of the stored save data being malformed.
     */
    public void saveTasks(TaskList tasks) throws DukeException {
        try {
            FileWriter saveWriter = new FileWriter(filepath);

            StringBuilder saveStringBuilder = new StringBuilder();
            for (int i = 1; i <= tasks.size(); i++) {
                saveStringBuilder.append(tasks.get(i).getSaveString());
            }

            saveWriter.write(saveStringBuilder.toString());
            saveWriter.close();

        } catch (Exception e) {
            String errorMsg = "Save file not found!\n"
                    + "Please don't manually edit the save file.";
            throw new DukeException(errorMsg);
        }
    }
}