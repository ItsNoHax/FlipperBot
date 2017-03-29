package fg.flipperbot.model;

/**
 *
 * Represents an account's ingame skills.
 * Created by Alberto Blanco<itsnohax@gmail.com> on 12/22/2015.
 */
public class Skills {

    private double combatLevel;
    private int totalLevel;
    public static int SKILL_COUNT = 23;
    private int level[];
    private double xp[];

    public Skills (int totalLevel, int level[], double xp[]) {
        this.totalLevel = totalLevel;
        this.level = level;
        this.xp = xp;

        this.combatLevel = calculateCombatLevel(level);
    }

    private double calculateCombatLevel(int[] level) {
            int attack = level[0];
            int defence = level[1];
            int strength = level[2];
            int hp = level[3];
            int prayer = level[5];
            int ranged = level[4];
            int magic = level[6];
            double combatLevel;
            combatLevel = ((defence + hp + Math.floor(prayer / 2)) * 0.25);
            double melee = (attack + strength) * 0.325;
            double ranger = Math.floor(ranged * 1.5) * 0.325;
            double mage = Math.floor(magic * 1.5) * 0.325;
            if (melee >= ranger && melee >= mage) {
                combatLevel += melee;
            } else if (ranger >= melee && ranger >= mage) {
                combatLevel += ranger;
            } else if (mage >= melee && mage >= ranger) {
                combatLevel += mage;
            }
            return combatLevel;
    }

    public double getCombatLevel() {
        return combatLevel;
    }

    public int getTotalLevel() {
        return totalLevel;
    }

    public int[] getLevel() {
        return level;
    }

    public double[] getXp() {
        return xp;
    }
}
