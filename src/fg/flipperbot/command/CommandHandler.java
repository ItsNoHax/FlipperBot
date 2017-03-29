package fg.flipperbot.command;

import fg.flipperbot.command.impl.Combat;
import jibble.pircbot.PircBot;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by alberto on 3/29/17.
 */
public class CommandHandler {

    private PircBot pircBot;

    public CommandHandler(PircBot pircBot) {
        this.pircBot = pircBot;
        initCommands();
    }
    private Map<String, Command> commandMap;

    private void initCommands() {
        commandMap = new HashMap<>();
        Combat combat = new Combat();
        commandMap.put(combat.getChatCommand(), combat);
    }

    public void handle(String chatCommand, String channel, String args) {
        Command command = commandMap.get(chatCommand);
        if (command != null) {
            try {
                command.run(args);
                if(command.printsMessage()) {
                    pircBot.sendMessage(channel, command.messageToPrint());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
