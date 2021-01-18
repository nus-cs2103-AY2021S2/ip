import java.util.ArrayList;
import java.util.Scanner;
import exception.*;
public class Duke {

    private static void dukeReply(String reply){
        System.out.println("\t____________________________________________________________");
        System.out.println("\t" + reply);
        System.out.println("\t____________________________________________________________");
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        dukeReply("Hello! I'm Duke\n" + "\tWhat can I do for you?");

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        while(sc.hasNextLine()){
            String line = sc.nextLine();
            String command = line.split(" ")[0];
            try {
                switch (command) {
                    case "bye":
                        dukeReply("Bye. Hope to see you again soon!");
                        break;
                    case "list": {
                        String reply = "Here are the tasks in your list:\n";
                        for (int i = 0; i < list.size(); i++) {
                            reply += "\t";
                            reply += (i + 1) + ". " + list.get(i);
                            if (i != list.size() - 1) reply += "\n";
                        }
                        if (list.size() != 0) dukeReply(reply);
                        else dukeReply("Your list is empty!");
                        break;
                    }
                    case "done": {
                        String[] ar = line.split(" ",2);
                        if(ar.length==1) throw new CommandException("Which task are you done with?");
                        line = line.split(" ",2)[1];
                        int index = Integer.parseInt(line)-1;
                        Task t = list.get(index);
                        t.isDone = true;
                        String reply = "Nice! I've marked this task as done:\n\t  "
                                + t.toString();
                        dukeReply(reply);

                        break;
                    }
                    case "delete": {
                        String[] ar = line.split(" ",2);
                        if(ar.length==1) throw new CommandException("Which task are you deleting?");
                        line = line.split(" ",2)[1];
                        int index = Integer.parseInt(line)-1;
                        Task t=list.get(index);
                        list.remove(index);
                        String reply = "Noted. I've removed this task:\n\t" + t.toString()
                                    + "\n\tNow you have " + list.size() + " task" + (list.size() != 1 ? "s " : " ") + "in the list.";

                        dukeReply(reply);

                        break;
                    }
                    case "todo": {
                        String[] ar = line.split(" ",2);
                        if(ar.length==1) throw new CommandException("I can't add an empty task to the list!");
                        line = line.split(" ",2)[1];
                        Todo t = new Todo(line);
                        list.add(t);
                        dukeReply("Got it. I've added this task:\n\t" + t.toString()
                                + "\n\tNow you have " + list.size() + " task" + (list.size() != 1 ? "s " : " ") + "in the list.");
                        break;
                    }
                    case "deadline": {
                        String[] ar = line.split(" ",2);
                        if(ar.length==1) throw new CommandException("I can't add an empty task to the list!");
                        line = line.split(" ",2)[1];
                        String[] result = line.split("/by ");
                        if(result.length==1) throw new CommandException("Er... when do you need to finish this /by?");
                        Deadline t = new Deadline(result[0], result[1]);
                        list.add(t);
                        dukeReply("Got it. I've added this task:\n\t" + t.toString()
                                + "\n\tNow you have " + list.size() + " task" + (list.size() != 1 ? "s " : " ") + "in the list.");
                        break;
                    }
                    case "event": {
                        String[] ar = line.split(" ",2);
                        if(ar.length==1) throw new CommandException("I can't add an empty task to the list!");
                        line = line.split(" ",2)[1];
                        String[] result = line.split("/at ");
                        if(result.length==1) throw new CommandException("Er... /at what time does this event start?");
                        Event t = new Event(result[0], result[1]);
                        list.add(t);
                        dukeReply("Got it. I've added this task:\n\t" + t.toString()
                                + "\n\tNow you have " + list.size() + " task" + (list.size() != 1 ? "s " : " ") + "in the list.");
                        break;
                    }
                    default: {
                        dukeReply("I don't understand");
                        break;
                    }
                }

            } catch (IndexOutOfBoundsException | NumberFormatException e){
                dukeReply("Please enter a valid value");
            } catch (CommandException e){
                dukeReply(e.getMessage());
            }
        }
        sc.close();
    }
}
