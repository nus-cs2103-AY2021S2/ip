import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.io.*;

public class Duke {

    static class FastIO extends PrintWriter
    {
        BufferedReader br;
        StringTokenizer st;

        public FastIO()
        {
            super(new BufferedOutputStream(System.out));
            br = new BufferedReader(new
                    InputStreamReader(System.in));
        }

        String next()
        {
            while (st == null || !st.hasMoreElements())
            {
                try
                {
                    st = new StringTokenizer(br.readLine());
                }
                catch (IOException  e)
                {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt()
        {
            return Integer.parseInt(next());
        }

        long nextLong()
        {
            return Long.parseLong(next());
        }

        double nextDouble()
        {
            return Double.parseDouble(next());
        }

        String nextLine()
        {
            String str = "";
            try
            {
                str = br.readLine();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            return str;
        }
    }

    public static class Task{
        protected String todo;
        protected boolean done;
        protected int type;

        public Task(String s) {
            this.todo = s;
            this.done = false;
        }

        public void setDone() {
            this.done = true;
        }

        public String saveToData() {
            if (this.done) {
                return ("T | 1 | " + todo);
            } else {
                return ("T | 0 | " + todo);
            }
        }

        public String dateFormat(LocalDateTime date) {
            return(date.format(DateTimeFormatter.ofPattern("MMM d yyyy hhmm a")));
        }

        @Override
        public String toString() {
            if (!this.done) {
                return ("[T][ ] " + todo);
            } else {
                return ("[T][X] " + todo);
            }
        }

    }

    public static class Deadline extends Task {
        private LocalDateTime doneBy;

        public Deadline(String s, String doneBy) {
            super(s);
            String[] split = doneBy.split("\\s+");
            if (split.length == 1) {
                this.doneBy = LocalDate.parse(doneBy).atTime(0,0);
            } else if (split.length == 2) {
                this.doneBy = LocalDate.parse(split[0]).atTime(LocalTime.parse(split[1]));
            } else {
                throw new DateTimeParseException("","",1);
            }

        }

        public String saveToData() {
            if (this.done) {
                return ("D | 1 | " + todo + " | " + doneBy.toString().replace("T", " "));
            } else {
                return ("D | 0 | " + todo + " | " + doneBy.toString().replace("T", " "));
            }
        }

        @Override
        public String toString() {
            if (!this.done) {
                return ("[D][ ] " + this.todo + " (by:" + dateFormat(this.doneBy) + ")");
            } else {
                return ("[D][X] " + this.todo + " (by:" + dateFormat(this.doneBy) + ")");
            }
        }
    }

    public static class Event extends Task {
        private LocalDateTime time;

        public Event(String s, String time) throws DateTimeParseException {
            super(s);
            String[] split = time.split("\\s+");
            if (split.length == 1) {
                this.time = LocalDate.parse(time).atTime(0,0);
            } else if (split.length == 2) {
                this.time = LocalDate.parse(split[0]).atTime(LocalTime.parse(split[1]));
            } else {
                throw new DateTimeParseException("","",1);
            }
        }

        public String saveToData() {
            if (this.done) {
                return ("E | 1 | " + todo + " | " + time.toString().replace("T", " "));
            } else {
                return ("E | 0 | " + todo + " | " + time.toString().replace("T", " "));
            }
        }

        @Override
        public String toString() {
            if (!this.done) {
                return ("[E][ ] " + this.todo + " (at:" + dateFormat(this.time) + ")");
            } else {
                return ("[E][X] " + this.todo + " (at:" + dateFormat(this.time) + ")");
            }
        }
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd + "\n");
        fw.close();
    }

    private static void appendToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath,true);
        fw.write(textToAdd + "\n");
        fw.close();
    }

    private static void saveAsFile(String filePath, List<Task> l) {
        boolean isFirst = true;
        if (l.isEmpty()) {
            try {
                writeToFile(filePath, " ");
            } catch (IOException e) {
                System.out.println("Something went wrong uwu: " + e.getMessage());
            }
        }
        for (Task t : l) {
            try {
                if (isFirst) {
                    writeToFile(filePath, t.saveToData());
                    isFirst = false;
                } else {
                    appendToFile(filePath, t.saveToData());
                }
            } catch (IOException e) {
                System.out.println("Something went wrong uwu: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___| uwu\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Nyahello! I'm Duwuke, your neighbourhood disgusting weeb bot!\n" +
                "What can I do for you? uwu");

        FastIO fio = new FastIO();

        List<Task> leest = new ArrayList<>();
        //int leestCounter = 0;

        String dataFile = "test/duke.txt";
        File taskData = new File(dataFile);

        //opens the duke.txt file to read from, creates file if not found
        try {
            Scanner s = new Scanner(taskData);
            while (s.hasNext()) {
                String[] line = s.nextLine().split(" \\| ");
                switch (line[0]) {
                    case "T" :
                        Task task = new Task(line[2]);
                        if (line[1].equals("1")) {
                            task.setDone();
                        }
                        leest.add(task);
                        break;
                    case "D" :
                        Deadline deadline = new Deadline(line[2], line[3]);
                        if (line[1].equals("1")) {
                            deadline.setDone();
                        }
                        leest.add(deadline);
                        break;
                    case "E" :
                        Event event = new Event(line[2], line[3]);
                        if (line[1].equals("1")) {
                            event.setDone();
                        }
                        leest.add(event);
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            try {
                taskData.createNewFile();
            } catch (IOException f) {
                try {
                    Path path = Paths.get("test/");
                    Files.createDirectories(path);
                    taskData.createNewFile();
                } catch (IOException g) {
                    System.err.println("Failed to create directory uwu");
                }

            }
        }


        while (true) {

            String input = fio.nextLine();
            String[] split = input.split("\\s+");

            switch (split[0]) {
                case "list":
                    if (leest.size() == 0) {
                        System.out.println("☹ OOPS!!! Your list is currently empty uwu.");
                    } else {
                        System.out.println("Here are the tasks in your list uwu:");
                        int counter = 1;
                        for (Task t : leest) {
                            System.out.println(counter + ". " + t);
                            counter++;
                        }
                    }
                    break;
                case "done":
                    try {
                        int done = Integer.parseInt(split[1]) - 1;
                        leest.get(done).setDone();
                        System.out.println("Sugoi! I've marked this task as done uwu:");
                        System.out.println(leest.get(done));
                        saveAsFile(dataFile, leest);
                    } catch (Exception e) {
                        System.out.println("☹ OOPS!!! Please indicate a valid task to complete uwu");
                    }
                    break;
                case "bye":
                    System.out.println("Bye, hope to see you again! uwu");
                    return;
                case "todo":
                    try {
                        leest.add(new Task(input.substring(5)));
                        System.out.println("    " + leest.get(leest.size() - 1));
                        System.out.println("Hai. I've added this task uwu:");
                        System.out.println("Now you have " + leest.size() + " task(s) in the list uwu");
                        saveAsFile(dataFile, leest);
                    } catch (Exception e) {
                        System.out.println("☹ OOPS!!! Please define your todo properly uwu.");
                    }
                    break;
                case "deadline":
                    try {
                        String[] splitagain = input.substring(9).split("/by");
                        leest.add(new Deadline(splitagain[0], splitagain[1].substring(1)));
                        System.out.println("    " + leest.get(leest.size() - 1));
                        System.out.println("Hai. I've added this task:");
                        System.out.println("Now you have " + leest.size() + " task(s) in the list uwu");
                        saveAsFile(dataFile, leest);
                    } catch (DateTimeParseException de) {
                        System.out.println("☹ OOPS!!! Please define your todo date/time in the " +
                                "YYYY-MM-DD HH:MM format uwu.");
                    } catch (Exception e) {
                        System.out.println("☹ OOPS!!! Please define your deadline properly uwu.");
                    }
                    break;
                case "event":
                    try {
                        String[] splitagain2 = input.substring(6).split("/at");
                        leest.add(new Event(splitagain2[0], splitagain2[1].substring(1)));
                        System.out.println("Hai. I've added this task:");
                        System.out.println("    " + leest.get(leest.size() - 1));
                        System.out.println("Now you have " + leest.size() + " task(s) in the list uwu");
                        saveAsFile(dataFile, leest);
                    } catch (DateTimeParseException de) {
                        System.out.println("☹ OOPS!!! Please define your todo date/time in the " +
                                "YYYY-MM-DD HH:MM format uwu.");
                    } catch (Exception e) {
                        System.out.println(e);
                        System.out.println("☹ OOPS!!! Please define your event properly uwu.");
                    }
                    break;
                case "delete":
                    try {
                        Task toDelete = leest.get(Integer.parseInt(split[1]) - 1);
                        System.out.println("Noted. I've removed this task uwu:");
                        System.out.println(toDelete);
                        leest.remove(toDelete);
                        System.out.println("Now you have " + leest.size() + " task(s) in the list uwu");
                        saveAsFile(dataFile, leest);
                    } catch (Exception e) {
                        System.out.println("☹ OOPS!!! Please indicate a valid task to delete uwu");
                    }
                    break;
                default:
                    System.out.println("☹ OOPS!!! Sumimasen, but I don't know what that means T^T");
                    break;
            }
        }
    }
}
