public class Parser {
    private final TaskList taskList;
    private final Ui ui;
    private final Storage storage;

    public Parser(TaskList taskList, Ui ui, Storage storage) {
        this.taskList = taskList;
        this.ui = ui;
        this.storage = storage;
    }

    public Command parse(String input) throws NumberFormatException, EmptyArgumentException, InvalidDateTimeException, InvalidIndexInputException {
        String[] commandAndInput = input.split(" ", 2);
        String command = commandAndInput[0];

        switch (command) {

            case ToDoCommand.COMMAND_WORD:
                return prepareToDo(commandAndInput);

            case DeadlineCommand.COMMAND_WORD:
                return prepareDeadline(commandAndInput);

            case EventCommand.COMMAND_WORD:
                return prepareEvent(commandAndInput);

            case DoneCommand.COMMAND_WORD:
                return prepareDone(commandAndInput);

            case DeleteCommand.COMMAND_WORD:
                return prepareDelete(commandAndInput);

            case ListCommand.COMMAND_WORD:
                return prepareList();

            case ByeCommand.COMMAND_WORD:
                return prepareExit();

            default:
                return prepareHelp();
        }
    }

    private Command prepareToDo(String[] arguments) {
        return new ToDoCommand(this.taskList, this.ui, this.storage, arguments[0]);
    }

    private Command prepareDeadline(String[] arguments) throws EmptyArgumentException, InvalidDateTimeException { // check date input here using regex
        if (arguments.length == 1) {
            throw new EmptyArgumentException("Please input a valid task description!");
        } else {
            String description = arguments[1];
            String[] taskInputAndDate = description.split("/", 2);
            taskInputAndDate[0] = taskInputAndDate[0].trim();
            taskInputAndDate[1] = taskInputAndDate[1].trim();
            return new DeadlineCommand(this.taskList, this.ui, this.storage, taskInputAndDate);
        }
    }

    private Command prepareEvent(String[] arguments) throws EmptyArgumentException, InvalidDateTimeException { // check date input here using regex
        if (arguments.length == 1) {
            throw new EmptyArgumentException("Please input a valid task description!");
        } else {
            String description = arguments[1];
            String[] taskInputAndDate = description.split("/", 2);
            taskInputAndDate[0] = taskInputAndDate[0].trim();
            taskInputAndDate[1] = taskInputAndDate[1].trim();
            return new EventCommand(this.taskList, this.ui, this.storage, taskInputAndDate);
        }
    }

    private int calcListPos(String taskIndex, String command) throws InvalidIndexInputException { // use regex to check number
        if (false) {
            throw new InvalidIndexInputException("'" + command + "' is command word; please pass a numerical index or "
                    + "start your task with another word!");
        } else {
            return Integer.parseInt(taskIndex) - 1;
        }
    }

    private Command prepareDone(String[] arguments) throws InvalidIndexInputException, EmptyArgumentException {
        if (arguments.length == 1) {
            throw new EmptyArgumentException("Please pass an index after the 'done' command!");
        } else {
            int position = calcListPos(arguments[1], arguments[0]);
            if (this.taskList.getList().size() == 0){
                throw new InvalidIndexInputException("You have already done all tasks!");
            } else if (position > this.taskList.getList().size() || position <= 0) {
                throw new InvalidIndexInputException("Please input an index from 1 to " + this.taskList.getList().size() + "!");
            } else {
                return new DoneCommand(this.taskList, this.ui, this.storage, position);
            }
        }
    }

    private Command prepareDelete(String[] arguments) throws InvalidIndexInputException, EmptyArgumentException {
        if (arguments.length == 1) {
            throw new EmptyArgumentException("Please pass an index after the 'delete' command!");
        } else {
            int position = calcListPos(arguments[1], arguments[0]);
            if (this.taskList.getList().size() == 0){
                throw new InvalidIndexInputException("There are no tasks to delete!");
            } else if (position > this.taskList.getList().size() || position <= 0) {
                throw new InvalidIndexInputException("Please input an index from 1 to " + this.taskList.getList().size() + "!");
            } else {
                return new DeleteCommand(this.taskList, this.ui, this.storage, position);
            }
        }
    }

    private Command prepareList() {
        return new ListCommand(this.taskList, this.ui, this.storage);
    }

    private Command prepareExit() {
        return new ByeCommand(this.taskList, this.ui, this.storage);
    }

    private Command prepareHelp() {
        return new HelpCommand(this.taskList, this.ui, this.storage);
    }
}
