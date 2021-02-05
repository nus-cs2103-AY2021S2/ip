import DukeTools.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Duke {
    private Ui ui;
    private TaskList taskList;
    private Storage storage;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e){
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    public void run() {
//        Welcome Page
        ui.printIntro();

//        Read Commands
//        Scanner scan = new Scanner(System.in);
        String input = " ";

//        Create Tools.Parser
        Parser parser = new Parser();

        while(!input.equals("bye")) {
            parser.readLine();
            input = parser.read;
            String command = parser.getCommand(input);

            switch (command) {
                case "bye":
                    storage.save(taskList);
                    ui.printBye();
                    break;

                case "list":
                    ui.printList(taskList);
                    break;

                case "done":
//                    Possible Error: index provided is out of bounds (NullPointerException
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;
                    Task task = taskList.getSingleTask(index);
                    task.markDone();

                    ui.printDone(task);
                    break;

                case "todo":
                    try {
                        String name = getTodoName(input);
                        Todo todo = new Todo(name);
                        taskList.addTask(todo);
                        ui.printTask(todo, taskList.getSize());
                    } catch(DukeException e) {
                        e.printError("Come On Fella! Your ToDo description cannot be empty!");
                    }
                    break;

                case "deadline":
                    try{
                        String name = getEventOrDeadlineName(input);
                        String by = getEventOrDeadlineAttribute(input);
                        LocalDate date = LocalDate.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                        Deadline deadline = new Deadline(name, date);
                        taskList.addTask(deadline);
                        ui.printTask(deadline, taskList.getSize());

                    } catch (DukeException e) {
                        e.printError("Hmm... You are either lacking a name or /by details!");
                    }
                    break;

                case "event":
                    try {
                        String name = getEventOrDeadlineName(input);
                        String at = getEventOrDeadlineAttribute(input);
                        Event event = new Event(name,at);
                        taskList.addTask(event);
                        ui.printTask(event, taskList.getSize());

                    } catch (DukeException e) {
                        e.printError("Hmm... You are either lacking a name or /at details!");
                    }
                    break;

                case "delete":
                    int i = Integer.parseInt(input.split(" ")[1]) - 1;
                    ui.printDelete(taskList.getSingleTask(i), taskList.getSize()-1);
                    taskList.deleteTask(i);
                    break;

                default:
//                    Does not exactly throw an exception*
                    ui.printUnknownCommand();
                    break;
            }
        }

    }

    public static void main(String[] args) {
//        filePath has been hard-coded: /data/<filename>
        new Duke("/data/modoc_tm.txt").run();
    }

    public static String getTodoName(String input) throws DukeException {
        try {
            String name = input.split(" ",2)[1].trim();
            return name;
        } catch (Exception e) {
            throw new DukeException();
        }
    }

    public static String getEventOrDeadlineName(String input) throws DukeException {
        try {
            String name = input.split("/")[0].split(" ",2)[1].trim();
            return name;
        } catch (Exception e) {
            throw new DukeException();
        }
    }

    public static String getEventOrDeadlineAttribute(String byDate) throws DukeException {
        try {
            String atBy = byDate.split("/")[1].split(" ",2)[1].trim();
            return atBy;
        } catch (Exception e) {
            throw new DukeException();
        }
    }

}
