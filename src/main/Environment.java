package main;

import java.util.HashMap;

class Environment {
    private HashMap<String, String> Variables = new HashMap<>();

    public String getValue(String key) {
        if (Variables.containsKey(key)) {
            return Variables.get(key);
        } else {
            return "";
        }
    }

    public void setValue(String key, String value) {
        Variables.put(key, value);
    }
}
