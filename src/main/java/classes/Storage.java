package main.java.classes;

import main.java.command.Deadline;
import main.java.command.Event;
import main.java.command.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

/**
 * Storage class to deal with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    private File file;

    /**
     * Constructor method.
     * @param filePath path to the saved file
     * @throws IOException if user IO is incorrect
     */
    public Storage(String filePath) throws IOException {
        this.file = new File(filePath);
        if (file.createNewFile()) {
            System.out.println("file created!");
        } else {
            System.out.println("file loaded!");
        }
    }

    /**
     * Method to save Task in saved file, converts Task objects to Strings in file
     * @param AL ArrayList of Task objects from user input
     * @throws IOException if user IO is incorrect
     */
    public void saveTasks(ArrayList<Task> AL) throws IOException {
        FileWriter fw = new FileWriter(this.file);
        for (Task temp : AL) {
            if (temp instanceof Deadline) {
                fw.write(String.format("D | %s | %s | %s",
                        temp.getIsDone(), temp.getDescription(), ((Deadline) temp).getBy()) + "\n");
            } else if (temp instanceof Event) {
                fw.write(String.format("E | %s | %s | %s",
                        temp.getIsDone(), temp.getDescription(), ((Event) temp).getAt()) + "\n");
            } else {
                fw.write(String.format("T | %s | %s",
                        temp.getIsDone(), temp.getDescription()) + "\n");
            }
        }
        fw.close();
    }

    /**
     * Method to load Tasks from saved file, converts Strings in saved file to Task objects.
     * @return an ArrayList AL of Task objects
     * @throws IOException if user IO is incorrect
     */
    public ArrayList<Task> loadTasks() throws IOException {
        ArrayList<Task> AL = new ArrayList<>();
        Scanner sc = new Scanner(this.file);
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            s = s.trim();

            String[] sArr = s.split("\\|", 4);

            if (sArr[0].trim().equals("D")) {
                SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.ENGLISH);
                Date date = null;
                try {
                    date = format.parse(sArr[3].trim());
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Deadline tempD = new Deadline(sArr[2].trim(), date);
                if (sArr[1].trim().equals("done")) {
                    tempD.isDone = true;
                } else if (sArr[1].trim().equals("not done")){
                    tempD.isDone = false;
                }
                AL.add(tempD);
            } else if (sArr[0].trim().equals("E")) {
                SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.ENGLISH);
                Date date = null;
                try {
                    date = format.parse(sArr[3].trim());
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Event tempE = new Event(sArr[2].trim(), date);
                if (sArr[1].trim().equals("done")) {
                    tempE.isDone = true;
                } else if (sArr[1].trim().equals("not done")) {
                    tempE.isDone = false;
                }
                AL.add(tempE);
            } else {
                ToDo tempT = new ToDo(sArr[2].trim());
                if (sArr[1].trim().equals("done")) {
                    tempT.isDone = true;
                } else if (sArr[1].trim().equals("not done")) {
                    tempT.isDone = false;
                }
                AL.add(tempT);
            }
        }
        return AL;
    }
}
