import java.util.*;
import java.io.*;

public class Duke {

    public static final int TODO = 0;
    public static final int DEADLINE = 1;
    public static final int EVENT = 2;

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
        private String doneBy;

        public Deadline(String s, String doneBy) {
            super(s);
            this.doneBy = doneBy;
        }

        @Override
        public String toString() {
            if (!this.done) {
                return ("[D][ ] " + this.todo + " (by:" + this.doneBy + ")");
            } else {
                return ("[D][X] " + this.todo + " (by:" + this.doneBy + ")");
            }
        }
    }

    public static class Event extends Task {
        private String time;

        public Event(String s, String time) {
            super(s);
            this.time = time;
        }

        @Override
        public String toString() {
            if (!this.done) {
                return ("[E][ ] " + this.todo + " (at:" + this.time + ")");
            } else {
                return ("[E][X] " + this.todo + " (at:" + this.time + ")");
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

        System.out.println("Hello! I'm Duwuke\n What can I do for you? uwu");

        FastIO fio = new FastIO();

        Task[] leest = new Task[100];
        int leestCounter = 0;

        while(true) {

            String input = fio.nextLine();
            String[] split = input.split("\\s+");

            switch (split[0]) {
                case "list":
                    System.out.println("Here are the tasks in your list uwu:");
                    for(int i = 0; i < leestCounter; i++) {
                        System.out.println((i + 1) + ". " + leest[i]);
                    }
                    break;
                case "done":
                    int done = Integer.parseInt(split[1]) - 1;
                    leest[done].setDone();
                    System.out.println("Sugoi! I've marked this task as done uwu: ");
                    System.out.println(leest[done]);
                    break;
                case "bye":
                    System.out.println("Bye, hope to see you again! uwu");
                    return;
                case "todo":
                    System.out.println("Hai. I've added this task uwu:");
                    leest[leestCounter] = new Task(input.substring(5));
                    System.out.println("    " + leest[leestCounter]);
                    leestCounter++;
                    System.out.println("Now you have " + leestCounter + " task(s) in the list uwu");
                    break;
                case "deadline":
                    System.out.println("Hai. I've added this task:");
                    String[] splitagain = input.substring(9).split("/by");
                    leest[leestCounter] = new Deadline(splitagain[0], splitagain[1]);
                    System.out.println("    " + leest[leestCounter]);
                    leestCounter++;
                    System.out.println("Now you have " + leestCounter + " task(s) in the list uwu");
                    break;
                case "event":
                    System.out.println("Hai. I've added this task:");
                    String[] splitagain2 = input.substring(6).split("/at");
                    leest[leestCounter] = new Deadline(splitagain2[0], splitagain2[1]);
                    System.out.println("    " + leest[leestCounter]);
                    leestCounter++;
                    System.out.println("Now you have " + leestCounter + " task(s) in the list uwu");
                    break;
                default:
                    System.out.println("uwu");
            }
        }
    }
}
