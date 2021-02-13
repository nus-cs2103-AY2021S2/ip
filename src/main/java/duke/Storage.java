package duke;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Contains static methods that deal with loading tasks from the file and saving tasks in the file.
 */
class Storage {

    /**
     * Creates a new text file on the local computer so that the list of tasks can be saved on the
     * hard disk.
     */
    public static void createFile() throws IOException {
        File textFile = new File("./data/duke.txt");
        // Creates the folder if it is not on the local hard disk.
        new File("./data").mkdirs();
        textFile.delete();
        textFile.createNewFile();
    }

    /**
     * Updates the text file whenever the task list changes.
     *
     * @param taskList the corresponding task list in which the text file is based on.
     */
    public static void update(ArrayList<Task> taskList) {
        File textFile = new File("./data/duke.txt");
        try {
            createFile();
            FileWriter fileWriter = new FileWriter(textFile);
            for (Task task : taskList) {
                if (task instanceof ToDo) {
                    fileWriter.write("T | ");
                } else if (task instanceof Deadline) {
                    fileWriter.write("D | ");
                } else if (task instanceof Event) {
                    fileWriter.write("E | ");
                }
                if (task.isComplete()) {
                    fileWriter.write("1 | " + task.taskName);
                } else {
                    fileWriter.write("0 | " + task.taskName);
                }
                if (task instanceof Deadline) {
                    fileWriter.write(" | " + ((Deadline) task).getDate());
                }
                if (task instanceof Event) {
                    fileWriter.write(" | " + ((Event) task).getDate());
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
     * @param file the text file in which the task list is created from.
     * @param taskList the corresponding task list to copy the text file over.
     * @throws IOException whenever an issue with reading the file arises.
     */
    public static void convert(File file, ArrayList<Task> taskList) throws IOException {
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            String[] parsed = removeSpaces(scanner.nextLine());
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
        scanner.close();
    }

    private static String[] removeSpaces(String input) {
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
        String month = date.substring(0, 3);
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
