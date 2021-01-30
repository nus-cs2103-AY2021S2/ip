package com.lirc572.ip;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Storage {

    public static final String fileName = "tasks.data";

    /**
     * Save the task list to disk
     *
     * @param tasks task list
     */
    public static void saveToFile(TaskList tasks) {
        try {
            File fileObj = new File(fileName);
            fileObj.createNewFile();
            FileWriter fileWriter = new FileWriter(fileName);
            fileWriter.write(tasks.toSavedString());
            fileWriter.close();
        } catch (IOException e) {
            Ui.printError(e);
        }
    }

    /**
     *
     */
    public static void readFromFile(TaskList tasks) {
        try {
            File fileObj = new File(fileName);
            fileObj.createNewFile();
            Scanner fileReader = new Scanner(fileObj);
            StringBuilder data = new StringBuilder();
            while (fileReader.hasNextLine()) {
                data.append(fileReader.nextLine() + "\n");
            }
            fileReader.close();
            String[] lines = data.toString().split("\n");
            for (String line : lines) {
                String[] sections = line.split(Pattern.quote(" | "));
                if (sections[0].equals("T")) {
                    Task task = new TodoTask(sections[2]);
                    if (sections[1].equals("1")) {
                        task.setIsDone(true);
                    }
                    tasks.add(task);
                } else if (sections[0].equals("D")) {
                    Task task = new DeadlineTask(sections[2], sections[3]);
                    if (sections[1].equals("1")) {
                        task.setIsDone(true);
                    }
                    tasks.add(task);
                } else if (sections[0].equals("E")) {
                    Task task = new EventTask(sections[2], sections[3]);
                    if (sections[1].equals("1")) {
                        task.setIsDone(true);
                    }
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            Ui.printError(e);
        }
    }
}
