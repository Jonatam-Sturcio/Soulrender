package bit.soulrender.Model.Monsters;

public class SkullModel extends MonsterBase {

	private String name;
	private int hp, armour, strenght, lvl;

	public SkullModel() {
		this.lvl = 1;
		this.name = "Caveira Negra";
		this.hp = 16;
		this.armour = 4;
		this.strenght = 5;
	}

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

}
