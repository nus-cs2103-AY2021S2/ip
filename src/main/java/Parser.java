
public class Parser {

    public Parser() {
    }

    // entirely for parsing uer input

    public static ParserOutput parse(String raw_in) throws DescriptionException, TimeException, NotFoundException {
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


                case "todo":
                    for (int i = 1; i < input.length; i++) {
                        joined = joined + " " + input[i];
                    }

                    return ParserOutput.addOutput(new Todo(joined));

                case "deadline":
                    int seq = 0;
                    while (!input[seq].equals("/by")) {
                        joined = joined + " " + input[seq];
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
                    return ParserOutput.addOutput(new Deadline(joined, timing.trim()));

                case "event":
                    int seq2 = 0;
                    while (!input[seq2].equals("/at")) {
                        joined = joined + " " + input[seq2];
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
                    return ParserOutput.addOutput(new Event(joined, timing.trim()));

                case "delete":
                    return ParserOutput.removeOutput(Integer.parseInt(input[1]));

                default:
                    throw new NotFoundException();
            }
        }
    }

}