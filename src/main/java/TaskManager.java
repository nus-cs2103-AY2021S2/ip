public class TaskManager {
    public static int manage(Task[] list, String[] info, int listLength) {
        if (info[0].equals("done")) {
            Task tobeDone = list[Integer.parseInt(info[1]) - 1];
            tobeDone.completed();
            System.out.println("Nice! I've marked this task as done: ");
            System.out.println("    " + tobeDone);
        }  else if (info[0].equals("list")) {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0;  i < listLength; i++) {
                System.out.println( (i+1) + "."  + list[i]);
            }
        } else {
            Task task = null;
            System.out.println("Got it. I've added this task: ");
            if (info[0].equals("todo")) {
                StringBuffer sb = new StringBuffer();
                for (int i = 1; i < info.length; i++) {
                    sb.append(info[i]);
                    if (i != info.length - 1) { sb.append(" "); }
                }
                task = new ToDo(sb.toString());
            } else {
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

            }
            list[listLength++] = task;
            System.out.println("    " + task);
            System.out.println("Now you have " + (listLength) + " tasks in the list.");
        }
        return listLength;
    }
}
