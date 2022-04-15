import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Parser {
    private static String input;
    private final Ui userInterface;
    private Storage storage = new Storage();

    Parser() {
        userInterface = new Ui();
        userInterface.welcomeUser();
    }

    private String removeExtraLinesAndSpacesFromInput(String input) {
        String cleanedInput = input.replaceAll("\n", "");
        cleanedInput = cleanedInput.toLowerCase();
        return cleanedInput;
    }

    /**
     * Decides which command to call given the input
     * by parsing the input into analyseInput().
     * @param userInput User's command given to the system
     * @param list of TaskList for reference and so changes can be made
     * @return respond to the user's input
     * @throws Exception
     */
    public String respondToInput(String userInput, TaskList list) throws Exception {
        input = removeExtraLinesAndSpacesFromInput(userInput);
        if (inputDoesNotContainBye()) {
            return analyseInput(list);
        } else {
            assert input.contains("bye") : "Oh no! You keyed in something I couldn't read :(";
            if (list.size() != 0) {
                assert storage != null : "did not initialise storage!";
                storage.saveHistory(list);
            }
            return userInterface.userLeaving();
        }
    }

    /**
     * Method checks if input contains string "bye"
     *
     * @return Boolean that whether input contains "bye"
     */
    public Boolean inputDoesNotContainBye() {
        return !input.contains("bye");
    }

    private Boolean inputContainsDone() {
        return input.contains("done");
    }

    private Boolean inputContainsTodo() {
        return input.contains("todo");
    }

    private Boolean inputContainsEvent() {
        return input.contains("event");
    }

    private Boolean inputContainsDeadline() {
        return input.contains("deadline");
    }

    private Boolean inputContainsDelete() {
        return input.contains("delete");
    }

    private Boolean inputContainsList() {
        return input.contains("list");
    }

    private Boolean inputContainsFind() {
        return input.contains("find");
    }


    /**
     * Method reads which number in list user wants to mark done.
     *
     * @return int val index in the list that user wants to mark as completed.
     */
    public int inputDoneAtIndex() throws ArrayIndexOutOfBoundsException {
        String[] stringArray = input.split(" ");
        assert stringArray.length == 2 : "Oh no! I don't understand what you want to be marked as done :(";
        String value = stringArray[1];
        int val = Integer.parseInt(value) - 1;
        return val;
    }

    /**
     * Method enters TaskList and marks the task at the value user indicated to mark as complete.
     */
    public String markDoneAtIndex(TaskList list) {
        int index = inputDoneAtIndex();
        assert index < list.size() && index >= 0 : "The number you want marked done is not in the list!";
        list.markDone(index);
        return userInterface.userDoneTask(list.getTaskAtIndex(index).toString());
    }

    /**
     * Method reads the Todo Task that user has input and removes the unnecessary string to be saved in Todo
     *
     * @return task that user has input.
     */
    public String inputTaskToDoIs() throws ArrayIndexOutOfBoundsException {
        String task = input.replaceFirst("todo", "");
        task = task.stripTrailing();
        if (task.isEmpty()) {
            throw new ArrayIndexOutOfBoundsException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        return task;
    }

    /**
     * Method adds the Todo task user indicated into a TaskList.
     */
    public String addTodoTask(TaskList list) {
        Task toAddIntoList = list.addTodo(inputTaskToDoIs());
        return userInterface.userAddTask(list, toAddIntoList);
    }

    /**
     * Method takes in the Date and Time that user input and returns an array of size 2 with date and time respectively
     *
     * @return String[] of size 2 with string relevant to date and time.
     */
    public String[] findTaskWithDate() {
        if (inputContainsEvent()) {
            input = input.replaceFirst("event", "");
        } else {
            input = input.replaceFirst("deadline", "");
        }
        input = input.stripLeading();
        return input.split("/", 2);
    }

    /**
     * Method formats user input of time into LocalDateTime
     *
     * @param inputDate of type String
     * @return LocalDateTime of the user input
     */
    static LocalDateTime inputDateAndTime(String inputDate) {
        String[] dataArray = inputDate.split(" ");
        assert dataArray.length == 3 : "Invalid input for date and time taken";
        LocalDate formatDate = inputDate(dataArray[1]);
        LocalTime formatTime = inputTime(dataArray[2]);
        return LocalDateTime.of(formatDate, formatTime);
    }

    /**
     * Method formats user input of time into LocalDate
     *
     * @return LocalDate of the user input
     */
    static LocalDate inputDate(String input) {
        String[] dateArray = input.split("/");
        LocalDate date = LocalDate.of(Integer.parseInt(dateArray[2]),
                                            Integer.parseInt(dateArray[1]),
                                            Integer.parseInt(dateArray[0]));
        return date;
    }

    /**
     * Formats user input of time into LocalDateTime
     *
     * @return LocalDateTime of the user input
     */
    static LocalTime inputTime(String input) {
        String hour = input.substring(0, 2);
        String minutes = input.substring(2);
        LocalTime time = LocalTime.of(Integer.parseInt(hour), Integer.parseInt(minutes));
        return time;
    }

    /**
     * Adds the Event created from input into TaskList
     * after reading what the input was
     * @param list TaskList that stores user's Tasks
     */
    public String addEventTask(TaskList list) {
        try {
            String[] taskAndDate = findTaskWithDate();
            Event eventToAdd = list.addEvent(taskAndDate[0], inputDateAndTime(taskAndDate[1]));
            return userInterface.userAddTask(list, eventToAdd);
        } catch (ArrayIndexOutOfBoundsException e) {
            return "I could not understand your deadline task.\n"
                    + "Try format it as \n"
                    + "Eg event conference call /at 2/12/2020 0800";
        }

    }

    /**
     * Adds a Deadline into TaskList passed into method
     * Deadline comes from input by user to be saved into TaskList
     * @param list TaskList that stores user's Tasks
     */
    public String addDeadlineTask(TaskList list) {
        String[] taskAndDate = findTaskWithDate();
        try {
            Deadline deadlineToAdd = list.addDeadline(taskAndDate[0], inputDateAndTime(taskAndDate[1]));
            return userInterface.userAddTask(list, deadlineToAdd);
        } catch (ArrayIndexOutOfBoundsException e) {
            return "I could not understand your deadline task.\n"
                    + "Try format it as \n"
                    + "Eg deadline return book /by 2/12/2019 1800";
        }

    }

    /**
     * Deletes the requested Task given in input
     * from TaskList passed into method
     * @param list TaskList that contains Task to be deleted
     */
    public String commandDelete(TaskList list) {
        String value = input.replaceFirst("delete", "");
        value = value.strip();
        try {
            int val = Integer.parseInt(value);
            assert list != null : "List not initiliased";
            Task delete = list.deleteTaskAtIndex(val - 1);
            return userInterface.userDeleteTask(delete, list);
        } catch (NumberFormatException e) {
            return "Index Value input is not a number, try again!";
        }
    }


    /**
     * Method formats input to content that user wishes to search for
     */
    private void contentToFind() {
        input = input.replaceFirst("find", "");
        input = input.strip();
    }


    private String displayFindList(TaskList list) {
        contentToFind();
        if (input.equals("")) {
            return "'Find' was entered with no content";
        }
        TaskList filteredList = list.filterFind(input);
        String result = "";
        if (filteredList.size() == 0) {
            result = result.concat("No similar matched found.");
        } else {
            result = result.concat(userInterface.tellUserListFound());
            result = result.concat(filteredList.listAllTasks());
        }
        assert !result.equals("") : "Something in displayFindList went wrong for find result.";
        return result;
    }

    private String analyseInput(TaskList list) throws Exception {
        String result = "";
        if (inputContainsDone()) {
            result = markDoneAtIndex(list);
        } else if (inputContainsTodo()) {
            result = addTodoTask(list);
        } else if (inputContainsDeadline()) {
            result = addDeadlineTask(list);
        } else if (inputContainsEvent()) {
            result = addEventTask(list);
        } else if (inputContainsDelete()) {
            result = commandDelete(list);
        } else if (inputContainsList()) {
            result = list.listAllTasks();
        } else if (inputContainsFind()) {
            result = displayFindList(list);
        } else {
            result = "Please enter a valid command!";
        }
        return result;
    }
}
