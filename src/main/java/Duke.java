import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) throws DukeException{
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        //starts by greeting the user
        System.out.println("Hello! I'm Duke.\nWhat can I do for you?\n");

        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();


        final String path = "src/main/java/data/tasks.txt";
        TaskData taskData = new TaskData(path);
        ArrayList<Task> taskList = taskData.openFile();
        int count;

        if (taskList.isEmpty()) {
            count = 0;
        } else {
            count = taskList.size();
        }

        //commands entered by the user
        while(!str.equals("bye")) {

            //handle exceptions
            try {
                String[] strs = str.split(" ");
                if (!strs[0].equals("list") && !strs[0].equals("done") && !strs[0].equals("deadline")
                        && !strs[0].equals("todo") && !strs[0].equals("event") && !strs[0].equals("delete")) {
                    throw new DukeException("I'm sorry, but I don't know what that means.\n");
                } else if (!strs[0].equals("list")&& !strs[0].equals("done") && !strs[0].equals("delete")
                        && strs.length < 2) {
                    throw new DukeException("The description of "+ strs[0] + " cannot be empty.\n");
                } else if ((strs[0].equals("done") || strs[0].equals("delete")) && strs.length < 2){
                    throw new DukeException("The task index is missing.\n");
                }
            } catch (DukeException ex) {
                System.out.println(ex);
                str = sc.nextLine();
                continue;
            }

            if (str.equals("list")) {
                //display them back to the user when requested
                System.out.println("Here are the tasks in your list:");
                if (count == 0) {
                    System.out.println("There is no task in the list.");
                } else {
                    for (int i = 1; i <= taskList.size(); i++) {
                        System.out.println(i + ". " + taskList.get(i - 1));
                    }
                }

                System.out.println();
                str = sc.nextLine();
            } else {
                String type = str.substring(0, str.indexOf(' '));
                String detail = str.substring(str.indexOf(' ') + 1);
                if (type.equals("done")) {
                    //mark tasks as done
                    int number = toInteger(detail);
                    try {
                        if(taskList.isEmpty() || number < 1 || number > taskList.size()){
                            throw new DukeException("The task index is invalid.\n");
                        }
                    } catch (DukeException ex) {
                        System.out.println(ex);
                        str = sc.nextLine();
                        continue;
                    }

                    taskList.get(number - 1).markAsDone();
                    System.out.println("Nice! I've marked this task as done: \n" + taskList.get(number - 1) + "\n");
                    str = sc.nextLine();
                } else if(type.equals("delete")){

                    int number = toInteger(detail);
                    try {
                        if(number < 1 || number > count){
                            throw new DukeException("The task index is invalid.\n");
                        }
                    } catch (DukeException ex) {
                        System.out.println(ex);
                        str = sc.nextLine();
                        continue;
                    }

                    Task removed = taskList.remove(number-1);
                    count--;
                    if (count == 1 || count == 0) {
                        System.out.println("Noted. I've removed this task:\n  " + removed + "\n"
                                +"Now you have " + count + " task in the list.\n");
                    }else{
                        System.out.println("Noted. I've removed this task:\n  " + removed + "\n"
                                +"Now you have " + count + " tasks in the list.\n");
                    }
                    str = sc.nextLine();
                }else {
                    //store task entered by the user

                    if (type.equals("todo")) {
                        taskList.add(new Todo(detail,0));
                    } else if (type.equals("event")) {
                        try {
                            String name = detail.substring(0, detail.indexOf(" /at "));
                            String time = detail.substring(detail.indexOf(" /at ") + 5);
                            DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                            LocalDateTime at = LocalDateTime.parse(time, dateTimeFormat);
                            taskList.add(new Event(name, 0, at));
                        } catch (DateTimeParseException ex) {
                            System.out.println("The input date time format is invalid. Please use: yyyy-MM-dd HHmm");
                        }
                    } else if (type.equals("deadline")) {
                        try {
                            String name = detail.substring(0, detail.indexOf(" /by "));
                            String time = detail.substring(detail.indexOf(" /by ") + 5);

                            DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                            LocalDateTime by = LocalDateTime.parse(time, dateTimeFormat);
                            taskList.add(new Deadline(name, 0, by));
                        } catch (DateTimeParseException ex) {
                            System.out.println("The input date time format is invalid. Please use: yyyy-MM-dd HHmm");
                        }

                    }
                    count++;
                    if (count == 1) {
                        System.out.println("Got it. I've added this task:\n  " + taskList.get(0) + "\nNow you have " + count + " task in the list.\n");
                    } else {
                        System.out.println("Got it. I've added this task:\n  " + taskList.get(count-1) + "\nNow you have " + count + " tasks in the list.\n");
                    }
                    str = sc.nextLine();
                }
                taskData.updateFile();

            }
        }

        //exits when the user types bye
        System.out.println("Bye. Hope to see you again soon!\n");
    }

    public static int toInteger(String toCheck) {
        try {
            return Integer.parseInt(toCheck);
        }
        catch(Exception e) {
            return 0;
        }
    }

}
