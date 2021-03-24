
public class Parser {

    public static ParserOutput parse(String raw_in) throws DescriptionException, TimeException, NotFoundException {
        String reconnected= "";
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


                case "todo":
                    for (String str: input) {
                        reconnected = reconnected + " " + str;
                    }

                    return ParserOutput.addOutput(new Todo(reconnected));

                case "deadline":
                    int seq = 0;
                    while (!input[seq].equals("/by")) {
                        reconnected = reconnected + " " + input[seq];
                        seq++;
                        if (seq == input.length) {
                            throw new DescriptionException(input[0]);
                        }
                    }

                    if (seq + 1 == input.length) {
                        throw new TimeException(input[0]);
                    }

                    for (int i = seq + 1; i < input.length; i++) {
                        timing = timing + " " + input[i];
                    }
                    return ParserOutput.addOutput(new Deadline(reconnected, timing.trim()));

                case "event":
                    int seq2 = 0;
                    while (!input[seq2].equals("/at")) {
                        reconnected = reconnected + " " + input[seq2];
                        seq2++;
                        if (seq2 == input.length) {
                            throw new DescriptionException(input[0]);
                        }
                    }

                    if (seq2 + 1 == input.length) {
                        throw new TimeException(input[0]);
                    }

                    for (int i = seq2 + 1; i < input.length; i++) {
                        timing = timing + " " + input[i];
                    }
                    return ParserOutput.addOutput(new Event(reconnected, timing.trim()));

                case "delete":
                    return ParserOutput.removeOutput(Integer.parseInt(input[1]));

                case "find":
                    return ParserOutput.findOutput(input[1]);
                default:
                    throw new NotFoundException();
            }
        }
    }

}