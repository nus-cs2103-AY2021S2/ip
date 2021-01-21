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
            String[] split = input.split(" ", 2); // Split user input
            if (input.equals("bye")){
                System.out.println("Very well, we shall meet again");
                break;
            }
            else if (input.equals("list")){
                System.out.println("You have forgotten quickly, but the Oracle Remembers");
                for (int i = 0; i< db.size(); i++){
                    System.out.println((i+1) + ". " + db.get(i));
                }
            }
            else if (split[0].equals("done") && split.length > 1){
                int i = Integer.parseInt(split[1]) - 1;
                try {
                    db.get(i).markDone();
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("The Oracle cannot work with an invalid index");
                    continue;
                }
                System.out.println((i+1) + ". " + db.get(i));
            }
            else if (split[0].equals("todo") && split.length > 1){
                db.add(new Todo(split[1]));
                System.out.println(db.size() + ". " + db.get(db.size()-1));
            }
            else if (split[0].equals("event") && split.length > 1){
                String[] params = split[1].split("/", 2);
                db.add(new Event(params[0], params[1]));
                System.out.println(db.size() + ". " + db.get(db.size()-1));
            }
            else if (split[0].equals("deadline") && split.length > 1){
                String[] params = split[1].split("/", 2);
                db.add(new Deadline(params[0], params[1]));
                System.out.println(db.size() + ". " + db.get(db.size()-1));
            }
            else {
                System.out.println("Your words are unclear, Neo");
            }
        }
    }
}
