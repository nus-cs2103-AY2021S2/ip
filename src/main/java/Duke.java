import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String logo = "░▄▀▄▀▀▀▀▄▀▄░░░░░░░░░\n"
                + "░█░░░░░░░░▀▄░░░░░░▄░\n"
                + "█░░▀░░▀░░░░░▀▄▄░░█░█\n"
                + "█░▄░█▀░▄░░░░░░░▀▀░░█\n"
                + "█░░▀▀▀▀░░░░░░░░░░░░█\n"
                + "█░░░░░░░░░░░░░░░░░░█\n"
                + "█░░░░░░░░░░░░░░░░░░█\n"
                + "░█░░▄▄░░▄▄▄▄░░▄▄░░█░\n"
                + "░█░▄▀█░▄▀░░█░▄▀█░▄▀░\n"
                + "░░▀░░░▀░░░░░▀░░░▀░░░\n";


        System.out.println("Greetings from\n" + logo);

        String spacer = "____________________________________________________________\n";
        String greet = "Woof! I'm Doge\n"
                + "What do you want me to do?\n"
                + "Type your request in below!\n";
        String goodbye = "Bye! Hope I was a good dog, see you again soon!\n";
        String terminate = "bye";



        System.out.println(spacer + greet + spacer);

        while (true) {
            String echo = sc.nextLine();
            System.out.println(spacer + echo + "\n" + spacer);
            if (echo.equals(terminate)) {
                System.out.println(spacer + goodbye + spacer);
                break;
            }
        }

        sc.close();

    }
}
