import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private String path;
    private File file;

    /**
     * Constructor to initialize storage
     */
    public Storage(String filePath) throws IOException {
        this.path = filePath;
        file = new File(filePath);
        this.checkIfExist();
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
                String command = fio.nextLine();
                Parser parser = new Parser(command);
                String commandType = parser.getTaskType();
                if(commandType.equals("todo")){
                    taskList.addTask(new TodoTask(command));
                }else if(commandType.equals("deadline")){
                    taskList.addTask(new DeadlineTask(command));
                }else if(commandType.equals("event")){
                    taskList.addTask(new EventTask(command));
                }
            }
            fio.close();
            return taskList;
        }catch(IOException e){
            throw new DukeException("failed to load file");
        }
    }

    public void writeTask(String task) throws DukeException{
        try {
            FileWriter fw = new FileWriter(file);
            fw.write(task);
            fw.close();
        }catch(IOException e){
            throw new DukeException("Error! Couldn't write on file!");
        }
    }

}
