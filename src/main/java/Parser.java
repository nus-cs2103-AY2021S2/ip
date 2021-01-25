public class Parser {

    TaskList taskList;
    Ui ui;

    public Parser(TaskList taskList, Ui ui) {
        this.taskList = taskList;
        this.ui = ui;
    }

    public boolean parse(String input) throws DukeException {
        if (input.equals("list")) {
            ui.print(taskList.toString());
        } else if (input.startsWith("done")) {
            int itemNo = Integer.parseInt(input.split(" ")[1]);
            Task selected = taskList.markAsDone(itemNo - 1);
            ui.displayDone(selected);
        } else if (input.startsWith("delete")) {
            int itemNo = Integer.parseInt(input.split(" ")[1]);
            Task selected = taskList.delete(itemNo - 1);
            ui.displayDeleted(selected);
        } else if (input.startsWith("todo")) {
            Task task = Todo.parse(input);
            taskList.add(task);
            ui.displayAdded(task);
        } else if (input.startsWith("deadline")) {
            Task task = Deadline.parse(input);
            taskList.add(task);
            ui.displayAdded(task);
        } else if (input.startsWith("event")) {
            Task task = Event.parse(input);
            taskList.add(task);
            ui.displayAdded(task);
        } else if (input.equals("bye")) {
            ui.bye();
            return false;
        } else {
            String error = "Sorry! I don't know what that means.";
            throw new DukeException(error);
        }
        return true;
    }
}