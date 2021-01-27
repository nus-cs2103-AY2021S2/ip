import java.util.*;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;


enum Call {
    DELETE, TODO, EVENT, DEADLINE, LIST, DONE
}

public class Duke {
    static String input = " ";
    static File fileTest= new File("PreviousTaskList.txt");
   
    static ArrayList<Task> list = new ArrayList<>();
    
    public static void main(String[] args) throws Exception {
        // System.out.println("file exists?: " + fileTest.exists());
        if(fileTest.exists()) {
            Scanner s = new Scanner(fileTest);
            while (s.hasNext()) {
                String current = s.nextLine().toLowerCase();
                // System.out.println(current);
                if(current.contains("todo")) {
                    Task task = Todo.readTask(current);
                    list.add(task);
                } else if (current.contains("deadline")) {
                    list.add(Deadline.readTask(current));
                } else if (current.contains("event")) {
                    list.add(Event.readTask(current));
                } else {
                    if(s.hasNext()){
                        current = s.nextLine();
                    } else{
                        throw new Exception("History saved corrupted");
                    }
                }
            }
        }

        LocalDate testDate = LocalDate.of(2000, 05, 03);
        System.out.println("testDate: " + testDate.getMonth());


        String greet = "Hello! I'm Duke \n What can I do for you?";
        System.out.println(greet);

        Scanner sc = new Scanner(System.in);
        input = sc.nextLine();
        cleanInput();
        Call call;
        while (!input.contains("bye")) {

            if(input.contains("list")) {
                call = Call.LIST;
            } else if (input.contains("done")){
                call = Call.DONE;
            } else if (input.contains("todo")) {
                call = Call.TODO;
            } else if (input.contains("event")) {
                call = Call.EVENT;
            } else if (input.contains("deadline")) {
                call = Call.DEADLINE;
            } else if (input.contains("delete")) {
                call = Call.DELETE;
            } else {
                if(sc.hasNextLine()) {
                    input = sc.nextLine();
                } else {
                    throw new Exception("Please enter a valid command!");
                }
                continue;
            }
            switch (call) {
                case LIST -> commandList();
                case DONE -> commandDone();
                case TODO -> commandTodo();
                case DEADLINE -> commandDeadline();
                case EVENT -> commandEvent();
                case DELETE -> commandDelete();
            }

            input = sc.nextLine();
            cleanInput();
        }

        // System.out.println("input was: " + input);
        // System.out.println("input is valid: " + validCommand());
        System.out.println("Bye. Hope to see you again soon!");
        sc.close();

        try {
            byeCommand();
        } catch (IOException io) {
            System.out.print("Could not save list!");
        }
    }

    static void commandDone() throws ArrayIndexOutOfBoundsException {
        try {
            String value = input.split(" ")[1];
            System.out.println(value);
            int val = Integer.parseInt(value);
            list.get(val - 1).isCompleted();
            System.out.println("Nice! I've marked this task as done:\n " + list.get(val - 1));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException("Please key in which task number to tick off.");
        }

    }

    static String addString(Task t) {
        return "Got it. I've added this task: \n  " + t.toString() + "\nNow you have " + list.size() + " tasks in the list.";
    }

    static void cleanInput() {
        input = input.replaceAll("\n", "");
        input = input.toLowerCase();
    }

    static void commandTodo() throws Exception{
        String task = input.replaceFirst("todo", "");
        task = task.stripTrailing();
        if (task.isEmpty()) {
            throw new Exception("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        Todo t1 = new Todo(task);
        list.add(t1);
        System.out.println(addString(t1));
    }

    static void commandDeadline() {
        String deadline = input.replaceFirst("deadline", "");
        deadline = deadline.stripLeading();
        String[] deadlineArr = deadline.split("/", 2);
        Deadline current = new Deadline(deadlineArr[0], inputDeadline(deadlineArr[1]));
        list.add(current);
        System.out.println(addString(current));
    }

    static void commandEvent() {
        String eventDetails = input.replaceFirst("event", "");
        eventDetails = eventDetails.stripLeading();
        String[] eventDeats = eventDetails.split("/", 2);
        Event current = new Event(eventDeats[0], inputDeadline(eventDeats[1]));
        list.add(current);
        System.out.println(addString(current));
    }

    static void commandDelete() {
        String value = input.replaceFirst("delete", "");
        value = value.strip();
        int val = Integer.parseInt(value);
        Task delete = list.get(val - 1);
        list.remove(val - 1);
        System.out.println("Noted. I've removed this task: \n " + delete + "\nNow you have " + list.size() + " tasks in the list.");
    }

    static void commandList() {
        if (list.size() == 0) {
            System.out.print("You have 0 tasks in your list. ");
        } else {
            System.out.println("Here are the tasks in your list:");
            for(int i = 0; i < list.size(); i++) {
                Task current = list.get(i);
                System.out.println(i+1 + ". " + current);
            }
        }
    }

    static LocalDateTime inputDeadline(String inputDate) {
        String[] dataArray = inputDate.split(" ");
        LocalDate formatDate = inputDate(dataArray[1]);
        LocalTime formatTime = inputTime(dataArray[2]);
        return LocalDateTime.of(formatDate, formatTime);

    }

    static LocalDate inputDate(String input) {
        String[] dateArray = input.split("/");
        LocalDate date = LocalDate.of(Integer.parseInt(dateArray[2]), 
                                            Integer.parseInt(dateArray[1]), 
                                            Integer.parseInt(dateArray[0]));
        return date;
    }

    static LocalTime inputTime(String input) {
        String hour = input.substring(0, 2);
        String minutes = input.substring(2); 
        LocalTime time = LocalTime.of(Integer.parseInt(hour), Integer.parseInt(minutes));
        return time;
    }

    static boolean validCommand() {
        return input.contains("delete") || input.contains("event") || input.contains("done") ||
                input.contains("todo") || input.contains("deadline") || input.contains("list");
    }

    static void byeCommand() throws IOException {
        FileWriter fw = new FileWriter("PreviousTaskList.txt");
        try {
            for (int i = 0; i < list.size(); i++) {
                if(i == 0) {
                    fw.write(list.get(i).toCommand());
                } else {
                    fw.write(System.lineSeparator() + list.get(i).toCommand());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        fw.close();
    }

}



