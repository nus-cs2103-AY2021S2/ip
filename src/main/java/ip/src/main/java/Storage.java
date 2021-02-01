import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    protected String filePath;
    protected Duke bot;

    Storage(String filePath, Duke bot){
        this.filePath = filePath;
        this.bot = bot;
    }

    public void loadTasks(String TaskData) throws FileNotFoundException {
        File f = new File(this.filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        Task newTask = new Task("");

        String[] taskData = TaskData.split(" \\| ");
            String type = taskData[0];
            String doneStatus = taskData[1];
            if (type.equals("T")) {
                String details = taskData[2];
                newTask = new ToDo(details);
                bot.addToBot(newTask);

            } else if (type.equals("E")) {
                String content = taskData[2];
                String at = taskData[3];
                newTask = new Event(content, at);
                bot.addToBot(newTask);

            } else {
                String content = taskData[2];
                String by = taskData[3];
                newTask = new Deadline(content, by);
                bot.addToBot(newTask);
            }

            if (doneStatus == " 1 ") {
                newTask.markDone();
            }

        }

    public void updateFile() throws IOException {
        FileWriter fw = new FileWriter(this.filePath);
        for(Task element:this.bot.list.list) {
            fw.write(element.toString() + "\n");

        }
        fw.close();
    }

}
