//import java.lang.reflect.Array;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        Ui ui = new Ui();
        Storage storage = new Storage(filePath);
        try {
            TaskList tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) throws Exception {
        new Duke("data/tasks.txt").run();
    }

    private void run() throws DukeException {
        runCommandLoopUntilExitCommand();
        exit();
    }

    private void runCommandLoopUntilExitCommand() {
        String commandText;
        do{
            commandText = ui.getUserCommand();
            String operator = new Parser().parseOperator(commandText);
            executeCommand(operator, commandText);
        } while(commandText.equals("bye"));
    }

    private void executeCommand(String operator, String commandText){
        Parser commandParser = new Parser();
        switch (operator){
            case "done":
                int taskNumberToComplete = commandParser.parseCommand(commandText);
                completeTask(taskNumberToComplete);
                break;
            case "delete":
                int taskNumberToDelete = commandParser.parseCommand(commandText);
                deleteTask(taskNumberToDelete);
                break;
            case "todo":
                String description = commandParser.parseCommand(commandText);
                addToDo(description);
                break;
            case "deadline":
                String[] detailsDeadline = commandParser.parseCommand(commandText);
                addDeadline(detailsDeadline );
                break;
            case "event":
                String[] detailsEvent = commandParser.parseCommand(commandText);
                addEvent(detailsEvent);
                break;
            case "display":
                displayList();
                break;
        }
        storage.updateTaskList(tasks);
    }

    private void addEvent(String[] details) throws DukeException{
        try {
            String description = details[0];
            String time = details[1];
            Event newTask = new Event(description, time);
            tasks.addTask(newTask);
        } catch (DukeException e){
            ui.showErrorMessage(e.getMessage());
        }
    }

    private void addDeadline(String[] details) throws DukeException{
        try {
            String description = details[0];
            String time = details[1];
            Deadline newTask = new Deadline(description, time);
            tasks.addTask(newTask);
        } catch (DukeException e){
            ui.showErrorMessage(e.getMessage());
        }
    }

    private void addToDo(String description) throws DukeException{
        try {
            ToDo newTask = new ToDo(description);
            tasks.addTask(newTask);
        } catch (DukeException e){
            ui.showErrorMessage(e.getMessage());
        }
    }

    private void completeTask(int taskNumber) throws DukeException{
        try{
            tasks.markTaskAsDone(taskNumber);
        } catch (DukeException e){
            ui.showErrorMessage(e.getMessage());
        }
    }

    private void deleteTask(int taskNumber) throws DukeException{
        try {
            tasks.deleteTask(taskNumber);
        } catch (DukeException e){
            ui.showErrorMessage(e.getMessage());
        }
    }

    private void displayList(){
        ui.printMyTask(tasks);
    }

    private void exit() {
        ui.showGoodbyeMessage();
        System.exit(0);
    }
}

//        while (input.hasNextLine()) {
//            String s = input.nextLine();
//            if (s.toLowerCase().equals("bye")) {
//                exit();
//                break;
//            }
//            if (s.toLowerCase().equals("list")) {
//                ui.printMyTask(tasks);
//            } else {
//                try {
//                    tasks.executeTask(s);
////                    executeTask(s, taskList, tasks);
//                } catch (DukeException e) {
//                    ui.showErrorMessage(e);
//                }
//            }
//        }
//    }
//
//    private void executeCommand(String s, TaskList myList) throws DukeException {
//        String[] parts = s.split(" ", 2);
//        String taskType = parts[0];
//
//        if (!taskList.contains(taskType.toLowerCase())) {
//            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
//        }
//
//        if (taskType.toLowerCase().equals("done")) {
//            try {
//                markTask(parts, myList);
//                FileWriting.saveTaskList(myList);
//            } catch (DukeException e) {
//                ui.showErrorMessage(e.getMessage());
//            }
//        }
//
//        if (taskType.toLowerCase().equals("delete")) {
//            try {
//                deleteTask(parts, myList);
//                FileWriting.saveTaskList(myList);
//            } catch (DukeException e) {
//                ui.showErrorMessage(e.getMessage());
//            }
//        }
//
//        if (taskType.toLowerCase().equals("todo")) {
//            try {
//                addToDo(parts, myList);
//                FileWriting.saveTaskList(myList);
//            } catch (DukeException e) {
//                ui.showErrorMessage(e.getMessage());
//            }
//        }
//
//        if (taskType.toLowerCase().equals("deadline")) {
//            try {
//                addDeadline(parts, myList);
//                FileWriting.saveTaskList(myList);
//            } catch (DukeException e) {
//                ui.showErrorMessage(e.getMessage());
//            }
//        }
//        if (taskType.toLowerCase().equals("event")) {
//            try {
//                addEvent(parts, myList);
//                FileWriting.saveTaskList(myList);
//            } catch (DukeException e) {
//                ui.showErrorMessage(e.getMessage());
//            }
//        }
//    }
//


/*

//    private ArrayList<Task> initialiseMyList(String filePath){
//        ArrayList<Task> myList = new ArrayList<>();
//        File f = new File(filePath); // create a File for the given file path
//        Scanner scanner = new Scanner(f); // create a Scanner using the File as the source
//        int taskNumber = 1;
//        while (scanner.hasNext()) {
//            String task = scanner.nextLine();
//            String type = task.substring(0,3);
//            String status = task.substring(4,5);
//            boolean isDone;
//            if (status.equals("\u2713")){
//                isDone = true;
//            } else{
//                isDone = false;
//            }
//
//            if (task.substring(0,2).equals("T")){
//                String[] description = {task.substring(7),""};
//                addToDo(description, myList);
//            }
//            if (task.substring(0,2).equals("D")){
//                String[] parts = task.substring(7).split(" (", 2);
//                String description = parts[0];
//                String time = parts[1].replace(")", "");
//
//            }
//            if (task.substring(0,2).equals("E")){
//                String[] parts = task.substring(7).split(" (", 2);
//                String description = parts[0];
//                String time = parts[1].replace(")", "");
//
//            }
//            addEvent(parts, myList);
//            myList.append();
//            taskNumber += 1;
//        }
//    }

//    private void runCommandLoop() {
//        Command command;
//        do {
//            String userCommandText = ui.getUserCommand();
//            command = new Parser().parseCommand(userCommandText);
//            CommandResult result = executeCommand(command);
//            recordResult(result);
//            ui.showResultToUser(result);
//        } while (!ExitCommand.isExit(command));
//    }


//    private void displayList(ArrayList<Task> myList){
//        System.out.println("---------------------------------------------");
//        System.out.println("Here are the tasks in your list:");
//        int len = myList.size();
//        for (int i = 1; i < len + 1; i++) {
//            Task curTask = myList.get(i - 1);
//            System.out.println(i + "." + curTask);
//        }
//        System.out.println("---------------------------------------------");
//    }

    private void markTask(String[] parts, TaskList myList) throws DukeException{
        // task to be done is empty
        if (parts.length == 1){
            throw new DukeException("OOPS!!! Please specify the task number.");
        } else {
            try {
                completeTask(parts, myList);
            } catch (DukeException e) {
                ui.showErrorMessage(e.getMessage());
            }
        }
    }

    private void completeTask(String[] parts, TaskList myList) throws DukeException{
        try {
            int taskNo = Integer.valueOf(parts[1]);
            Task curTask = myList.getTask(taskNo - 1);
            curTask.markAsDone();
            ui.showCompleteMessage(curTask);
//            System.out.println("---------------------------------------------");
//            System.out.println("Nice! I've marked this task as done:");
//            System.out.println(curTask);
//            System.out.println("---------------------------------------------");
        } catch (Exception e) {
            throw new DukeException("OOPS!!! The task cannot be found.");
        }
    }

    private void deleteTask(String[] parts, TaskList myList) throws DukeException {
        // task to be done is empty
        if (parts.length == 1) {
            throw new DukeException("OOPS!!! The task cannot be found.");
        } else {
            try {
                removeTask(parts, myList);
            } catch (DukeException e){
                ui.showErrorMessage(e.getMessage());
            }
        }
    }

    private void removeTask(String[] parts, TaskList myList) throws DukeException {
        try {
            int taskNo = Integer.valueOf(parts[1]);
            Task curTask = myList.getTask(taskNo - 1);
            myList.removeTask(taskNo - 1);
            ui.showDeleteMessage(curTask, myList.getSize());
//            System.out.println("---------------------------------------------");
//            System.out.println("Noted. I've removed this task:");
//            System.out.println(curTask);
//            System.out.println("Now you have " + myList.size() + " tasks in the list.");
//            System.out.println("---------------------------------------------");
        } catch (Exception e){
            throw new DukeException("OOPS!!! The task cannot be found.");
        }
    }

    private void addToDo(String[] parts, TaskList myList) throws DukeException{
        if(parts.length == 1) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        } else{
            if (parts[1].isBlank()){
                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
            }
            ToDo newTask = new ToDo(parts[1]);
            myList.add(newTask);
            ui.showAddMessage(newTask, myList.getSize());

//            System.out.println("---------------------------------------------");
//            System.out.println("Got it. I've added this task:");
//            System.out.println("  " + newTask);
//            System.out.println("Now you have " + myList.size() + " tasks in the list.");
//            System.out.println("---------------------------------------------");
        }
    }

    private void addDeadline(String[] parts, TaskList myList) throws DukeException{
        if (parts.length == 1) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
        } else {
            String task = parts[1];
            if (task.isBlank()){
                throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
            }

            String[] details = task.split(" /by ", 2);
            if (details.length == 1){
                throw new DukeException("OOPS!!! The time of a deadline is invalid.");
            }
            String description = details[0];
            String by = details[1];
            Deadline newTask = new Deadline(description, by);

            myList.add(newTask);
            ui.showAddMessage(newTask, myList.getSize());

//            System.out.println("---------------------------------------------");
//            System.out.println("Got it. I've added this task:");
//            System.out.println("  " + newTask);
//            System.out.println("Now you have " + myList.size() + " tasks in the list.");
//            System.out.println("---------------------------------------------");
        }
    }

    private void addEvent(String[] parts, TaskList myList) throws DukeException {
        if (parts.length == 1) {
            throw new DukeException("OOPS!!! The description of an event cannot be empty.");
        } else {
            String task = parts[1];
            if (task.isBlank()){
                throw new DukeException("OOPS!!! The description of an event cannot be empty.");
            }

            String[] details = task.split(" /at ", 2);
            if (details.length == 1) {
                throw new DukeException("OOPS!!! The time of an event is invalid.");
            }
            String description = details[0];
            String time = details[1];
            Event newTask = new Event(description, time);

            myList.add(newTask);
            ui.showAddMessage(newTask, myList.getSize());

//            System.out.println("---------------------------------------------");
//            System.out.println("Got it. I've added this task:");
//            System.out.println("  " + newTask);
//            System.out.println("Now you have " + myList.size() + " tasks in the list.");
//            System.out.println("---------------------------------------------");
        }
    }
}

 */