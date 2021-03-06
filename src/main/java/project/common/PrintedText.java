package project.common;

/**
 * All the {@code String} constants commonly used in the application.
 */
public enum PrintedText {
    LOGO {
        @Override
        public String toString() {
            return "           ___  _           ,_,\n"
                    + "         |  _  | |   ___   / /\n"
                    + "         | | | | |  / _ \\ =; ;=,\n"
                    + "         | |_| | |_| |_| \\ | |\n"
                    + "          \\___/\\___/---;_| |_|\n";
        }
    },

    BORDER {
        @Override
        public String toString() {
            return " --*---*---*---*---*---*---*---*---*---*---*---*---*---*---*-- \n";
        }
    },

    WELCOME_MESSAGE {
        @Override
        public String toString() {
            return BORDER + "  Hey there, Olaf here!\n"
                    + "  What will we be doing today?\n" + BORDER;
        }
    },

    TODO_FORMAT {
        @Override
        public String toString() {
            return "  todo <TASK DESCRIPTION>\n";
        }
    },

    DEADLINE_FORMAT {
        @Override
        public String toString() {
            return "  deadline <TASK DESCRIPTION> /by <YYYY-MM-DD HH:mm>\n";
        }
    },

    EVENT_FORMAT {
        @Override
        public String toString() {
            return "  event <TASK DESCRIPTION> /at <YYYY-MM-DD HH:mm> to <YYYY-MM-DD HH:mm>\n";
        }
    },

    DONE_FORMAT {
        @Override
        public String toString() {
            return "  done <TASK NUMBER>\n";
        }
    },

    DELETE_FORMAT {
        @Override
        public String toString() {
            return "  delete <TASK NUMBER>\n";
        }
    },

    FIND_FORMAT {
        @Override
        public String toString() {
            return "  find <SEARCH TERM> [,<SEARCH TERM>]\n";
        }
    },

    HELP {
        @Override
        public String toString() {
            return "  Please use the following to update me:\n\n"
                    + "  " + TODO_FORMAT + "      \\_ adds a task to be done\n"
                    + "  " + DEADLINE_FORMAT + "      \\_ adds a deadline\n"
                    + "  " + EVENT_FORMAT + "      \\_ adds an event\n"
                    + "  " + DONE_FORMAT + "      \\_ marks a task as done\n"
                    + "  " + DELETE_FORMAT + "      \\_ deletes a task from the list\n"
                    + "  " + FIND_FORMAT + "      \\_ finds tasks that match the search terms\n"
                    + "        *! separate the search terms with a ','\n"
                    + "    list\n      \\_ lists all tasks, deadlines & events\n"
                    + "      with their respective task number\n"
                    + "    remind\n      \\_ lists deadlines & events for the next week\n"
                    + "    bye\n      \\_ exists this application\n";
        }
    },

    EMPTY_TASKLIST_ERROR {
        @Override
        public String toString() {
            return BORDER + "  You don't have any tasks yet :)\n"
                    + "  Type 'help' to see how you can add a task\n" + BORDER;
        }
    },
}
