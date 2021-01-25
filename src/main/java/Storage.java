import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

// DEALS WITH LOADING TASKS FROM FILE AND SAVING TASKS IN FILE
public class Storage {
    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws IOException{
        BufferedReader reader = null;
        ArrayList<Task> list = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(this.filePath));
            String line;
            Task task;
            line = reader.readLine();
            while (line != null) {
                String[] tokens = line.split(" \\| ");
                if (tokens[0].equals("T")) {
                    task = new Todo(tokens[2]);
                    task.isDone = tokens[1].equals("1");
                    list.add(task);
                    line = reader.readLine();
                } else if (tokens[0].equals("D")) {
                    LocalDate date = LocalDate.parse(tokens[3], DateTimeFormatter.ofPattern("MMM dd yyyy"));
                    task = new Deadline(tokens[2], date);
                    task.isDone = tokens[1].equals("1");
                    list.add(task);
                    line = reader.readLine();
                } else if (tokens[0].equals("E")) {
                    task = new Event(tokens[2], tokens[3]);
                    task.isDone = tokens[1].equals("1");
                    list.add(task);
                    line = reader.readLine();
                }
            }
        } catch (IOException e) {
            File file = new File("duke.txt");
        }
        return list;
    }

    public void save(ArrayList<Task> updatedList) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(filePath);
        for (Task task : updatedList) {
            writer.println(task.toString());
        } writer.close();
    }
}
