package fg.flipperbot.command.impl;

import fg.flipperbot.command.Command;
import fg.flipperbot.model.Skills;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Get the user's combat skills
 * Created by alberto on 3/29/17.
 */
public class Combat implements Command {

    private String messageToPrint;
    private String username;

    @Override
    public String getChatCommand() {
        return "cmb";
    }

    @Override
    public void run(String username) throws IOException {
        this.username = username;
        Skills skills = getStats();
        messageToPrint = formattedMessage(skills);
    }

    @Override
    public boolean printsMessage() {
        return true;
    }

    @Override
    public String messageToPrint() {
        return messageToPrint;
    }


    private String formattedMessage(Skills skills) {
        StringBuilder sb = new StringBuilder();
        sb.append(username).append("["+skills.getCombatLevel()+"] ")
        .append(" | Attack: "+skills.getLevel()[0])
        .append(" | Strength: "+skills.getLevel()[2])
        .append(" | Defence: "+skills.getLevel()[1])
        .append(" | Hitpoints: "+skills.getLevel()[3])
        .append(" | Range: "+skills.getLevel()[4])
        .append(" | Magic: "+skills.getLevel()[6])
        .append(" | Prayer: "+skills.getLevel()[5]);
        return sb.toString();
    }

    public Skills getStats() throws IOException {
        URL obj = new URL("http://services.runescape.com/m=hiscore_oldschool/index_lite.ws?player=" + username.replace(" ", "_"));
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

        int lvl[] = new int[Skills.SKILL_COUNT];
        double xp[] = new double[Skills.SKILL_COUNT];

        int totalLevel = Integer.parseInt(in.readLine().toString().split(",")[1]);

        for(int i = 0; i < Skills.SKILL_COUNT; i++) {
            String[] result = in.readLine().toString().split(",");
            lvl[i] = Integer.parseInt(result[1]);
            xp[i] = Double.parseDouble(result[2]);
        }
        in.close();
        con.disconnect();

        Skills skills = new Skills(totalLevel, lvl, xp);
        return skills;
    }
}
