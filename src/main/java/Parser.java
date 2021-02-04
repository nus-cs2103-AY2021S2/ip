import javax.swing.*;
import java.util.Scanner;

public class Parser {

    public Parser() {
    }

    // entirely for parsing uer input

    public static ParserOutput parse(String raw_in) throws DukeDescriptionException, DukeTimingException, DukeNotFoundException {
        String joined = "";
        String timing = "";
        String[] input = raw_in.split(" ");
        if (raw_in.equals("bye")) {
            return ParserOutput.byeOutput();
        } else {
            switch (input[0]) {
                case "list":
                    return ParserOutput.listOutput();
                case "done":
                    int itemToBeUpdatedIndex = Integer.parseInt(input[1]);
                    return ParserOutput.doneOutput(itemToBeUpdatedIndex);

                    /*
                    Task task = itemList.get(itemToBeUpdatedIndex);
                    task.markDone();
                    store.set(itemToBeUpdatedIndex, task);
                    System.out.println("Alright, I will mark this as done.\n" + input[1] + ". " + task.toString());
                    break;*/
                case "todo":
                    for (int i = 1; i < input.length; i++) {
                        joined = joined + " " + input[i];
                    }

                    return ParserOutput.addOutput(new Todo(joined));
//                    store.add(new Todo(joined));
//                    count++;
//                    System.out.println("Added " + raw_in + "\nYou now have " + count + " items in your list.");
//                    break;
                case "deadline":
                    int seq = 0;
                    while (!input[seq].equals("/by")) {
                        joined = joined + " " + input[seq];
                        seq++;
                        if (seq == input.length) {
                            throw new DukeDescriptionException(input[0]);
                        }
                    }

                    if (seq + 1 == input.length) {
                        throw new DukeTimingException(input[0]);
                    }

                    for (int i = seq + 1; i < input.length; i++) {
                        timing = timing + " " + input[i];
                    }
                    return ParserOutput.addOutput(new Deadline(joined, timing.trim()));
//                    store.add(new Deadline(joined, timing.trim()));
//                    count++;
//                    System.out.println("Added " + joined + "\nYou now have " + count + " items in your list.");
//                    break;
                case "event":
                    int seq2 = 0;
                    while (!input[seq2].equals("/at")) {
                        joined = joined + " " + input[seq2];
                        seq2++;
                        if (seq2 == input.length) {
                            throw new DukeDescriptionException(input[0]);
                        }
                    }

                    if (seq2 + 1 == input.length) {
                        throw new DukeTimingException(input[0]);
                    }

                    for (int i = seq2 + 1; i < input.length; i++) {
                        timing = timing + " " + input[i];
                    }
                    return ParserOutput.addOutput(new Event(joined, timing.trim()));
//                    store.add(new Event(joined, timing.trim()));
//                    count++;
//                    System.out.println("Added " + joined + "\nYou now have " + count + " items in your list.");
//                    break;
                case "delete":
                    return ParserOutput.removeOutput(Integer.parseInt(input[1]));
//                    store.remove(Integer.parseInt(input[1]));
//                    System.out.println("I have removed item " + input[1] + ".");
                default:
                    throw new DukeNotFoundException();
            }
        }
    }

}
