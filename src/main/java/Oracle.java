import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Oracle {
    // db is the arraylist used to store all the tasks given to THE ORACLE
    private static ArrayList<Task> db = new ArrayList<>();

    public static void main(String[] args) {
        // INTRODUCTORY GREETINGS
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

        //load file if available
        try {
            File store = new File("./oracle_data.txt");
            Scanner myReader = new Scanner(store);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] sorted = data.split("\u001E");
                Boolean isDone = sorted[1].equals("T");
                switch (sorted[0]){
                    case "T":
                        db.add(new Todo(isDone, sorted[2]));
                        break;
                    case "D":
                        db.add(new Deadline(isDone, sorted[2], sorted[3]));
                        break;
                    case "E":
                        db.add(new Event(isDone, sorted[2], sorted[3]));
                }
            }
            System.out.println("Loaded stored information from ./oracle_data.txt");
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Could not find the storage file, starting fresh database");
        }

        // SCANNER takes input from user in a while loop, parses input using a series of if-else statements
        Scanner S = new Scanner(System.in);
        while (true) {
            String input = S.nextLine();  // Read user input
            String[] split = input.split(" ", 2); // Split user input - used for operations with params
            /* CASE: TERMINATION */
            if (input.equals("bye")) {
                System.out.println("Very well, we shall meet again");
                break;
            }
            /* CASE: LIST */
            else if (input.equals("list")) {
                System.out.println("You have forgotten quickly, but the Oracle Remembers");
                for (int i = 0; i < db.size(); i++) {
                    System.out.println((i + 1) + ". " + db.get(i));
                }
            }
            /*
             CASE: MARK TASK AS DONE
             use: marks the task with given id as done
             form: 'done {id}', where id is a valid int corresponding to size of tasklist
            */
            else if (split[0].equals("done")) {
                try {
                    int i = Integer.parseInt(split[1]) - 1;
                    db.get(i).markDone();
                    System.out.println((i + 1) + ". " + db.get(i));
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("The Oracle recalls you only have " + db.size() + " tasks. Give a valid index");
                } catch (NumberFormatException e) {
                    System.out.println("Give The Oracle the index of the done task");
                }
            }
            /*
             CASE: DELETE TASK
             use: deletes the task with the given id from the arraylist
             form: 'delete {id}', where id is a valid int corresponding to size of tasklist
            */
            else if (split[0].equals("delete")) {
                try {
                    int i = Integer.parseInt(split[1]) - 1;
                    Task t = db.get(i);
                    db.remove(i);
                    System.out.println("Erased: " + (i + 1) + ". " + t);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("The Oracle recalls you only have " + db.size() + " tasks. Give a valid index");
                } catch (NumberFormatException e) {
                    System.out.println("Give The Oracle the index of the delete task");
                }
            }
            /*
             CASE: CREATE TODO
             use: creates a todo with the given description
             form: 'todo {description}'
            */
            else if (split[0].equals("todo")) {
                try {
                    db.add(new Todo(split[1]));
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Give The Oracle a the description of the todo");
                    continue;
                }
                System.out.println(db.size() + ". " + db.get(db.size() - 1));
            }
            /*
             CASE: CREATE EVENT
             use: creates a event with the given description and time
             form: 'event {description} /{time}'
            */
            else if (split[0].equals("event") && split.length > 1) {
                try {
                    String[] params = split[1].split("/", 2);
                    db.add(new Event(params[0], params[1]));
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Give The Oracle both the description/time of the event");
                    continue;
                }
                System.out.println(db.size() + ". " + db.get(db.size() - 1));
            /*
             CASE: CREATE DEADLINE
             use: creates a deadline with the given description and deadline
             form: 'event {description} /{deadline}'
            */
            } else if (split[0].equals("deadline")) {
                try {
                    String[] params = split[1].split("/", 2);
                    db.add(new Deadline(params[0], params[1]));
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Give The Oracle both the description/deadline of the deadline");
                    continue;
                }
                System.out.println(db.size() + ". " + db.get(db.size() - 1));
            }
            /*
             CASE: UNKNOWN INSTRUCTION
            */
            else {
                System.out.println("Your words are unclear, Neo");
            }
        }

        // store data
        try {
            FileWriter myWriter = new FileWriter("./oracle_data.txt");
            for (Task task : db){
                myWriter.write(task.toStorage()+ '\n');
            }
            myWriter.close();
            System.out.println("Successfully saved the information");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
