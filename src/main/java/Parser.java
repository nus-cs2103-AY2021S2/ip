public class Parser {

    /**
     * Parses user input strings and decides what action is to be taken and what tasks are to be created.
     * @param rawIn String input from user
     * @return ParserOutput object that contains info on what action to take, and if any tasks are created,
     * the task itself
     * @throws DukeDescriptionException If no task description given
     * @throws DukeTimingException If no timing given, for Deadlines and Events only
     * @throws DukeNotFoundException If first word in user input cannot be understood
     */
    public static ParserOutput parse(String rawIn) throws DukeDescriptionException,
            DukeTimingException, DukeNotFoundException {
        String joined = "";
        String timing = "";
        String[] input = rawIn.split(" ");
        if (rawIn.contains("|")) {
            System.out.println("HIHIHIHI pipe");
            return pipeParse(rawIn);
        }
        if (rawIn.equals("bye")) {
            return ParserOutput.byeOutput();
        } else {
            switch (input[0]) {
            case "all":
                //Fallthrough, alias of list
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
            case "delete":
                return ParserOutput.removeOutput(Integer.parseInt(input[1]));
            case "find":
                return ParserOutput.findOutput(input[1]);
            default:
                throw new DukeNotFoundException();
            }
        }
    }

    private static ParserOutput pipeParse(String rawIn) throws DukeTimingException,
            DukeDescriptionException, DukeNotFoundException {
        String[] inputs = rawIn.split("\\|");
        ParserOutput firstPart = parse(inputs[0].strip());
        if (inputs.length > 2) {
            throw new DukeNotFoundException();
        }
        String nextAction = inputs[1].strip();
        System.out.println(rawIn);
        if (nextAction.equals("done")) {
            return ParserOutput.pipeOutput(firstPart, 2);
        } else if (nextAction.equals("remove")) {
            return ParserOutput.pipeOutput(firstPart, 1);
        } else {
            throw new DukeNotFoundException();
        }
    }

}
