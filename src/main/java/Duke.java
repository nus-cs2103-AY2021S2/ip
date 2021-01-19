import java.util.Scanner;

public class Duke {
    private static final String HORIZONTAL_RULE = "____________________________________________________________";
    private static final String indentation = "    ";
    public static void main(String[] args) {
        String greeting = HORIZONTAL_RULE + "\nHello! I am Duke\n" + "What can I do for you?\n" + HORIZONTAL_RULE;
        System.out.println(greeting);
        Scanner sc = new Scanner(System.in);
        boolean isBye = false;
        while (!isBye){
            String command = sc.nextLine();
            System.out.println(HORIZONTAL_RULE);
            if(command.equals("bye")){
                System.out.println(command + ". Hope to see you again soon!\n"+ HORIZONTAL_RULE);
                sc.close();
                isBye = true;
            }
            else{
                System.out.println(command +"\n"+ HORIZONTAL_RULE);
            }
        }
    }
}
