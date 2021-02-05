package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Parser {

    public static void parseTodoCommand(Scanner input, TaskList taskList, Ui ui, Database database){
        try {
            String s1 = input.nextLine();
            if (s1.equals("")) {
                throw new DukeException(" Enter a valid todo task");
            } else {
                char[] chars = s1.toCharArray();
                if(chars[1] == ' '){
                    throw new DukeException(" Enter valid todo task");
                } else {
                    try{
                        String desc = s1.substring(1);
                        Todo newTodo = new Todo(desc);
                        taskList.addTask(newTodo);
                        ui.showSuccessfulAddedMessage(taskList.getSize(), newTodo);
                        database.writeTaskToFile(taskList.getList());
                    } catch (Exception e){
                        System.out.println(" Enter a valid todo task");
                    }
                }
            }
        } catch (DukeException e){
            ui.showErrorMessage(e.getMessage());
        }
    }

    public static void parseEventCommand(Scanner input, TaskList taskList, Ui ui, Database database){
        try{
            String s1 = input.nextLine();
            if(s1.equals("")){
                throw new DukeException(" Enter a valid event task.");
            } else {
                try {
                    String wholeString = s1.substring(1);
                    String[] parts = wholeString.split(" /at ");
                    String eventDesc = parts[0];
                    if(parts.length == 1){
                        throw new DukeException(" Please adhere to convention:\n(event event_name /at event_details)");
                    } else {
                        String eventDetails = parts[1];
                        Event newEvent = new Event(eventDesc, eventDetails);
                        taskList.addTask(newEvent);
                        ui.showSuccessfulAddedMessage(taskList.getSize(), newEvent);
                        database.writeTaskToFile(taskList.getList());
                    }
                } catch(DukeException e) {
                    ui.showErrorMessage(e.getMessage());
                }
            }

        } catch (DukeException e){
            ui.showErrorMessage(e.getMessage());
        }

    }

    public static void parseDeadlineCommand(Scanner input, TaskList taskList, Ui ui, Database database){
        try{
            String s1 = input.nextLine();
            if(s1.equals("")){
                throw new DukeException(" Enter valid deadline task.");
            } else {
                try {
                    String wholeString = s1.substring(1);
                    String[] parts = wholeString.split(" /by ");
                    String deadlineDesc = parts[0];
                    if(parts.length == 1){
                        throw new DukeException(" Please adhere to convention:\n(deadline task_name /by deadline date(YYYY-MM-DD))");
                    } else {
                        String dl = parts[1];
                        try{
                            LocalDate deadline = LocalDate.parse(dl);
                            Deadline newDeadline = new Deadline(deadlineDesc, deadline);
                            taskList.addTask(newDeadline);
                            ui.showSuccessfulAddedMessage(taskList.getSize(), newDeadline);
                            database.writeTaskToFile(taskList.getList());
                        } catch (DateTimeParseException e){
                            ui.showErrorMessage(" Please enter date as follows\n YYYY-MM-DD");
                        }
                    }
                } catch(DukeException e) {
                    ui.showErrorMessage(e.getMessage());
                }
            }
        } catch (DukeException e){
            ui.showErrorMessage(e.getMessage());
        }

    }

    public static void parseDoneCommand(Scanner input, TaskList taskList, Ui ui, Database database){
        try{
            String s1 = input.nextLine();
            if(s1.equals("")){
                throw new DukeException(" Please specify what task is done");
            } else {
                String parts[] = s1.split(" ");
                if(parts.length> 2){
                    throw new DukeException(" Please insert valid index to mark as done");
                } else {
                    try {
                        String indexString = parts[1];
                        int index =Integer.parseInt(indexString);
                        Task t = taskList.getList().get(index - 1);
                        t.markAsDone();
                        ui.markTaskAsDone(t);
                        database.writeTaskToFile(taskList.getList());
                    } catch (Exception e){
                        ui.showErrorMessage(" Please enter a valid index");
                    }
                }
            }

        } catch(DukeException e){
            ui.showErrorMessage(e.getMessage());
        }
    }

    public static void parseDeleteCommand(Scanner input, TaskList taskList, Ui ui, Database database){
        try{
            String s1 = input.nextLine();
            if(s1.equals("")){
                throw new DukeException(" Please specify which task to delete");
            } else {
                String parts[] = s1.split(" ");
                if(parts.length> 2){
                    throw new DukeException(" Please insert valid index to delete");
                } else {
                    try {
                        String indexString = parts[1];
                        int index =Integer.parseInt(indexString);
                        Task t = taskList.getList().get(index-1);
                        taskList.removeTask(index-1);
                        ui.showTaskAsDeleted(t);
                        database.writeTaskToFile(taskList.getList());

                    } catch (Exception e){
                        ui.showErrorMessage(" Please enter a valid index");
                    }
                }
            }

        } catch(DukeException e){
            ui.showErrorMessage(e.getMessage());
        }
    }

    public static void parseListCommand(TaskList taskList, Ui ui){
        ui.showListContent(taskList);

    }

    public static void parseByeCommand(Ui ui){
        ui.showByeMessage();
        System.exit(0);
    }

    public static void parseDefault(Scanner input, Ui ui){
        input.nextLine();
        ui.showDefaultStatement();
    }

}
