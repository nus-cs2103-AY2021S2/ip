import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws DukeException {
        ArrayList<Task> inputArr = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int n = 0;
        int taskNum = 0;
        String dateTime = "", taskDesc = "";
        ArrayList<String> validInputs = new ArrayList<>(Arrays.asList("bye", "list", "done", "todo", "deadline", "event", "delete"));
        Output.printWelcomeMsg();

        while(sc.hasNext()) {
            String input = sc.next();
            try {
                DukeException.validInputHandler(validInputs, input);
            } catch (DukeException e) {
                Output.printErrorMsg(e);
                continue;
            }
            switch(input) {
                case "bye":
                    break;
                case "list":
                    try {
                        DukeException.listHandler(n);
                    } catch (DukeException e) {
                        Output.printErrorMsg(e);
                        continue;
                    }
                    Output.addLine();
                    for(int i = 1; i <= n; i++) {
                        Task task = inputArr.get(i - 1);
                        System.out.println("    " + i + ". " + "[" + task.getType() + "]" + "[" + task.getStatusIcon() + "] " + task.getDescription());
                    }
                    Output.addLine();
                    System.out.println();
                    break;
                case "done":
                    String temp = sc.nextLine();
                    taskNum = temp.equals("") ? 0 : Integer.parseInt(temp.substring(1));
                    try {
                        DukeException.doneHandler(taskNum, n);
                    } catch (DukeException e) {
                        Output.printErrorMsg(e);
                        continue;
                    }

                    Task done = inputArr.get(taskNum - 1);
                    done.toggleStatus();

                    Output.printDoneMsg(done.getStatusIcon(), done.getDescription());
                    break;
                case "todo":
                    taskDesc = sc.nextLine();
                    try {
                        DukeException.taskHandler(taskDesc);
                    } catch (DukeException e) {
                        Output.printErrorMsg(e);
                        continue;
                    }
                    Todo todo = new Todo(taskDesc);
                    inputArr.add(n, todo);
                    n++;
                    Output.printTodoMsg(todo.getStatusIcon(), todo.getDescription(), n);
                    break;
                case "deadline":
                    taskDesc = sc.nextLine();
                    try {
                        DukeException.taskHandler(taskDesc);
                        DukeException.dateHandler(taskDesc.split(" /").length);
                    } catch (DukeException e) {
                        Output.printErrorMsg(e);
                        continue;
                    } catch (NullPointerException np) {
                        Output.printErrorMsg(np);
                        continue;
                    }
                    dateTime = taskDesc.split(" /by ")[1];
                    taskDesc = taskDesc.split("/")[0] + " (by: " + dateTime + ")";
                    Deadline deadline = new Deadline(taskDesc);
                    inputArr.add(n, deadline);
                    n++;

                    Output.printDeadlineMsg(deadline.getStatusIcon(), deadline.getDescription(), n);
                    break;
                case "event":
                    taskDesc = sc.nextLine();
                    try {
                        DukeException.taskHandler(taskDesc);
                        DukeException.dateHandler(taskDesc.split(" /").length);
                    } catch (DukeException e) {
                        Output.printErrorMsg(e);
                        continue;
                    }
                    dateTime = taskDesc.split(" /at ")[1];
                    taskDesc = taskDesc.split(" /")[0] + " (at: " + dateTime + ")";
                    Event event = new Event(taskDesc);
                    inputArr.add(n, event);
                    n++;

                    Output.printEventMsg(event.getStatusIcon(), event.getDescription(), n);
                    break;
                case "delete":
                    String delete = sc.nextLine();
                    taskNum = delete.equals("") ? 0 : Integer.parseInt(delete.substring(1));
                    try {
                        DukeException.deleteHandler(taskNum, n);
                    } catch (DukeException e) {
                        Output.printErrorMsg(e);
                        continue;
                    }
                    taskDesc = taskDesc.split(" /")[0] + "(at: " + dateTime.substring(3) + ")";
                    Task deleteTask = inputArr.get(taskNum - 1);
                    inputArr.remove(taskNum - 1);
                    n--;

                    Output.printDeleteMsg(deleteTask.getStatusIcon(), deleteTask.getDescription(), n);
                    break;
                default:
                    inputArr.set(n, new Task(taskDesc, ""));
                    n++;
                    Output.printAddedMsg(input);
                    break;
            }
            if(input.equals("bye")) break;

        }
        Output.printByeMsg();
    }
}