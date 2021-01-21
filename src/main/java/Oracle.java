import java.util.ArrayList;
import java.util.Scanner;

public class Oracle {
    private static ArrayList<Task> db = new ArrayList<>();
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

        Scanner S = new Scanner(System.in);
        while(true) {
            String input = S.nextLine();  // Read user input
            String[] split = input.split(" ", 2);
            if (input.equals("bye")){
                System.out.println("Very well, we shall meet again");
                break;
            }
            else if (input.equals("list")){
                System.out.println("You have forgotten quickly, but the Oracle Remembers");
                for (int i = 0; i< db.size(); i++){
                   printTask(i);
                }
            }
            else if (split[0].equals("done") && split.length > 1){
                int i = Integer.parseInt(split[1]) - 1;
                try {
                    db.get(i).markDone();
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Invalid index of task, please try again");
                    continue;
                }
                printTask(i);
            }
            else {
                db.add(new Task(input));
                System.out.println("I will keep " + "\"" + input + "\" in mind");
            }
        }
    }

    private static void printTask(int i){
        String index = (i+1) + ". ";
        String doneStatus = "[" + db.get(i).getStatusIcon() + "] ";
        System.out.println(index + doneStatus + db.get(i).description);
    }
}
