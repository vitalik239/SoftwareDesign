package main;

import java.io.InputStream;
import java.util.ArrayList;

public interface Command {
    public InputStream execute(ArrayList<String> args, InputStream prev)
            throws CommandException;
}
