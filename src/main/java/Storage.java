import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with saving and loading DukeList from duke.txt
 */
public class Storage {

    private final String filePath;
    private final File file;

    /**
     * Creates file object, creates the directory and file if either cannot be found
     * @param filePath String of the relative path
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.file = new File(filePath);
        if (!file.exists()) {
            File dataFile = new File("data");
            if (dataFile.mkdir()) {
                try {
                    if (file.createNewFile()) {
                        System.out.println("New save file created");
                    }
                } catch (IOException e) {
                    System.out.println("Save file found");
                }
            }
        }
    }

    /**
     * saves contents of DukeList into Duke.txt file
     * @param list DukeList object
     */
    public void save(DukeList list) {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (int i = 0; i < list.getSize(); i++) {
                Task curr = list.get(i);
                if (curr instanceof ToDos) {
                    if (curr.isTaskDone()) {
                        fw.write("T|1|" + curr.getTaskName() + "\n");
                    } else {
                        fw.write("T|0|" + curr.getTaskName() + "\n");
                    }
                } else if (curr instanceof Events) {
                    if (curr.isTaskDone()) {
                        fw.write("E|1|" + curr.getTaskName() + "|" + ((Events) curr).getDuration() + "\n");
                    } else {
                        fw.write("E|0|" + curr.getTaskName() + "|" + ((Events) curr).getDuration() + "\n");
                    }
                } else {
                    if (curr.isTaskDone()) {
                        fw.write("D|1|" + curr.getTaskName() + "|" + ((Deadlines) curr).getBy() + "\n");
                    } else {
                        fw.write("D|0|" + curr.getTaskName() + "|" + ((Deadlines) curr).getBy() + "\n");
                    }
                }
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error occurred when trying to save file");
        }
    }

    /**
     * Returns an ArrayList to be passed as an argument for DukeList constructor
     * @return an ArrayList of Task
     * @throws FileNotFoundException if file duke.txt is not found
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        Scanner s = new Scanner(file);
        ArrayList<Task> list = new ArrayList<>();
        while (s.hasNextLine()) {
            String curr = s.nextLine();
            String[] currArray = curr.split("\\|");

            switch (currArray[0]) {
            case "T": {
                ToDos currTask;
                if (currArray[1].equals("0")) {
                    currTask = new ToDos(currArray[2]);
                } else {
                    currTask = new ToDos(currArray[2], true);
                }
                list.add(currTask);
                break;
            }
            case "D": {
                Deadlines currTask;
                if (currArray[1].equals("0")) {
                    currTask = new Deadlines(currArray[2], LocalDate.parse(currArray[3]));
                } else {
                    currTask = new Deadlines(currArray[2], LocalDate.parse(currArray[3]), true);
                }
                list.add(currTask);
                break;
            }
            case "E": {
                Events currTask;
                if (currArray[1].equals("0")) {
                    currTask = new Events(currArray[2], LocalDate.parse(currArray[3]));
                } else {
                    currTask = new Events(currArray[2], LocalDate.parse(currArray[3]), true);
                }
                list.add(currTask);
                break;
            }
            }
        }
        return list;
    }
}
