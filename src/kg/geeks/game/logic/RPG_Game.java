package kg.geeks.game.logic;

import kg.geeks.game.players.*;

import java.util.Random;

public class RPG_Game {
    public static Random random = new Random();
    private static int roundNumber;

    public static void startGame() {
        Boss boss = new Boss(10000, 50, "Volland de Mort");

        Warrior warrior1 = new Warrior(270, 10, "Ahiles");
        Warrior warrior2 = new Warrior(290, 15, "Heracle");
        Medic doc = new Medic(250, 5, "Gendolf", 15);
        Medic assistant = new Medic(300, 5, "Frodo", 5);
        Magic magic = new Magic(280, 20, "Potter", 5);
        Berserk berserk = new Berserk(260, 15, "Axe");
        Witcher witcher = new Witcher(250, "Zuck", 250);
        Thor thor = new Thor(250, 15, "Thori");
        Golem golem = new Golem(500, 5, "Stone");
        Avrora avrora = new Avrora(200, 15, "Ava");
        Hacker hacker = new Hacker(180, 30, "Neo");

        Hero[] heroes = {warrior1, warrior2, doc, assistant, magic, berserk, witcher, thor, golem, avrora, hacker};

        showStatistics(boss, heroes);

        while (!isGameOver(boss, heroes)) {
            playRound(boss, heroes);
        }
    }

    private static void showStatistics(Boss boss, Hero[] heroes) {
        System.out.println(Main.ANSI_YELLOW + "ROUND " + roundNumber +
                " --------------------------------------------------------------------"
                + Main.ANSI_RESET);
        System.out.println(boss);
        for (int i = 0; i < heroes.length; i++) {
            System.out.println(heroes[i]);
        }
    }

    private static boolean isGameOver(Boss boss, Hero[] heroes) {
        if (boss.getHealth() <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        boolean allHeroesDead = true;
        for (int i = 0; i < heroes.length; i++) {
            if (heroes[i].getHealth() > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
    }

    private static void playRound(Boss boss, Hero[] heroes) {
        roundNumber++;
        boss.chooseDefence();
        showStatistics(boss, heroes);
        boss.attack(heroes);
        for (Hero hero : heroes) {
            if (boss.getHealth() > 0 && hero.getHealth() > 0 && (boss.getDefence() != hero.getAbility()
                    || boss.getDefence() == SuperAbility.INVISIBILITY)) { // игнорируем Аврору чтобы невидимость продолжалась 2 раунда подряд
                hero.attack(boss);
                if (!(hero instanceof Magic)) { // если герой не маг - задействуем суперсилу
                    hero.applySuperPower(boss, heroes);
                }
            }
        }
        for (Hero hero : heroes) { // маги будут задействовать суперсилу после атаки других героев
            if (hero instanceof Magic && hero.getHealth() > 0 && boss.getDefence() != hero.getAbility()) {
                hero.applySuperPower(boss, heroes);
            }
        }
    }
}
