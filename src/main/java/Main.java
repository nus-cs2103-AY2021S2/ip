import java.io.IOException;

public class Main {
    private Storage storage;
    private Ui ui;
    private TaskList taskList;

    public static void main(String[] args) {
        new Main().run(args);
    }

    public void run(String[] args) {
        // Initialize the required components
        initialize(args);

        // Run infinite loop asking for user command until user enter exit command
        runLoop();

        // Exit the program
        exit();
    }

    private void initialize(String[] args) {
        try {
            this.ui = new Ui();
            this.storage = initializeStorage(args);
            this.taskList = storage.loadTasks();

            // Print greeting
            ui.printGreeting();
        } catch (InvalidStorageFilePathException ex) {
            System.out.println("Failed to initialize storage. Exiting...");
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            System.out.println("Failed to load storage file. Exiting...");
            throw new RuntimeException(ex);
        }
    }

    private void exit() {
        // Print exit message
        ui.printExitMessage();
    }

    private Storage initializeStorage(String[] args) throws InvalidStorageFilePathException {
        if (args.length > 0) {
            // User has specified a file path for the storage
            return new Storage(args[0]);
        } else {
            // Using the default file path as user did not specify a file path
            return new Storage();
        }
    }

    private void runLoop() {
        Command command = null;
        do {
            try {
                ui.printDivider();
                String userInput = ui.getUserInput();
                command = new Parser().parseCommand(userInput);
                ui.printDivider();
                CommandResult commandResult = executeCommand(command);
                storage.saveTasksIfPresent(commandResult.getUpdatedTaskList());
                updateTaskListIfPresent(commandResult.getUpdatedTaskList());
                ui.print(commandResult.getMessageForUser());
            } catch (InvalidCommandException | StorageException | InvalidDescriptionException |
                    NoDescriptionException ex) {
                ui.printDivider();
                System.out.println(ex.getMessage());
            }
        } while (!ByeCommand.isByeCommand(command));
    }
    
    private CommandResult executeCommand(Command command) {
        command.setTaskList(taskList);
        return command.execute();
    }
    
    private void updateTaskListIfPresent(TaskList taskList) {
        if (taskList != null) {
            this.taskList = taskList;
        }
    }
    
//    private void executeCommand(Command command, String arguments) {
//        try {
//            ui.printDivider();
//            switch (command) {
//            case BYE:
//                off();
//                break;
//            case LIST:
//                ui.printAllTasks(taskList);
//                break;
//            case DONE:
//                markTaskAsDone(arguments);
//                storage.saveTasks(taskList);
//                break;
//            case TODO:
//            case DEADLINE:
//            case EVENT:
//                createTask(command, arguments);
//                storage.saveTasks(taskList);
//                break;
//            case DELETE:
//                deleteTask(arguments);
//                storage.saveTasks(taskList);
//                break;
//            case HELP:
//                ui.printHelp();
//                break;
//            }
//        } catch (NoDescriptionException | InvalidDescriptionException | StorageException ex) {
//            System.out.println(ex.getMessage());
//        }
//    }
//
//    private void createTask(Command taskType, String taskDescription) throws NoDescriptionException, 
//            InvalidDescriptionException {
//        if (taskDescription.isBlank()) {
//            throw new NoDescriptionException("OOPS!!! The description of a task cannot be empty.");
//        }
//        Task task = null;
//        switch (taskType) {
//        case TODO:
//            task = createToDoTask(taskDescription.strip());
//            break;
//        case DEADLINE:
//            task = createDeadlineTask(taskDescription.strip());
//            break;
//        case EVENT:
//            task = createEventTask(taskDescription.strip());
//            break;
//        }
//        if (task != null) {
//            taskList.addTask(task);
//            ui.printAddedMessage(task);
//            ui.printTaskListSize(taskList);
//        }
//    }
//
//    private Task createToDoTask(String taskDescription) {
//        return new ToDoTask(taskDescription);
//    }
//
//    private Task createDeadlineTask(String taskDescription) throws InvalidDescriptionException {
//        String[] deadlineInputArr = taskDescription.split("/by");
//        String deadlineTaskName = deadlineInputArr[0].strip();
//        String userInputDateTime = deadlineInputArr[1].strip();
//        LocalDateTime deadline = parseDateTime(userInputDateTime);
//        return new DeadlineTask(deadlineTaskName, deadline);
//    }
//    
//    private static LocalDateTime parseDateTime(String dateString) throws InvalidDescriptionException {
//        try {
//            return LocalDateTime.parse(dateString, Formatter.INPUT_DATE_FORMATTER);
//        } catch (DateTimeParseException ex) {
//            throw new InvalidDescriptionException("Please enter a valid date and time for a deadline task " +
//                    "using this format:\ndeadline task_name /by dd/mm/yyyy HHHH");
//        }
//    }
//
//    private Task createEventTask(String taskDescription) {
//        String[] eventInputArr = taskDescription.split("/at");
//        String eventTaskName = eventInputArr[0].strip();
//        String eventTime = eventInputArr[1].strip();
//        return new EventTask(eventTaskName, eventTime);
//    }
//
//    private void deleteTask(String arguments) throws NoDescriptionException,
//            InvalidDescriptionException, IndexOutOfBoundsException {
//        if (arguments.isBlank()) {
//            throw new NoDescriptionException("Please indicate a task number to be deleted.");
//        }
//        try {
//            int index = Integer.parseInt(arguments.strip()) - 1;  // Account for 0-based indexing
//            Task task = taskList.getTask(index);
//            taskList.deleteTask(index);
//            ui.printDeletedMessage(task);
//            ui.printTaskListSize(taskList);
//        } catch (NumberFormatException ex) {
//            throw new InvalidDescriptionException("Please enter a valid task number");
//        } catch (IndexOutOfBoundsException ex) {
//            throw new InvalidDescriptionException("Please enter a valid index!");
//        }
//    }
//
//    private void markTaskAsDone(String arguments) throws NoDescriptionException,
//            InvalidDescriptionException, IndexOutOfBoundsException {
//        if (arguments.isBlank()) {
//            throw new NoDescriptionException("Please indicate a task number to be marked as done.");
//        }
//        try {
//            int index = Integer.parseInt(arguments.strip()) - 1;  // Account for 0-based indexing
//            taskList.completeTask(index);
//        } catch (NumberFormatException ex) {
//            throw new InvalidDescriptionException("Please enter a valid task number");
//        } catch (IndexOutOfBoundsException ex) {
//            throw new InvalidDescriptionException("Please enter a valid index!");
//        }
//    }
}
