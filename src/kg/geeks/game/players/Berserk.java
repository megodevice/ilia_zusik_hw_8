package kg.geeks.game.players;

import kg.geeks.game.logic.Main;

public class Berserk extends Hero {
    private int blockedDamage;

    public Berserk(int health, int damage, String name) {
        super(health, damage, name, SuperAbility.BLOCK_DAMAGE_AND_REVERT);
    }

    public int getBlockedDamage() {
        return blockedDamage;
    }

    public void setBlockedDamage(int blockedDamage) {
        this.blockedDamage = blockedDamage;
    }

    @Override
    public void applySuperPower(Boss boss, Hero[] heroes) {
        boss.setHealth(boss.getHealth() - this.blockedDamage);
        System.out.println(Main.ANSI_GREEN + "Berserk " + this.getName() + " reverted "
                + blockedDamage + Main.ANSI_RESET);
    }

}
