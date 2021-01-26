package duke;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DukeStorage {
    protected String fileName;

    protected DukeStorage() {
        this.fileName = "storage/storage.txt";
        File file = new File(this.fileName);
        if (!file.getParentFile().exists()) {
            //TODO exception handling
            System.out.println("History not found, creating one now...");
            if (file.getParentFile().mkdirs()) {
                System.out.println("New history created!");
            } else {
                //TODO exception handling
                System.out.println("History creation failed!");
            }
        } else {
            System.out.println("History loaded");
        }
    }

    protected ArrayList<Task> load() throws IOException {
        File file = new File(fileName);
        try {
            if (!file.exists()) {
                if (file.createNewFile()) {
                    System.out.println("New history created!");
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        Scanner sc = new Scanner(file);
        ArrayList<Task> taskList = new ArrayList<>();
        while (sc.hasNext()) {
            String[] currLine = sc.nextLine().split("\\|");
            if (currLine[0].length() < 4) {
                //TODO: Throw error
                continue;
            }
            String type = currLine[0].substring(0, currLine[0].length() - 1);
            if (currLine[1].length() < 1) {
                //TODO: Throw error
                continue;
            }
            String description = currLine[1].substring(1, currLine[1].length() - 2);

            switch (type) {
            case("Todo"): {
                taskList.add(new Todo(description));
                break;
            }
            case("Deadline"): {
                if (currLine[2].length() < 1) {
                    //TODO: Throw error
                    continue;
                }
                String by = currLine[2].substring(1);
                taskList.add(new Deadline(description, by));
                break;
            }
            case("Event"): {
                if (currLine[2].length() < 1) {
                    //TODO: Throw error
                    continue;
                }
                String at = currLine[2].substring(1);
                taskList.add(new Event(description, at));
                break;
            }
            }
        }
        return taskList;
    }

    protected void unload(DukeTaskList taskList) throws IOException {
        StringBuilder content = new StringBuilder();
        for (Task t : taskList.getTaskList()) {
            String currTask = "";
            switch (t.getClass().getSimpleName()) {
            case "Todo" : {
                currTask = "Todo | " + t.getDescription();
                break;
            }
            case "Deadline": {
                currTask = "Deadline | " + t.getDescription() + " | " + ((Deadline) t).getBy();
                break;
            }
            case "Event": {
                currTask = "Event | " + t.getDescription() + " | " + ((Event) t).getAt();
            }
            }
            content.append(currTask);
            content.append("\n");
        }
        FileWriter fw = new FileWriter(this.fileName);
        fw.write(content.toString().substring(0, content.toString().length() - 1));
        fw.close();
    }
}
