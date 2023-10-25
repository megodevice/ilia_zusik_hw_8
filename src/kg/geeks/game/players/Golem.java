package kg.geeks.game.players;

import kg.geeks.game.logic.Main;

public class Golem extends Hero {

    public Golem(int health, int damage, String name) {
        super(health, damage, name, SuperAbility.STONE_PROTECTOR);
    }

    private int collectionDamage; // принятый урон для статистики раунда

    public int getCollectionDamage() {
        return collectionDamage;
    }

    public void setCollectionDamage(int collectionDamage) {
        this.collectionDamage = collectionDamage;
    }

    @Override
    public void applySuperPower(Boss boss, Hero[] heroes) {
        if (this.getCollectionDamage() > 0) {
            System.out.println(Main.ANSI_CYAN + "Golem " + this.getName() + " took over "
                    + this.getCollectionDamage() + " damage points." + Main.ANSI_RESET);
            this.setCollectionDamage(0);
        }
    }
}
