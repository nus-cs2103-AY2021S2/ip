package Duke.Helper;

import Duke.Task.Deadline;
import Duke.Task.Event;
import Duke.Task.Task;
import Duke.Task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String path;

    public Storage(String path) {
        this.path = path;
    }

    public ArrayList<Task> readDataFromFile(){
        try {
            File f = new File(path);
            Scanner sc = new Scanner(f);
            ArrayList<Task> tasks = new ArrayList<>();
            while (sc.hasNextLine()){
                String currLine = sc.nextLine();
                String[] information = currLine.split("\\|");
                switch (information[0].charAt(0)) {
                    case 'T': {
                        Task newTask = new Todo(information[2]);
                        if (information[1].trim().equals("1")) newTask.markAsDone();
                        tasks.add(newTask);
                        break;
                    }
                    case 'D': {
                        Task newTask = new Deadline(information[2], information[3]);
                        if (information[1].trim().equals("1")) newTask.markAsDone();
                        tasks.add(newTask);
                        break;
                    }
                    case 'E': {
                        Task newTask = new Event(information[2], information[3]);
                        if (information[1].trim().equals("1")) newTask.markAsDone();
                        tasks.add(newTask);
                        break;
                    }
                }
            }
            return tasks;
        } catch (FileNotFoundException e) {
            System.out.println("File not found " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public void writeDataToFile(ArrayList<Task> list){
        try {
            File f = new File(path);
            FileWriter fw = new FileWriter(f);
            for (Task task : list){
                String content = task.getType().trim() + " | " + task.getStatusNumber() + " | " +
                        task.getDescription().trim() + task.getTime().trim();
                fw.write(content + '\n');
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Unable to write to file " + e.getMessage());
        }
    }
}
