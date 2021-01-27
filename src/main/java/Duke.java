import java.util.Scanner;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;


enum Call {
    DELETE, TODO, EVENT, DEADLINE, LIST, DONE
}

public class Duke {
    //Parser
    static String input = " ";

    static Storage storage = new Storage(); 

    static TaskList list = new TaskList();

    static Parser parser = new Parser();
    
    static Ui userInterface = new Ui(); 
    public static void main(String[] args) throws Exception {
        if(storage.isSavedHistory()) {
            storage.initialise(list);
        }

        userInterface.welcomeUser();

        //Parser
        parser.readInput(list);
        // Scanner sc = new Scanner(System.in);
        // input = sc.nextLine();
        // cleanInput();
        // while (!input.contains("bye")) {
        //     if(input.contains("list")) {
        //         commandList();
        //     } else if (input.contains("done")){
        //         commandDone();
        //     } else if (input.contains("todo")) {
        //         commandTodo();
        //     } else if (input.contains("event")) {
        //         commandEvent();
        //     } else if (input.contains("deadline")) {
        //         commandDeadline();
        //     } else if (input.contains("delete")) {
        //         commandDelete();
        //     } else {
        //         if(sc.hasNextLine()) {
        //             input = sc.nextLine();
        //         } else {
        //             throw new Exception("Please enter a valid command!");
        //         }
        //         continue;
        //     }
        //     input = sc.nextLine();
        //     cleanInput();
        // }

        // userInterface.userLeaving();
        // sc.close();

        //Storage
        try {
            byeCommand();
        } catch (IOException io) {
            System.out.print("Could not save list!");
        }
    }

    // //Parser
    // static void commandDone() throws ArrayIndexOutOfBoundsException {
    //     try {
    //         int val = parser.inputContainsDoneAtIndex();
    //         list.markDone(val);
    //         userInterface.userDoneTask(list.getTaskAtIndex(val).toString());
    //     } catch (ArrayIndexOutOfBoundsException e) {
    //         throw new ArrayIndexOutOfBoundsException("Please key in which task number to tick off.");
    //     }

    // }

    // //Parser
    // static void cleanInput() {
    //     input = input.replaceAll("\n", "");
    //     input = input.toLowerCase();
    // }

    // //Parser
    // static void commandTodo() throws Exception{
    //     String task = parser.inputTaskToDoIs();
    //     list.addTodo(task);
    //     userInterface.userAddTask(list);
    // }

    // //Parser
    // static void commandDeadline() {
    //     String deadline = input.replaceFirst("deadline", "");
    //     deadline = deadline.stripLeading();
    //     String[] deadlineArr = deadline.split("/", 2);
    //     list.addDeadline(deadlineArr[0], inputDeadline(deadlineArr[1]));
    //     userInterface.userAddTask(list);
    // }

    // //Parser
    // static void commandEvent() {
    //     String eventDetails = input.replaceFirst("event", "");
    //     eventDetails = eventDetails.stripLeading();
    //     String[] eventDeats = eventDetails.split("/", 2);
    //     list.addEvent(eventDeats[0], inputDeadline(eventDeats[1]));
    //     userInterface.userAddTask(list);
    // }

    // //Parser
    // static void commandDelete() {
    //     String value = input.replaceFirst("delete", "");
    //     value = value.strip();
    //     int val = Integer.parseInt(value);
    //     Task delete = list.deleteTaskAtIndex(val - 1);
    //     userInterface.userDeleteTask(delete, list);
    // }

    // //Parser
    // static void commandList() {
    //     list.listAllTasks();
    // }

    //Parser
    // static LocalDateTime inputDeadline(String inputDate) {
    //     String[] dataArray = inputDate.split(" ");
    //     LocalDate formatDate = inputDate(dataArray[1]);
    //     LocalTime formatTime = inputTime(dataArray[2]);
    //     return LocalDateTime.of(formatDate, formatTime);
    // }

    // //Parser
    // static LocalDate inputDate(String input) {
    //     String[] dateArray = input.split("/");
    //     LocalDate date = LocalDate.of(Integer.parseInt(dateArray[2]), 
    //                                         Integer.parseInt(dateArray[1]), 
    //                                         Integer.parseInt(dateArray[0]));
    //     return date;
    // }

    // //Parser
    // static LocalTime inputTime(String input) {
    //     String hour = input.substring(0, 2);
    //     String minutes = input.substring(2); 
    //     LocalTime time = LocalTime.of(Integer.parseInt(hour), Integer.parseInt(minutes));
    //     return time;
    // }

    // //Parser
    // static boolean validCommand() {
    //     return input.contains("delete") || input.contains("event") || input.contains("done") ||
    //             input.contains("todo") || input.contains("deadline") || input.contains("list");
    // }

    static void byeCommand() throws IOException {
        storage.saveHistory(list);
    }

}



