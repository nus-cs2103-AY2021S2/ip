package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Scanner;

import duke.place.Place;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * A class that handles all I/O related matter.
 */
public class Storage {


    private static final int POSITION_DONE = 4;
    private static final int POSITION_AT = 8;

    private static final char MARK_TASK = 'T';
    private static final char MARK_DEADLINE = 'D';
    private static final char MARK_DONE = '0';
    private static final String MARK_AT = "@@@";
    private static final String MARK_SEPARATE = " | ";
    private static final String MARK_STATUS_ZERO = "0";
    private static final String MARK_STATUS_ONE = "1";

    private static final String FILE_PATH = "data";
    private static final String FILE_NAME_TASK = "taskData.txt";
    private static final String FILE_NAME_PLACE = "placeData.txt";

    private static final String ERROR_SAVE = "Unable to save file.";



    // Output date and time in this manner.
    private static final DateTimeFormatter FORMAT_PRINT =
            DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");

    private static File taskFile;
    private static File placeFile;
    private static Scanner scannerTask;
    private static Scanner scannerPlace;

    /**
     * Converts data that saved in taskData.txt to Task object.
     *
     * @param command Strings in the format of my own design.
     * @return Tasks to be stored in the TaskList.
     */
    public static Task loadTaskData(String command) {

        if (command.charAt(0) == MARK_TASK) {
            Todo result = new Todo(command.substring(POSITION_AT));
            if (command.charAt(POSITION_DONE) != MARK_DONE) {
                result.markAsDone();
            }
            return result;
        } else if (command.charAt(0) == MARK_DEADLINE) {
            int position = command.indexOf(MARK_AT);
            Deadline result = new Deadline(command.substring(POSITION_AT, position - 1),
                    LocalDateTime.from(FORMAT_PRINT.parse(command.substring(position + MARK_AT.length()))));
            if (command.charAt(POSITION_DONE) != MARK_DONE) {
                result.markAsDone();
            }
            return result;
        } else {
            int position = command.indexOf(MARK_AT);
            Event result = new Event(command.substring(POSITION_AT, position - 1),
                    LocalDateTime.from(FORMAT_PRINT.parse(command.substring(position + MARK_AT.length()))));
            if (command.charAt(POSITION_DONE) != MARK_DONE) {
                result.markAsDone();
            }
            return result;
        }
    }

    /**
     * Converts data that saved in placeData.txt to Place object.
     *
     * @param command Strings in the format of my own design.
     * @return Tasks to be stored in the PlaceList.
     */
    public static Place loadPlaceData(String command) {
        int position = command.indexOf(MARK_SEPARATE);
        return new Place(command.substring(0, position),
                command.substring(position + MARK_SEPARATE.length()));
    }

    /**
     * Converts Task to string in a designed manner.
     *
     * @param task Task that need to be saved.
     * @return Output string that need to be written into save data.
     */
    public static String saveTaskData(Task task) {
        if (task.getSaveType().charAt(0) == MARK_TASK) {
            return task.getSaveType() + MARK_SEPARATE
                    + (task.getStatus() ? MARK_STATUS_ONE : MARK_STATUS_ZERO)
                    + MARK_SEPARATE + task.getDescription() + "\n";
        } else {
            return task.getSaveType() + MARK_SEPARATE
                    + (task.getStatus() ? MARK_STATUS_ONE : MARK_STATUS_ZERO)
                    + MARK_SEPARATE + task.getDescription() + " " + MARK_AT + task.getSaveTime() + "\n";
        }
    }

    /**
     * Converts Place to string in a designed manner.
     *
     * @param place Place that need to be saved.
     * @return Output string that need to be written into save data.
     */
    public static String savePlaceData(Place place) {
        return place.getDescription() + MARK_SEPARATE + place.getLocation() + "\n";
    }



    /**
     * Initialize the save data file and scanner of that file.
     *
     * @throws IOException When unable to create a file due to any security reason.
     */
    public static void init() throws IOException {
        File dir = new File(FILE_PATH);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        taskFile = new File(FILE_PATH + File.separatorChar + FILE_NAME_TASK);
        if (!taskFile.exists()) {
            taskFile.createNewFile();
        }
        placeFile = new File(FILE_PATH + File.separatorChar + FILE_NAME_PLACE);
        if (!placeFile.exists()) {
            placeFile.createNewFile();
        }
        assert placeFile.exists() : "Cannot save data.";

        scannerTask = new Scanner(taskFile);
        scannerPlace = new Scanner(placeFile);

    }

    /**
     * Loads the entire save data to a TaskList by calling multiple loadTaskData.
     *
     * @return TaskList that presents the initial state of Duke robot.
     */
    public static TaskList loadToTaskList() {
        TaskList list = new TaskList();
        while (Objects.requireNonNull(scannerTask).hasNextLine()) {
            list.addJob(loadTaskData(scannerTask.nextLine()));
        }
        return list;
    }

    /**
     * Loads the entire save data to a PlaceList by calling multiple loadPlaceData.
     *
     * @return PlaceList that presents the initial state of Duke robot.
     */
    public static PlaceList loadToPlaceList() {
        PlaceList list = new PlaceList();
        while (Objects.requireNonNull(scannerPlace).hasNextLine()) {
            list.addPlace(loadPlaceData(scannerPlace.nextLine()));
        }
        return list;
    }

    /**
     * Initialises the writer and writes to save data file by calling multiple saveData.
     *
     * @param listT Data need to be converted and written to TaskList.
     * @param listP Data need to be converted and written to PlaceList.
     */
    public static void writeToData(TaskList listT, PlaceList listP) {
        String saveTaskData = "";
        String savePlaceData = "";
        for (int i = 0; i < listT.getSize(); i++) {
            saveTaskData = saveTaskData.concat(saveTaskData(listT.getJob(i)));
        }
        for (int j = 0; j < listP.getSize(); j++) {
            savePlaceData = savePlaceData.concat(savePlaceData(listP.getPlace(j)));
        }
        try {
            FileWriter writerTask = new FileWriter(taskFile);
            FileWriter writerPlace = new FileWriter(placeFile);
            writerTask.write(saveTaskData);
            writerPlace.write(savePlaceData);
            writerTask.close();
            writerPlace.close();
        } catch (IOException e) {
            System.out.println(ERROR_SAVE);
        }
    }
}
