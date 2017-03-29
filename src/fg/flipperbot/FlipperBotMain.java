package fg.flipperbot;

import jibble.pircbot.IrcException;
import java.io.IOException;
import java.util.Properties;

/**
 * FlipperBot is #Flippergang's official twitch bot.
 * @version 0.1
 * @author Alberto Blanco <itsnohax@gmail.com> on 11/20/2015.
 */
public class FlipperBotMain {

    public static void main (String args []) throws IrcException, IOException {
        PropertiesLoader propertiesLoader = new PropertiesLoader();
        Properties properties = propertiesLoader.getPropValues();

        FlipperBot flipperBot = new FlipperBot(properties.getProperty("username"));

        //Enable debugging output
        flipperBot.setVerbose(true);

        // Connect to the twitch IRC server.
        flipperBot.connect("irc.chat.twitch.tv", 6667, properties.getProperty("password"));
    }
}
