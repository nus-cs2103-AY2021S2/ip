import java.util.Scanner;

/**
 * Main Class of Execution for IP.
 */
public class Duke {
    /**
     * Main method for execution.
     * @param args Command Line Argument
     */
    public static void main(String[] args) {
        //Logo Display
        String logo = " ____            _      ____   ____\n" +
                "|    \\ |        / \\    |    \\ |    \\\n" +
                "|____/ |       /___\\   |____/ |____/\n" +
                "|    \\ |      /     \\  |  \\   |    \\\n" +
                "|____/ |____ /       \\ |   \\_ |____/\n";
        System.out.println("You are now in the presence of\n" + logo);

        //Import IO
        Scanner sc = new Scanner(System.in);
        Blarb blarb = new Blarb();

        while (sc.hasNextLine()) {
            if (!blarb.execute(sc.nextLine())) {
                break;
            }
        }
    }
}

