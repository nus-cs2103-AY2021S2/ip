import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static List<Task> tasks = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws EmptyArgumentException, InvalidCommandException {
        String logo =
                " ____        _        \n"
                        + "|  _ \\ _   _| | _____ \n"
                        + "| | | | | | | |/ / _ \\\n"
                        + "| |_| | |_| |   <  __/\n"
                        + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hi Im Duke, how may I help you?");
        while(true) {
            try {
                String cmd = sc.next();
                if (cmd.equals("bye")) {
                    byeUser(logo);
                    break;
                } else if (cmd.equals("list")) {
                    listItems();
                } else if (cmd.equals("done")) {
                    int itemNo = sc.nextInt();
                    markItemAsDone(itemNo);
                } else if (cmd.equals("delete")) {
                    int itemNo = sc.nextInt();
                    deleteItem(itemNo);
                } else {
                    String typeOfEvent = cmd;
                    String eventDescription = sc.nextLine();
                    addItem(typeOfEvent, eventDescription);
                }
            }
            catch (InvalidCommandException e) {
                System.out.println(e.getMessage());
            }
            catch (EmptyArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void addItem(String typeOfEvent, String eventDescription) throws EmptyArgumentException, InvalidCommandException {
        if(typeOfEvent.equals("todo")) {
            tasks.add(new Todos(eventDescription));
        }
        else {
            String[] _eventDescription = eventDescription.split("/");
            StringBuilder firstPart = new StringBuilder(_eventDescription[0]).append(" ");
            StringBuilder secondPart = new StringBuilder(_eventDescription[1]);
            secondPart.insert(0, '(');
            secondPart.insert(3, ':');
            secondPart.append(')');
            firstPart.append(secondPart);
            if (typeOfEvent.equals("deadline")) tasks.add(new Deadline(firstPart.toString()));
            else if (typeOfEvent.equals("event")) tasks.add(new Event(firstPart.toString()));
        }
        System.out.print("added: ");
        System.out.println(tasks.get(tasks.size()-1));
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
    }

    private static void deleteItem(int itemNo) {
        Task toBeDeleted = tasks.get(itemNo-1);
        tasks.remove(itemNo-1);
        System.out.println("Noted.I have removed this task:");
        System.out.print("  ");
        System.out.println(toBeDeleted);
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
    }

    private static void markItemAsDone(int itemNo) {
        tasks.get(itemNo-1).isCompleted = true;
        System.out.println("Nice, I have marked this task as done!");
        System.out.print("  ");
        System.out.print(tasks.get(itemNo-1));
    }

    private static void listItems() {
        System.out.println("here are your tasks:");
        for(int itemNo=1;itemNo<=tasks.size();itemNo++) {
            System.out.print("  ");
            System.out.println("" + itemNo + ". " + tasks.get(itemNo-1));
        }
    }

    private static void byeUser(String logo) {
        System.out.println("Bye bye, have a nice day! Thanks for using " + logo);
    }

}
