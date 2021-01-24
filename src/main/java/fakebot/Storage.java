package fakebot;

import fakebot.task.Task;
import fakebot.task.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {

    private String fileToEdit;

    public Storage(String fileName, String filePath) {
        filePath = createDirectory(filePath);
        fileToEdit = filePath+fileName;
    }

    private String createDirectory(String filePath) {
        File file = new File(filePath);

        file.mkdirs();

        //Remove directory and use root instead since it invalid
        if(!file.isDirectory()) {
            return "";
        }
        return filePath;
    }

    public void writeTasksToFIle(TaskList taskList) {
        String textToAdd = Parser.convertStringsToString(Parser.convertTasksToStrings(taskList));
        try {
            writeToFile(textToAdd);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(fileToEdit);
        fw.write(textToAdd);
        fw.close();
    }

    public List<Task> tryReadTaskFile() {
        List<Task> taskList = new ArrayList<Task>();
        try {
            List<String> stringList = readFile();
            taskList = Parser.parseStringsToTasks(stringList);
        }catch (FileNotFoundException e) {
            //Do nothing, return empty list.
        }
        return taskList;
    }

    private List<String> readFile() throws FileNotFoundException {
        List<String> stringList = new ArrayList<String>();

        File f = new File(fileToEdit);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            stringList.add(s.nextLine());
        }

        return stringList;
    }

}
