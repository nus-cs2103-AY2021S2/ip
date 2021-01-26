import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            File savefile = new File(filepath);
            Scanner saveReader = new Scanner(savefile);

            while (saveReader.hasNextLine()) {
                String savedata = saveReader.nextLine();
                tasks.add(TaskParser.parseTask(savedata));
            }

            saveReader.close();
        } catch (FileNotFoundException e) {
            tasks.clear();

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
            String errorMsg = "Save file not found!\n" +
                    "Please don't manually edit the save file.";
            throw new DukeException(errorMsg);
        }
    }
}