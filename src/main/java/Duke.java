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

    public static void displayAddedTaskMessage(){
        System.out.println(lines + "\nGot it. I've added this task: \n"
                + taskArray[count-1].toString() + "\n Now you have " + count
                + " tasks in your list \n" + lines);
    }

    public static void addTask(String userInput, String typeOfTask){

        boolean defaultTask = false;

        switch (typeOfTask){
            case ("todo"):
                taskArray [count++] = new ToDos(generateTaskName(userInput, "todo"));
                break;

            case("deadline"):

                String dueBy[] = userInput.split("/by");
                taskArray [count++] = new Deadlines(generateTaskName(dueBy[0], "deadline"),
                        dueBy[dueBy.length-1]);
                break;
            case("event"):

                String dueDetails[] = userInput.split("/at ");

                String date[] = dueDetails[dueDetails.length-1].split(" ");

                String[] startTimeArr = date[date.length-1].split("-");
                String startTime = startTimeArr[0];
                String endTime = startTimeArr[startTimeArr.length-1];

                taskArray [count++] = new Events(generateTaskName(dueDetails[0], "event"),
                        date[0], startTime,endTime);
                break;
            default:
                taskArray [count++] = new Task (userInput);
                defaultTask = true;
                System.out.println("added: " + userInput + "\n" + lines);
                break;
        }
        if(!defaultTask) {
            displayAddedTaskMessage();
        }

    }

    public static String generateTaskName(String userInput, String type){
        return userInput.replace(type, "");
    }


    public static void executeCommand(String command) {

        String commandArray[] = command.split("\\s+");

        switch (commandArray[0]) {
            case ("list"):

                System.out.println(lines + "\nHere are the tasks in your list:");
                for (int i = 0; i < count; i++) {
                    System.out.println((i + 1) + "." + taskArray[i].toString());
                }
                System.out.println(lines);
                break;

            case ("done"):

                int index = Integer.parseInt(commandArray[1])-1;
                taskArray[index].setCompleted();

                System.out.println(lines + "\nNice! I'll make this task as done: \n"
                        + taskArray[index].toString() + "\n" + lines);
                break;


            default:
                addTask(command, commandArray[0]);
                break;
        }
    }

    public static void main(String[] args) {

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
