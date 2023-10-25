package kg.geeks.game.players;

public class Magic extends Hero {
    public Magic(int health, int damage, String name, int boostPoints) {
        super(health, damage, name, SuperAbility.BOOST);
        this.boostPoints = boostPoints;
    }

    private int boostPoints; // единицы буста

    public int getBoostPoints() {
        return boostPoints;
    }

    public void setBoostPoints(int boostPoints) {
        this.boostPoints = boostPoints;
    }

    @Override
    public void applySuperPower(Boss boss, Hero[] heroes) {
        for (Hero hero : heroes) {
            hero.setDamage(hero.getDamage() + boostPoints); // добавить всем героям буст
        }
    }
}
