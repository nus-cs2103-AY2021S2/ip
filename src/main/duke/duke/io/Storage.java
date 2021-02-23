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
import java.util.List;
import java.util.SplittableRandom;

import duke.DukeException;
import duke.DukeList;
import duke.tasktype.Task;
import duke.tasktype.Deadline;
import duke.tasktype.Event;
import duke.tasktype.Todo;

public class Storage {
    private StringBuffer stringBufferOfTasks = new StringBuffer();
    private String filename;
    private Path path;

    public Storage (String filename){
        this.filename = filename;
        getTaskListInFile(getDirectory());
    }
    private void getTaskListInFile (String current){
        String[] parents = filename.split("/");
        String parent = parents[0];
        Path directPath = Paths.get(current,parent);
        Path filePath = Paths.get(current,filename);
        boolean directoryExist = Files.exists(directPath);
        boolean fileExist = Files.exists(filePath);
        try{
            if(!directoryExist){
                Files.createDirectories(directPath);
            }
            if(!fileExist){
                Files.createFile(filePath);
           }
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
    private String getDirectory(){
        return System.getProperty("user.dir");
    }
    public void removeTask(String toRemove){
        String newLine = System.getProperty("line.separator");
        toRemove = toRemove + newLine;
        setReplacing(toRemove, "", stringBufferOfTasks);
    }
    public void setReplacing(String toReplace, String replacement, StringBuffer stringBuffer) throws StringIndexOutOfBoundsException{
        int start = stringBuffer.indexOf(toReplace);
        int end = start + toReplace.length();
        stringBuffer.replace(start,end, replacement);
    }
    public void changeText(String toChange, String changeTo){
        setReplacing(toChange,changeTo,stringBufferOfTasks);
    }

    public void addText(String toAdd){
        String newLine = System.getProperty("line.separator");
        toAdd = toAdd + newLine;
        stringBufferOfTasks.append(toAdd);
    }


    public DukeList load(){
        DukeList dukeList = new DukeList();
        stringBufferOfTasks = new StringBuffer();
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(new DataInputStream(
                    new FileInputStream(this.filename))));
            String line = reader.readLine();
            while(line != null){

            }

        }
        catch(IOException e){
            System.out.println(filename + "could not be opened" + e.getMessage());
        }
        catch(ArrayIndexOutOfBoundsException e){
            System.out.println(e.getMessage());
        }
        catch (DukeException e){
            System.out.println(e.getMessage());
        }
    }
}
