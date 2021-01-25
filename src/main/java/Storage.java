import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    public static ArrayList<Task> retrieve(String directory) throws FileNotFoundException {
        String filepath = directory + "/SwitchBlade.txt";
        File file = new File(filepath);
        ArrayList<Task> output = new ArrayList<>();

        if (Files.exists(Path.of(filepath))) {
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String[] stringArr = sc.nextLine().split(" \\| ");

                switch (stringArr[0]) {
                    case "D":
                        Deadline d = new Deadline(stringArr[2], stringArr[1], stringArr[3].equals("T"));
                        output.add(d);
                        break;
                    case "E":
                        myEvent e = new myEvent(stringArr[2], stringArr[1], stringArr[3].equals("T"));
                        output.add(e);
                        break;
                    case "T":
                        Task t = new Task(stringArr[1], stringArr[2].equals("T"));
                        output.add(t);
                        break;
                }
            }

        } else {
            throw new FileNotFoundException();
        }

        return output;
    }

    public static void save(ArrayList<Task> outputToFile, String directory) throws IOException {
        String filepath = directory + "/SwitchBlade.txt";
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
                        + ((Deadline) t).datetime);
            } else if (t instanceof myEvent) {
                sb.append("E | "
                        + ((myEvent) t).datetime);
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
