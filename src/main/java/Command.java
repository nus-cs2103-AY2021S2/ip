import java.util.*;

public class Command {
    List<Task> list;

    public Command(List<Task> list) {
        this.list = list;
    }

    public List<Task> handleCommand(String command) throws DukeException {
        int currentID = list.size() + 1;
        String[] inputs = command.split(" ");
        String action = inputs[0];
        if (action.equals("list")) {
            System.out.println(Aligner.align("Here are the tasks in your list:"));
            for (Task t : list) {
                System.out.println(Aligner.align(t.getID() + "." + t.toString()));
            }
        } else if (action.equals("done")) {
            int taskNo = Integer.parseInt(String.valueOf(command.charAt(5))) - 1;
            list.get(taskNo).markAsDone();
            System.out.println(Aligner.align("Good job! I've marked this task as done:"));
            System.out.println(Aligner.align(list.get(taskNo).toString()));
        } else if (action.equals("todo") || action.equals("deadline") ||
                    action.equals("event")) {
            if (action.equals("todo")) {
                if (inputs.length == 1) {
                    throw new DukeException("OOPS! The description of a todo cannot be empty.");
                }
                System.out.println(Aligner.align("Sure! I've added this task:"));
                ToDo newToDo = new ToDo(command.substring(5), currentID);
                list.add(newToDo);
                System.out.println(Aligner.align(newToDo.toString()));
            } else {
                int indexOfDate = command.indexOf("/");
                if (action.equals("dead")) {
                    if (inputs.length == 1) {
                        throw new DukeException("OOPS! The description of a deadline cannot be empty.");
                    }
                    System.out.println(Aligner.align("Sure! I've added this task:"));
                    String date = command.substring(indexOfDate + 4);
                    Deadline newDeadline = new Deadline(command.substring(9, indexOfDate), currentID, date);
                    list.add(newDeadline);
                    System.out.println(Aligner.align(newDeadline.toString()));
                } else {
                    if (inputs.length == 1) {
                        throw new DukeException("OOPS! The description of an event cannot be empty.");
                    }
                    System.out.println(Aligner.align("Sure! I've added this task:"));
                    String date = command.substring(indexOfDate + 4);
                    Event newEvent = new Event(command.substring(6, indexOfDate), currentID, date);
                    list.add(newEvent);
                    System.out.println(Aligner.align(newEvent.toString()));
                }
            }
            currentID++;
            System.out.println(Aligner.align("Now you have a whopping " + list.size() + " task(s) in the list."));
        } else {
            throw new DukeException("OOPS! Sorry, I have no idea what that means :(");
        }
    return list;
    }
}
