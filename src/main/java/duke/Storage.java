package duke;

import duke.command.ListCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private final String location = "./duke.txt";
    private final Path path;

    public Storage() {
        this.path = Paths.get(location);
        File file = new File(location);
        if(Files.notExists(this.path)){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void storeData(ArrayList<Task> list) throws IOException{
        FileWriter file = new FileWriter(this.location);
        for(Task t: list){
            String data = "";

            switch(t.getType()){
                case "todo":{
                    data = String.format("%s|%s|%s", t.getType(), t.getDescription(), t.getIsDone());
                    break;
                }
                case "event":{
                    data = String.format("%s|%s|%s|%s", t.getType(), t.getDescription(), ((Event)t).getAt(), t.getIsDone());
                    break;
                }
                case "deadline":{
                    data = String.format("%s|%s|%s|%s", t.getType(), t.getDescription(), ((Deadline)t).getBy(), t.getIsDone());
                    break;
                }
            }
            file.write(data + "\n");
        }
        file.close();
    }

    public ArrayList<Task> loadData() throws IOException{
        System.out.println("Loading saved data...");
        Scanner sc = new Scanner(path.toFile());
        ArrayList<Task> list = new ArrayList<>();
        while(sc.hasNextLine()){
            String line = sc.nextLine();
            String[] ar = line.split("\\|");
            switch(ar[0]) {
                case "todo": {
                    Todo t = new Todo(ar[1]);
                    list.add(t);
                    break;
                }
                case "event": {
                    list.add(new Event(ar[1], LocalDate.parse(ar[2])));
                    break;
                }
                case "deadline": {
                    list.add(new Deadline(ar[1], LocalDate.parse(ar[2])));
                    break;
                }
            }
            if(ar[ar.length-1].equals("T")) list.get(list.size()-1).done();
        }
        new ListCommand(list);
        return list;


    }
}
