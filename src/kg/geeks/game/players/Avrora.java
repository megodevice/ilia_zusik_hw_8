package kg.geeks.game.players;

import kg.geeks.game.logic.Main;
import kg.geeks.game.logic.RPG_Game;

public class Avrora extends Hero {
    public Avrora(int health, int damage, String name) {
        super(health, damage, name, SuperAbility.INVISIBILITY);
    }

    private int collectionDamage = 0; // собираем урон от босса в режиме невидимости
    private boolean invisibilityRound1 = true; // невидимость на первый раунд
    private boolean invisibilityRound2 = true; // невидимость на второй раунд
    private boolean invisibilityEnd = true; // невидимость использована

    @Override
    public void setHealth(int health) {
        if ((health > this.getHealth()) || (invisibilityRound1 && invisibilityRound2 && invisibilityEnd)) { // если героя лечат
            super.setHealth(health); //  или если невидимость не используется то присваиваем урон(лечение) как обычно
        } else if ((!invisibilityRound1 || !invisibilityRound2) && invisibilityEnd) { // если невидимость используется в данный момент
            collectionDamage += this.getHealth() - (this.getHealth() - health); // накапливаем урон
        } else {
            super.setHealth(health);
        }
    }

    @Override
    public void applySuperPower(Boss boss, Hero[] heroes) {
        if (invisibilityRound1 && invisibilityRound2 && invisibilityEnd) { // если оба раунда невидимости не задействованы то пробуем задействовать
            if (RPG_Game.random.nextBoolean()) { // если выпадает правда то задействуем невидимость
                invisibilityRound1 = false;
                System.out.println(Main.ANSI_PURPLE + "Avrora " + this.getName()
                        + " apply invisibility for next round!!!" + Main.ANSI_RESET);
            }
        } else if (!invisibilityRound1 && invisibilityRound2 && invisibilityEnd) { // если один раунд был невидимый то следующий тоже бует невидимый
            invisibilityRound2 = false;
            System.out.println(Main.ANSI_PURPLE + "Avrora " + this.getName()
                    + " apply invisibility for next round!!!" + Main.ANSI_RESET);
        } else if (!invisibilityRound1 && !invisibilityRound2 && invisibilityEnd) { // если 2 раунда было проведено в невидимости то заканчиваем ее действие
            invisibilityEnd = false;
            System.out.println(Main.ANSI_PURPLE + "Avrora " + this.getName()
                    + " finished casting invisibility and dealt accumulated " + collectionDamage + " damage!!!" + Main.ANSI_RESET);
            boss.setHealth(boss.getHealth() - this.collectionDamage); // и наносим накопленный урон боссу
        }
    }

}
