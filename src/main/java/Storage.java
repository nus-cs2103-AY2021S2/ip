import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    protected String filePath;
    private static final Ui ui = new Ui();

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void save(TaskList taskList) {
        StringBuilder data = new StringBuilder();
        try {
            FileWriter writer = new FileWriter(filePath);
            for(int i = 0; i < taskList.getSize(); i++) {
                data.append(taskList.getTask(i));
                if(i < taskList.getSize() - 1) {
                    data.append("\n");
                }
            }
            writer.write(data.toString());
            writer.close();
        } catch(IOException error) {
            ui.showSavingError();
        }
    }

    public List<Task> load() throws DukeException {
        List<Task> data = new ArrayList<>();
        try {
            File txt = new File(this.filePath);
            if(!txt.exists()) {
                File parentDir = txt.getParentFile();
                if(!parentDir.exists()) {
                    parentDir.mkdir();
                }
                txt.createNewFile();
            }
            Scanner myReader = new Scanner(txt);
            while(myReader.hasNextLine()) {
                String[] taskInfo = myReader.nextLine().split(" \\| ");
                Task curr;
                switch(taskInfo[0]) {
                    case "T":
                        curr = new Todo(taskInfo[2]);
                        if(taskInfo[1].equals("\u2713")) {
                            curr.markAsDone();
                        }
                        data.add(curr);
                        break;
                    case "D":
                        String localDate = taskInfo[3].replaceAll(" ", "-");
                        curr = new Deadline(taskInfo[2], LocalDate.parse(localDate, DateTimeFormatter.ofPattern("dd-MMM-yyyy")));
                        if(taskInfo[1].equals("\u2713")) {
                            curr.markAsDone();
                        }
                        data.add(curr);
                        break;
                    case "E":
                        curr = new Event(taskInfo[2], taskInfo[3]);
                        if(taskInfo[1].equals("\u2713")) {
                            curr.markAsDone();
                        }
                        data.add(curr);
                        break;
                }
            }
        } catch(IOException | DateTimeParseException error) {
            ui.showLoadingError();
        }
        return data;
    }
}
