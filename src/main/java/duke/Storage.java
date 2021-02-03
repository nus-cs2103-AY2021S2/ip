package duke;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Storage class which handles the saving and laoding of tasklist to and from a text file
 */
public class Storage {

    public Storage() {

    }

    /**
     * Saves the existing tasks in the tasklist to a textfile
     * @throws IOException when the filename does not exist
     */
    public static void saveTaskList() throws IOException {
        try {
            File file = new File("src/main/java/duke/duke.txt");
            FileWriter fw = new FileWriter(file);
            for (int i = 0; i < Parser.getTaskList().size(); ++i) {
                fw.write(Parser.getTaskList().get(i).toString() + "\n");
            }
            fw.close();
        } catch (Exception ex) {
            System.out.println("error" + ex.getMessage());
        }
    }

    /**
     * Loads the tasks from the text file back into the task list array
     * @throws FileNotFoundException if the specified file does not exist
     */
    public static void loadTaskList() throws FileNotFoundException {
        String line  = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/java/duke/duke.txt"));
            while ((line = br.readLine()) != null) {
                String[] spiltLine = line.split("\\s+");
                char typeOfEvent = spiltLine[0].charAt(1);
                String desc = "";
                String date = "";
                int start = 0;
                for(int i = 1; i < spiltLine.length; ++i) {
                    if(spiltLine[i].equals("(by:")||spiltLine[i].equals("(at:")) {
                        start = i;
                        break;
                    }
                    if(desc.equals("")) {
                        desc += spiltLine[i];
                    } else {
                        desc = desc + " " + spiltLine[i];
                    }
                }
                for(int i = start + 1; i < spiltLine.length - 1; ++i) {
                    if(date.equals("")) {
                        date += spiltLine[i];
                    } else {
                        date = date + " " + spiltLine[i];
                    }
                }
                DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("E, MMM d yyyy");
                DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                date = LocalDate.parse(date, inputFormat).format(outputFormat);
                String time = spiltLine[spiltLine.length-1].substring(0,spiltLine[spiltLine.length-1].length()-1);
                date = date + " " + time;
                switch(typeOfEvent) {
                    case 'D': {
                        DeadlineTask task = new DeadlineTask(desc, date);
                        Parser.getTaskList().add(task);
                        break;
                    }
                    case 'E': {
                        EventTask task = new EventTask(desc, date);
                        Parser.getTaskList().add(task);
                        break;
                    }
                    case 'T': {
                        ToDoTask task = new ToDoTask(desc);
                        break;
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    };

}
