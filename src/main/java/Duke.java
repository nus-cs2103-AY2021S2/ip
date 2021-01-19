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

        System.out.println("Nyahello! I'm Duwuke, your neighbourhood disgusting weeb bot!\n" +
                "What can I do for you? uwu");

        FastIO fio = new FastIO();

        List<Task> leest = new ArrayList<>();
        //int leestCounter = 0;

        while(true) {

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
                    } catch (Exception e) {
                        System.out.println("☹ OOPS!!! Please define your todo properly uwu.");
                    }
                    break;
                case "deadline":
                    try {
                        String[] splitagain = input.substring(9).split("/by");
                        leest.add(new Deadline(splitagain[0], splitagain[1]));
                        System.out.println("    " + leest.get(leest.size() - 1));
                        System.out.println("Hai. I've added this task:");
                        System.out.println("Now you have " + leest.size() + " task(s) in the list uwu");
                    } catch (Exception e) {
                        System.out.println("☹ OOPS!!! Please define your deadline properly uwu.");
                    }
                    break;
                case "event":
                    try {
                        String[] splitagain2 = input.substring(6).split("/at");
                        leest.add(new Deadline(splitagain2[0], splitagain2[1]));
                        System.out.println("    " + leest.get(leest.size() - 1));
                        System.out.println("Hai. I've added this task:");
                        System.out.println("Now you have " + leest.size() + " task(s) in the list uwu");
                    } catch (Exception e) {
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
