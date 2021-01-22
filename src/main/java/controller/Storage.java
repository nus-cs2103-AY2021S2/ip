package controller;

import task.Deadline;
import task.Event;
import task.ToDo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

import java.time.LocalDate;

public class Storage {
    private final String fileName;

    public Storage(String fileName){
        this.fileName = fileName;
    }

    public void handleSave(TaskList taskList) {
        try {
            FileWriter csvWriter = new FileWriter(fileName);
            for (int i = 1; i <= taskList.getNumberOfTasks(); i++) {
                csvWriter.append(taskList.getTaskAtIndex(i).parseToCSVRow());
                csvWriter.append("\n");
            }
            csvWriter.flush();
            csvWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving your changes. Please try again later.");
        }
    }

    public TaskList handleLoad() {
        try {
            TaskList listOfTasks = new TaskList();
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            while(line != null) {
                String[] details = line.split(",");
                switch (details[0]){
                    case "T":
                        listOfTasks.addTask(new ToDo(details[2], Boolean.parseBoolean(details[1])));
                        break;
                    case "D":
                        LocalDate dateD = LocalDate.parse(details[3]);
                        listOfTasks.addTask(new Deadline(details[2], Boolean.parseBoolean(details[1]), dateD));
                        break;
                    case "E":
                        LocalDate dateE = LocalDate.parse(details[3]);
                        listOfTasks.addTask(new Event(details[2], Boolean.parseBoolean(details[1]), dateE));
                        break;
                }
                line = reader.readLine();
            }
            return listOfTasks;
        } catch (FileNotFoundException e) {
            System.out.println("It seems like you do not have a save file. A new one will be created.");
            makeNewFile();
            return new TaskList();
        } catch (IOException e) {
            System.out.println("An error occurred while loading. Please try again later.");
            System.exit(1);
            return null;
        }
    }

    private void makeNewFile() {
        try{
            new File(fileName).createNewFile();
        } catch (IOException e) {
            System.out.println("An error occurred while loading. Please try again later.");
        }
    }
}
