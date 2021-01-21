import java.util.ArrayList;
import java.util.Scanner;

public class Oracle {
    public static void main(String[] args) {
        String logo =
                "              $$$@@@@@@@\n"
                + "         ##########$$$$$$@@$\n"
                + "       #**!!!!!!!!**####$$$$$$#\n"
                + "     **!!==!=;;=;;!!!**###$$$$$$#\n"
                + "    **!!==;::~~::;;;;=!!*####$$$$##\n"
                + "   !!!!=;::~-,,,,--:;;=!!**########*\n"
                + "  !!!!=;:~,........-~:==!!**#######**\n"
                + " =!*!!=;:~,.........-:;=!!***######**\n"
                + " !***!!=:~,...     .-:;=!!****####***=\n"
                + " !*###**=;=..       -:;=!!**********!=\n"
                + ":!*###$##*=:       -:;=!!!********!!!=\n"
                + ":!##$$$$$$#*~     :;===!!*********!!=;\n"
                + "~=*#$$@@@@$$#*! ====!!!!!*****!!*!!==\n"
                + " ;!*#$$@@@$$##**!!!!!!!!!!*!*!!!!!==:\n"
                + " :;!*#$$$$$$###******!!!!!!!!!!!==;:\n"
                + "  ;!=!*#######******!!!!!!!!!===;;:,\n"
                + "   :;==!!********!!!!!!!!====;;;:~\n"
                + "    -:;!====!=!!!!!!!======;;::~,\n"
                + "      -~:;===;======;=;;;:::~-,\n"
                + "        .-~~::::;:::::~:~--.\n";
        System.out.println(logo + "\nGreetings Neo, what can the Oracle do for you?");

        ArrayList<Task> theOracleRemembers = new ArrayList<>();

        Scanner S = new Scanner(System.in);
        while(true) {
            String input = S.nextLine();  // Read user input
            if (input.equals("bye")){
                System.out.println("Very well, we shall meet again");
                break;
            }

            else if (input.equals("list")){
                System.out.println("You have forgotten quickly, but the Oracle Remembers");
                for (int i=0; i<theOracleRemembers.size(); i++){
                    System.out.println((i+1) + ". " + theOracleRemembers.get(i).description);
                }
            }
            else {
                theOracleRemembers.add(new Task(input));
                System.out.println("I will keep " + "\"" + input + "\" in mind");
            }
        }
    }
}
