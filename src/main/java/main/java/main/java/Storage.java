package main.java;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

/**
 * The class that deals with loading tasks from the file and saving tasks in the file.
 */
class Storage {

    /**
     * Creates a new text file so that the list of tasks can be saved on the hard disk.
     */
    public static void createFile() throws java.io.IOException  {
        new File("./data").mkdirs();
        File textFile = new File("./data/duke.txt");
        textFile.delete();
        textFile.createNewFile();
    }

    /**
     * Updates the text file whenever the task list changes.
     *
     * @param taskList the corresponding task list in which the text file is based on
     */
    public static void update(ArrayList<Task> taskList) {
        File textFile = new File("./data/duke.txt");
        try {
            createFile();
            FileWriter fileWriter = new FileWriter(textFile);
            for (Task t : taskList) {
                if (t instanceof ToDo) {
                    fileWriter.write("T | ");
                } else if (t instanceof Deadline) {
                    fileWriter.write("D | ");
                } else if (t instanceof Event) {
                    fileWriter.write("E | ");
                }
                if (t.isComplete()) {
                    fileWriter.write("1 | " + t.taskName);
                } else {
                    fileWriter.write("0 | " + t.taskName);
                }
                if (t instanceof Deadline) {
                    fileWriter.write(" | " + ((Deadline) t).getDate());
                }
                if (t instanceof Event) {
                    fileWriter.write(" | " + ((Event) t).getDate());
                }
                fileWriter.write("\n");
            }
            fileWriter.close();
        } catch (IOException exception) {
            System.out.println(exception);
        }
    }

    /**
     * Method to read an existing text file and convert it into a corresponding task list within
     * the Dukebot.
     *
     * @param file the text file in which the task list is created from
     * @param taskList the corresponding task list to copy the text file over
     * @throws IOException
     */
    public static void convert(File file, ArrayList<Task> taskList) throws IOException{
        Scanner scan = new Scanner(file);
        while (scan.hasNext()) {
            String[] parsed = splitter(scan.nextLine());
            String type = parsed[0];
            boolean isDone = parsed[1].equals("1");
            String taskName;
            if (type.equals("T")) {
                taskName = parsed[2].substring(1);
                taskList.add(new ToDo(taskName, isDone));
            } else {
                taskName = parsed[2].substring(1);
                LocalDate date = dateConverter(parsed[3].substring(1));
                if (type.equals("D")) {
                    taskList.add(new Deadline(taskName, isDone, date));
                } else if (type.equals("E")) {
                    taskList.add(new Event(taskName, isDone, date));
                }
            }
        }
        scan.close();
    }

    private static String[] splitter(String input) {
        String[] result = new String[4];
        int counter = 0;
        result[0] = "";
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '|') {
                counter++;
                result[counter] = "";
            } else if (input.charAt(i) != ' ' || counter >= 2) {
                result[counter] += input.charAt(i);
            }
        }
        return result;
    }

    private static LocalDate dateConverter(String date) {
        String month = date.substring(0,3);
        String day = date.split(" ")[1];
        String year = date.split(" ")[2];
        String numericalMonth = " ";
        if (month.equals("Jan")) {
            numericalMonth = "01";
        } else if (month.equals("Feb")) {
            numericalMonth = "02";
        } else if (month.equals("Mar")) {
            numericalMonth = "03";
        } else if (month.equals("Apr")) {
            numericalMonth = "04";
        } else if (month.equals("May")) {
            numericalMonth = "05";
        } else if (month.equals("Jun")) {
            numericalMonth = "06";
        } else if (month.equals("Jul")) {
            numericalMonth = "07";
        } else if (month.equals("Aug")) {
            numericalMonth = "08";
        } else if (month.equals("Sep")) {
            numericalMonth = "09";
        } else if (month.equals("Oct")) {
            numericalMonth = "10";
        } else if (month.equals("Nov")) {
            numericalMonth = "11";
        } else if (month.equals("Dec")) {
            numericalMonth = "12";
        }
        return LocalDate.parse(year + "-" + numericalMonth + "-" + day);
    }
}
