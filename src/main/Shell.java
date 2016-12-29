package main;

import java.io.*;
import java.util.ArrayList;

public class Shell {
    private static Environment env = new Environment();
    private static Tokenizer tokenizer = new Tokenizer();
    private static Executor executor = new Executor();

    public static void main(String[] arg) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            while (true) {
                String command = reader.readLine();
                if (command.equals("exit")) {
                    break;
                }
                if (command.isEmpty()) {
                    continue;
                }
                proceed(command);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void proceed(String command) {
        try {
            ArrayList<Tokenizer.Token> tokens = tokenizer.tokenize(command);
            ArrayList<Tokenizer.Token> tokensAfterSubstitute = Tokenizer.substitute(tokens, env);

            InputStream result = executor.execute(tokensAfterSubstitute, env);
            if (result != null) {
                System.out.print(result.toString());
            }
        }
        catch (CommandException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
