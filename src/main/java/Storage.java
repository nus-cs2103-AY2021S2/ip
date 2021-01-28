import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collection;

public class Storage {
    private static String fileName;

    public ArrayList<Task> load(String fileName,Ui ui) {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File f = new File(fileName);
            f.createNewFile();
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String strTask = s.nextLine();
                Task task = Parser.parseForText(strTask, ui);
                tasks.add(task);
            }
            this.fileName = fileName;
            s.close();
        } catch (IOException e) {
            ui.showError("Unable to create file");
        }
        return tasks;
    }

    public void save(Collection<Task> taskList) {
        try{
            FileWriter fw = new FileWriter(this.fileName);
            for(Task t : taskList) {
                fw.write(t.save());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("File cannot be opened");
        }
    }
}
