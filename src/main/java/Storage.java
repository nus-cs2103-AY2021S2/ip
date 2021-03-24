import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

/**
 * Stores the task list so that it can be recovered even after the user exits from the program.
 */
public class Storage {

    /**
     * When the user starts the program the saved task list is recovered from the saved file.
     * @return Returns the number of tasks that is now in the Array List.
     * @throws FileNotFoundException In the case that the file where the task list was saved cannot be found.
     */
    static int uploadFromHardDrive() throws FileNotFoundException {
        int count = 0;
        File f = new File("./data/tasks.txt");
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            count++;
            String[] spl = s.nextLine().split("@@@", 6);
            assert spl.length > 0 : "Impossible input. Error uploading to/from hardDrive.";
            Task toAdd;
            switch(spl[0]) {
            case "T":
                toAdd = new Todo(spl[2]);
                if (spl.length == 4 && !spl[3].equals("")) {
                    toAdd.addTag(spl[3]);
                }
                break;
            case "D":
                toAdd = new Deadline(spl[2], LocalDate.parse(spl[3]), LocalTime.parse(spl[4]));
                break;
            case "E":
                toAdd = new Event(spl[2], LocalDate.parse(spl[3]), LocalTime.parse(spl[4]));
                break;
            default:
                System.out.println("Error in uploading from hardDrive.");
                return -1;
            }

            if (spl[1].equals("1")) {
                toAdd.finished();
            }

            if (spl.length == 6 && !spl[5].equals("")) {
                toAdd.addTag(spl[5]);
            }

            TaskList.getStorage().add(toAdd);
        }
        return count;
    }

    /**
     * When the user exits a program the current task list is saved to a file.
     */
    static void uploadToHardDrive() {
        try {
            File file = new File("./data/tasks.txt");
            assert file.exists() : "Impossible for the file to vanish right after the program finishes.";
            FileWriter fw = new FileWriter(file);
            String between = "@@@";
            for (Task t : TaskList.getStorage()) {
                String initial = t.getInitial();
                String done = t.getDone();
                String description = t.getDescription();
                String tag = "";
                if (t.getIsTagged()) {
                    tag = t.getTag();
                }

                if (initial.equals("T")) {
                    fw.write(initial + between + done + between + description + between + tag + "\n");
                } else {
                    LocalDate date = t.getDate();
                    LocalTime time = t.getTime();
                    fw.write(initial + between + done + between + description
                            + between + date + between + time + between + tag + "\n");
                }
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("ERROR! D: There has been some error in the hard drive.");
        }
    }
}
