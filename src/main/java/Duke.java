import java.util.Scanner;

public class Duke {
    public static final String GREETING = "Hello! I'm Duke\nWhat can I do for you?";
    public static final String BYE = "Bye. Hope to see you again soon!";
    public static final String FILE_PATH = "data/duke.txt";

    private final Scanner sc;
    private final Ui ui;
    private final Storage storage;
    private final TaskList taskList;

    public Duke(String path){
        this.sc = new Scanner(System.in);
        this.storage = new Storage(path);
        this.taskList = new TaskList(storage.readDataFromFile());
        this.ui = new Ui();
    }

    public static void main(String[] args) {
        Duke duke = new Duke(FILE_PATH);
        duke.run();
    }

    public void printWelcome(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        ui.printResponse(GREETING);
    }

    public void run() {
        printWelcome();
        while (sc.hasNextLine()){
            String command = sc.nextLine().trim();
            if (command.equalsIgnoreCase(Command.BYE.getAction())){
                ui.printResponse(BYE);
                break;
            } else if (command.equalsIgnoreCase(Command.LIST.getAction())){
                ui.printAllTask(taskList.getList());
            } else if (command.equalsIgnoreCase(Command.DONE.getAction()) ||
                    command.equalsIgnoreCase(Command.DELETE.getAction())){
                try {
                    throw new InvalidIndex(command, taskList.getList().size());
                } catch (InvalidIndex e){
                    ui.printResponse(e.getMessage());
                }
            } else if (command.toLowerCase().startsWith(Command.DONE.getAction() + " ")){
                try{
                    int doneIndex = Integer.parseInt(command.substring(5));
                    String result = taskList.finishTask(doneIndex);
                    ui.printResponse(result);
                } catch (NumberFormatException | InvalidIndex e){
                    System.out.println(e.getMessage());
                }
            } else if (command.toLowerCase().startsWith(Command.DELETE.getAction() + " ")){
                try{
                    int deleteIndex = Integer.parseInt(command.substring(7));
                    String result = taskList.deleteTask(deleteIndex);
                    ui.printResponse(result);
                } catch (NumberFormatException | InvalidIndex e){
                    ui.printResponse(e.getMessage());
                }
            } else {
                try {
                    String status = taskList.addTask(command);
                    ui.printResponse(status);
                } catch (NoSuchCommandException | EmptyTaskException | InvalidTask e){
                    ui.printResponse(e.getMessage());
                }
            }
            //update the file
            storage.writeDataToFile(taskList.getList());
        }
    }
}
