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

import duke.DukeException;;
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
        this.filename = new File(filename);
        accessTaskListInFileSystem(getCurrentDirectory());
    }
    private void taskListinFile (String current){
        String[] parents = filename.split("/");
        String parent = parents[0];
        Path direcPath = Path(current,parent);
        Path filePath = Path(current,filename);
        boolean directoryExist = Files.exists(direcPath);
        boolean fileExist = Files.exists(filePath);
        try{
            if(!directoryExist){
                Files.createDirectories(direcPath);
            }
            if(!fileExist){
                Files.createFile(filePath);
           }
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    public DukeList load(){
        DukeList dukeList = new DukeList();
        StringBufferTasks = new StringBuffer();
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream(this.filename))));
            String line = reader.readline();
        }
    }
}
