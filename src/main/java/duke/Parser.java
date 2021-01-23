package duke;

public class Parser {
    TaskList taskList;
    
    Parser(TaskList taskList) {
        this.taskList = taskList;
    }
    
    void parse(String command) {
        if (command.equals("bye")) {
            Ui.exit();
        } else if (command.equals("list")) {
            taskList.listTask();
        } else if (command.startsWith("done")) {
            taskList.markDone(command.substring(5));
        } else if (command.startsWith("delete")) {
            taskList.deleteTask(command.substring(7));
        } else {
            taskList.addTask(command);
        }
    }
}
