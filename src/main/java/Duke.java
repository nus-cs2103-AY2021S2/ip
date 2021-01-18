import java.util.Scanner;

public class Duke {

    private static Task[] taskArray;
    private static int count = 0;
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
                + taskArray[count - 1].toString() + "\n Now you have " + count
                + " tasks in your list \n" + lines);
    }

    public static void addTask(String userInput, String typeOfTask) throws DukeException {

        int addToTask = 0;
        String taskName;

        switch (typeOfTask) {
            case ("todo"):

                taskName = returnTaskName(userInput,"todo");
                if(!taskName.equals("no task name")){
                    taskArray[count++] = new ToDos(returnTaskName(userInput, "todo"));
                    addToTask= 1;
                }
                break;

            case ("deadline"):

                if(userInput.contains("/by")){
                    String dueBy[] = userInput.split("/by");
                    taskName = returnTaskName(dueBy[0], "deadline");

                    if (!taskName.equals("no task name") || !checkForAdditionalInfo(dueBy[0])) {
                        taskArray[count++] = new Deadlines(taskName, dueBy[dueBy.length - 1]);
                        addToTask = 1;
                    }
                }else {
                    checkForAdditionalInfo("wrongInfo");
                }

                break;
            case ("event"):

               if(userInput.contains("/at")) {

                    String dueDetails[] = userInput.split("/at ");
                    String date[] = dueDetails[dueDetails.length - 1].split(" ");

                    String[] startTimeArr = date[date.length - 1].split("-");
                    String startTime = startTimeArr[0];
                    String endTime = startTimeArr[startTimeArr.length - 1];
                    taskName = returnTaskName(dueDetails[0], "event");

                    if (!taskName.equals("no task name") || !checkForAdditionalInfo(date[0])) {
                        taskArray[count++] = new Events(taskName, date[0], startTime, endTime);
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

    public static boolean invalidItemNumber(int no){

        try{
            if(no <= 0){
                throw new DukeException("There are no task in list. Please add some task and try again.");
            }
            return false;
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
                if(!invalidItemNumber(count)) {
                    System.out.println(lines + "\nHere are the tasks in your list:");
                    for (int i = 0; i < count; i++) {
                        System.out.println((i + 1) + "." + taskArray[i].toString());
                    }
                    System.out.println(lines);
                }
                break;

            case ("done"):
                int index = Integer.parseInt(commandArray[1])-1;

                if(!invalidItemNumber(index)) {
                    taskArray[index].setCompleted();
                    System.out.println(lines + "\nNice! I'll make this task as done: \n"
                            + taskArray[index].toString() + "\n" + lines);
                }
                break;
            default:
                addTask(command, commandArray[0]);
        }
    }

    public static void main(String[] args) throws DukeException {

        displayWelcomeMessage();

        String userInput = sc.nextLine();
        taskArray = new Task[100];

        while (!userInput.equals("bye")) {
            executeCommand(userInput);
            userInput = sc.nextLine();
        }
        displayEndMessage();
    }
}
