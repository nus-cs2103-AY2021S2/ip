package kobe;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.nio.charset.StandardCharsets;


public class Storage {
    private String pathName;
    private TaskList tasks;
    private File file;
    public static String ind = "    ";
    public static String line = ind + "____________________________________________________________\n" + ind;
    public static String line2 = ind + "____________________________________________________________\n";

    /**
     * Constructor for Storage object.
     *
     * @param pathName the name of the path where the text file is stored
     * @param tasks    the list of tasks to be retrieved and written
     * @param ui       the user interface to inform the user of the outcome
     */
    Storage(String pathName, TaskList tasks, Ui ui) {
        this.pathName = "data/kobe.txt";
        if (!pathName.endsWith(".txt")) {
            this.file = new File(pathName, "kobe.txt");
            this.pathName = file.getPath();
        } else {
            this.file = new File(this.pathName);
        }
        this.file.getParentFile().mkdirs();

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
            br.close();
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        } finally {
            this.tasks = tasks;
        }
    }

    /**
     * To save the current TaskList into the text file
     */
    public static void saveFile(Storage storage) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(storage.pathName,
                StandardCharsets.US_ASCII))) {

            if (storage.tasks.size() == 0) {
                //Nothing happens
            } else {
                for (int i = 0; i < storage.tasks.size(); i++) {
                    bw.write(ind + (i + 1) + ". " + storage.tasks.get(i) + "\n");
                }
            }
            bw.close();
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        } catch (NullPointerException e) {
            System.out.println("NullPointerException: " + e);
        }
    }

}