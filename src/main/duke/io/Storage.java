package duke.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import duke.tasktype.Task;
import duke.tasktype.Event;
import duke.tasktype.Deadline;
import duke.tasktype.Todo;
import java.util.List;

import duke.DukeList;

public class Storage {
    private StringBuffer stringBufferOfTasks = new StringBuffer();
    private String filename;
    private Path path;

    public Storage(String filename) {
        this.filename = filename;
        getTaskListInFile(getDirectory());
    }

    private void getTaskListInFile(String current) {
        String[] parents = filename.split("/");
        String parent = parents[0];
        Path directPath = Paths.get(current, parent);
        Path filePath = Paths.get(current, filename);
        boolean directoryExist = Files.exists(directPath);
        boolean fileExist = Files.exists(filePath);
        try {
            if (!directoryExist) {
                Files.createDirectories(directPath);
            }
            if (!fileExist) {
                Files.createFile(filePath);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private String getDirectory() {
        return System.getProperty("user.dir");
    }

    public void removeTask(String toRemove) {
        String newLine = System.getProperty("line.separator");
        toRemove = toRemove + newLine;
        setReplacing(toRemove, "", stringBufferOfTasks);
    }

    public void setReplacing(String toReplace, String replacement, StringBuffer stringBuffer) throws StringIndexOutOfBoundsException {
        int start = stringBuffer.indexOf(toReplace);
        int end = start + toReplace.length();
        stringBuffer.replace(start, end, replacement);
    }

    public void changeText(String toChange, String changeTo) {
        setReplacing(toChange, changeTo, stringBufferOfTasks);
    }

    public void addText(String toAdd) {
        String newLine = System.getProperty("line.separator");
        toAdd = toAdd + newLine;
        stringBufferOfTasks.append(toAdd);
    }


    public DukeList load() {
        DukeList dukeList = new DukeList();
        stringBufferOfTasks = new StringBuffer();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new DataInputStream(
                    new FileInputStream(this.filename))));
            String line = reader.readLine();
            while (line != null) {

            }

        } catch (IOException e) {
            System.out.println(filename + "could not be opened" + e.getMessage());
        }
        return dukeList;
    }
    public void convertFromFile(String[] input, DukeList dukeList){
        List<Task> taskList = dukeList.getTaskList();
        String type = input[0];
        int done = Integer.parseInt(input[1]);
        String name = input[2];
        int counter = dukeList.getTaskList().size();
        switch(type) {
            case ("E"):
                String dateTime = input[3];
                Task E = new Event(name, dateTime);
                if(done == 1){
                    E.setDone();
                }
                taskList.add(counter, E);
                break;
            case("D"):
                dateTime = input[3];
                Task D = new Deadline(name, dateTime);
                if(done == 1){
                    D.setDone();
                }
                taskList.add(counter, D);
                break;
            case("T"):
                Task T = new Todo(name);
                if(done == 1){
                    T.setDone();
                }
                taskList.add(counter, T);
                break;
            default:
                break;
        }
    }
    public void runCommands(String line, DukeList dukeList){
        String[] input = line.split("~");
        String type = input[0];
        convertFromFile(input,dukeList);
        stringBufferOfTasks.append(input).append("\r\n");
    }

    public void writeFile(){
        try{
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename));
            bufferedWriter.write(stringBufferOfTasks.toString());
            bufferedWriter.close();
        }
        catch(Exception e){
            System.out.println("Error when writing to File: " + e.getMessage());
        }
    }
}
