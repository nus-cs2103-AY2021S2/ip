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

        private String todo;
        private boolean done;

        public Task(String s) {
            this.todo = s;
            this.done = false;
        }

        public void setDone() {
            this.done = true;
        }

        @Override
        public String toString() {
            if (!done) {
                return ("[ ] " + todo);
            } else {
                return ("[X] " + todo);
            }
        }

    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
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
                    for(int i = 0; i < leestCounter; i++) {
                        System.out.println((i + 1) + ". " + leest[i] + " uwu");
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
                default:
                    leest[leestCounter] = new Task(input);
                    leestCounter++;
                    System.out.println("added: " + input + " uwu");
            }
        }

    }
}
