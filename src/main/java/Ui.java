public class Ui {

        public void Greet() {
            String greeting = "____________________________________________________________\n"
                    + "Hello! I'm Duke, 恭喜发财 \u263a.\n"
                    + "What can I do for you?\n"
                    + "____________________________________________________________\n";
            System.out.println(greeting);

        }

    public void Exit() {
        String exit = "____________________________________________________________\n"
                + "Bye. Hope to see you again soon!\n"
                + "Wish you all the best for CS2103T\n"
                + "____________________________________________________________\n";

        Duke.canExit = true;
        System.out.println(exit);

    }


}
