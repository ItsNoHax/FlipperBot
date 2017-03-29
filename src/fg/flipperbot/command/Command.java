package fg.flipperbot.command;

import java.io.IOException;

/**
 * A basic interface for Chat commands
 * Created by alberto on 3/29/17.
 */
public interface Command {

    /*
     * The text that triggers the command.
     */
    String getChatCommand();

    /*
     * What to do when the command is triggered.
     */
    void run(String args) throws IOException;

    /*
     * Does the command print a message
     */
    boolean printsMessage();

    /*
     * Message to print
     */
    String messageToPrint();
}
