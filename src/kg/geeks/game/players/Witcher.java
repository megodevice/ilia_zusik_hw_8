package kg.geeks.game.players;

import kg.geeks.game.logic.Main;

public class Witcher extends Hero {

    public int getResurrectionPoints() {
        return resurrectionPoints;
    }

    public void setResurrectionPoints(int resurrectionPoints) {
        this.resurrectionPoints = resurrectionPoints;
    }

    public Witcher(int health, String name, int resurrectionPoints) {
        super(health, 0, name, SuperAbility.RESURRECTION);
        this.resurrectionPoints = resurrectionPoints;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " " +
                this.getName() + " health: " + this.getHealth();
    }

    @Override
    public void setDamage(int damage) { // поскольку колдун не может наносить урон
    } // то и изменять его не требуется

    private int resurrectionPoints; // единицы возрождения

    @Override
    public void applySuperPower(Boss boss, Hero[] heroes) {
        for (Hero hero : heroes) { // ищем первого мертвого героя
            if (hero.getHealth() == 0) {
                hero.setHealth(resurrectionPoints); // воскрешаем
                this.setHealth(0);  // сам колдун погибает
                System.out.println(Main.ANSI_RED + hero.getName() + " was resurrected by " + this.getName() + Main.ANSI_RESET);
                break;
            }
        }
    }
}
