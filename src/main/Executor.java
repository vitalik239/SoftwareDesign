package main;

import java.io.InputStream;
import java.util.ArrayList;

public class Executor {
    InputStream execute(ArrayList<Tokenizer.Token> tokens, Environment environment) {
        for (Tokenizer.Token token : tokens) {
            System.out.println(token.type + " " + token.content);
        }
        return null;
    }
}
