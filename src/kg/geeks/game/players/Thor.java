package kg.geeks.game.players;

import kg.geeks.game.logic.Main;
import kg.geeks.game.logic.RPG_Game;

public class Thor extends Hero {

    public Thor(int health, int damage, String name) {
        super(health, damage, name, SuperAbility.DEAFEN);
    }

    @Override
    public void applySuperPower(Boss boss, Hero[] heroes) {
        boolean stun = RPG_Game.random.nextBoolean(); // получил ли босс оглушительный удар
        boss.setStun(stun);
        if (stun) { // если да то сообщяем об этом
            System.out.println(Main.ANSI_YELLOW + boss.getClass().getSimpleName() + " " + boss.getName() + " was deafened by "
                    + this.getClass().getSimpleName() + " " + this.getName() + "!!!" + Main.ANSI_RESET);
        }
    }
}
