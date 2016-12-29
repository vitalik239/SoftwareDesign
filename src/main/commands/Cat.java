package main.commands;

import main.Command;
import main.CommandException;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Cat implements Command {
    @Override
    public InputStream execute(ArrayList<String> args, InputStream prev) throws CommandException {
        ArrayList<InputStream> iss = new ArrayList<>();
        for (String arg : args) {
            try {
                iss.add(new FileInputStream(arg));
            } catch (FileNotFoundException ex) {
                throw new CommandException("cat", args, "file " + arg + " not found");
            }
        }
        return new SequenceInputStream(Collections.enumeration(iss));
    }
}
