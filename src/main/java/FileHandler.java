import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileHandler {

    public FileHandler() { }

    public List<Task> readFromFile() throws IOException, DukeException {
        List<Task> list = new ArrayList<>();
        File directory = new File("data");
        if (!directory.exists()) { //creating directory if it doesn't exist
            directory.mkdir();
        }
        File f = new File("data/duke.txt");
        if (!f.exists()) {
            f.createNewFile();
        }
        Scanner scf = new Scanner(f);
        while (scf.hasNext()) {
            try {
                String nextLine = scf.nextLine();
                Command com = new Command(list);
                list = com.handleFileCommand(nextLine); //input format: deadline 0 return book /by June 6th
            } catch (DukeException ex) {
                System.out.println(ex);
            }
        }
        return list;
    }

    public void writeToFile(List<Task> list) throws IOException {
        //before writing to it, clear file to make sure it is empty and no content is leftover from last run
        FileWriter fw = new FileWriter("data/duke.txt");
        PrintWriter pw = new PrintWriter("data/duke.txt");
        pw.print("");
        pw.close();
        String textToAdd = "";
        for (Task t : list) {
            textToAdd = textToAdd + t.generateText() + "\n";
        }
        fw.write(textToAdd);
        fw.close();
    }


}
