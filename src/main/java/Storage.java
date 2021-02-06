package main.java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import main.java.entity.Deadline;
import main.java.entity.Event;
import main.java.entity.Task;
import main.java.entity.Todo;

/**
 * A utility class to be called only by TaskManager to help manage
 * Tasks stored on disk
 */
class Storage {

    private File file;
    private BufferedReader br;
    private FileWriter fw;
    protected Storage(String filepath) {
        this.file = new File(filepath);
        FileReader fr;
        try {
            file.getParentFile().mkdirs();
            file.createNewFile();
            System.out.println(file.getAbsolutePath());
            fr = new FileReader(file);
            br = new BufferedReader(fr);
        } catch (IOException e) {
            System.out.println("Error: Storage failed to initialize...");
            e.printStackTrace();
        }
    }

    protected List<Task> readAll() {
        List<Task> list = new ArrayList<>();
        String str;
        try {
            while ((str = br.readLine()) != null) {
                list.add(readItem(str));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    protected Task readItem(String str) {
        String[] strarr = str.split("\\|");
        String type = strarr[0];
        boolean isDone = strarr[1].equals("1");
        Task task;
        if (type.equals("T")) {
            task = new Todo(strarr[2], isDone);
        } else if (type.equals("E")) {
            try {
                LocalDate date = LocalDate.parse(strarr[4]);
                task = new Event(strarr[2], strarr[3], date, isDone);
            } catch (DateTimeParseException e) {
                task = new Event(strarr[2], strarr[3], strarr[4], isDone);
            }
        } else if (type.equals("D")) {
            try {
                LocalDate date = LocalDate.parse(strarr[4]);
                task = new Deadline(strarr[2], strarr[3], date, isDone);
            } catch (DateTimeParseException e) {
                task = new Deadline(strarr[2], strarr[3], strarr[4], isDone);
            }
        } else {
            System.out.println("Error: Item type not categorized when reading from file. Returning null task...");
            task = null;
        }
        return task;
    }

    protected void updateFile(List<Task> list) {
        try {
            fw = new FileWriter(file, false);
        } catch (IOException e) {
            System.out.println("Failed to open file writer.");
            e.printStackTrace();
        }
        list.forEach((t) -> {
            try {
                fw.write(t.toFileString() + "\n");
                fw.flush();
            } catch (IOException e) {
                System.err.println("Failed to update file.");
                e.printStackTrace();
            }
        });
    }
}
