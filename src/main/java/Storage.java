import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    protected String path;
    protected File localFile;
    protected boolean fileOriginallyPresent;
    protected DateValidation validate;

    public Storage(String path) {
        this.path = path;
        this.localFile = new File(path);
        this.fileOriginallyPresent = true;
        this.validate = new DateValidation();
    }

    public void checkFileExistence() {
        File directory = new File("data");
        if (!directory.isDirectory()) {
            directory.mkdir();
        }
        if (!localFile.exists()) {
            try {
                this.fileOriginallyPresent = false;
                localFile.createNewFile();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    public void addTask(Task task) throws IOException {
        FileWriter fw = new FileWriter(this.localFile, true);
        fw.write(task.toString() + "\n");
        fw.close();
    }

    public List<Task> loadTasks() throws FileNotFoundException {
        List<Task> tasks = new ArrayList<Task>();
        if (this.fileOriginallyPresent) {
            Scanner contents = new Scanner((this.localFile));
            while (contents.hasNext()) {
                String data = contents.nextLine();
                Character type = data.charAt(1);
                Character isDone = data.charAt(4);
                int startingIndex = data.indexOf('(');
                int endingIndex = data.indexOf(')');
                switch (type) {
                case 'T':
                    String todoDescription = data.substring(7);
                    ToDo todo = new ToDo(todoDescription);
                    if (isDone == 'X') {
                        todo.setIsDone();
                    }
                    tasks.add(todo);
                    break;
                case 'D':
                    String deadlineDescription = data.substring(7, startingIndex - 1);
                    String by = data.substring(startingIndex + 5, endingIndex);
                    Deadline deadline = new Deadline(deadlineDescription, validate.convertDate(by));
                    if (isDone == 'X') {
                        deadline.setIsDone();
                    }
                    tasks.add(deadline);
                    break;
                case 'E':
                    String eventDescription = data.substring(9, startingIndex - 1);
                    String time = data.substring(startingIndex + 5, endingIndex);
                    Event event = new Event(eventDescription, time);
                    if (isDone == 'X') {
                        event.setIsDone();
                    }
                    tasks.add(event);
                    break;
                }
            }
        }
        return tasks;
    }

    public void deleteTask(Task task) throws IOException {
        Scanner contents = new Scanner(this.localFile);
        File temp = new File("data/temp.txt");
        temp.createNewFile();
        FileWriter tempFile = new FileWriter(temp, true);
        while (contents.hasNext()) {
            String data = contents.nextLine();
            if (!data.equals(task.toString())) {
                tempFile.write(data + "\n");
            }
        }
        tempFile.close();
        copyFile(temp,localFile);
        temp.delete();
    }

    public String markTask(Task task) throws IOException {
        File temp = new File("data/temp.txt");
        temp.createNewFile();
        FileWriter tempFile = new FileWriter(temp, true);
        Scanner contents = new Scanner(this.localFile);
        String response = "";

        while (contents.hasNext()) {
            String data = contents.nextLine();
            if (!data.equals(task.toString())) {
                tempFile.write(data + "\n");
            } else {
                response = task.markAsDone();
                tempFile.write(task.toString() + "\n");
            }
        }
        tempFile.close();
        copyFile(temp, localFile);
        temp.delete();
        return response;
    }

    public void copyFile(File input, File output) throws IOException {
        FileInputStream in = new FileInputStream(input);
        FileOutputStream out = new FileOutputStream(output);
        byte[] data = new byte[1024];
        int len = in.read(data);
        while (len != -1) {
            out.write(data, 0, len);
            len = in.read(data);
        }
        in.close();
        out.close();
    }
}
