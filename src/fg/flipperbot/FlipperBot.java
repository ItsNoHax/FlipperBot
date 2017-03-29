package fg.flipperbot;

import fg.flipperbot.command.CommandHandler;
import jibble.pircbot.PircBot;

/**
 * Created by Alberto on 11/20/2015.
 */
public class FlipperBot extends PircBot{

    //TODO: Temporary while testing
    private static String DEFAULT_CHANNEL = "#flippergangrs";
    private CommandHandler commandHandler;

    public FlipperBot(String username) {
       setName(username);
       commandHandler = new CommandHandler(this);
    }

    @Override
    protected void onConnect() {
        joinChannel(DEFAULT_CHANNEL);
        requestCAP();
        sendMessage(DEFAULT_CHANNEL, "Flippergang's twitch bot has now joined this chat. Still in development.");
    }

    @Override
    protected void onMessage(String channel, String sender, String login, String hostname, String message) {
        if(message.startsWith("!")) {
            //Dealing with commands
            String[] parts = message.split(" ", 2);
            String command = parts[0].substring(1);
            String args = parts[1];
            commandHandler.handle(command, channel, args);
        }
    }
}
