public enum PrintText {
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
            return " --*---*---*---*---*---*---*---*---*---*--";
        }
    },

    WELCOME_MESSAGE {
        @Override
        public String toString() {
            return LOGO + "\n" + BORDER + "\n  Hey there, Olaf here!" +
                    "\n  What will we be doing today?";
        }
    },

    HELP {
        @Override
        public String toString() {
            return "  Please use the following to update me:\n\n" +
                    "    todo <task description>\n      \\_ adds a task to be done\n" +
                    "    deadline <task description> /by <DD-MM-YYYY>\n      \\_ adds a deadline\n" +
                    "    event <task description> /at <DD-MM-YYYY HHMM> to <DD-MM-YYYY HHMM>" +
                    "\n      \\_ adds an event\n" +
                    "    list\n      \\_ lists all TaskList.tasks, deadlines and events\n" +
                    "    done <task number>\n      \\_ marks a task as done\n" +
                    "    delete <task number>\n      \\_ deletes a task from the list\n" +
                    "    bye\n      \\_ exists this application\n";
        }
    }
}
