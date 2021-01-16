import java.util.Scanner;

public class Duke {

    private static String[] taskArray;
    private static int count =0;
    private static Scanner sc = new Scanner(System.in);
    static final String lines = "----------------------------------------";


    public static void displayWelcomeMessage(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hi There! Greetings from \n" + logo);
        System.out.println(lines + "\n" + " Good day! I'm Duke" + "\n" + " How can I help you today? " + "\n" + lines);

    }

    public static void displayEndMessage(){
        sc.close();
        System.out.println( lines + "\n" + " Bye. Hope to see you again!" + "\n" + lines);
    }

    public static void executeCommand(String command){
        if(!command.equals("list")){
            taskArray[count] = command;
            count++;
            System.out.println( "added: " + command + "\n" + lines);
        }
        else{
            for (int i = 0; i <count; i++) {
                System.out.println((i+1) + ". " +  taskArray[i]);
            }
            System.out.println(lines);
        }
    }

    public static void main(String[] args) {

        displayWelcomeMessage();

        String userInput = sc.nextLine();
        taskArray = new String[100];

        while(!userInput.equals("bye")) {
            executeCommand(userInput);
            userInput = sc.nextLine();
        }

        displayEndMessage();
    }
}