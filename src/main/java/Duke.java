import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello from Gambit, how may I assist you today?");
        Scanner sc = new Scanner(System.in);

        while(sc.hasNext()) {
            String word = sc.next();
            if(!word.equals("bye")) {
                System.out.println(word);
            } else {
                System.out.println("Bye, hope to see you again!");
                break;
            }
        }
    }
}
