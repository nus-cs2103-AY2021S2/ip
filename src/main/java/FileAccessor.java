import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class FileAccessor {
    public static void ReadFromTasks(String path, ArrayList<Task> tasks) throws FileNotFoundException {
        File f = new File(path);
        Scanner sc = new Scanner(f);
        while(sc.hasNext()) {
            String s = sc.nextLine();
            String[] split = s.split("\\|");// '|' is a special character, has a diff meaning also so need to escape
            // with backslash. but backslash also special character, so need to escape that too so another backslash.
            //or can also escape with [|]
            boolean isDone = false;
            if(split[1].equals("1")) {
                isDone = true;
            }
            //consider indexoutofbounds exception
            if(split[0].equals("T")) {
                tasks.add(new Todo(split[2], isDone));
            } else if (split[0].equals("E")) {
                tasks.add(new Event(split, isDone));
            } else if (split[0].equals("D")) {
                tasks.add(new Deadline(split, isDone));
            } else {
                System.out.println(split[0]);
                throw new IllegalArgumentException();
            }
        }
    }

    //assume in correct format
    /*public static void WriteToTasks(String path, ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(path);
        for(Task task : tasks) {
            String done = "0";
            if(task.isDone) {
                done = "1";
            }
            String s = "";
            if(task instanceof Todo) {
                Todo todo = (Todo)task;
                s = s + "T|" + done + "|" + todo.task + "\n";
            } else if (task instanceof Deadline) {
                Deadline deadline = (Deadline)task;
                s = s + "D|" + done + "|" + deadline.task + "|" + deadline.deadline + "\n";
            } else {
                Event event = (Event)task;
                s = s + "E|" + done + "|" + event.task + "|" + event.event + "\n";
            }
            fw.write(s);
        }
    }*/
}
