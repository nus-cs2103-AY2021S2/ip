import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
public class Duke {
    private static String FILE_PATH = "./data/";
    private static String FILE_NAME = "history.txt";
    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    public static void main(String[] args) {
        Duke duke = new Duke(FILE_PATH, FILE_NAME);
        duke.doCommand();
    }

    public Duke(String filePath, String fileName) {
        ui = new Ui();
        storage = new Storage(filePath, fileName);
        taskList = new TaskList(storage.readPreviousFile());
    }

    /**
     * Process command given by user.
     */
    private void doCommand() {
        Scanner sc = new Scanner(System.in);
        ui.printGreetings();

        while (sc.hasNext()) {
            String word = sc.nextLine();
            String[] tmp = word.split(" ");
            String command = tmp[0];
            if (word.equals("bye")) {
                ui.printBye();
                break;
            }
            else if (word.equals("list")) {
                doList();
            }
            else if (command.equals("done")) {
                int index = Integer.parseInt(tmp[1]);
                doDone(index);
            }
            else if (command.equals("delete")) {
                int index = Integer.parseInt(tmp[1]);
                doDelete(index);
            }
            else if (command.equals("find")) {
                doFind(word);
            }
            else {
                doTask(word);
            }
            storage.saveTasks(taskList);
        }
    }

    /**
     * Process command list given by user.
     */
    private void doList() {
        for (int i=0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            ui.printList(i, task);
        }
    }

    /**
     * Process command done given by user.
     * @param index Index of task from TaskList
     */
    private void doDone(int index) {
        try {
            Task currTask = taskList.get(index - 1);
            currTask = currTask.doTask();
            taskList.set(index - 1, currTask);
            ui.printDoneSuccess(currTask);
        }
        catch (ArrayIndexOutOfBoundsException e) {
            ui.printDoneFail();
        }
    }

    /**
     * Process command delete given by user.
     * @param index Index of task from TaskList
     */
    private void doDelete(int index) {
        try {
            Task currTask = taskList.get(index - 1);
            taskList.remove(index - 1);
            ui.printDeleteSuccess(currTask, taskList.size());
        }
        catch (ArrayIndexOutOfBoundsException e) {
            ui.printDeleteFail();
        }
    }

    /**
     * Process command find given by user.
     * @param word String inputted by user.
     */
    private void doFind(String word){
        try {
            String toSearch = word.substring(5);
            List<Task> searchedTaskList = taskList.find(toSearch);
            ui.printFindSuccess(searchedTaskList);
        } catch (StringIndexOutOfBoundsException e) {
            ui.printFindFail(new NoMeaningException("☹ OOPS!!! The description of a find cannot be empty."));
        }
    }

    /**
     * Process task type command given by user.
     * @param word the whole sentences entered by the user
     */
    private void doTask(String word) {
        try {
            String[] tmp = word.split(" ");
            String command = tmp[0];
            if (command.equals("todo")) {
                doToDo(word);
            }
            else if (command.equals("deadline")) {
                doDeadline(word);
            }
            else if (command.equals("event")) {
                doEvent(word);
            }
            else {
                throw new NoMeaningException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
        catch (NoMeaningException e){
            ui.printTaskFail(e);
        }
    }

    /**
     * Process task command todo given by user.
     * @param word the whole sentences entered by the user
     */
    private void doToDo(String word) throws NoMeaningException {
        try {
            String realWord = word.substring(5);
            ToDo todo = new ToDo(realWord);
            taskList.add(todo);
            doTaskFinally(todo);
        } catch (StringIndexOutOfBoundsException e) {
            throw new NoMeaningException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }

    /**
     * Process task command deadline given by user.
     * @param word the whole sentences entered by the user
     */
    private void doDeadline(String word) throws NoMeaningException {
        try {
            String realWord = word.substring(9);
            String[] deadlineWords = realWord.split("/by");
            String deadlineWord = deadlineWords[0];
            String deadlineTime = deadlineWords[1];

            String[] deadlineDateHours = deadlineTime.split(" ");
            LocalDate deadlineDate = LocalDate.parse(deadlineDateHours[1]);
            LocalTime deadlineHour = LocalTime.parse(deadlineDateHours[2]);
            Deadline deadline = new Deadline(deadlineWord, deadlineDate, deadlineHour);
            taskList.add(deadline);
            doTaskFinally(deadline);
        } catch (StringIndexOutOfBoundsException e) {
            throw new NoMeaningException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
    }

    /**
     * Process task command event given by user.
     * @param word the whole sentences entered by the user
     */
    private void doEvent(String word) throws NoMeaningException {
        try {
            String realWord = word.substring(6);
            String[] eventWords = realWord.split("/at");
            String eventWord = eventWords[0];
            String eventTime = eventWords[1];

            String[] eventDateHours = eventTime.split(" ");
            LocalDate eventDate = LocalDate.parse(eventDateHours[1]);
            LocalTime eventHour = LocalTime.parse(eventDateHours[2]);
            Event event = new Event(eventWord, eventDate, eventHour);
            taskList.add(event);
            doTaskFinally(event);
        } catch (StringIndexOutOfBoundsException e) {
            throw new NoMeaningException("☹ OOPS!!! The description of a event cannot be empty.");
        }
    }

    /**
     * Process task typed command after all the above functions.
     * @param task Task to print
     */
    private void doTaskFinally(Task task) {
        ui.printTaskFinally(task, taskList.size());
    }
}
