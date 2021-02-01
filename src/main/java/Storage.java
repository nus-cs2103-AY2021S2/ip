package main.java;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class Storage {
    private File f;

    public Storage(String filePath) throws IOException {
        this.f = new File(filePath);
        if (f.createNewFile()) {
            System.out.println("file created!");
        } else {
            System.out.println("file loaded!");
        }
    }

    public void saveTasks(ArrayList<Task> AL) throws IOException {
        FileWriter fw = new FileWriter(this.f);
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

    public ArrayList<Task> loadTasks() throws IOException {
        ArrayList<Task> AL = new ArrayList<>();
        Scanner sc = new Scanner(this.f);
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
