import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static ArrayList<Task> taskArraylist = new ArrayList<Task>();
    private static Scanner sc = new Scanner(System.in);
    static final String lines = "----------------------------------------";

    public static void displayWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hi There! Greetings from \n" + logo);
        System.out.println(lines + "\n" + " Good day! I'm Duke" + "\n" + " How can I help you today? " + "\n" + lines);

    }

    public static void displayEndMessage() {
        sc.close();
        System.out.println(lines + "\n" + " Bye. Hope to see you again!" + "\n" + lines);
    }

    public static void displayAddedTaskMessage() {
        System.out.println(lines + "\nGot it. I've added this task: \n\t"
                + taskArraylist.get(taskArraylist.size()-1).toString() + "\n Now you have " + taskArraylist.size()
                + " tasks in your list \n" + lines);
    }

    public static void addTask(String userInput, String typeOfTask) throws DukeException {

        int addToTask = 0;
        String taskName;

        switch (typeOfTask) {
            case ("todo"):

                taskName = returnTaskName(userInput,"todo");
                if(!taskName.equals("no task name")){
                    taskArraylist.add(new ToDos(returnTaskName(userInput, "todo")));
                    addToTask= 1;
                }
                break;

            case ("deadline"):

                if(userInput.contains("/by")){
                    String dueBy[] = userInput.split("/by ");
                    taskName = returnTaskName(dueBy[0], "deadline");
                    String[] stringTime = dueBy[1].split(" ");

                    DateTimeFormatter df = DateTimeFormatter.ofPattern("d/MM/yyyy");
                    LocalDate date = LocalDate.parse(stringTime[0], df);

                    DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("HHmm");
                    LocalTime time = LocalTime.parse(stringTime[1],inputFormat);

                    if (!taskName.equals("no task name") || !checkForAdditionalInfo(dueBy[0])) {
                        taskArraylist.add( new Deadlines(taskName,date,time ));
                        addToTask = 1;
                    }
                }else {
                    checkForAdditionalInfo("wrongInfo");
                }

                break;
            case ("event"):

               if(userInput.contains("/at")) {

                    String dueDetails[] = userInput.split("/at ");
                    String dateTime[] = dueDetails[1].split(" ");

                    String[] timeArr = dateTime[1].split("-");
                    String startTime = timeArr[0];
                    String endTime = timeArr[timeArr.length - 1];

                    DateTimeFormatter df = DateTimeFormatter.ofPattern("d/MM/yyyy");
                    LocalDate localdate = LocalDate.parse(dateTime[0], df);

                    DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("HHmm");

                    taskName = returnTaskName(dueDetails[0], "event");

                    if (!taskName.equals("no task name") || !checkForAdditionalInfo(dueDetails[1])) {
                        taskArraylist.add(new Events(taskName, localdate, LocalTime.parse(startTime,inputFormat),
                                LocalTime.parse(endTime,inputFormat)));
                        addToTask = 1;
                    }
                } else{
                    checkForAdditionalInfo("wrongInfo");
                }

                break;
            default:
                returnTaskName(userInput,"default");
        }

        if(addToTask == 1){
            displayAddedTaskMessage();
        }

    }

    public static String generateTaskName(String userInput, String type) throws DukeException {

        String taskName  = userInput.replace(type, "");

        if(type.equals("default")){
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        } else if (taskName.isBlank() && !type.equals("default")) {
            throw new DukeException("OOPS!!! The description of a " + type + " cannot be empty.");
        }  else{
            return taskName;
        }
    }

    public static String returnTaskName(String userInput, String type){

        String task = "no task name";
        try {
            task = generateTaskName(userInput,type);
        } catch (DukeException exception){
            exception.printMessage();
        }
        return task;
    }

    public static boolean invalidItemNumber(int no, String type){

        try{
            if(no < 0 || no >= taskArraylist.size() && !type.equals("done") ){
                throw new DukeException("There are no task in list. Please add some task and try again.");
            }
            else if(no >= taskArraylist.size() && type.equals("delete") ){
                throw new DukeException("Item number " + no + " is not found in list. Please check again");
            }else {
                return false;
            }

        }catch (DukeException e){
            e.printMessage();
            return true;
        }
    }

    public static void noAdditionalInfo(String s) throws DukeException {
        if(s.isBlank()){
            throw new DukeException("OOPS!!! The deadline or start/end time cannot be empty");
        }
        if(s.equals("wrongInfo")){
            throw new DukeException("OOPS!!! Wrong command to add deadlines or start/end time. " +
                    "Use /by for deadline and /at for event");
        }
    }

    public static boolean checkForAdditionalInfo (String input){
        try{
            noAdditionalInfo(input);
            return true;
        }
        catch (DukeException de){
            de.printMessage();
            return false;
        }
    }

    public static void executeCommand(String command) throws DukeException {

        String commandArray[] = command.split("\\s+");

        switch (commandArray[0]) {

            case ("list"):
                if(!invalidItemNumber(taskArraylist.size()-1, "")) {
                    System.out.println(lines + "\nHere are the tasks in your list:");
                    for (int i = 0; i < taskArraylist.size(); i++) {
                        System.out.println((i + 1) + "." + taskArraylist.get(i).toString());
                    }
                    System.out.println(lines);
                }
                break;

            case ("done"):
                int index = Integer.parseInt(commandArray[1])-1;
                if(!invalidItemNumber(index,"")) {
                    taskArraylist.get(index).setCompleted();
                    System.out.println(lines + "\nNice! I'll make this task as done: \n"
                            + taskArraylist.get(index).toString() + "\n" + lines);
                }
                break;

            case("delete"):
                int indexInArray = Integer.parseInt(commandArray[1])-1;
                if(!invalidItemNumber(indexInArray, "delete")) {
                    System.out.println(lines + "\nNice! I've removed this task: \n"
                            + taskArraylist.get(indexInArray).toString() + "\n" + lines);
                    taskArraylist.remove(indexInArray);
                }
                break;
            default:
                addTask(command, commandArray[0]);
        }
    }

    public static void main(String[] args) throws DukeException {

        displayWelcomeMessage();

        String userInput = sc.nextLine();

        while (!userInput.equals("bye")) {
            executeCommand(userInput);
            userInput = sc.nextLine();
        }
        displayEndMessage();
    }
}
