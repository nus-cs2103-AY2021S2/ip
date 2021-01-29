import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Storage class to store the data in a file
 */
public class Storage {
    private String path;
    private File file;

    /**
     * Constructor to initialize storage
     */
    public Storage(String filePath) throws IOException {
        this.path = filePath;
        file = new File(filePath);
    }

    public void checkIfExist(){
        if(file.exists()){
            return;
        }
        try{
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Tasks load() throws DukeException{
        try{
            Tasks taskList = new Tasks();
            Scanner fio = new Scanner(file);
            while(fio.hasNextLine()){
                String line = fio.nextLine();
                String[] command = line.split(" ");
                if(command[0].equals("todo")){
                    taskList.addTask(new TodoTask(line));
                }else if(command[0].equals("deadline")){
                    taskList.addTask(new DeadlineTask(line));
                }else if(command[0].equals("event")){
                    taskList.addTask(new EventTask(line));
                }
            }
            fio.close();
            return taskList;
        }catch(IOException e){
            throw new DukeException("failed to load file");
        }
    }


    public void saveTask(Tasks tasks){
        this.checkIfExist();
        try{
            PrintWriter pw = new PrintWriter(file);
            for(int i = 0; i < tasks.numOfTasks(); i++){
                pw.println(tasks.getTask(i).getTaskName());
            }
            pw.close();
        }catch (FileNotFoundException e){

        }
    }

}
