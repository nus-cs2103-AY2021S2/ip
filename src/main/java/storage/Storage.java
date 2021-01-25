package storage;

import Exceptions.DukeException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;

import java.util.ArrayList;
import java.util.Scanner;

import java.time.LocalDateTime;

public class Storage {
    private File f;

    private final String path = "src/main/java/data/myDuke.txt";

    public Storage() throws DukeException {
        createFile();
    }

    public void createFile() throws DukeException {
        try {
            this.f = new File(path);
            if (!f.createNewFile()) {
                System.out.println("File is loading...");
            } else {
                System.out.println("File already exists!");
            }
        } catch (IOException e){
            throw new DukeException("☹ OOPS!!! File creation error!");
        }
    }

    public ArrayList<Task> displayTasks() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner s = new Scanner(f);
            while(s.hasNextLine()) {
               Task task = parseTasks(s.nextLine());
               tasks.add(task);
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("☹ OOPS!!! File not found!");
        }
        return tasks;
    }

    public Task parseTasks(String task) throws DukeException {
        String[] description = task.split(" / ", 4);
        Task t = null;
        try {
            LocalDateTime dateTime;
            String type = description[0];
            switch (type) {
                case "T":
                    t = new ToDo(description[2]);
                    break;

                case "D":
                    dateTime = LocalDateTime.parse(description[3]);
                    t = new Deadline(description[2], dateTime);
                    break;

                case "E":
                    dateTime = LocalDateTime.parse(description[3]);
                    t = new Event(description[2], dateTime);
                    break;
            }

            if (description[1].equals("1")) {
                t.markAsDone();
            }

        } catch (Exception e){
            throw new DukeException("☹ OOPS!!! I do not understand what are you saying!");
        }

        return t;
    }

    public void saveTasks(ArrayList<Task> tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter(path);
            for (Task t : tasks) {
                fw.write(t.toSave() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("☹ OOPS!!! Tasks cannot be saved.");
        }
    }
}
