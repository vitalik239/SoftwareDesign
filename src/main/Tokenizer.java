package main;

import java.util.ArrayList;
import java.util.HashMap;

public class Tokenizer {
    HashMap <String, Command> type = new HashMap<>();

    enum Type {
        PIPELINE, QUOTE, DOUBLE_QUOTE, SUBSTITUTE, ASSIGNMENT, TEXT
    }

    public static class Token {
        Type type;
        String content;

        Token(Type type, String content) {
            this.type = type;
            this.content = content;
        }
    }

    public ArrayList<Token> tokenize(String command) {
        ArrayList<Token> result = new ArrayList<>();
        int pos = 0;
        while (pos < command.length()) {
            while (command.charAt(pos) == ' ') {
                pos++;
            }

            if (command.charAt(pos) == '\'') {
                int end = pos + 1;
                while (command.charAt(end) != '\'') {
                    end++;
                    if (end >= command.length()) {
                        throw new ArrayIndexOutOfBoundsException();
                    }
                }
                result.add(new Token(Type.QUOTE, command.substring(pos + 1, end)));
                pos = end + 1;
            } else if (command.charAt(pos) == '\"') {
                int end = pos + 1;
                while (command.charAt(end) != '\"') {
                    end++;
                    if (end >= command.length()) {
                        throw new ArrayIndexOutOfBoundsException();
                    }
                }
                result.add(new Token(Type.DOUBLE_QUOTE, command.substring(pos + 1, end)));
                pos = end + 1;
            } else if (command.charAt(pos) == '|') {
                result.add(new Token(Type.PIPELINE, "|"));
                pos++;
            } else if (command.charAt(pos) == '$') {
                int end = pos + 1;
                while (end < command.length() && command.charAt(end) != ' ') {
                    end++;
                }
                result.add(new Token(Type.SUBSTITUTE, command.substring(pos + 1, end)));
            } else {
                int end = pos + 1;
                while (end < command.length() && command.charAt(end) != ' ') {
                    end++;
                }
                if (command.substring(pos, end).contains("=")) {
                    result.add(new Token(Type.ASSIGNMENT, command.substring(pos, end)));
                }
                else {
                    result.add(new Token(Type.TEXT, command.substring(pos, end)));
                }
                pos = end + 1;
            }

        }
        return result;
    }

    public static ArrayList<Token> substitute(ArrayList<Token> tokens, Environment env) {
        ArrayList<Token> result = new ArrayList<>();
        for (Token token : tokens) {
            if (token.type == Type.SUBSTITUTE) {
                result.add(new Token(Type.TEXT, env.getValue(token.content)));
            } else {
                result.add(token);
            }
        }
        return result;
    }
}
