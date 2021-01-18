public enum DukeCommand {
    DEADLINE {
        @Override
        public void runCommand(String actions) throws DukeException {
            if (actions.isEmpty() || actions.isBlank()) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            if (!actions.contains("/by")) {
                throw new DukeException("☹ OOPS!!! You have entered an invalid format.");
            }

            String[] inputs = actions.split("/by");
            if (inputs.length < 2) {
                throw new DukeException("☹ OOPS!!! You have entered an invalid format.");
            }

            Deadline newDeadline = new Deadline(inputs[0].trim(), inputs[1].trim());
            Duke.addCommand(newDeadline);
        }
    },
    DELETE {
        @Override
        public void runCommand(String actions) throws DukeException {
            int index = Integer.parseInt(actions) - 1;
            int listSize = Duke.taskList.size();

            if (index < 0 || index >= listSize) {
                throw new DukeException("☹ OOPS!!! The number you entered is invalid.");
            }

            Duke.deleteCommand(index);
        }
    },
    DONE {
        @Override
        public void runCommand(String actions) throws DukeException {
            int index = Integer.parseInt(actions) - 1;
            int listSize = Duke.taskList.size();

            if (index < 0 || index >= listSize) {
                throw new DukeException("☹ OOPS!!! The number you entered is invalid.");
            }

            Duke.doneCommand(index);
        }
    },
    EVENT {
        @Override
        public void runCommand(String actions) throws DukeException {
            if (actions.isEmpty() || actions.isBlank()) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            if (!actions.contains("/at")) {
                throw new DukeException("☹ OOPS!!! You have entered an invalid format.");
            }

            String[] inputs = actions.split("/at");
            if (inputs.length < 2) {
                throw new DukeException("☹ OOPS!!! You have entered an invalid format.");
            }

            Event newEvent = new Event(inputs[0].trim(), inputs[1].trim());
            Duke.addCommand(newEvent);
        }
    },
    LIST {
        @Override
        public void runCommand(String actions) throws DukeException {
            int listSize = Duke.taskList.size();
            if (listSize <= 0) {
                throw new DukeException("☹ OOPS!!! Your task list is empty.");
            }

            Duke.listCommand();
        }
    },
    TODO {
        @Override
        public void runCommand(String actions) throws DukeException {
            if (actions.isEmpty() || actions.isBlank()) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }

            ToDo newToDo = new ToDo(actions);
            Duke.addCommand(newToDo);
        }
    };

    public abstract void runCommand(String actions) throws DukeException;

    public static boolean isContains(String value) {
        for (DukeCommand cmd : values()) {
            if (cmd.name().equals(value)) {
                return true;
            }
        }
        return false;
    }
}
