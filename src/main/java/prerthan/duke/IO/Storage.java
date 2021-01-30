package sharadhr.duke.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import sharadhr.duke.task.Task;
import sharadhr.duke.task.TaskList;

/**
 * A class to handle file read/write operations by the Duke program.
 */
public class Storage
{
    private Path taskFile;
    
    private BufferedReader reader;
    private BufferedWriter writer;
    
    public Storage(String... directory)
    {
        this.taskFile = Paths.get(".", directory).normalize().toAbsolutePath();
        
        try
        {
            Files.createDirectories(this.taskFile.getParent());

            if (Files.notExists(this.taskFile))
            {
                Files.createFile(this.taskFile);
            }

            this.writer = Files.newBufferedWriter(this.taskFile, StandardOpenOption.WRITE, StandardOpenOption.APPEND);
            this.reader = Files.newBufferedReader(this.taskFile);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    public void appendTask(Task task)
    {
        try
        {
            writer.append(task.encode());
            writer.newLine();
            writer.flush();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void decodeLine(String line)
    {
        
    }
    
    public TaskList loadFromFile()
    {


        return null;
    }
}