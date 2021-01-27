import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Storage {

    private String edit;
    public Storage(String path, String name){
        path = makeDirectory(path);
        edit = path+name;
    }
    public String makeDirectory(String path){
        File file = new File(path);
        file.mkdirs();
        if (!file.isDirectory()) {
            return "";
        }
        else {
            return path;
        }
    }
    public void writeTasks(TaskList tasks){
        String s = "";
        for(Task i: tasks.getList()){
            s = s + i.toString() + "\n";
        }
        try{
            FileWriter fw = new FileWriter(edit);
            fw.write(s);
            fw.close();
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    
    public ArrayList<String> readFile() throws FileNotFoundException{
        try {
            ArrayList<String> tasks = new ArrayList<>();
            File f = new File(edit);
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                tasks.add(s.nextLine());
            }
            return tasks;
        }
        catch (FileNotFoundException e){
            throw new FileNotFoundException("No File");
        }
    }



}
