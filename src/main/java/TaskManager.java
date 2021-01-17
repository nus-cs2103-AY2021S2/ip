import java.util.ArrayList;

public class TaskManager {
    public static int listLength = 0;

    //checks the type of instruction given based on the first word in the user input
    //throws exception if insufficient/wrong instruction given
    //increments listLength if a valid task and decrements if a delete instruction
    //adds a task to the list if task given and removes if a delete instruction
    //prints all tasks if input is list
    //completes a certain task if input is done
    public static void manage(ArrayList<Task> list, String[] info) throws DukeException {
        int length = info.length;
        if (info[0].equals("done")) {
            if (length == 1) {
                throw new DukeException("OOPS! Task completed is not specified");
            }
            if (Integer.parseInt(info[1]) > listLength || Integer.parseInt(info[1]) <= 0) {
                throw new DukeException("OOPS! There is no such specified task");
            }
            Task tobeDone = list.get(Integer.parseInt(info[1]) - 1);
            tobeDone.completed();
            System.out.println("Nice! I've marked this task as done: ");
            System.out.println("    " + tobeDone);
        }  else if (info[0].equals("list")) {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0;  i < listLength; i++) {
                System.out.println( (i+1) + "."  + list.get(i));
            }
        } else if (info[0].equals("delete")) {
            int taskIndex = Integer.parseInt(info[1]) - 1;
            if (taskIndex > listLength || taskIndex < 0) {
                throw new DukeException("OOPS!!! There is no task in that line to delete");
            }
            System.out.println(" Noted. I've removed this task: ");
            System.out.println(list.remove(taskIndex));
            listLength--;
            System.out.println("Now you have " + listLength + " tasks in the list.");
        }else {
            Task task = null;
            if (info[0].equals("todo")) {
                if (length == 1) {
                    throw new DukeException("OOPS!!! The description of a todo cannot be empty");
                }
                StringBuffer sb = new StringBuffer();
                for (int i = 1; i < info.length; i++) {
                    sb.append(info[i]);
                    if (i != info.length - 1) { sb.append(" "); }
                }
                task = new ToDo(sb.toString());
            } else if (info[0].equals("event") || info[0].equals("deadline")){
                if (length == 1) {
                    if (info[0].equals("event")) {
                        throw new DukeException("OOPS! Specifics are needed for this event");
                    } else {
                        throw new DukeException("OOPS! Specifics are needed for this deadline");
                    }
                }
                StringBuffer description = new StringBuffer();
                StringBuffer dateAndTime = new StringBuffer();
                boolean descriptionDone = false;
                for (int i = 1; i < info.length; i++) {
                    if (info[i].equals("/at") || info[i].equals("/by")) {
                        descriptionDone = true;
                        i++;
                    }
                    if (descriptionDone) {
                        dateAndTime.append(info[i]);
                        if (i != info.length - 1) { dateAndTime.append(" "); }
                    } else {
                        description.append(info[i]);
                        if (i != info.length - 1) { description.append(" "); }
                    }
                }
                if (info[0].equals("event")) {
                    task = new Event(description.toString(), dateAndTime.toString());
                } else if (info[0].equals("deadline")) {
                    task = new Deadline(description.toString(), dateAndTime.toString());
                }

            } else {
                throw new DukeException("Sorry but I don't understand what that means! :-(");
            }
            list.add(task);
            listLength++;
            System.out.println("Got it. I've added this task: ");
            System.out.println("    " + task);
            System.out.println("Now you have " + (listLength) + " tasks in the list.");
        }
    }
}
