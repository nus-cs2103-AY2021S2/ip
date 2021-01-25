import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskManager {
    protected ArrayList<Task> list;

    public TaskManager() {
        list = new ArrayList<>();
    }

    public void takeEvent(String input, ArrayList<Task> list) {
        this.list = list;
        ErrorChecker e = new ErrorChecker(input, list);

        if (input.equals("list")) {
            listEvents();
            return;
        } else if (e.check()) {
            if (input.startsWith("done")) {
                markDone(input);
            } else if (input.startsWith("delete")) {
                deleteTask(input);
            } else {
                addNewTask(input);
            }
        }

    }

    public void markDone(String input) {
        Task task = list.get(Integer.parseInt(input.substring(5)) - 1);
        task.markAsDone();
        System.out.println(Duke.line + "\n" + (char) 9 + (char) 9 + "Good job! You got " + task.description
                + " done!\n" + Duke.line);
    }

    public void addNewTask(String input) {
        Task newTask;
        if (input.startsWith("todo")) {
            newTask = new TodoTask(input.substring(5));
        } else if (input.startsWith("deadline")) {
            String[] inputSplit = input.split("/");
//            newTask = new DeadlineTask(inputSplit[0].substring(9, inputSplit[0].length() - 1),
//                    inputSplit[1].substring(3));

//            String date = inputSplit[1].substring(3);
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("DD/MM/YYYY");

//            try {
//                LocalDate.parse(date, formatter);
//            } catch (DateTimeParseException e) {
//                System.out.println(Duke.line + "\n" + (char) 9 + (char) 9
//                        + "Oops, I don't understand that date format!\n"  + (char) 9 + (char) 9
//                        + "Please re-enter in DD/MM/YYYY\n" + Duke.line);
//            }

//            LocalDate formattedDate = LocalDate.parse(date, formatter);
//            newTask = new DeadlineTask(inputSplit[0].substring(9, inputSplit[0].length() - 1),
//                    formattedDate);
            DateTimeConverter dateTimeConverter = new DateTimeConverter(inputSplit);
            newTask = new DeadlineTask(inputSplit[0].substring(9, inputSplit[0].length() - 1),
                    dateTimeConverter.convertDate());
        } else {
            String[] inputSplit = input.split("/");
//            newTask = new EventTask(inputSplit[0].substring(6, inputSplit[0].length() - 1),
//                    inputSplit[1].substring(3));

//            String date = inputSplit[1].substring(3, inputSplit[1].length() - 1);
//            String from = inputSplit[2].substring(5, inputSplit[2].length() - 1).toUpperCase();
//            String to = inputSplit[3].substring(3).toUpperCase();
//            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMMM d yyyy");
//            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h a");
//            LocalDate formattedDate = LocalDate.parse(date, dateFormatter);
//            LocalTime formattedFrom = LocalTime.parse(from, timeFormatter);
//            LocalTime formattedTo = LocalTime.parse(to, timeFormatter);
//            newTask = new EventTask(inputSplit[0].substring(6, inputSplit[0].length() - 1),
//                    formattedDate, formattedFrom, formattedTo);
            DateTimeConverter dateTimeConverter = new DateTimeConverter(inputSplit);
            newTask = new EventTask(inputSplit[0].substring(6, inputSplit[0].length() - 1),
                    dateTimeConverter.convertDate(), dateTimeConverter.convertTime("from"),
                    dateTimeConverter.convertTime("to"));
        }
        list.add(newTask);
        System.out.println(Duke.line + "\n" + (char) 9 + (char) 9 + "Added: " + newTask.toString() + "\n" + Duke.line);
    }

    public void deleteTask(String input) {
//        Task task = list.get(Character.getNumericValue(input.charAt(5)) - 1);
//        task.markAsDone();
//        System.out.println("Good job! You got " + task.description + " done!");
        Task task = list.get(Integer.parseInt(input.substring(7)) - 1);
        list.remove(Integer.parseInt(input.substring(7)) - 1);
        System.out.println(Duke.line + "\n" + (char) 9 + (char) 9 + "Deleted: " + task.toString() + "\n" + Duke.line);
    }

    public void listEvents() {
        System.out.println(Duke.line + "\n" + (char) 9 + (char) 9 + "Here is a list of your tasks:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("" + (char) 9 + (char) 9 + (char) 9 + (i + 1) + ". " + list.get(i).toString());
        }
        System.out.println(Duke.line);
    }
}
