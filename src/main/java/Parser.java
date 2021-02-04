import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Class parser makes sense of the user commands
 *
 * @version 28 Jan 2021
 * @author Zhang Peng
 */
public class Parser {
    public Parser() {
    }

    public String makingSenseOfUserCommand(ArrayList<Task> arrayList, String path, String input) {

      //while (scanner.hasNextLine()) {
       //     input = scanner.nextLine();

                if (!(input.contains("todo") || input.contains("event") || input.contains("deadline")
                        || input.contains("list") || input.contains("done") || input.contains("delete") || input.contains("find"))) {
                    return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
                } else if (input.equals("todo") || input.equals("event") || input.equals("deadline")
                        || input.equals("done") || input.equals("delete") || input.equals("find")) {
                    return "☹ OOPS!!! The description of a todo cannot be empty.";
                }                                // can add in more exceptions

            if (input.equals("bye")) {
                return "Bye. Hope to see you again soon!";

            } else if (input.equals("list")) {

                String one = "Here are the tasks in your list:";
                for (Task m : arrayList) {
                    one = one + m.index + ". " + m + "\n";
                }
                return one;


            } else if (input.contains("done") && !(input.equals("done"))) {
                int x = Integer.parseInt(input.substring(5));

                    if (x > arrayList.size()) {
                        return "☹ OOPS!!! Sorry item no found :-(";
                    } else {

                       String one = "Nice! I've marked this task as done:";
                        one = one + arrayList.get(x - 1).markAsDone().getStatusIcon() + " "
                                + arrayList.get(x - 1).description;
                        arrayList.set(x - 1, arrayList.get(x - 1).markAsDone());
                        return one;

                    }

            } else if (input.contains("todo") && !(input.equals("todo"))) {

                String one = "Got it. I've added this task: "+ "\n";
                Task task = new Todo(input.substring(5));
                new TaskList().addToList(arrayList, task);
                one = one + "Now you have " + arrayList.size() + " task(s) in the list";
                return one;


            } else if (input.contains("deadline") && !(input.equals("deadline"))) {
                String one = "Got it. I've added this task: "+ "\n";
                String[] parts = input.split("/",2);
                String part1 = parts[0];
                String part2 = parts[1];

                //e.g. deadline return book /by 02/12/2019
                if (part2.contains("/")) {
                    String dateString = part2.substring(3);
                    String temp = dateString.substring(6)+"-"+dateString.substring(3,5) + "-" +dateString.substring(0,2);
                    LocalDate xx = LocalDate.parse(temp);
                    String f = xx.format(DateTimeFormatter.ofPattern("MMM d yyyy"));

                    Task task = new Deadline(part1.substring(9), f);
                    new TaskList().addToList(arrayList, task);
                } else {
                    Task task = new Deadline(part1.substring(9), part2.substring(3));
                    new TaskList().addToList(arrayList, task);
                }

                one = one + "Now you have " + arrayList.size() + " task(s) in the list";
                return one;

            } else if (input.contains("event") && !(input.equals("event"))) {
               String one = "Got it. I've added this task: " + "\n";
                String[] parts = input.split("/",2);
                String part1 = parts[0];
                String part2 = parts[1];

                // e.g. event project meeting /at 02/12/2019 2-4pm
                if (part2.contains("/")) {
                    String dateString = part2.substring(3,13);
                    String timeString = part2.substring(13);
                    String temp = dateString.substring(6) + "-" + dateString.substring(3, 5) + "-" + dateString.substring(0, 2);
                    LocalDate xx = LocalDate.parse(temp);
                    String f = xx.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + timeString ;

                    Task task = new Event(part1.substring(6), f);
                    new TaskList().addToList(arrayList, task);
                } else {
                    Task task = new Event(part1.substring(6), part2.substring(3));
                    new TaskList().addToList(arrayList, task);
                }
                one = one + "Now you have " + arrayList.size() + " task(s) in the list";
                return one;

            }  else if (input.contains("delete") && !(input.equals("delete"))) {

                int deletedNumber = Integer.parseInt(input.substring(7));

                    if (deletedNumber > arrayList.size()) {
                        return "☹ OOPS!!! Sorry item no found :-(";
                    } else {
                        String one = "Noted. I've removed this task: " + "\n";
                        new TaskList().deleteFromList(arrayList, deletedNumber);
                        one = one + "Now you have " + arrayList.size() + " task(s) in the list";
                        return one;
                    }

            } else if (input.contains("find") && !(input.equals("find"))) {
                String one = "Here are the matching tasks in your list:";

                ArrayList<Task> tt = new ArrayList<>();

                int counterOne = 1;

                for (Task ttt :arrayList) {
                    if (ttt.description.contains(input.substring(5))) {
                        ttt.index = counterOne;
                        tt.add(ttt);
                        counterOne++;
                    }
                }

                for (Task m : tt) {
                    String two = m.index + ". " + m;
                    two = two +"\n";
                    return two;
                }

            }
            int counterTwo = 1;
            for (Task s : arrayList) {
                s.index = counterTwo;
                counterTwo++;
            }
            new Storage().savingFile(arrayList, path);

     return "\n";
}


}
