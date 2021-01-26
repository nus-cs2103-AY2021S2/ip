package duke;

import duke.exceptions.FileIoException;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Todo;
import duke.tasks.Task;
import duke.tasks.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String path;
    private File file;
    private static DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");

    public Storage(String path) throws FileIoException {
        this.path = path;
        try {
            file = new File(path);
            file.createNewFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new FileIoException();
        }
    }

    public ArrayList<Task> getTasks() throws FileIoException {
        ArrayList<Task> list = new ArrayList<>();
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String nextLine = sc.nextLine();
                String[] spiltString = nextLine.split(" \\|\\| ");
                String taskType = spiltString[0];
                boolean isCompleted = spiltString[1].equals("1");
                String taskName = spiltString[2];

                if (spiltString.length == 4) {
                    LocalDate time = LocalDate.parse(spiltString[3], OUTPUT_FORMATTER);
                    if (taskType.equals("D")) {
                        list.add(new Deadline(taskName, isCompleted, time.toString()));
                    } else if (taskType.equals("E")) {
                        list.add(new Event(taskName, isCompleted, time.toString()));
                    } else {
                        throw new FileIoException();
                    }
                } else {
                    if (taskType.equals("T")) {
                        list.add(new Todo(taskName, isCompleted));
                    } else
                        throw new FileIoException();
                }

            }
        } catch (FileNotFoundException | FileIoException e) {
            throw new FileIoException();
        }
        return list;
    }

    public void writeFile(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(path);
        ArrayList<Task> list = tasks.getList();

        for (Task curr : list) {
            String storageString = curr.toStorageString();
            fw.write(storageString + "\n");
        }
        fw.close();
    }

}
