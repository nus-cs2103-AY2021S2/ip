package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DataStorage {
    private static final String filePath = "./src/main/java/duke/data/data.txt";

    public static File getFile() throws IOException, DukeException {

        File file = new File(filePath);
        if(!file.exists()){
            try {
                file.createNewFile();
            }catch (Exception e){
                e.printStackTrace();
                throw new DukeException("Error creating file");
            }
        }
        return file;
    }

    public static void save(ArrayList<Task> taskAL) throws DukeException {
        try {
            File file = getFile();
            FileWriter writer = new FileWriter(file);
            for (int i = 0; i < taskAL.size(); i++) {
                writer.write(taskAL.get(i).changeFormat() + '\n');
                writer.flush();
            }
            writer.close();
        }catch (Exception e){
            throw new DukeException("error writing into file");
        }

    }

    public static ArrayList<Task> loadFile() throws DukeException, IOException {
        File file = getFile();
        Task t = new Task();
        ArrayList<Task> taskAL = new ArrayList<>();
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String s = sc.nextLine();
                taskAL.add(t.changeToTaskFormat(s));
            }
            sc.close();
        } catch (FileNotFoundException e) {
            throw new DukeException("Error finding file");
        }
        return taskAL;

    }

}
