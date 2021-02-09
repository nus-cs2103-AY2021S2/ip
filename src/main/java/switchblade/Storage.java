package switchblade;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * switchblade.Storage will be the class used to handle any IO from switchblade.SwitchBlade to its storage file
 *
 * @author leeyueyang
 */

public class Storage {

    /**
     * Retrieves and interprets information from "switchblade.SwitchBlade.txt"
     *
     * @return Tasks read from switchblade.SwitchBlade.txt file
     * @param directory String of directory containing "switchblade.SwitchBlade.txt"
     * @exception FileNotFoundException "switchblade.SwitchBlade.txt" which contains the data has not been created
     */
    public static ArrayList<Task> retrieve(String directory) throws FileNotFoundException {
        String filepath = directory + "/switchblade.SwitchBlade.txt";
        File file = new File(filepath);
        ArrayList<Task> eventsList = new ArrayList<>();

        if (Files.exists(Path.of(filepath))) {
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String[] stringArr = sc.nextLine().split(" \\| ");

                switch (stringArr[0]) {
                case "D":
                    Deadline d = new Deadline(stringArr[2], stringArr[1], stringArr[3].equals("T"));
                    eventsList.add(d);
                    break;
                case "E":
                    if (Parser.findEventDatetime(stringArr[1]) != null) {
                        myEvent e = new myEvent(stringArr[2], Parser.findEventDatetime(stringArr[1]), stringArr[3].equals("T"));
                        eventsList.add(e);
                    }
                    break;
                case "T":
                    Task t = new Task(stringArr[1], stringArr[2].equals("T"));
                    eventsList.add(t);
                    break;
                }
            }

        } else {
            throw new FileNotFoundException();
        }

        return eventsList;
    }

    /**
     * Takes and interprets information from "switchblade.SwitchBlade.txt"
     *
     * @param outputToFile ArrayList of Tasks to be loaded into "switchblade.SwitchBlade.txt"
     * @param directory String of directory containing "switchblade.SwitchBlade.txt"
     * @exception IOException Error was encountered attempting to write to "switchblade.SwitchBlade.txt"
     */
    public static void save(List<Task> outputToFile, String directory) throws IOException {
        String filepath = directory + "/switchblade.SwitchBlade.txt";
        File file = new File(filepath);
        File folder = new File(directory);
        if (!Files.exists(Path.of(directory))) {
            boolean bool = folder.mkdirs();
        }

        if (!Files.exists(Path.of(filepath))) {
            boolean b = file.createNewFile();
        }


        FileWriter writer = new FileWriter(filepath);
        StringBuilder sb = new StringBuilder();

        //format: {task type} | {datetime (if applicable)} | {description} | {completion}
        for (Task t : outputToFile) {
            if (t instanceof Deadline) {
                sb.append("D | "
                        + ((Deadline) t).datetime.savingDatetime());
            } else if (t instanceof myEvent) {
                sb.append("E | "
                        + ((myEvent) t).startDatetime.savingDatetime()
                        + " /to "
                        + ((myEvent) t).endDatetime.savingDatetime());
            } else {
                sb.append("T");
            }

            sb.append(" | "
                    + t.getDescription()
                    + " | "
                    + (t.getCompleted() ? "T" : "F")
                    + "\n");
        }

        writer.write(sb.toString());
        writer.close();
    }
}
