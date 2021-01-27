import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Scanner;

public class Parser {
    private Scanner scanner; 
    static String input;
    Ui userInterface;
    private TaskList list;

    Parser(){
        scanner = new Scanner(System.in);
        userInterface = new Ui();
        userInterface.welcomeUser();
        list = new TaskList();
    }

    private void removeExtraLinesAndSpacesFromInput() {
        input = input.replaceAll("\n", "");
        input = input.toLowerCase();
    }

    public void readInput(TaskList list) throws Exception {
        if(scanner.hasNextLine()) {
            input = scanner.nextLine(); 
            removeExtraLinesAndSpacesFromInput();
            if(!inputDoesNotContainBye()) {
                userInterface.userLeaving();
                scanner.close();
            } else {
                analyseInput(list);
            }
        } else {
            userInterface.userLeaving();
            scanner.close();
        }

    }

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


/////////////Command Done start ///////////
    public int inputContainsDoneAtIndex() throws ArrayIndexOutOfBoundsException {
        String value = input.split(" ")[1];
        int val = Integer.parseInt(value) - 1;
        return val;
    }

    public void markDoneAtIndex(TaskList list) {
        int index = inputContainsDoneAtIndex();
        list.markDone(index);
        userInterface.userDoneTask(list.getTaskAtIndex(index).toString());
    }
    
///////Command Done End ////////////////

//////////////Command ToDo Start ////////////////

    public String inputTaskToDoIs() throws ArrayIndexOutOfBoundsException {
        String task = input.replaceFirst("todo", "");
        task = task.stripTrailing();
        if (task.isEmpty()) {
            throw new ArrayIndexOutOfBoundsException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        return task;
    }

    public void addTodoTask(TaskList list) {
        list.addTodo(inputTaskToDoIs());
        userInterface.userAddTask(list);
    }

///////////Command todo End ////////////////////


/////Command Deadline and Event Start /////////////
    public String[] findTaskWithDate() {
        if(inputContainsEvent()) {
            input = input.replaceFirst("event", "");
        } else {
            input = input.replaceFirst("deadline", "");
        }
        input = input.stripLeading();
        return input.split("/", 2);
    }

    static LocalDateTime inputDateAndTime(String inputDate) {
        String[] dataArray = inputDate.split(" ");
        LocalDate formatDate = inputDate(dataArray[1]);
        LocalTime formatTime = inputTime(dataArray[2]);
        return LocalDateTime.of(formatDate, formatTime);
    }

    static LocalDate inputDate(String input) {
        String[] dateArray = input.split("/");
        LocalDate date = LocalDate.of(Integer.parseInt(dateArray[2]), 
                                            Integer.parseInt(dateArray[1]), 
                                            Integer.parseInt(dateArray[0]));
        return date;
    }

    static LocalTime inputTime(String input) {
        String hour = input.substring(0, 2);
        String minutes = input.substring(2); 
        LocalTime time = LocalTime.of(Integer.parseInt(hour), Integer.parseInt(minutes));
        return time;
    }

    public void addEventTask(TaskList list) {
        String[] taskAndDate = findTaskWithDate();
        list.addEvent(taskAndDate[0], inputDateAndTime(taskAndDate[1]));
        userInterface.userAddTask(list);
    } 

    public void addDeadlineTask(TaskList list) {
        String[] taskAndDate = findTaskWithDate();
        list.addDeadline(taskAndDate[0], inputDateAndTime(taskAndDate[1]));
        userInterface.userAddTask(list);
    }

////////// command event and deadline end //////////////

////////////// Command delete START ///////////////

    public void commandDelete(TaskList list) {
        String value = input.replaceFirst("delete", "");
        value = value.strip();
        int val = Integer.parseInt(value);
        Task delete = list.deleteTaskAtIndex(val - 1);
        userInterface.userDeleteTask(delete, list);
    }

////////////// Command delete End ///////////////




    private void analyseInput(TaskList list) throws Exception {
        while(inputDoesNotContainBye()) {
            if(inputContainsDone()) {
                markDoneAtIndex(list);
            } else if (inputContainsTodo()) {
                addTodoTask(list);
            } else if (inputContainsDeadline()) {
                addDeadlineTask(list);
            } else if (inputContainsEvent()) {
                addEventTask(list);
            } else if (inputContainsDelete()) {
                commandDelete(list);
            } else if (inputContainsList()) {
                list.listAllTasks();
            } else {
                if(scanner.hasNextLine()) {
                    input = scanner.nextLine();
                } else {
                    throw new Exception("Please enter a valid command!");
                }
                continue;
            }
            readInput(list);
        }
    }




}
