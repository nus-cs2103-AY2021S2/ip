import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    public Storage() {
    }

    public void loadingFile(ArrayList<Task> arrayList, Scanner s) {
        while (s.hasNextLine()) {
            String line = s.nextLine();
            if (line.contains("[D]")) {
                String[] parts = line.split("by");
                String part1 = parts[0];
                String part2 = parts[1];
                Task task = new Deadline(part1.substring(7,part1.length()-1), part2.substring(2,part2.length()-1));
                if (part1.charAt(4) == 'X') {
                    task.isDone = true;
                }
                task.index = arrayList.size() + 1;
                arrayList.add(task);
            } else if (line.contains("[E]")){
                String[] parts = line.split("at");
                String part1 = parts[0];
                String part2 = parts[1];
                Task task = new Event(part1.substring(7,part1.length()-1), part2.substring(2,part2.length()-1));
                if (part1.charAt(4) == 'X') {
                    task.isDone = true;
                }
                task.index = arrayList.size() + 1;
                arrayList.add(task);
            } else if (line.contains("[T]")){
                Task task = new Todo(line.substring(7));
                if (line.charAt(4) == 'X') {
                    task.isDone = true;
                }
                task.index = arrayList.size() + 1;
                arrayList.add(task);
            }

        }
    }

    public void savingFile(ArrayList<Task> arrayList, String path) {
        try {
            int i = 0 ;
            if (arrayList.size() == 0) {

                System.out.print("");
            } else if (arrayList.size() == 1) {
                writeToFile(path, arrayList.get(i) + System.lineSeparator());
            } else {
                writeToFile(path, arrayList.get(i) + System.lineSeparator());
                i++;
                while (i <= arrayList.size()-1) {
                    appendToFile(path, arrayList.get(i) + System.lineSeparator());
                    i++;  // append to file with in the writetofile.
                }}

        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    private static void writeToFile(String path, String s) throws IOException{
        FileWriter fw = new FileWriter(path);
        fw.write(s);
        fw.close();
    }

    private static void appendToFile(String path, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(path, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }
}
