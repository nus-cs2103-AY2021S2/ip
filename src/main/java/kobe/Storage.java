package kobe;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Storage {
    private String pathName;
    private TaskList tasks;
    public static String ind = "    ";
    public static String line = ind + "____________________________________________________________\n" + ind;
    public static String line2 = ind + "____________________________________________________________\n";


    /**
     * Constructor for Storage object.
     *
     * @param pathName  the name of the path where the text file is stored
     * @param tasks  the list of tasks to be retrieved and written
     * @param ui  the user interface to inform the user of the outcome
     */
    Storage(String pathName, TaskList tasks, Ui ui) {
        this.pathName = pathName;

        //Read line by line of the storage file
        try (BufferedReader br = new BufferedReader(
                new FileReader(pathName, StandardCharsets.US_ASCII))) {

            boolean isFileEmpty = true;
            String readLine = br.readLine();

            while (readLine != null) {
                isFileEmpty = false;
                System.out.println(readLine);

                tasks.addItemByString(readLine);
                readLine = br.readLine();
            }

            if (!isFileEmpty) {
                System.out.println(line + "Here are tasks that Kobe has retrieved!\n" + line);
            } else {
                ui.showLoadingError();
            }

        } catch (IOException e) {
            //do nothing
        } finally {
            this.tasks = tasks;
        }
    }

    /**
     * To save the TaskList into the text file
     */
    //UPDATE THE KOBE.TXT FILE
    public void saveFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.pathName,
                StandardCharsets.US_ASCII))) {

            for (int i = 0; i < this.tasks.size(); i++) {
                bw.write(ind + (i + 1) + ". " + this.tasks.get(i) + "\n");
            }

        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
    }

}