import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;
public class Duke {
    private static String FILE_PATH = "./data/";
    private static String FILE_NAME = "history.txt";
    private Ui ui;
    private Storage storage;
    private TaskManager taskManager;

    public static void main(String[] args) {
        Duke duke = new Duke(FILE_PATH, FILE_NAME);
        duke.doCommand();
    }

    public Duke(String filePath, String fileName) {
        ui = new Ui();
        storage = new Storage(filePath, fileName);
        taskManager = new TaskManager(storage.readPreviousFile());
    }

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
            else {
                doTask(word);
            }
            storage.saveTasks(taskManager);
        }
    }

    private void doList() {
        for (int i=0; i < taskManager.size(); i++) {
            Task task = taskManager.get(i);
            ui.printList(i, task);
        }
    }

    private void doDone(int index) {
        try {
            Task currTask = taskManager.get(index - 1);
            currTask = currTask.doTask();
            taskManager.set(index - 1, currTask);
            ui.printDoneSuccess(currTask);
        }
        catch (ArrayIndexOutOfBoundsException e) {
            ui.printDoneFail();
        }
    }

    private void doDelete(int index) {
        try {
            Task currTask = taskManager.get(index - 1);
            taskManager.remove(index - 1);
            ui.printDeleteSuccess(currTask, taskManager.size());
        }
        catch (ArrayIndexOutOfBoundsException e) {
            ui.printDeleteFail();
        }
    }

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

    private void doToDo(String word) throws NoMeaningException {
        try {
            String realWord = word.substring(5);
            ToDo todo = new ToDo(realWord);
            taskManager.add(todo);
            doTaskFinally(todo);
        } catch (StringIndexOutOfBoundsException e) {
            throw new NoMeaningException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }

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
            taskManager.add(deadline);
            doTaskFinally(deadline);
        } catch (StringIndexOutOfBoundsException e) {
            throw new NoMeaningException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
    }

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
            taskManager.add(event);
            doTaskFinally(event);
        } catch (StringIndexOutOfBoundsException e) {
            throw new NoMeaningException("☹ OOPS!!! The description of a event cannot be empty.");
        }
    }

    private void doTaskFinally(Task task) {
        ui.printTaskFinally(task, taskManager.size());
    }
}
