package main;

import java.util.ArrayList;

public class CommandException extends RuntimeException {
    private String command;
    private ArrayList<String> args;
    private String message;

    public CommandException(String command, ArrayList<String> args, String message) {
        this.command = command;
        this.args = args;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return "Command " + command + " with arguments:\n"
                + String.join(" ", args) + "\n caused an error:\n" + message;
    }
}
