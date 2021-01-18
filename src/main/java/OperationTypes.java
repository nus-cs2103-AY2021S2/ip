public enum OperationTypes {
    LIST {
        @Override
        public String toString() {
            return "list----get the current task list";
        }
    },
    TODO{
        @Override
        public String toString() {
            return "todo [task description]----add a todo to list";
        }
    },
    EVENT{
        @Override
        public String toString() {
            return "event [task description] /at [task time]----add an event to list";
        }
    },
    DEADLINE{
        @Override
        public String toString() {
            return "deadline [task description] /by [task time]----add a deadline to list";
        }
    },
    DONE{
        @Override
        public String toString() {
            return "done [task number]----get a task done, specified by the task number";
        }
    },
    DELETE{
        @Override
        public String toString() {
            return "delete [task number]----delete a task, specified by the task number";
        }
    },
    BYE{
        @Override
        public String toString() {
            return "bye----quit the application";
        }
    },
    HELP{
        @Override
        public String toString() {
            return "help----get help with the commands";
        }
    };

    public static void printInstructions() {
        System.out.println("Here are a list of commands available :)");
        for (OperationTypes t : OperationTypes.values()) {
            System.out.println(t.toString());
        }
    }
}
