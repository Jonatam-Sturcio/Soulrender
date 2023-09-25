package bit.soulrender.Model.Monsters;

import java.util.Random;

public abstract class MonsterBase {

	private String name;
	private int hp, armour, strenght, lvl;
	protected Random rng = new Random();

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

	public int Atk() {
		double aux;
		if (rng.nextInt(3) % 2 == 0) {
			aux = getStrenght() * (0.8 + ((double) rng.nextInt(6) / 10));
		} else {
			aux = getStrenght() * (0.8 - ((double) rng.nextInt(3) / 10));
		}
		return round(aux);
	}

	private int round(double valor) {
		if (valor % 1 >= 0.5) {
			return (int) (valor + 1);
		} else {
			return (int) valor;
		}
	}
}
