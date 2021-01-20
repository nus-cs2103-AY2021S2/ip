import java.util.*;

public class Duke {
    public static final String line = String.format("%" + 5 + "s", " ") + "____________________________________________________________________";
    public static final Scanner sc = new Scanner(System.in);
    public static List<Task> list = new ArrayList<>();
    public static int currentID = 1;

    public static String align(String s) { // to align output
        int stringLength = s.length();
        int LINELENGTH = 73;
        int left = (int) Math.ceil((LINELENGTH - stringLength) / 2);
        int right = (int) Math.floor((LINELENGTH - stringLength) / 2);
        String newString;
        newString = String.format("%" + left + "s", " ") + s + String.format("%" + right + "s", " ");
        return newString;
    }

    public static void start() {
        System.out.println(line);
        String logo = "\n" +
                "     ███╗░░░███╗░█████╗░██╗░░░██╗███╗░░░███╗░█████╗░██╗░░░██╗\n" +
                "     ████╗░████║██╔══██╗██║░░░██║████╗░████║██╔══██╗██║░░░██║\n" +
                "     ██╔████╔██║██║░░██║██║░░░██║██╔████╔██║██║░░██║██║░░░██║\n" +
                "     ██║╚██╔╝██║██║░░██║██║░░░██║██║╚██╔╝██║██║░░██║██║░░░██║\n" +
                "     ██║░╚═╝░██║╚█████╔╝╚██████╔╝██║░╚═╝░██║╚█████╔╝╚██████╔╝\n" +
                "     ╚═╝░░░░░╚═╝░╚════╝░░╚═════╝░╚═╝░░░░░╚═╝░╚════╝░░╚═════╝░";
        System.out.println(logo + " is back!\n     What have you awoken MouMou for?");
        System.out.println(line);
    }

    public static void handleCommand(String command) {
        System.out.println(line);
        if (command.equals("list")) {
            System.out.println(align("Here are the tasks in your list:"));
            for (Task t : list) {
                System.out.println(align(t.getID() + "." + t.toString()));
            }
        } else if (command.substring(0,4).equals("done")) {
            int taskNo = Integer.parseInt(String.valueOf(command.charAt(5))) - 1;
            list.get(taskNo).markAsDone();
            System.out.println(align("Nice! I've marked this task as done:"));
            System.out.println(align(list.get(taskNo).toString()));
        } else {
            System.out.println(align("Sure! I've added this task:"));
            if (command.substring(0,4).equals("todo")) {
                ToDo newToDo = new ToDo(command.substring(5), currentID);
                list.add(newToDo);
                System.out.println(align(newToDo.toString()));
            } else {
                int indexOfDate = command.indexOf("/");
                if (command.substring(0,4).equals("dead")) {
                    String date = command.substring(indexOfDate + 4);
                    Deadline newDeadline = new Deadline(command.substring(9, indexOfDate), currentID, date);
                    list.add(newDeadline);
                    System.out.println(align(newDeadline.toString()));
                } else {
                    String date = command.substring(indexOfDate + 4);
                    Event newEvent = new Event(command.substring(6, indexOfDate), currentID, date);
                    list.add(newEvent);
                    System.out.println(align(newEvent.toString()));
                }
            }
            currentID++;
            System.out.println(align("Now you have " + list.size() + " tasks in the list."));
        }
        System.out.println(line);
    }

    public static void end() {
        System.out.println(line);
        System.out.println(align("Goodnight! MouMou will go back to sleep now."));
        System.out.println(line);
    }

    public static void main(String[] args) {
        start();
        String input = sc.nextLine();
        while (!input.equals("bye")) { //user input is not bye
            handleCommand(input);
            input = sc.nextLine();
        }
        end();
    }
}
