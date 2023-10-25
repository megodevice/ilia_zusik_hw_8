package kg.geeks.game.players;

import kg.geeks.game.logic.Main;
import kg.geeks.game.logic.RPG_Game;


public class Boss extends GameEntity {
    private SuperAbility defence;

    public boolean isStun() {
        return stun;
    }

    public void setStun(boolean stun) {
        this.stun = stun;
    }

    private boolean stun = false; // оглушен ли босс

    public Boss(int health, int damage, String name) {
        super(health, damage, name);
    }

    public SuperAbility getDefence() {
        return defence;
    }

    public void chooseDefence() {
        SuperAbility[] values = SuperAbility.values();
        int randomIndex = RPG_Game.random.nextInt(values.length);
        this.defence = values[randomIndex];
    }

    public void attack(Hero[] heroes) {
        if (!this.isStun()) { // если босс не оглушен то проводим атаку
            Golem golem = null; // наш голем на атаку
            int damageForRound = this.getDamage(); // атака на раунд полная по умолчанию
            for (Hero hero : heroes) {
                if (hero instanceof Golem && hero.getHealth() > 0 && this.defence != SuperAbility.STONE_PROTECTOR) {
                    golem = (Golem) hero; // ищем живого голема при условии что босс не защищен от его силы
                    damageForRound -= this.getDamage() / 5; // атака на героев снижается на 1/5
                    break;
                }
            }
            for (int i = 0; i < heroes.length; i++) {
                if (heroes[i].getHealth() > 0) {
                    if (heroes[i] instanceof Berserk && this.defence != SuperAbility.BLOCK_DAMAGE_AND_REVERT) { // если атака идет на берсерка и босс не  защищен от него
                        if (golem != null) { // если голем найден живым то он примет на себя часть удара
                            golem.setHealth(golem.getHealth() - this.getDamage() / 5);
                            if (golem.getHealth() == 0) {
                                damageForRound = this.getDamage(); // если голем не перенес урон то дальше герои получат полный урон
                            }
                            else {
                                golem.setCollectionDamage(golem.getCollectionDamage() + this.getDamage() / 5);
                            }
                        }
                        ((Berserk) heroes[i]).setBlockedDamage(damageForRound / 5);
                        heroes[i].setHealth(heroes[i].getHealth() -
                                (damageForRound - ((Berserk) heroes[i]).getBlockedDamage()));

                    } else {
                        if (golem != null) { // если голем найден живым то он примет на себя часть удара
                            golem.setHealth(golem.getHealth() - this.getDamage() / 5);
                            if (golem.getHealth() == 0) {
                                damageForRound = this.getDamage(); // если голем не перенес урон то дальше герои получат полный урон
                            }
                            else {
                                golem.setCollectionDamage(golem.getCollectionDamage() + this.getDamage() / 5);
                            }
                        }
                        heroes[i].setHealth(heroes[i].getHealth() - damageForRound);
                    }
                }
            }
        } else { // если босс был оглушен то он встает
            this.setStun(false);
        }
    }

    @Override
    public String toString() {
        return super.toString() + " defence: " + Main.ANSI_RED
                + ((this.getDefence() == null) ? "NO DEFENCE" : this.getDefence()) + Main.ANSI_RESET;
    }
}
