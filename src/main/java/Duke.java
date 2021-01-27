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

    //Storage
    // static File fileTest= new File("PreviousTaskList.txt");
    static Storage storage = new Storage(); 

    //TaskList
    // static ArrayList<Task> list = new ArrayList<>();
    static TaskList list = new TaskList();
    
    static Ui userInterface = new Ui(); 
    public static void main(String[] args) throws Exception {
        // System.out.println("file exists?: " + fileTest.exists());
        // if(fileTest.exists()) {
        //     Scanner s = new Scanner(fileTest);
        //     while (s.hasNext()) {
        //         String current = s.nextLine().toLowerCase();
        //         // System.out.println(current);
        //         if(current.contains("todo")) {
        //             Task task = Todo.readTask(current);
        //             list.add(task);
        //         } else if (current.contains("deadline")) {
        //             list.add(Deadline.readTask(current));
        //         } else if (current.contains("event")) {
        //             list.add(Event.readTask(current));
        //         } else {
        //             if(s.hasNext()){
        //                 current = s.nextLine();
        //             } else{
        //                 throw new Exception("History saved corrupted");
        //             }
        //         }
        //     }
        //     s.close();
        // }
        if(storage.isSavedHistory()) {
            storage.initialise(list);
        }

        //Ui
        // String greet = "Hello! I'm Duke \n What can I do for you?";
        // System.out.println(greet);
        
        userInterface.welcomeUser();

        //Parser
        Scanner sc = new Scanner(System.in);
        input = sc.nextLine();
        cleanInput();
        Call call;
        while (!input.contains("bye")) {
            if(input.contains("list")) {
                call = Call.LIST;
            } else if (input.contains("done")){
                call = Call.DONE;
            } else if (input.contains("todo")) {
                call = Call.TODO;
            } else if (input.contains("event")) {
                call = Call.EVENT;
            } else if (input.contains("deadline")) {
                call = Call.DEADLINE;
            } else if (input.contains("delete")) {
                call = Call.DELETE;
            } else {
                if(sc.hasNextLine()) {
                    input = sc.nextLine();
                } else {
                    throw new Exception("Please enter a valid command!");
                }
                continue;
            }
            switch (call) {
                case LIST -> commandList();
                case DONE -> commandDone();
                case TODO -> commandTodo();
                case DEADLINE -> commandDeadline();
                case EVENT -> commandEvent();
                case DELETE -> commandDelete();
            }
            input = sc.nextLine();
            cleanInput();
        }

        //Ui
        // System.out.println("Bye. Hope to see you again soon!");
        userInterface.userLeaving();
        sc.close();

        //Storage
        try {
            byeCommand();
        } catch (IOException io) {
            System.out.print("Could not save list!");
        }
    }

    //Parser
    static void commandDone() throws ArrayIndexOutOfBoundsException {
        try {
            String value = input.split(" ")[1];
            int val = Integer.parseInt(value) - 1;
            list.markDone(val);
            userInterface.userDoneTask(list.getTaskAtIndex(val).toString());
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException("Please key in which task number to tick off.");
        }

    }

    //Parser
    static void cleanInput() {
        input = input.replaceAll("\n", "");
        input = input.toLowerCase();
    }

    //Parser
    static void commandTodo() throws Exception{
        String task = input.replaceFirst("todo", "");
        task = task.stripTrailing();
        if (task.isEmpty()) {
            throw new Exception("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        list.addTodo(task);
        userInterface.userAddTask(list);
    }

    //Parser
    static void commandDeadline() {
        String deadline = input.replaceFirst("deadline", "");
        deadline = deadline.stripLeading();
        String[] deadlineArr = deadline.split("/", 2);
        list.addDeadline(deadlineArr[0], inputDeadline(deadlineArr[1]));
        userInterface.userAddTask(list);
    }

    //Parser
    static void commandEvent() {
        String eventDetails = input.replaceFirst("event", "");
        eventDetails = eventDetails.stripLeading();
        String[] eventDeats = eventDetails.split("/", 2);
        list.addEvent(eventDeats[0], inputDeadline(eventDeats[1]));
        userInterface.userAddTask(list);
    }

    //Parser
    static void commandDelete() {
        String value = input.replaceFirst("delete", "");
        value = value.strip();
        int val = Integer.parseInt(value);
        Task delete = list.deleteTaskAtIndex(val - 1);
        userInterface.userDeleteTask(delete, list);
    }

    //Parser
    static void commandList() {
        list.listAllTasks();
    }

    //Parser
    static LocalDateTime inputDeadline(String inputDate) {
        String[] dataArray = inputDate.split(" ");
        LocalDate formatDate = inputDate(dataArray[1]);
        LocalTime formatTime = inputTime(dataArray[2]);
        return LocalDateTime.of(formatDate, formatTime);
    }

    //Parser
    static LocalDate inputDate(String input) {
        String[] dateArray = input.split("/");
        LocalDate date = LocalDate.of(Integer.parseInt(dateArray[2]), 
                                            Integer.parseInt(dateArray[1]), 
                                            Integer.parseInt(dateArray[0]));
        return date;
    }

    //Parser
    static LocalTime inputTime(String input) {
        String hour = input.substring(0, 2);
        String minutes = input.substring(2); 
        LocalTime time = LocalTime.of(Integer.parseInt(hour), Integer.parseInt(minutes));
        return time;
    }

    //Parser
    static boolean validCommand() {
        return input.contains("delete") || input.contains("event") || input.contains("done") ||
                input.contains("todo") || input.contains("deadline") || input.contains("list");
    }

    static void byeCommand() throws IOException {
        storage.saveHistory(list);
    }

}



