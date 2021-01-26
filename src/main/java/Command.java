import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Command {
    List<Task> list;

    public Command(List<Task> list) {
        this.list = list;
    }

    public List<Task> handleCommand(String command) throws DukeException {
        String[] inputs = command.split(" ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");
        if (inputs.length == 0) {
            throw new DukeException("OOPS! Please enter a command or say bye so I can go back to sleep!");
        }
        String action = inputs[0];
        if (action.equals("list")) {
            System.out.println(Aligner.align("Here are the tasks in your list:"));
            for (int i = 1; i < list.size() + 1; i++) {
                System.out.println(Aligner.align(i + "." + list.get(i - 1).toString()));
            }
        } else if (action.equals("done")) {
            if (inputs.length == 1) {
                throw new DukeException("OOPS! Please tell me what to mark as done!");
            }
            int taskNo = Integer.parseInt(String.valueOf(command.charAt(5))) - 1;
            list.get(taskNo).markAsDone();
            System.out.println(Aligner.align("Good job! I've marked this task as done:"));
            System.out.println(Aligner.align(list.get(taskNo).toString()));
        } else if (action.equals("todo")) {
            if (inputs.length == 1) {
                throw new DukeException("OOPS! The description of a todo cannot be empty.");
            }
            System.out.println(Aligner.align("Sure! I've added this task:"));
            ToDo newToDo = new ToDo(command.substring(5));
            list.add(newToDo);
            System.out.println(Aligner.align(newToDo.toString()));
            System.out.println(Aligner.align("Now you have a whopping " + list.size() + " task(s) in the list."));
        } else if (action.equals("deadline")) { //input format yyyy-mm-dd tttt
            if (inputs.length == 1) {
                throw new DukeException("OOPS! The description of a deadline cannot be empty.");
            }
            int indexOfDate = command.indexOf("/");
            System.out.println(Aligner.align("Sure! I've added this task:"));
            String dateAndTime = command.substring(indexOfDate + 4);
            String date = dateAndTime.split(" ")[0];
            String time = dateAndTime.split(" ")[1];
            Deadline newDeadline = new Deadline(command.substring(9, indexOfDate), LocalDate.parse(date),
                    LocalTime.parse(time, formatter));
            list.add(newDeadline);
            System.out.println(Aligner.align(newDeadline.toString()));
            System.out.println(Aligner.align("Now you have a whopping " + list.size() + " task(s) in the list."));
        } else if (action.equals("event")) { //input format: yyyy-mm-dd tttt-tttt
            if (inputs.length == 1) {
                throw new DukeException("OOPS! The description of an event cannot be empty.");
            }
            int indexOfDate = command.indexOf("/");
            System.out.println(Aligner.align("Sure! I've added this task:"));

            String dateAndTime = command.substring(indexOfDate + 4);
            String date = dateAndTime.split(" ")[0];
            String startTime = dateAndTime.split(" ")[1].split("-")[0];
            String endTime = dateAndTime.split(" ")[1].split("-")[1];
            Event newEvent = new Event(command.substring(6, indexOfDate), LocalDate.parse(date),
                    LocalTime.parse(startTime, formatter), LocalTime.parse(endTime, formatter));
            list.add(newEvent);
            System.out.println(Aligner.align(newEvent.toString()));
            System.out.println(Aligner.align("Now you have a whopping " + list.size() + " task(s) in the list."));
        } else if (action.equals("delete")) {
            int taskNo = Integer.parseInt(String.valueOf(command.charAt(7))) - 1;
            Task removedTask = list.remove(taskNo);
            System.out.println(Aligner.align("Alright! I've removed this task:"));
            System.out.println(Aligner.align(removedTask.toString()));
            System.out.println(Aligner.align("Now you have a whopping " + list.size() + " task(s) in the list."));
        } else if (action.equals("date")) {
            if (inputs.length == 1) {
                throw new DukeException("OOPS! Please give me the date that you want to check in YYYY-MM-DD format.");
            }
            LocalDate d = LocalDate.parse(inputs[1]);
            List<Task> toPrint = new ArrayList<>();
            for (Task t : list) {
                if (t.getDate() != null && t.getDate().equals(d)) {
                    toPrint.add(t);
                }
            }
            for (int i = 1; i < toPrint.size() + 1; i++) {
                System.out.println(Aligner.align(i + "." + toPrint.get(i - 1).toString()));
            }
        } else {
            throw new DukeException("OOPS! Sorry, I have no idea what that means :(");
        }
        return list;
    }
}
