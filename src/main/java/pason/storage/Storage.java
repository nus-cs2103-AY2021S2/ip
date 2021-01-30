package pason.storage;

import pason.exceptions.PasonException;
import pason.tasks.Deadline;
import pason.tasks.Event;
import pason.tasks.Task;
import pason.tasks.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private static String FILE_DIRECTORY = "data";
    private static String FILE_NAME = "tasks.txt";

    public Storage() {
    }

    public List<Task> loadTasks() throws FileNotFoundException, PasonException {
        File directory = new File(FILE_DIRECTORY);
        File file = new File(FILE_DIRECTORY + "/" + FILE_NAME);
        if (!directory.exists()) {
            directory.mkdir();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch(IOException e) {
                throw new PasonException(e.getMessage());
            }
        }
        List<Task> tasks = new ArrayList<>();
        Scanner s = new Scanner(file);
        Task task;
        int failedImports = 0;
        while (s.hasNext()) {
            task = parseFileEntry(s.nextLine());
            if(task != null) {
                tasks.add(task);
            } else {
                failedImports++;
            }
        }
        if(failedImports > 0) {
            throw new PasonException("There was a problem parsing "
                    + failedImports + " task(s) from the file.");
        }
        return tasks;
    }

    public static Task parseFileEntry(String text) {
        String[] splitString = text.split(" \\| ");
        if (splitString[0].equals("T") && splitString.length == 3) {
            ToDo newToDo = new ToDo(splitString[2]);
            if (splitString[1].equals("1")) {
                newToDo.markAsDone();
            }
            return newToDo;
        } else if (splitString[0].equals("E") && splitString.length == 4) {
            String[] eventDateAndExtra = splitString[3].split(" ");
            Event newEvent = new Event(splitString[2], LocalDate.parse(eventDateAndExtra[0]),
                    (eventDateAndExtra.length == 1 ? null : eventDateAndExtra[1]));
            if (splitString[1].equals("1")) {
                newEvent.markAsDone();
            }
            return newEvent;
        } else if (splitString[0].equals("D") && splitString.length == 4) {
            Deadline newDeadline = new Deadline(splitString[2],
                    LocalDateTime.parse(splitString[3]));
            if (splitString[1].equals("1")) {
                newDeadline.markAsDone();
            }
            return newDeadline;
        } else {
            return null;
        }
    }

    public void saveAllTasks(List<Task> tasks) throws IOException {
        try {
            FileWriter fw = new FileWriter(FILE_DIRECTORY
                    + "/" + FILE_NAME);
            for(int i = 0; i < tasks.size(); i++) {
                fw.write(tasks.get(i).toFileFormat() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    public void appendTask(Task task) throws IOException {
        try {
            FileWriter fw = new FileWriter(FILE_DIRECTORY
                    + "/" + FILE_NAME, true);
            fw.write(task.toFileFormat() + "\n");
            fw.close();
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }
}
