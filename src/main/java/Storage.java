import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
   * Storage is the class that assist the application in reading and writing from an
   * external file
   * 
   */
public class Storage {
    String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void store(String line) throws DukeException {
        try{
            FileWriter fw = new FileWriter(this.filePath, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.println(line);
            pw.close();
        } catch (IOException e) {
            throw new DukeException("Error storing file");
        }
    }

    public Scanner read() throws DukeException {
        try {
            Scanner fileScanner = new Scanner(new File(this.filePath));
            return fileScanner;
        } catch (FileNotFoundException e) {
            throw new DukeException("Error scanning file");
        }
    }

    public void rewrite(TaskList taskList) throws DukeException {
        FileWriter tfw;
        try {
            tfw = new FileWriter(this.filePath);
            BufferedWriter tbw = new BufferedWriter(tfw);
            PrintWriter tpw = new PrintWriter(tbw);
            for (int i = 1; i <= taskList.size(); i++) {
                Task writeTask = taskList.get(i-1);
                tpw.println(i + "." + writeTask.toString());
            }
            tpw.close();
        } catch (IOException e) {
            throw new DukeException("Error editing file");
        }
    }

    public List<Task> load() throws DukeException {
        try {
            List<Task> taskList = new ArrayList<Task>(100);
        FileWriter fw = new FileWriter(this.filePath, true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter pw = new PrintWriter(bw);
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String readLine = "";
        while ((readLine = br.readLine()) != null) {
            String[] read = readLine.split("]", 2);
            String type = read[0].replace("[", "");
            if (type.contains("T")) {
                String sequence = read[1];
                String[] data = sequence.split("] ");
                Task newTask = new ToDos(data[1]);
                if (sequence.contains("\u2718")) {
                    newTask.markAsDone();
                }
                taskList.add(newTask);
            } else if (type.contains("D")) {
                try {
                    String sequence = read[1];
                    String[] data = sequence.split("] ");
                    String secondData = data[1].replace("(by: ", "/").replace(")", "/");
                    String[] seperateTime = secondData.split("/");
                    SimpleDateFormat formatter = new SimpleDateFormat("MMM d yyyy", Locale.ENGLISH);
                    Date date = formatter.parse(seperateTime[1]);
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                    Task newTask = new Deadline(seperateTime[0], dateFormat.format(date));
                    if (sequence.contains("\u2718")) {
                        newTask.markAsDone();
                    }
                    taskList.add(newTask);
                } catch (ParseException ex) {
                    String sequence = read[1];
                    String[] data = sequence.split("] ");
                    String secondData = data[1].replace("(by: ", "").replace(")", "");
                    SimpleDateFormat formatter = new SimpleDateFormat("MMM d yyyy", Locale.ENGLISH);
                    Date date = formatter.parse(secondData);
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                    Task newTask = new Deadline(data[0], dateFormat.format(date));
                    if (sequence.contains("\u2718")) {
                        newTask.markAsDone();
                    }
                    taskList.add(newTask);
                }
            } else if (type.contains("E")) {
                try {
                    String sequence = read[1];
                    String[] data = sequence.split("] ");
                    String secondData = data[1].replace("(at: ", "/").replace(")", "/");
                    String[] seperateTime = secondData.split("/");
                    SimpleDateFormat formatter = new SimpleDateFormat("MMM d yyyy", Locale.ENGLISH);
                    Date date = formatter.parse(seperateTime[1]);
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Task newTask = new Events(seperateTime[0], dateFormat.format(date));
                    if (sequence.contains("\u2718")) {
                        newTask.markAsDone();
                    }
                    taskList.add(newTask);
                } catch (ParseException ex) {
                    String sequence = read[1];
                    String[] data = sequence.split("] ");
                    String secondData = data[1].replace("(at: ", "").replace(")", "");
                    SimpleDateFormat formatter = new SimpleDateFormat("MMM d yyyy", Locale.ENGLISH);
                    Date date = formatter.parse(secondData);
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Task newTask = new Events(data[0], dateFormat.format(date));
                    if (sequence.contains("\u2718")) {
                        newTask.markAsDone();
                    }
                    taskList.add(newTask);

                }

            }
        }
        pw.close();
        br.close();
        return taskList;
        }catch(IOException | ParseException e){
            throw new DukeException("Error Loading File");
        }
        
    }
}
