import java.io.File;
import java.io.FileNotFoundException;

import java.io.FileWriter;
import java.io.IOException;

import java.util.Arrays;
import java.util.Scanner;

public class Storage {
    private final String DATA_FOLDER_PATH = "data";
    private final String DUKE_FILE_PATH = "data/duke.txt";
    private TaskList tasks;

    public Storage(TaskList tasks) {
        this.tasks = tasks;
    }

    public void loadData() throws FileNotFoundException {
        File dataFolder = new File(DATA_FOLDER_PATH);
        if (!dataFolder.exists()) {
            dataFolder.mkdir();
        }
        File dukeFile = new File(DUKE_FILE_PATH);
        if (dukeFile.exists()) {
            Scanner s = new Scanner(dukeFile); // create a Scanner using the File as the source
            while (s.hasNext()) {
                readTaskList(s.nextLine());
            }
        }
    }

    public void readTaskList(String taskData) {
        String[] splits = taskData.split(" \\| ");
        if (splits[0].equals("T")) {
            Todo addedTask = new Todo(Arrays.asList(splits).get(2), Arrays.asList(splits).get(1).equals("1"));
            tasks.addTask(addedTask);
        } else if (splits[0].equals("D")) {
            try {
                Deadline addedTask = new Deadline(Arrays.asList(splits).get(2), Arrays.asList(splits).get(1).equals("1"), Arrays.asList(splits).get(3));
                tasks.addTask(addedTask);
            } catch (DukeException e) {
                System.out.println(e);
            }
        } else if (splits[0].equals("E")) {
            try {
                Event addedTask = new Event(Arrays.asList(splits).get(2), Arrays.asList(splits).get(1).equals("1"), Arrays.asList(splits).get(3));
                tasks.addTask(addedTask);
            } catch (DukeException e) {
                System.out.println(e);
            }
        }
    }

    public void writeNewFile(String userInput) throws IOException {
        FileWriter fw = new FileWriter(DUKE_FILE_PATH);
        fw.write(userInput);
        fw.close();
    }

    public void writeTaskList() {
        String userInput = tasks.joinToTxt();
        try {
            writeNewFile(userInput);
        } catch (IOException e) {
            System.out.println("ERROR");
        }
    }

}
