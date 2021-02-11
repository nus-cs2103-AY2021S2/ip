package duke.utils;

import duke.task.Task;
import duke.task.TaskList;
import duke.ui.ErrorBox;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Statistics class that keep track of the number of done tasks;
 */
public class Statistics {
    private static final String FILEPATH = "data/Statistics.txt";

    private static int totalTaskHistory = 0;

    private int numberOfDoneTasks;
    private int numberOfIncompleteTask;

    public Statistics (int numberOfDoneTasks, int numberOfIncompleteTask) {
        this.numberOfDoneTasks = numberOfDoneTasks;
        this.numberOfIncompleteTask = numberOfIncompleteTask;
    }

    /**
     * Generate the statistics of the current taskList.
     * @return the current statistics of the current taskList.
     */
    public static Statistics getStatistics() {
        int numberOfDoneTasks = 0;
        int numberOfIncompleteTasks = 0;
        List<Task> tasks = TaskList.getTaskList();

        for (Task task : tasks) {
            if (task.getDoneStatus().equals("X")) {
                numberOfDoneTasks++;
            } else {
                numberOfIncompleteTasks++;
            }
        }

        return new Statistics(numberOfDoneTasks, numberOfIncompleteTasks);
    }

    /**
     * This method write the Statistics of Duke in Statistics.txt.
     *
     */
    public static void updateStatistics(Task task) {
        File statsFile = new File(FILEPATH);
        if (!statsFile.exists()) {
            initialize();
        }

        totalTaskHistory++;
        try {
            FileWriter fw = new FileWriter(FILEPATH, true);
            fw.write(reformatTask(task));
            fw.close();
        } catch (IOException err) {
            ErrorBox.display(err.getMessage());
        }

    }

    /**
     * Reformats the task to be written in file.
     * @param task user task.
     * @return a different string format of the task.
     */
    private static String reformatTask(Task task) {
        String taskDisplay = task.toString();
        String taskType = taskDisplay.substring(0, 3);
        String taskStatus = taskDisplay.substring(3, 6);
        String taskContent = taskDisplay.substring(9);

        return totalTaskHistory + "\n" + taskType + taskStatus + taskContent + "\n";
    }

    /**
     * Initializes the statistic file.
     */
    public static void initialize() {
        File statisticsFile = new File(FILEPATH);
        if (!statisticsFile.exists()) {
            try {
                File pastRecord = new File("data/Duke.txt");
                pastRecord.delete();
                pastRecord.createNewFile();
                statisticsFile.createNewFile();
                FileWriter fw = new FileWriter(FILEPATH);
                fw.write("Task history: \n");
                fw.close();
            } catch (IOException err) {
                err.printStackTrace();
            }
        }
    }

    /**
     * Clear all history in the statistics file.
     */
    public static void clear() {
        totalTaskHistory = 0;
        File file = new File(FILEPATH);
        file.delete();
    }


    @Override
    public String toString() {
        return String.format("You have %d doneTasks and %d incomplete tasks\n",
                numberOfDoneTasks, numberOfIncompleteTask);
    }
}
