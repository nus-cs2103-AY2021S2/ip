package controllers;

import java.util.Arrays;
import java.util.Scanner;

import models.Command;
import views.Greeting;

public class AppController {
  /**
   * AppController starts to listen to commands from the user and performs actions
   * as required by user's commands
   */
  public void start() {
    // initialise greeting view
    Greeting greeting = new Greeting();

    // greet user
    greeting.greet();

    // initialise scanner
    Scanner sc = new Scanner(System.in);

    // loop will be broken only by 'bye'
    while (true) {
      // list has structure [command, ... command related args]
      Command command = new Command(Arrays.asList((sc.nextLine().split(" "))));

      // try catch statement wraps around getCommand which may throw an error if
      // command input does not adhere to command format
      try {
        switch (command.getCommand()) {

          case "list":
            // list all the current todos
            System.out.println("list");
            break;

          case "todo":
            // add a new todo to the model
            System.out.println("Add new Todo");
            break;

          case "deadline":
            // amend a current todo's deadline
            System.out.println("Amend Deadline");
            break;

          case "done":
            // mark a todo as done
            System.out.println("Todo mark as done");
            break;

          case "bye":
            // break out of main function
            greeting.bye();

            // close scanner preventing mem leak
            sc.close();

            // return to end function
            return;

          default:
            // print command printed in if not recognised
            System.out.println(command.getCommand());
            break;
        }

      } catch (Exception e) {
        e.printStackTrace();
      }
    }

  }

}
