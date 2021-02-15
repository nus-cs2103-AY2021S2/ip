package kobe;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;


public class TaskList {
    private ArrayList<Task> tasks;
    public static String ind = "    ";
    public static String line = ind + "____________________________________________________________\n" + ind;
    public static String line2 = ind + "____________________________________________________________\n";

    /**
     * Constructor for TaskList.
     */
    TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Gets the task associated to the task number in the TaskList.
     *
     * @param taskNumber  refers to the task number of the corresponding task in the TaskList
     * @return  the task associated to the task number in the TaskList.
     */
    public Task get(int taskNumber) {
        return this.tasks.get(taskNumber);
    }

    /**
     * Gets the size of the TaskList.
     *
     * @return  the size of the TaskList.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Adds a task into the TaskList, based on the command typed by the user.
     *
     * @param echoedText  the name of the task
     * @param type  the type of the task
     * @param condition  the condition of the task
     */
    public void addItem(String echoedText, String type, String condition) {
        //Recognise if condition is time
        DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
        df1.setLenient(false);
        Task currentTask;
        try {
            df1.parse(condition);
            LocalDate d1 = LocalDate.parse(condition);
            currentTask = new Task(false, echoedText, type, d1);
            this.tasks.add(currentTask);
            Ui.addAddDateTaskResponse(currentTask, this.tasks.size());

        } catch (ParseException | NullPointerException e) { //not in the format
            currentTask = new Task(echoedText, type, condition);
            this.tasks.add(currentTask);
            Ui.addAddNormalTaskResponse(currentTask, this.tasks.size());
        }
    }

    /**
     * Adds a task into the TaskList,
     * based on the formatted command string read from the saved text file
     *
     * @param text  the line of formatted command string from the saved file
     */
    public void addItemByString(String text) {

        String[] intoParts1 = text.split("\\[", 2);
        String type = intoParts1[1].substring(0, 1);

        String[] intoParts2 = intoParts1[1].split("\\[", 2);
        String isItDone = intoParts2[1].substring(0, 1);

        String[] intoParts3 = intoParts2[1].split("\\]", 2);
        String taskName = intoParts3[1].substring(1);

        String condition = "";

        if (intoParts3[1].split(":", 2).length != 1) { //There is a condition, cos it can be split even more
            //Task without the condition
            intoParts3 = intoParts3[1].substring(1).split("\\(", 2);
            String taskNameStr = intoParts3[0].substring(0);
            taskName = taskNameStr.substring(0, taskNameStr.length() - 1); //to fix extra space formed at the end

            //Getting the condition
            String[] intoParts4 = intoParts3[1].split(": ", 2);
            String[] intoParts5 = intoParts4[1].split("\\)", 2);

            condition = intoParts5[0].substring(0);
        } else {
        }

        if (type.equals("T")) {
            type = "todo";
        } else if (type.equals("D")) {
            type = "deadline";
        } else if (type.equals("E")) {
            type = "event";
        }

        boolean isItDoneBoolean = false;
        if (isItDone.equals("X")) {
            isItDoneBoolean = true;
        }

        //Recognise time
        DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
        df1.setLenient(false);
        try {
            df1.parse(condition);
            LocalDate d1 = LocalDate.parse(condition);
            this.tasks.add(new Task(isItDoneBoolean, taskName, type, d1));
        } catch (ParseException | NullPointerException e) { //not in the format
            this.tasks.add(new Task(isItDoneBoolean, taskName, type, condition));
        }
    }

    /**
     * Marks a task as completed
     *
     * @param taskNumber  the task number associated to the task that has been completed
     * @param ui  the user interface to inform the user of the outcome
     */
    public void completeTask(int taskNumber) {
        this.tasks.get(taskNumber).markAsDone();
        Task currentTask = this.tasks.get(taskNumber);
        Ui.addCompleteTaskResponse(taskNumber, currentTask);

    }

    /**
     * Deletes a task
     *
     * @param taskNumber  the task number associated to the task that has been completed
     * @param ui  the user interface to inform the user of the outcome
     */
    public void deleteTask(int taskNumber) {
        if (this.tasks.isEmpty()) { //Managing empty lists from the start
            Ui.addEmptyTaskListResponse();
        } else {
            Task currentTask = this.tasks.get(taskNumber);
            this.tasks.remove(taskNumber);
            int currentTaskListSize = this.tasks.size();
            Ui.addRemoveTaskResponse(currentTask, currentTaskListSize);
        }
    }

    /**
     * Finds a task corresponding to the String input by the user
     *
     * @param keyword  the String input by the user
     * @param ui  the user interface to inform the user of the outcome
     */
    public void findTask(String keyword, Ui ui) {
        ui.showLine();
        System.out.print(ui.ind() + "Kobe has found these tasks in your list:\n");
        String currentTaskName = "";
        int counter = 1;
        for (int i = 0; i < tasks.size(); i++) {
            currentTaskName = tasks.get(i).getTaskName();
            if (currentTaskName.indexOf(keyword) != -1) {
                System.out.println(ui.ind() + counter + ": " + tasks.get(i).toString());
                counter++;
            }
        }
        ui.showLine();
    }

}
