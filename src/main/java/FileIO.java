import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileIO {

    private static final String SPLIT_REGEX = "-'@,-@,1'-";

    private enum TaskType { TODO, EVENT, DEADLINE }

    private String fileToEdit;

    public FileIO(String fileName, String filePath) {
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

    public void writeTasksToFIle(List<Task> taskList) {
        String textToAdd = convertStringsToString(convertTasksToStrings(taskList));
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
            taskList = parseStringsToTasks(stringList);
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

    public String convertStringsToString(List<String> stringList) {
        StringBuilder builder = new StringBuilder();
        for (String s : stringList) {
            builder.append(s);
            builder.append(System.lineSeparator());
        }
        return builder.toString();
    }

    public List<String> convertTasksToStrings(List<Task> taskList) {
        List<String> strings = new ArrayList<String>();

        for(int i = 0; i < taskList.size() ; i++) {
            Task currentTask = taskList.get(i);
            StringBuilder currentString = new StringBuilder();
            if (currentTask instanceof ToDos) {
                currentString.append(TaskType.TODO.name());
                currentString.append(SPLIT_REGEX);
                currentString.append(currentTask.isComplete());
                currentString.append(SPLIT_REGEX);
                currentString.append(currentTask.getTaskName());
            }else if (currentTask instanceof Events) {
                Events events = (Events) currentTask;
                currentString.append(TaskType.EVENT.name());
                currentString.append(SPLIT_REGEX);
                currentString.append(currentTask.isComplete());
                currentString.append(SPLIT_REGEX);
                currentString.append(events.getTaskName());
                currentString.append(SPLIT_REGEX);
                currentString.append(events.getTiming());
            }else if (currentTask instanceof Deadlines) {
                Deadlines deadlines = (Deadlines) currentTask;
                currentString.append(TaskType.DEADLINE.name());
                currentString.append(SPLIT_REGEX);
                currentString.append(currentTask.isComplete());
                currentString.append(SPLIT_REGEX);
                currentString.append(deadlines.getTaskName());
                currentString.append(SPLIT_REGEX);
                currentString.append(deadlines.getDeadline());
            }
            strings.add(currentString.toString());
        }
        return strings;
    }

    public List<Task> parseStringsToTasks(List<String> stringList) {
        List<Task> tasks = new ArrayList<Task>();

        for (String line : stringList) {
            String[] parts = line.split(SPLIT_REGEX);
            Task currentTask = null;
            switch (TaskType.valueOf(parts[0])) {
            case TODO:
                currentTask = new ToDos(parts[2]);
                break;
            case EVENT:
                currentTask = new Events(parts[2] ,parts[3]);
                break;
            case DEADLINE:
                currentTask = new Deadlines(parts[2], parts[3]);
                break;
            }
            
            if(Boolean.parseBoolean(parts[1]))
                currentTask.markComplete();

            tasks.add(currentTask);
        }
        return tasks;
    }
}
