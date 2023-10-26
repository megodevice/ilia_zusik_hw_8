package kg.geeks.game.players;

import kg.geeks.game.logic.Main;
import kg.geeks.game.logic.RPG_Game;

public class Hacker extends Hero {
    public Hacker(int health, int damage, String name) {
        super(health, damage, name, SuperAbility.HACK);
    }

    private boolean hack = false; // переменная для чередования использования суперсилы

    @Override
    public void applySuperPower(Boss boss, Hero[] heroes) {
        if (hack) { // если предыдущий раз не использовалась суперсила то используем сейчас
            int randomHealth;
            int randomHero;
            randomHealth = RPG_Game.random.nextInt(100);
            randomHero = RPG_Game.random.nextInt(heroes.length);
            boss.setHealth(boss.getHealth() - randomHealth);
            while (heroes[randomHero].getHealth() == 0) { //рандомный живой герой для передачи здоровья
                randomHero = RPG_Game.random.nextInt(heroes.length);
            }
            heroes[randomHero].setHealth(heroes[randomHero].getHealth() + randomHealth);
            System.out.println(Main.ANSI_RED + this.getClass().getSimpleName() + " " + this.getName() +
                    " transferred " + randomHealth + " points of health from " + boss.getClass().getSimpleName() + " " +
                    boss.getName() + " to " + heroes[randomHero].getClass().getSimpleName() + " " +
                    heroes[randomHero].getName() + Main.ANSI_RESET);
            hack = false;
        } else {
            hack = true; // если предыдущий раз использовалась суперсила то не используем в следующий раз
        }
    }
}
