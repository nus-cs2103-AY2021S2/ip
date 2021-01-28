/**
 * Duke is a task manager.
 * <p>
 * Currently supports these functionalities:
 * <p>
 * bye 
 * <p>  - Close Duke
 * <p>
 * list
 * <p>  - List out all task
 * <p>
 * done [number]
 * <p>  - Mark selected task as done
 * <p>
 * todo [description]
 * <p>  - Add a todo task
 * <p>
 * deadline [description] /by [due date]
 * <p>  - Add a deadline task with a due date (YYYY-MM-DD)
 * <p>
 * event [description] /at [date]
 * <p>  - Add a event task with a date (YYYY-MM-DD)
 * <p>
 * delete [number]
 * <p>  - Delete a task
 * <p>
 * save
 * <p>  - save checklist to "data/dukeData.txt"
 * <p>
 * load
 * <p>  - Load previously saved checklist
 * <p>
 * help
 * <p>  - Display list of commands
 * <p>
 * search [keyword/date]
 * <p>  - Display all task containing the following keyword.
 * <p>  - If keyword is in a valid date format(YYYY-MM-DD), display all task on that date.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui();
        
        try {
            tasks = new TaskList(storage.loadTaskList());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke("data/dukeData.txt");
        duke.start();
    }

    /**
     * Starts the Duke bot.
     */
    public void start() {
        ui.greetings();

        for (;;) {

            String input = ui.readInput();

            try {
                Parser.parseInput(input);
            } catch (DukeInputException e) {
                ui.displayError(e);
                continue;
            }

            String[] s = input.split(" ", 2);
            String command = s[0];
            String args = s.length == 2 ? s[1] : "";

            try {
                switch (command) { 
                case "bye": 
                    if (exit()) {
                        return;
                    }
                    break;
                case "list": 
                    ui.displayList(tasks.listOutTask());
                    break; 
                case "done":
                    completeTask(args);
                    break;
                case "todo":
                    addTask(Todo.createTodo(args));
                    break;
                case "deadline":
                    addTask(Deadline.createDeadline(args));
                    break;
                case "event":
                    addTask(Event.createEvent(args));
                    break;
                case "delete":
                    deleteTask(args);
                    break;
                case "save":
                    storage.saveTaskList(tasks.toList());
                    ui.saved();
                    break;
                case "load":
                    tasks = new TaskList(storage.loadTaskList());
                    ui.loaded();
                    break;
                case "help":
                    ui.help();
                    break;
                case "search":
                    ui.displayList(tasks.search(args));
                    break;
                } 
            } catch (DukeException e) {
                ui.displayError(e);
            }
        }
    }

    private void completeTask(String num) throws DukeInputException {
        int taskNum = Integer.parseInt(num);
        Task t = tasks.completeTask(taskNum - 1);
        ui.completeTask(t.toString());
    }

    private void addTask(Task t) {
        tasks.addTask(t);
        ui.addTask(t.toString(), tasks.size());
    }

    private void deleteTask(String num) throws DukeInputException {
        int taskNum = Integer.parseInt(num);
        Task t = tasks.deleteTask(taskNum - 1);
        ui.deleteTask(t.toString(), tasks.size());
    }

    private boolean exit() {
        String s = ui.saveFilePrompt();
        try {
            Parser.parseYesNo(s);
        } catch (DukeInputException e) {
            ui.displayError(e);
            return exit();
        }
        if (s.equals("y")) {
            try {
                storage.saveTaskList(tasks.toList());
                ui.saved();
            } catch (DukeException e) {
                ui.displayError(e);
                return false;
            }
        }
        ui.exit();
        return true;
    }
}
