import java.util.NoSuchElementException;
import java.util.Scanner;

public class Maya {
    public static void main(String[] args) {
        String logo = "          ____                               \n" +
                "        ,'  , `.                                   \n" +
                "     ,-+-,.' _ |                                   \n" +
                "  ,-+-. ;   , ||                                   \n" +
                " ,--.'|'   |  ;|                                   \n" +
                "|   |  ,', |  ':  ,--.--.        .--,    ,--.--.   \n" +
                "|   | /  | |  || /       \\     /_ ./|   /       \\  \n" +
                "'   | :  | :  |,.--.  .-. | , ' , ' :  .--.  .-. | \n" +
                ";   . |  ; |--'  \\__\\/: . ./___/ \\: |   \\__\\/: . . \n" +
                "|   : |  | ,     ,\" .--.; | .  \\  ' |   ,\" .--.; | \n" +
                "|   : '  |/     /  /  ,.  |  \\  ;   :  /  /  ,.  | \n" +
                ";   | |`-'     ;  :   .'   \\  \\  \\  ; ;  :   .'   \\\n" +
                "|   ;/         |  ,     .-./   :  \\  \\|  ,     .-./\n" +
                "'---'           `--`---'        \\  ' ; `--`---'    \n" +
                "                                 `--` ";

        System.out.println(logo);

        System.out.println("    ____________________________________");
        System.out.println("    Hello, I'm Maya! \n    What can I do for you?");
        System.out.println("    ____________________________________");

        Scanner sc = new Scanner(System.in);
        ListManager list = new ListManager();

        while (sc.hasNext()) {
            try {
                String command = sc.next();
                System.out.println("    ____________________________________");

                switch(command) {
                    case "bye":
                        System.out.println("    Bye. Hope to see you again soon!");
                        break;
                    case "list":
                        list.displayList();
                        break;
                    case "done":
                        // To get the index 
                        int index = sc.nextInt();
                        list.markTaskAsDone(index);
                        break;
                    case "todo":
                        String name = sc.nextLine();
                        if (!name.equals("")) {
                            list.addTask(new Todo(name));
                        } else {
                            throw new NoSuchElementException("    ☹ OOPS!!! The description of a todo cannot be empty.");
                        }
                        break;
                    case "deadline":
                        String desc = sc.nextLine();
                        if (!desc.equals("")) {
                            String[] split = desc.split("/by", 2);
                            list.addTask(new Deadline(split[0], split[1].trim()));
                        } else {
                            throw new NoSuchElementException("    ☹ OOPS!!! The description of a deadline cannot be empty.");
                        }
                        break;
                    case "event":
                        String description = sc.nextLine();
                        if (!description.equals("")) {
                            String[] split = description.split("/at", 2);
                            list.addTask(new Event(split[0], split[1].trim()));
                        } else {
                            throw new NoSuchElementException("    ☹ OOPS!!! The description of an event cannot be empty.");
                        }
                        break;
                    case "delete":
                        // To get the index 
                        int i = sc.nextInt();
                        list.removeTask(i);
                        break;
                    default:
                        throw new UnknownCommandException();
                }

                // To exit the program with the command "bye"
                if (command.equals("bye")) {
                    break;
                }           
            } catch (UnknownCommandException e) {
                System.out.println(e.getMessage());
            } catch (NoSuchElementException e) {
                System.out.println(e.getMessage());
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
            } finally {
                System.out.println("    ____________________________________");
            }
        }
        
        sc.close();
    }
}
