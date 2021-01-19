import javax.net.ssl.HostnameVerifier;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String HORIZONTAL_RULE = "____________________________________________________________";
    private static ArrayList<String> userList = new ArrayList<>();
    public static void main(String[] args) {
        Duke.basicProgram();
    }
    private static void basicProgram(){
        String greeting = HORIZONTAL_RULE + "\nHello! I am Duke\n" + "What can I do for you?\n" + HORIZONTAL_RULE;
        String exitCommand = "bye";
        String listCommand = "list";
        System.out.println(greeting);
        Scanner sc = new Scanner(System.in);
        boolean isBye = false;
        while (!isBye){
            String command = sc.nextLine();
            System.out.println(HORIZONTAL_RULE);
            if(command.equals(exitCommand)){
                System.out.println(command + ". Hope to see you again soon!\n"+ HORIZONTAL_RULE);
                sc.close();
                isBye = true;
            }
            else if(command.equals(listCommand)){
                Duke.printUserList();
                System.out.println(HORIZONTAL_RULE);
            }
            else{
                Duke.userList.add(command);
                System.out.println("added: " + command +"\n"+ HORIZONTAL_RULE);
            }
        }
    }
    private static void printUserList(){
        for(int i = 0; i< userList.size();i++){
            System.out.println(i+1 + ". " + userList.get(i));
        }
    }
}
