import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String line = String.format("%" + 5 + "s", " ") + "____________________________________________________________________";
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
        Scanner sc = new Scanner(System.in);
        String toPrint = "";
        int currentID = 1;
        String input = sc.nextLine();
        List<Task> list = new ArrayList<>();
        while (!input.equals("bye")) { //user input is not bye
            System.out.println(line);
            if (input.equals("list")) {
                System.out.println(align("Here are the tasks in your list:"));
                for (Task t : list) {
                    System.out.println(align(t.getID() + "." + t));
                }
            } else if (input.substring(0,4).equals("done")) {
                int taskNo = Integer.parseInt(String.valueOf(input.charAt(5))) - 1;
                list.get(taskNo).markAsDone();
                System.out.println(align("Nice! I've marked this task as done:"));
                System.out.println(align(list.get(taskNo).toString()));
            } else {
                Task newTask = new Task(input, currentID);
                list.add(newTask);
                currentID++;
                toPrint = "added: " + newTask.getDescription();
                System.out.println(align(toPrint));
            }
            System.out.println(line);
            input = sc.nextLine();
        }
        System.out.println(line);
        System.out.println(align("Goodnight! MouMou will go back to sleep now."));
        System.out.println(line);
    }

    static String align(String s) { // to align
        int stringLength = s.length();
        int LINELENGTH = 73;
        int left = (int) Math.ceil((LINELENGTH - stringLength) / 2);
        int right = (int) Math.floor((LINELENGTH - stringLength) / 2);
        String newString = "";
        newString = String.format("%" + left + "s", " ") + s + String.format("%" + right + "s", " ");
        return newString;
    }
}
