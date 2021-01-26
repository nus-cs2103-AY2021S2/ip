package duke;
import java.util.Scanner;

public class Parser {
    public Parser(){
    }

    protected void start(Duke duke,TaskList taskList, Ui ui, Storage storage){
        Scanner sc = new Scanner(System.in);
        String scannedLine = sc.nextLine();
        while (duke.isOn) {
            try {
                handleCommand(duke, scannedLine, taskList, ui, storage);
            } catch (Exception e) {
                System.out.format("%s\n☹ %s\n%s", Duke.line, e.getMessage(), Duke.line);
            } finally {
                scannedLine = sc.nextLine();
            }
        }
    }

    protected void handleCommand(Duke duke,String currLine, TaskList taskList, Ui ui, Storage storage) throws Exception {
        // basic commands
        currLine = currLine.toLowerCase();
        String[] parsedLine = currLine.split(" ");
        if (currLine.startsWith("list")) {
            ui.printListTasks(taskList);
        } else if(currLine.startsWith("find")){
            ui.find(taskList, currLine);
        } else if(currLine.startsWith("save")){
            storage.save(ui, taskList);
            System.out.println("Your information has been saved!");
        } else if (currLine.startsWith("bye")) {
            taskList.bye(duke);
            ui.bye();
        } else if (currLine.startsWith("delete")) {
            Task task = taskList.delete(Integer.parseInt(parsedLine[1]));
            ui.delete(task, taskList.list.size());
        } else if (currLine.startsWith("done")) {
            Task task = taskList.doTask(Integer.parseInt(parsedLine[1]));
            ui.doTask(task);
        } else if (currLine.startsWith("todo")) {
            Task task = taskList.addTask(new Todo(currLine));
            ui.addTask(task, taskList.list.size());
        } else if (currLine.startsWith("deadline")) {
            Task task = taskList.addTask(new Deadline(currLine));
            ui.addTask(task, taskList.list.size());
        } else if (currLine.startsWith("event")) {
            Task task = taskList.addTask(new Event(currLine));
            ui.addTask(task, taskList.list.size());
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
