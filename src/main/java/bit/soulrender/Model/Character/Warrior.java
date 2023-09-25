package bit.soulrender.Model.Character;

public class Warrior extends CharacterBase {

	private String name;
	private int hp, mp, armour, strenght, lvl;

	public int getLvl() {
		return lvl;
	}

	public void setLvl(int lvl) {
		this.lvl = lvl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getMp() {
		return mp;
	}

	public void setMp(int mp) {
		this.mp = mp;
	}

	public int getArmour() {
		return armour;
	}

	public void setArmour(int armour) {
		this.armour = armour;
	}

	public int getStrenght() {
		return strenght;
	}

	public void setStrenght(int strenght) {
		this.strenght = strenght;
	}

	public Warrior(String name) {
		this.name = name;
		this.hp = 20;
		this.mp = 0;
		this.armour = 3;
		this.strenght = 5;
		this.lvl = 1;
	}

	public int strongAtk() {
		if (this.lvl < 10) {
			return super.round(strenght * 1.5);
		} else if (lvl < 20) {
			return super.round(strenght * 1.4);
		} else {
			return super.round(strenght * 1.3);
		}
	}

	@Override
	public int atk() {
		double aux;
		if (rng.nextInt(3) % 2 == 0) {
			aux = strenght * (0.8 + ((double) rng.nextInt(4) / 10));
		} else {
			aux = strenght * (0.8 - ((double) rng.nextInt(4) / 10));
		}
		return round(aux);
	}

	@Override
	public void defence() {
		this.armour = round(armour * 1.4);
	}

}
