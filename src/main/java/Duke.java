import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException ex) {
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        Scanner sc = new Scanner(System.in);
        String request = "";

        while (!request.equals("bye")) {
            request = sc.nextLine();
            String[] tk = request.split(" ");
            request = tk[0];
            String taskname = "";
            // System.out.println(request);
            for (int i = 1; i < tk.length; i++) {
                taskname += tk[i];
                if (i < tk.length - 1) {
                    taskname += " ";
                }
            }

            if (request.equals("bye")) {
                ui.showBye();
                break;
            } else if (request.equals("list")) {
                ui.printList(tasks);
            } else if (request.equals("done")) {
                try {
                    int taskNo = Integer.parseInt(taskname);
                    ui.printMarked(tasks.markDone(taskNo));
                    storage.save(tasks);
                } catch (DukeException ex) {
                    ui.printFormatted(ex.getMessage());
                } catch (NumberFormatException ex) {
                    ui.printFormatted("Please enter integer values..");
                }
            } else if (request.equals("delete")) {
                try {
                    int taskNo = Integer.parseInt(taskname);
                    ui.printRemoved(tasks, tasks.removeTask(taskNo));
                    storage.save(tasks);
                } catch (DukeException ex) {
                    ui.printFormatted(ex.getMessage());
                } catch (NumberFormatException ex) {
                    ui.printFormatted("Please enter integer values..");
                }
            } else if (request.equals("todo")) {
                try {
                    Task task = Task.createTask(taskname, request, "", "");
                    ui.printAdded(tasks, tasks.addTask(task));
                    storage.save(tasks);
                } catch (DukeException ex) {
                    ui.printFormatted(ex.getMessage());
                }
            } else if (request.equals("deadline")) {
                try {
                    String[] deadStr = formatCommand(taskname);
                    Task task = Task.createTask(deadStr[0], request, deadStr[1], deadStr[2]);
                    ui.printAdded(tasks, tasks.addTask(task));
                    storage.save(tasks);
                } catch (DukeException ex) {
                    ui.printFormatted(ex.getMessage());
                }
            } else if (request.equals("event")) {
                try {
                    String[] eventStr = formatCommand(taskname);
                    Task task = Task.createTask(eventStr[0], request, eventStr[1], eventStr[2]);
                    ui.printAdded(tasks, tasks.addTask(task));
                    storage.save(tasks);
                } catch (DukeException ex) {
                    ui.printFormatted(ex.getMessage());
                }
            } else {
                try {
                    throwDK();
                } catch (DukeException ex) {
                    ui.printFormatted(ex.getMessage());
                }
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }



    public static void throwDK() throws DukeException {
        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public static String[] formatCommand(String n) throws DukeException {
        try {
            String[] arr = new String[3];
            arr[0] = n.split("/")[0].split(" ")[0];
            arr[1] = n.split("/")[1].substring(n.split("/")[1].split(" ")[0].length() + 1, n.split("/")[1].length());
            arr[2] = n.split("/")[1].split(" ")[0];
            return arr;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! The format you have entered is wrong.");
        }
    }

}
