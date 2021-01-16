import java.util.*;

public class Duke {
    public static String topBorder = "\n    ********************************* \n";
    public static String btmBorder = "    ********************************* \n";
    public static ArrayList<Task> list = new ArrayList<>();
    public static String markMsg = "You have marked this task as completed: \n";
    public static String addMsg = "Roger that! Added the following task: \n \n      ";
    public static String deleteMsg = "Roger that! Deleted the follow task: \n \n      ";
    public static String indent4 = "     ";

    public static void printBox(String input) {
        System.out.println(topBorder + indent4 + input + "\n" + btmBorder);
    }

    public static void printList(List<Task> list) {
        System.out.print(topBorder);
        System.out.println("     This is your present task list: \n");
        for (int i = 1; i <= list.size(); i++) {
            if (i == list.size()) {
                System.out.println(indent4 + i + ") " + list.get(i - 1));
            } else {
                System.out.println(indent4 + i + ") " + list.get(i - 1));
            }
        }

        System.out.println(btmBorder);
    }

    public static String getTally() {
        return "     Currently you have " + list.size() + " tasks in the list.";
    }

    public static void process(String input) {
        try {
            String[] content = input.split("\\s+");
            String command = content[0];
            if (command.equals("list")) {
                printList(list);
            } else {
                if (command.equals("done")) {
                    int index = Integer.parseInt(content[1]);
                    Task task = list.get(index - 1);
                    task.complete();
                    printBox(markMsg + "      " + task);
                } else if (command.equals("delete")) {
                    int index = Integer.parseInt(content[1]);
                    String task = list.get(index - 1).toString();
                    list.remove(index - 1);
                    printBox(deleteMsg + task + "\n \n" + getTally());

                } else {

                    String[] description = Arrays.copyOfRange(content, 1, content.length);
                    Task newTask;
                    if (command.equals("todo")) {
                        newTask = new Todo(description);
                        list.add(newTask);
                        printBox(addMsg + newTask + "\n \n" + getTally());
                    } else if (command.equals("deadline")) {
                        newTask = new Deadline(description);
                        list.add(newTask);
                        printBox(addMsg + newTask + "\n \n" + getTally());
                    } else if (command.equals("event")) {
                        newTask = new Event(description);
                        list.add(newTask);
                        printBox(addMsg + newTask + "\n \n" + getTally());
                    } else {
                        printBox("☹ OOPS!!! Incorrect input, please check!");
                    }

                }

            }
        } catch (DukeException err) {
            printBox(err.getMessage());

        }
        // catch (Exception err) {
        // printBox("☹ OOPS!!! Incorrect input, please check!");
        // }
    }

    public static void main(String[] args) {
        String welcomeMsg = "         █▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀█ \n" + "         █░░╦─╦╔╗╦─╔╗╔╗╔╦╗╔╗░░█ \n"
                + "         █░░║║║╠─║─║─║║║║║╠─░░█ \n" + "         █░░╚╩╝╚╝╚╝╚╝╚╝╩─╩╚╝░░█ \n"
                + "         █▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄█ \n";
        String logo = "         ──────▄▀▄─────▄▀▄ \n" + "         ─────▄█░░▀▀▀▀▀░░█▄ \n"
                + "         ─▄▄──█░░░░░░░░░░░█──▄▄ \n" + "         █▄▄█─█░░▀░░┬░░▀░░█─█▄▄█ ";

        String greeting = "Hey there! I'm Kawaii Kat \n" + "     How can i assist you nya~?";

        String exitMessage = "Farewell. See you soon :)!";

        System.out.println(welcomeMsg + logo);
        printBox(greeting);

        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        while (!input.equals(("bye"))) {
            process(input);
            input = sc.nextLine();
        }

        printBox(exitMessage);

    }
}
