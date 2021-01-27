import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class FileHandler {

    public FileHandler() { }

    public TaskList handleFileCommand(String command, TaskList tasks) throws DukeException { //T # 1 # read book # June 6th
        String[] inputs = command.split(" # ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");
        String action = inputs[0];
        if (action.equals("T")) {
            ToDo newTask = new ToDo(inputs[2]);
            if (Integer.parseInt(inputs[1]) == 1) {
                newTask.markAsDone();
            }
            tasks = tasks.add(newTask);
        } else if (action.equals("D")) {
            String dateAndTime = inputs[3];
            String date = dateAndTime.split(" ")[0];
            String time = dateAndTime.split(" ")[1];
            Deadline newDeadline = new Deadline(inputs[2], LocalDate.parse(date),
                    LocalTime.parse(time, formatter));
            if (Integer.parseInt(inputs[1]) == 1) {
                newDeadline.markAsDone();
            }
            tasks = tasks.add(newDeadline);
        } else if (action.equals("E")) {
            String dateAndTime = inputs[3];
            String date = dateAndTime.split(" ")[0];
            String startTime = dateAndTime.split(" ")[1].split("-")[0];
            String endTime = dateAndTime.split(" ")[1].split("-")[1];
            Event newEvent = new Event(inputs[2], LocalDate.parse(date),
                    LocalTime.parse(startTime, formatter), LocalTime.parse(endTime, formatter));

            if (Integer.parseInt(inputs[1]) == 1) {
                newEvent.markAsDone();
            }
            tasks = tasks.add(newEvent);
        } else {
            throw new DukeException("Sorry! No such command is allowed.");
        }
        return tasks;
    }

    public TaskList readFromFile(TaskList tasks) throws IOException, DukeException {
        File directory = new File("data");
        if (!directory.exists()) { //creating directory if it doesn't exist
            directory.mkdir();
        }
        File f = new File("data/duke.txt");
        if (!f.exists()) {
            f.createNewFile();
        }
        Scanner scf = new Scanner(f);
        while (scf.hasNext()) {
            try {
                String nextLine = scf.nextLine();
                tasks = handleFileCommand(nextLine, tasks); //input format: deadline 0 return book /by June 6th
            } catch (DukeException ex) {
                System.out.println(ex);
            }
        }
        return tasks;
    }

    public void writeToFile(TaskList list) throws IOException {
        //before writing to it, clear file to make sure it is empty and no content is leftover from last run
        FileWriter fw = new FileWriter("data/duke.txt");
        PrintWriter pw = new PrintWriter("data/duke.txt");
        pw.print("");
        pw.close();
        String textToAdd = "";
        for (Task t : list.getList()) {
            textToAdd = textToAdd + t.generateText() + "\n";
        }
        fw.write(textToAdd);
        fw.close();
    }


}
