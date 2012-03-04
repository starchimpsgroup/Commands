package de.ernst.software.command.impl;

import de.ernst.software.command.Command;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: cernst
 * Date: 04.03.12
 * Time: 11:23
 */
public class Copy implements Command {
    private final List<String> arguments = new ArrayList<>();

    @Override
    public Command addArgument(final String argument) {
        arguments.add(argument);
        return this;
    }

    @Override
    public void execute() throws Exception {
        if (arguments.size() > 1) {
            final List<String> options = new ArrayList<>();
            final List<String> paths = new ArrayList<>(2);
            final List<String> names = new ArrayList<>(2);
            for (final String argument : arguments) {
                if (argument.charAt(0) == '-') {
                    // TODO option exist?
                    options.add(argument.substring(1));
                } else {
                    final File file = new File(argument);
                    if (file.exists()) {
                        System.out.println("The " + (file.isDirectory() ? "directory" : "file") + " '" + file.getPath() + "' exist!");
                        if (file.isDirectory()) {
                            paths.add(file.getPath());
                            names.add("");
                        } else {
                            paths.add(getPath(file, 1));
                            names.add(getFileName(file));
                        }
                    } else {
                        if (paths.size() == 0) {
                            System.out.println("The path '" + file.getPath() + "' not exist!");
                            break;
                        } else {
                            if(isDirectoryPath(file)) {
                            } else {
                            }
                            // TODO create dir!
                        }
                    }
                }
            }
        } else {
            throw new Exception("Not enough arguments to process!");
        }
    }

    private boolean isDirectoryPath(final File file) {
        return file.getPath().endsWith("\\");
    }

    private String getPath(final File file, final int trim) {
        final String[] split = file.getPath().split("\\\\");
        String path = "";
        for (int i = 0; i < split.length - trim; i++) {
            path += split[i] + "\\";
        }
        System.out.println(path);
        return path;
    }

    private String getFileName(final File file) {
        final String[] split = file.getPath().split("\\\\");
        System.out.println(split[split.length - 1]);
        return split[split.length - 1];
    }
}
