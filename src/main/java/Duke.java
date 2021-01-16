import java.util.Scanner;

public class Duke {
    private static final String TOP_BORDER = "╭--------------------------------------------╮";
    private static final String BTM_BORDER = "╰|╱ -----------------------------------------╯";
    private static final String PADDING = "  ";

    public static void main(String[] args) {
        printGreeting();

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            echoInput(input);
            input = sc.nextLine();
        }
        sc.close();

        printBye();
    }

    public static void printGreeting() {
        String logo = "              .--.    .-.         .-.   \n" +
                "             : .-'    : :         : :   \n" +
                " .--.  .--.  : `;.--. : `-.  .--. : `-. \n" +
                "'  ..'' .; ; : :' '_.'' .; :' '_.'' .; :\n" +
                "`.__.'`.__,_;:_;`.__.'`.__.'`.__.'`.__.'";
        System.out.println(logo);
        String greeting = " ╭------------------------------------------------------------------╮\n"
                        + " |  Hello! I'm cafebeb, here to help you keep track of measly tasks | \n"
                        + " |  in your mundane human life. How may I help you today?           |\n"
                        + " ╰|╱ ---------------------------------------------------------------╯  \n";

        System.out.println(greeting);
    }

    public static void printBye() {
        String farewell = " ╭---------------------------------------╮\n"
                        + " |  Bye! Hope you complete your tasks!   |\n"
                        + " ╰|╱ ------------------------------------╯\n";
        System.out.println(farewell);
    }

    public static void echoInput(String input) {
        System.out.println(TOP_BORDER);
        System.out.println(PADDING + input);
        System.out.println(BTM_BORDER);
    }
}

