public class IO {
    private static String DIVIDER = "____________________________________________________________";
    public static void printBotMessage(String message){
        String printMessage = DIVIDER + message + DIVIDER;
        print(message);
    }
    private static void print(String message) {
        System.out.println(message);
    }
}
