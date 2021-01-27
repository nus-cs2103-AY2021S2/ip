import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static void main(String[] args) {

        ArrayList<Task> list = Storage.readFromFile();
        Scanner sc = new Scanner(System.in);
        Ui.greet();
        boolean byeFlag = true;

        while (byeFlag) {
            try {
                String input = sc.nextLine();
                Command command = Parser.parseCommand(input);
                switch (command) {
                case TODO:
                    ToDo t = Parser.parseToDo(input);
                    list.add(t);
                    Storage.writeToFile(list);
                    Ui.taskAddConfirmation(t, list);
                    break;
                case DEADLINE:
                    Deadline d = Parser.parseDeadline(input);
                    list.add(d);
                    Storage.writeToFile(list);
                    Ui.taskAddConfirmation(d, list);
                    break;
                case EVENT:
                    Event e = Parser.parseEvent(input);
                    list.add(e);
                    Storage.writeToFile(list);
                    Ui.taskAddConfirmation(e, list);
                    break;
                case LIST:
                    Ui.listTasks(list);
                    break;
                case DONE:
                    int doneIndex = Parser.parseDone(input, list);
                    Task doneTask = list.get(doneIndex);
                    list.get(doneIndex).markAsDone();
                    Storage.writeToFile(list);
                    Ui.taskDoneConfirmation(list, doneTask);
                    break;
                case DELETE:
                    int deleteIndex = Parser.parseDelete(input, list);
                    Task deletedTask = list.get(deleteIndex);
                    list.remove(deleteIndex);
                    Storage.writeToFile(list);
                    Ui.taskDeleteConfirmation(list, deletedTask);
                    break;
                case BYE:
                    Ui.byeMessage();
                    byeFlag = false;
                    break;
                }
            } catch (DukeException e) {
                Ui.dukeExceptionMessage(e);
            }
        }
    }
}