import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Scanner;
import java.time.LocalDate;

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
            String[] spl = s.nextLine().split("@@@", 5);
            Task to_add;
            switch(spl[0]) {
            case "T":
                to_add = new Todo(spl[2]);
                break;
            case "D":
                to_add = new Deadline(spl[2], LocalDate.parse(spl[3]), LocalTime.parse(spl[4]));
                break;
            default: // Default is the case "E"
                to_add = new Event(spl[2], LocalDate.parse(spl[3]), LocalTime.parse(spl[4]));
            }

            if (spl[1].equals("1")) {
                to_add.finished();
            }
            TaskList.getStorage().add(to_add);
        }
        return count;
    }

    /**
     * When the user exits a program the current task list is saved to a file.
     */
    static void uploadToHardDrive() {
        try {
            FileWriter fw = new FileWriter("./data/tasks.txt");
            String between = "@@@";
            for (Task t : TaskList.getStorage()) {
                String zero = t.getInitial();
                String one = t.getDone();
                String two = t.getDescription();
                if (zero.equals("T")) {
                    fw.write(zero + between + one + between + two + "\n");
                } else {
                    LocalDate three = t.getDate();
                    LocalTime four = t.getTime();
                    fw.write(zero + between + one + between + two + between + three + between + four + "\n");
                }
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("ERROR! D: There has been some error in the hard drive.");
        }
    }
}
