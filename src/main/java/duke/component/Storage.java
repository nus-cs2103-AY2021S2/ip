package duke.component;

import duke.task.Task;
import duke.task.ToDo;
import duke.task.Deadline;
import duke.task.Event;

import java.io.*;
import java.util.ArrayList;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public String[] load() throws IOException {
        ArrayList<String> lines = new ArrayList<>();
        BufferedReader br = null;
        try {
            FileReader fr = new FileReader(this.filePath);
            br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } finally {
            if (br != null) {
                br.close();
            }
        }
        return lines.toArray(new String[lines.size()]);
    }

    public void save(TaskList tl) throws IOException {
        BufferedWriter bw = null;
        try {
            FileWriter fw = new FileWriter(this.filePath);
            bw = new BufferedWriter(fw);
            for (Task t : tl.getTasks()) {
                String line = "";
                String isDone = t.getIsDone() ? "1" : "0";
                if (t instanceof ToDo) {
                    line = "T|" + isDone + "|" + t.getName();
                } else if (t instanceof Deadline) {
                    Deadline dl = (Deadline) t;
                    line = "D|" + isDone + "|" + dl.getName() + "|" + dl.getSaveBy();
                } else if (t instanceof Event) {
                    Event e = (Event) t;
                    line = "D|" + isDone + "|" + e.getName() + "|" + e.getSaveAt();
                }
                bw.write(line);
                bw.newLine();
            }
        } finally {
            if (bw != null) {
                bw.close();
            }
        }
    }
}
