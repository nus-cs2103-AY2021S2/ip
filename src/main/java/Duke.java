import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.ArrayList;
import static java.lang.Integer.parseInt;


public class Duke {
    public static void main(String[] args) throws FileNotFoundException {
        Ui ui;
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String hello = "What can I do for you?\n";
        String goodbye = "Bye. Hope to see you again soon!\n";
        String SAVE_FILE_PATH = "./data/";
        String SAVE_FILE_NAME = "data.txt";
        Storage storage;
        storage = new Storage(SAVE_FILE_PATH,SAVE_FILE_NAME);
        ArrayList<String> input;
        TaskList tasks;
        ui = new Ui();
        try {
            input = storage.readFile();
            tasks = new TaskList(readInput(input));
        }
        catch (FileNotFoundException e){
            throw new FileNotFoundException("No File Detected");
        }
        ui.printResponse("Hello from\n" + logo);
        ui.printResponse(hello);
        String cmd = ui.readLine();
        String[] pre = cmd.split("\\s+");
        while(!cmd.equals("bye")) {
            int n;
            switch (pre[0]) {
                case "list" :
                    ui.printTasks(tasks);
                    storage.writeTasks(tasks);
                    break;
                case "done" :
                    n = parseInt(pre[1]) - 1;
                    doneTask(tasks, n,ui);
                    storage.writeTasks(tasks);
                    break;
                case "delete" :
                    n = parseInt(pre[1]) - 1;
                    deleteTask(tasks, n,ui);
                    storage.writeTasks(tasks);
                    break;
                case "deadline" :
                    addDeadline(tasks, cmd,ui);
                    storage.writeTasks(tasks);
                    break;
                case "event" :
                    addEvent(tasks, cmd,ui);
                    storage.writeTasks(tasks);
                    break;
                case "todo" :
                    addToDo(tasks, pre,ui);
                    storage.writeTasks(tasks);
                    break;
                default :
                    addError(ui);
            }
            cmd = ui.readLine();
            pre = cmd.split("\\s+");
        }
        ui.printResponse(goodbye);
    }

    private static void deleteTask(TaskList tasks, int n, Ui ui) {
        try {
            Task t = tasks.get(n);
            ui.printResponse("Noted. I've removed this task: ");
            tasks.remove(n);
            ui.printResponse(t.toString());
            int size = tasks.size();
            printTotalTasks(size,ui);
        }
        catch(IndexOutOfBoundsException e){
            ui.printResponse("Wrong task ID");
        }
    }

    private static void printTotalTasks(int size, Ui ui) {
        String msg;
        if(size != 1) {
             msg = "Now you have " + size + " tasks in the list";
        }
        else{
             msg = "Now you have " + size + " task in the list";
        }
        ui.printResponse(msg);
    }

    private static void doneTask(TaskList tasks, int n,Ui ui) {
        try {
            tasks.set(n, tasks.get(n).finish());
            ui.printResponse("Nice! I've marked this task as done: \n");
            ui.printResponse(tasks.get(n).toString());
        }
        catch(IndexOutOfBoundsException e){
            ui.printResponse("Wrong task ID");
        }
    }

    private static void addError(Ui ui) {
        ui.printResponse("Command not understood");
    }

    private static void addToDo(TaskList tasks, String[] pre, Ui ui) {
        if (pre.length > 0) {
            tasks.add(Parser.parseTodo(pre));
            ui.printResponse("Got it. I've added this task:");
            ui.printResponse(tasks.get(tasks.size() - 1).toString());
            printTotalTasks(tasks.size(),ui);
        }
        else {
            ui.printResponse("Please add a description for todo.");
        }
    }

    private static void addEvent(TaskList tasks, String cmd,Ui ui) {
        String[] pre2 = cmd.split("/at");
        try {
            tasks.add(Parser.parseEvent(pre2));
            ui.printResponse("Got it. I've added this task:");
            ui.printResponse(tasks.get(tasks.size() - 1).toString());
            printTotalTasks(tasks.size(),ui);
        }
        catch (ArrayIndexOutOfBoundsException  e){
            ui.printResponse("Please enter a description for event");
        }
    }

    private static void addDeadline(TaskList tasks, String cmd, Ui ui) {
        String[] pre2 = cmd.split("/by");
        try {
            tasks.add(Parser.parseDeadlinne(pre2));
            ui.printResponse("Got it. I've added this task:");
            ui.printResponse(tasks.get(tasks.size() - 1).toString());
            printTotalTasks(tasks.size(),ui);
        }
        catch (ArrayIndexOutOfBoundsException  e){
            ui.printResponse("Please enter a description for deadline");
        }
    }
    private static ArrayList<Task> readInput(ArrayList<String> strings){
        ArrayList<Task> tasks = new ArrayList<>();
        for(String i: strings){
            String[] texts = i.split("\\s+");
            switch(texts[0].charAt(1)){
                case 'D':
                    tasks.add(Parser.stringToDeadline(i));
                    break;
                case 'T':
                    tasks.add(Parser.stringToTodo(i));
                    break;
                case 'E':
                    tasks.add(Parser.stringToEvent(i));
                    break;
            }
        }
    return tasks;
    }

}