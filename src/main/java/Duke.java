public class Duke {
    public static void main(String[] args) {

        FastIO reader = new FastIO();

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        String input = reader.nextLine();

        while(true) {
            if (input.equals("bye")) {
                reader.println("Bye. Hope to see you again soon!");
                break;
            } else {
                System.out.println(input);
                input = reader.nextLine();
            }
        }

        reader.close();
    }
}

