package mike;

import command.Command;
import exception.MikeCommandExecutionException;

public class Mike {

    private final String logo =
            "  __  __   _   _               _     _                _    _                      _\n" +
            " |  \\/  | (_) | |             | |   | |              | |  | |                    | |   \n" +
            " | \\  / |  _  | | __   ___    | |_  | |__     ___    | |__| |   __ _  __      __ | | __\n" +
            " | |\\/| | | | | |/ /  / _ \\   | __| | '_ \\   / _ \\   |  __  |  / _` | \\ \\ /\\ / / | |/ /\n" +
            " | |  | | | | |   <  |  __/   | |_  | | | | |  __/   | |  | | | (_| |  \\ V  V /  |   < \n" +
            " |_|  |_| |_| |_|\\_\\  \\___|    \\__| |_| |_|  \\___|   |_|  |_|  \\__,_|   \\_/\\_/   |_|\\_\\\n" +
            "                                                                                       \n" +
            "                                           __     __\n" +
            "                                          /'(  _  )`\\\n" +
            "                                         / . \\/^\\/ . \\\n" +
            "                                        /  _)_`-'_(_  \\\n" +
            "                                       /.-~   ).(   ~-.\\\n" +
            "                                      /'     /\\_/\\     `\\\n" +
            "                                             \"-V-\"";

    public String filePath = System.getProperty("user.dir") + "/data/TodoList.txt";
    public boolean isRunning;
    public TaskList taskList;
    Storage storage;

    /**
     * Mike.Mike chatbot constructor
     */
    public Mike() {
        this.isRunning = true;
        this.taskList = new TaskList();
    }

    /**
     * Initialises mike chatbot by printing welcome message and reading stored tasklist file
     */
    public void mikeInit() {
        System.out.println("Hello from\n" + this.logo);
        this.storage = new Storage(filePath);
        this.taskList = storage.readListFromFile();
    }

    /**
     * Initialises mike chatbot by printing welcome message and reading stored tasklist file from specified filepath
     * @param filepath filepath to saved taskList
     */
    public void mikeInit(String filepath) {
        this.filePath = filepath;
        this.mikeInit();
    }

    /**
     * Executes the command provided and returns the response generated by the command
     * @param inputCommand Command.Command object containing parameters required
     * @return Response generated by the command
     */
    public String getResponse(Command inputCommand) throws MikeCommandExecutionException {
        try {
            this.isRunning = !inputCommand.isExitCommand();
            this.taskList = inputCommand.runCommand(this.taskList);
            this.storage.updateListFile(this.taskList);
            return inputCommand.getResponse();
        } catch (MikeCommandExecutionException e) {
            throw new MikeCommandExecutionException(e.getCommand() ,e.getMessage());
        }
    }
}


