package duke;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.task.TaskType;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;

import java.util.Scanner;

/**
 * Represents file storage & deals with loading tasks from the file and saving tasks in the file
 */
public class Storage {

    protected String filePath;

    /**
     * Creates a new instance of <code>Storage</code> at the specified file path.
     *
     * @param filePath
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the file at the given file path.
     *
     * @return Task list of existing tasks in file.
     * @throws DukeException If file path does not exist.
     * @throws IOException If there are input or output issues.
     */
    public TaskList load() throws DukeException, IOException {
        Path path = Path.of(this.filePath);
        if (Files.notExists(path)) {
            this.createFile();
            throw new DukeException("File path does not exist! "
                    + "A new file has been created according to given file path.");
        } else {
            return this.convertTextFileToTaskList();
        }
    }

    /**
     * Creates a new folder and new file at the given file path.
     *
     * @throws IOException
     */
    public void createFile() throws IOException {
        String folderPath = this.filePath.substring(0, this.filePath.lastIndexOf("/") + 1);
        File folder = new File(folderPath);
        folder.mkdirs();

        File file = new File(filePath);
        file.createNewFile();
    }

    /**
     * Append text to existing file.
     *
     * @param filePath     Path of file.
     * @param textToAppend Text to append to existing file.
     * @throws IOException If there are input or output errors.
     */
    public void appendToFile(String filePath, String textToAppend) throws IOException {
        Path path = Path.of(filePath);
        if (Files.notExists(path)) {
            this.createFile();
        }
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAppend + "\n");
        fw.close();
    }

    /**
     * Reads text file and returns task list with existing tasks in text file.
     *
     * @return Task list of existing tasks in file.
     * @throws IOException   If there are input or output errors.
     * @throws DukeException If description of task is not in the correct format.
     */
    public TaskList convertTextFileToTaskList() throws IOException, DukeException {
        File f = new File(this.filePath);
        Scanner sc = new Scanner(f);
        TaskList taskList = new TaskList();

        while (sc.hasNextLine()) {
            String nextLine = sc.nextLine();
            String taskTypeLetter = Character.toString(nextLine.charAt(1));
            if (taskTypeLetter.equals("T")) {
                taskList.addTask(TaskType.TODO, nextLine.substring(7), true, this);
            } else if (taskTypeLetter.equals("D")) {
                String[] nextLineArr = nextLine.substring(7).split(" \\(by: ");
                String[] dateTime = nextLineArr[1].substring(0, nextLineArr[1].lastIndexOf(")")).split(", ");
                String description = nextLineArr[0] + " /by "
                        + Storage.convertOutputDateToInputDate(dateTime[0]) + " "
                        + Storage.convertOutputTimeToInputTime(dateTime[1]);
                taskList.addTask(TaskType.DEADLINE, description, true, this);
            } else if (taskTypeLetter.equals("E")) {
                String[] nextLineArr = nextLine.substring(7).split(" \\(at: ");
                String[] dateTime = nextLineArr[1].substring(0, nextLineArr[1].lastIndexOf(")")).split(", ");
                String description = nextLineArr[0] + " /at "
                        + Storage.convertOutputDateToInputDate(dateTime[0]) + " "
                        + Storage.convertOutputTimeToInputTime(dateTime[1]);
                taskList.addTask(TaskType.EVENT, description, true, this);
            }
        }

        return taskList;
    }

    /**
     * Converts output date format into a format that can be parsed into <code>LocalDate</code>.
     *
     * @param outputDate Output date.
     * @return Date in a format that can be parsed into <code>LocalDate</code>
     */
    public static String convertOutputDateToInputDate(String outputDate) {
        String[] outputDateArr = outputDate.split(" ");
        String year = outputDateArr[2];
        String month;
        if (outputDateArr[0].equals("Jan")) {
            month = "01";
        } else if (outputDateArr[0].equals("Feb")) {
            month = "02";
        } else if (outputDateArr[0].equals("Mar")) {
            month = "03";
        } else if (outputDateArr[0].equals("Apr")) {
            month = "04";
        } else if (outputDateArr[0].equals("May")) {
            month = "05";
        } else if (outputDateArr[0].equals("Jun")) {
            month = "06";
        } else if (outputDateArr[0].equals("Jul")) {
            month = "07";
        } else if (outputDateArr[0].equals("Aug")) {
            month = "08";
        } else if (outputDateArr[0].equals("Sep")) {
            month = "09";
        } else if (outputDateArr[0].equals("Oct")) {
            month = "10";
        } else if (outputDateArr[0].equals("Nov")) {
            month = "11";
        } else {
            month = "12";
        }
        String day = String.format("%02d", Integer.parseInt(outputDateArr[1]));
        return year + "-" + month + "-" + day;
    }

    /**
     * Converts output time format into a format that can be parsed into <code>LocalTime</code>.
     *
     * @param outputTime Output time.
     * @return Time in a format that can be parsed into <code>LocalTime</code>
     */
    public static String convertOutputTimeToInputTime(String outputTime) {
        String amOrPm = outputTime.substring(outputTime.length() - 2);
        String time = outputTime.substring(0, outputTime.length() - 2);
        String[] timeArr = time.split(":");
        int additionalHours = 0;
        if (amOrPm.equals("PM")) {
            additionalHours = 12;
        }

        String hour = String.format("%02d", Integer.parseInt(timeArr[0]) + additionalHours);
        return hour + ":" + timeArr[1];
    }

}
