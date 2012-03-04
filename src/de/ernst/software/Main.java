package de.ernst.software;

import de.ernst.software.command.Command;
import de.ernst.software.command.impl.Copy;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: cernst
 * Date: 04.03.12
 * Time: 10:50
 */
public class Main {
    private static final Map<String, Class<? extends Command>> commands = new HashMap<>();

    public static void main(String[] args) {
        commands.put("copy", Copy.class);
        Command command = null;

        for (final String argument : args) {
            if (command == null) {
                final String key = argument.toLowerCase();
                if (commands.containsKey(key)) {
                    try {
                        command = commands.get(key).newInstance();
                    } catch (InstantiationException | IllegalAccessException e) {
                        System.out.println("The command '" + key + "' is not accepted!");
                        break;
                    }
                } else {
                    System.out.println("The command '" + key + "' does not exist!");
                    break;
                }
            } else {
                command.addArgument(argument);
            }
        }
        if (command != null)
            try {
                command.execute();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
    }
}
