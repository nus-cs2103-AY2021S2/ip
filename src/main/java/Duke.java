/**
 * Duke is a task manager.
 * 
 * <p>Currently supports these functionalities:
 * 
 * <p>bye 
 *   - Close Duke
 * <p>list
 *   - List out all task
 * <p>done [number]
 *   - Mark selected task as done
 * <p>todo [description]
 *   - Add a todo task
 * <p>deadline [description] /by [due date]
 *   - Add a deadline task with a due date (YYYY-MM-DD)
 * <p>event [description] /at [date]
 *   - Add a event task with a date (YYYY-MM-DD)
 * <p>delete [number]
 *   - Delete a task
 * <p>save
 *   - save checklist to "data/dukeData.txt"
 * <p>load
 *   - Load previously saved checklist
 * <p>help
 *   - Display list of commands
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
     * Starts the Duke bot
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
                switch(command) { 
                case "bye": 
                    ui.exit(); 
                    return;
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
                } 
            } catch (DukeException e) {
                ui.displayError(e);
            }
        }
    }

    private void completeTask(String num) throws DukeException {
        int taskNum = Integer.parseInt(num);
        Task t = tasks.completeTask(taskNum - 1);
        ui.completeTask(t.toString());
    }

    private void addTask(Task t) {
        tasks.addTask(t);
        ui.addTask(t.toString(), tasks.size());
    }

    private void deleteTask(String num) throws DukeException {
        int taskNum = Integer.parseInt(num);
        Task t = tasks.deleteTask(taskNum - 1);
        ui.deleteTask(t.toString(), tasks.size());
    }
}
