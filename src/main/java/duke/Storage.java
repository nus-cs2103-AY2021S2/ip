package duke;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;
import java.io.IOException;

public class Storage {
    
    private File file;
    
    /**
    * Constructor for the Storage class. The filename argument will be used to create file for storing 
    * and retrieving the user's TaskList.
    * @param filename the name of the file to save TaskList information
    */
    public Storage(String filename) {
        file = new File(filename);
    }
    
    /**
    * Returns the TaskList object previously saved by the chatbot when the user terminates the program.
    * @return TaskList object
    */
    public TaskList loadTaskList() {
        
        try {
            if (file.createNewFile()) {
                return new TaskList();
            } else {
                
                BufferedReader br = new BufferedReader(new FileReader(file));
                TaskList taskList = new TaskList();
                
                String line;
                while((line = br.readLine()) != null) {
                    String[] split = line.split("\\|");
                    
                    // Create new task for each line
                    char type = split[0].charAt(0);
                    
                    boolean isDone = false;
                    if (split[1].equals("D")) {
                        isDone = true;
                    }
                    
                    String name = split[2];
                    
                    if (type == 'D' || type == 'E') {
                        LocalDate dateTime = LocalDate.parse(split[3]);
                        
                        Task task = new Task(name, type, dateTime);
                        if (isDone)
                            task.mark();

                        taskList.add(task);
                        
                    } else {
                        Task task = new Task(name);
                        if (isDone)
                            task.mark();

                        taskList.add(task);
                    }
                }
                
                return taskList;
                    
                
            }
            
        } catch(IOException e) {
            return new TaskList();
        }
        
    }
    
    /**
    * Saves the TaskList object provided in the argument into the file created by the constructor of this class.
    * @param TaskList object to be saved
    */
    public void saveTaskList(TaskList taskList) throws DukeException {
        
        try {
            FileWriter fileWriter = new FileWriter(file, false);
            
            for (int i = 0; i < taskList.count(); i++) {
                
                Task t = taskList.getTask(i);
                
                String line = "" + t.getType() + "|";
                if (t.getDone()) {
                    line += "D|";
                } else {
                    line += "ND|";
                }
                
                line += t.getName();
                
                if (t.getType() == 'D' | t.getType() == 'E') {
                    line += "|" + t.getDate();
                }
                
                line += "\n";

                fileWriter.write(line);
                
            }

            fileWriter.flush();
            fileWriter.close();
            
        } catch (IOException e) {
            throw new DukeException("An error occured while saving the task list!");
        }
        
    }
    
}