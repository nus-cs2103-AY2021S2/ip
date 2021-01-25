package main.java;

import main.java.entity.Deadline;
import main.java.entity.Event;
import main.java.entity.Task;
import main.java.entity.Todo;

import java.io.*;
import java.nio.Buffer;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class Storage {

    private File file;
    private BufferedReader br;
    public Storage(String filepath) {
        this.file = new File(filepath);
        FileReader fr;
        try {
            file.createNewFile();
            fr = new FileReader(file);
            br = new BufferedReader(fr);
        } catch (IOException e) {
            System.out.println("Error: Storage failed to initialize...");
            e.printStackTrace();
        }
    }

    public List<Task> readAll() {
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

    public Task readItem(String str) {
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

    public void updateFile(List<Task> list){
//        FileOutputStream fos = new FileOutputStream(file, false);
        try {
            FileWriter fw = new FileWriter(file, false);
            for (Task task : list) {
                String output = task.toFileString();
                fw.write(output);
                fw.write("\n");
                fw.flush();
            }
        } catch (Exception e) {
            System.err.println("Failed to update file");
            e.printStackTrace();
        }
    }
}
