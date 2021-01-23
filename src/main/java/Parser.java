public class Parser {
    private final static String TODO = "todo";
    private final static String DELETE = "delete";
    private final static String DONE = "done";
    private final static String EVENT = "event";
    private final static String DEADLINE = "deadline";
    private final static String LIST = "list";

    public static void manage(String taskDetails, TaskManager taskManager) throws DukeException {
        String[] inputArray = taskDetails.split(" ");
        String command = inputArray[0];
        int inputLength = inputArray.length;
        if (inputLength == 0) {
            throw new DukeException("No command entered! Please enter a valid command.");
        } else if (command.equals(LIST)) {
            taskManager.printList();
        } else if (command.equals(DONE)) {
            if (inputLength == 2) {
                int taskId = Integer.valueOf(inputArray[1]);
                taskManager.done(taskId);
            } else {
                throw new DukeException(("Invalid done command. Please try again!"));
            }
        } else if (command.equals(DELETE)) {
            if (inputLength == 2) {
                int taskId = Integer.valueOf(inputArray[1]);
                taskManager.delete(taskId);
            } else {
                throw new DukeException(("Invalid delete command. Please try again!"));
            }
        } else if (command.equals(TODO)) {
            if (inputLength >= 2) {
                String description = taskDetails.split(" ", 2)[1];
                taskManager.add(command, description);
            } else {
                throw new DukeException("Invalid todo command. Please try again!");
            }
        } else if (command.equals(EVENT)) {
            if (inputLength > 2 && taskDetails.contains("/at")) {
                String description = taskDetails.split(" ", 2)[1];
                taskManager.add(command, description);
            } else {
                throw new DukeException("Invalid event command. Please try again!");
            }
        } else if (command.equals(DEADLINE)) {
            if (inputLength > 2 && taskDetails.contains("/by")) {
                String description = taskDetails.split(" ", 2)[1];
                taskManager.add(command, description);
            } else {
                throw new DukeException("Invalid deadline command. Please try again!");
            }
        } else {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
