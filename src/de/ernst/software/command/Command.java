package de.ernst.software.command;

/**
 * Created by IntelliJ IDEA.
 * User: cernst
 * Date: 04.03.12
 * Time: 11:11
 */
public interface Command {
    public Command addArgument(final String argument);

    public void execute() throws Exception;
}
