package main.commands;

import main.Command;
import main.CommandException;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Echo implements Command {
    @Override
    public InputStream execute(ArrayList<String> args, InputStream prev) throws CommandException {
        String result = String.join(" ", args);
        return new ByteArrayInputStream(result.getBytes(StandardCharsets.UTF_8));
    }
}
