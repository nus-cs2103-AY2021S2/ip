import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser {

    public static void parseTodoCommand(Scanner input, ArrayList<Task> myList, Ui ui, Database database){
        try {
            String s1 = input.nextLine();
            if (s1.equals("")) {
                throw new DukeException(" Enter a valid todo");
            } else {
                char[] chars = s1.toCharArray();
                if(chars[1] == ' '){
                    throw new DukeException(" Enter valid todo");
                } else {
                    try{
                        String desc = s1.substring(1);
                        Todo newTodo = new Todo(desc);
                        myList.add(newTodo);
                        ui.showSuccessfulAddedMessage(myList.size(), newTodo);
                        database.writeTaskToFile(myList);
                    } catch (Exception e){
                        System.out.println(" Enter a valid todo");
                    }
                }
            }
        } catch (DukeException e){
            ui.showErrorMessage(e.getMessage());
        }
    }

    public static void parseEventCommand(Scanner input, ArrayList<Task> myList, Ui ui, Database database){
        try{
            String s1 = input.nextLine();
            if(s1.equals("")){
                throw new DukeException(" Enter valid Event.");
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
                        myList.add(newEvent);
                        ui.showSuccessfulAddedMessage(myList.size(), newEvent);
                        database.writeTaskToFile(myList);
                    }
                } catch(DukeException e) {
                    ui.showErrorMessage(e.getMessage());
                }
            }

        } catch (DukeException e){
            ui.showErrorMessage(e.getMessage());
        }

    }

    public static void parseDeadlineCommand(Scanner input, ArrayList<Task> myList, Ui ui, Database database){
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
                            myList.add(newDeadline);
                            ui.showSuccessfulAddedMessage(myList.size(), newDeadline);
                            database.writeTaskToFile(myList);
                        } catch (DateTimeParseException e){
                            System.out.println(" Please enter date as following\n");
                            System.out.println(" YYYY-MM-DD");
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

    public static void parseDoneCommand(Scanner input, ArrayList<Task> myList, Ui ui, Database database){
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
                        Task t = myList.get(index - 1);
                        t.markAsDone();
                        ui.markTaskAsDone(t);
                        database.writeTaskToFile(myList);
                    } catch (Exception e){
                        ui.showErrorMessage(" Please enter a valid index");
                    }
                }
            }

        } catch(DukeException e){
            ui.showErrorMessage(e.getMessage());
        }
    }

    public static void parseDeleteCommand(Scanner input, ArrayList<Task> myList, Ui ui, Database database){
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
                        Task t = myList.get(index-1);
                        myList.remove(index-1);
                        ui.showTaskAsDeleted(t);
                        database.writeTaskToFile(myList);

                    } catch (Exception e){
                        ui.showErrorMessage(" Please enter a valid index");
                    }
                }
            }

        } catch(DukeException e){
            ui.showErrorMessage(e.getMessage());
        }
    }

    public static void parseListCommand(ArrayList<Task> myList, Ui ui){
        ui.showListContent(myList);

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
