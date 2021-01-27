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

    TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public Task get(int taskNumber) {
        return this.tasks.get(taskNumber);
    }

//    private void add(Task task) {
//        this.tasks.add(task);
//    }

    public int size() {
        return this.tasks.size();
    }

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
            System.out.print(line + "Got it! Kobe marked down this date!\n");
            System.out.println(ind + "Kobe added this task:\n" + ind + ind +
                    currentTask);
        } catch (ParseException | NullPointerException e) { //not in the format
//            tasks.add(new Task(echoedText, type, condition));
            currentTask = new Task(echoedText, type, condition);
            this.tasks.add(currentTask);
            System.out.println(line + "Got it! Kobe added this task:\n" + ind + ind +
                    currentTask);
        }
        System.out.println(ind + "Kobe sees that you have " + this.tasks.size() + " task(s) in the list.\n" + line);
    }

    //Can put in Parser
    public void addItemByString(String text) {

        String[] intoParts1 = text.split("\\[", 2);
        String type = intoParts1[1].substring(0, 1);
//        System.out.println(Arrays.toString(intoParts1));
//        System.out.println("Type: " + type);

        String[] intoParts2 = intoParts1[1].split("\\[", 2);
        String isItDone = intoParts2[1].substring(0, 1);
//        System.out.println(Arrays.toString(intoParts2));
//        System.out.println("Done: " + isItDone);

        String[] intoParts3 = intoParts2[1].split("\\]", 2);
        String taskName = intoParts3[1].substring(1);
//        System.out.println("intoParts3: " + Arrays.toString(intoParts3));
//        System.out.println("Task: " + currentTask);
//        System.out.println("length: " + intoParts3[1].split(":", 2).length);

        String condition = "";

        if (intoParts3[1].split(":", 2).length != 1) { //There is a condition, cos it can be split even more
            //Task without the condition
            intoParts3 = intoParts3[1].substring(1).split("\\(", 2);
            String taskNameStr = intoParts3[0].substring(0);
            taskName = taskNameStr.substring(0, taskNameStr.length() - 1); //to fix extra space formed at the end
//            System.out.println("NewTask: " + taskName);

            //Getting the condition
            String[] intoParts4 = intoParts3[1].split(": ", 2);
//            System.out.println("intoParts4: " + Arrays.toString(intoParts4));
            String[] intoParts5 = intoParts4[1].split("\\)", 2);

            condition = intoParts5[0].substring(0);
//            System.out.println(Arrays.toString(intoParts5));
//            System.out.println("Condition: " + condition);
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
//            System.out.print(ind + "Kobe marked down this date!\n");
        } catch (ParseException | NullPointerException e) { //not in the format
            this.tasks.add(new Task(isItDoneBoolean, taskName, type, condition));
        }
    }

    public void completeTask(int taskNumber, Ui ui) {
        this.tasks.get(taskNumber).markAsDone();
        System.out.print(line + "Nice work! Kobe will mark your task as done!\n" + ind);
        System.out.println(ind + tasks.get(taskNumber));
        ui.showLine();
    }

    public void deleteTask(int taskNumber, Ui ui) {
        if (this.tasks.isEmpty()) { //Managing empty lists from the start
            System.out.print(ui.line() + "Kobe sees no more tasks from the list!\n" + line + "\n");
        } else {
            System.out.print(line + "Okay! Kobe will remove your task from the list!\n" + ind);
            System.out.println(ind + tasks.get(taskNumber));
            this.tasks.remove(taskNumber);
            System.out.println(ind + "Kobe sees that you now have " + tasks.size() + " task(s) in the list.");
            if (tasks.isEmpty()) { //If it's now empty, inform them.
                System.out.print(ind + "Your list is now empty!\n");
            }
            ui.showLine();
        }
    }

}
