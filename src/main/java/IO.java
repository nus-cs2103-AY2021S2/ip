import java.util.Scanner;

public class IO {
    private static String DIVIDER = "____________________________________________________________\n";
    public static void printBotMessage(String message){
        String printMessage = DIVIDER + message + "\n"+DIVIDER;
        print(printMessage);
    }
    private static void print(String message) {
        System.out.println(message);
    }
    public static String readLine(){
        Scanner scanf = new Scanner(System.in);
        String input = scanf.nextLine();
        return input;
    }
}
