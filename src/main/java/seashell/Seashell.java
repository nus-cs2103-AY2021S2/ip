package seashell;

import java.util.Scanner;

public class Seashell {

    private TaskList taskListObj;
    private final SaveHandler saveHandler;
    private final Ui ui;

    public Seashell() {
        this.saveHandler = new SaveHandler();
        this.taskListObj = new TaskList(this.saveHandler.loadSave());
        this.ui = new Ui();
    }

    public void start() {
        ui.showWelcome();
        Scanner sc = new Scanner(System.in);
        Parser parser = new Parser();
        boolean isExit = false;
        while (!isExit) {
            String command = sc.nextLine();
            try {
                CommandType commandType = parser.parse(command);
                switch (commandType) {
                    case EXIT:
                        ui.showExit();
                        isExit = true;
                        break;
                    case LIST:
                        this.taskListObj.listTasks();
                        break;
                    case DONE:
                        this.taskListObj = this.taskListObj.setDone(command, this.saveHandler);
                        break;
                    case DELETE:
                        this.taskListObj = this.taskListObj.delete(command, this.saveHandler);
                        break;
                    case TODO:
                        this.taskListObj = this.taskListObj.createTodo(command, this.saveHandler);
                        break;
                    case DEADLINE:
                        this.taskListObj = this.taskListObj.createDeadline(command, this.saveHandler);
                        break;
                    case EVENT:
                        this.taskListObj = this.taskListObj.createEvent(command, this.saveHandler);
                        break;
                    case HELP:
                        System.out.println(Ui.HELP_TEXT);
                        break;
                    case CLEAR:
                        this.taskListObj = this.taskListObj.clear(this.saveHandler);
                        break;
                    case FIND:
                        this.taskListObj.find(command);
                        break;
                    case INVALID:
                        throw new SeashellException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (SeashellException e) {
                ui.showError(e.getMessage());
            }
        }
    }
}
