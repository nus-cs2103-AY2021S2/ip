package main.java.duke;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Storage {

    public Storage() {

    }

    public static void saveTaskList() {
        try {
            File file = new File("src/main/java/duke/duke.txt");
            FileWriter fw = new FileWriter(file);
            //PrintWriter pw = new PrintWriter(file);
            for (int i = 0; i < Parser.getTaskList().size(); ++i) {
                fw.write(Parser.getTaskList().get(i).toString() + "\n");
            }
            fw.close();
            //pw.close();
        } catch (Exception ex) {
            System.out.println("error" + ex.getMessage());
        }
    }

    public static void loadTaskList() throws Exception {
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
