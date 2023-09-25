package bit.soulrender.Controller;

import bit.soulrender.Model.Character.CharacterBase;
import bit.soulrender.Model.Character.Warrior;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class CharacterController {
	
	private void saveCharacter(CharacterBase chr) {
		try {
			FileOutputStream file = new FileOutputStream("src/main/resources/Save/Character.bit");
			ObjectOutputStream writer = new ObjectOutputStream(file);
			writer.writeObject(chr);
			writer.close();
			file.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Error: " + ex.getMessage());
		} catch (IOException ex) {
			System.out.println("Error: " + ex.getMessage());
		}
	}
	
	public void createCharacter(String name) {
		Warrior war;
		if(name.isEmpty()){
			war = new Warrior("Player");
		}else{
			war = new Warrior(name);
		}
		try {
			FileOutputStream file = new FileOutputStream("src/main/resources/Save/Character.bit");
			ObjectOutputStream writer = new ObjectOutputStream(file);
			writer.writeObject(war);
			writer.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Error: " + ex.getMessage());
		} catch (IOException ex) {
			System.out.println("Error: " + ex.getMessage());
		}
		
	}
	
	public Warrior loadCharacter() {
		try {
			Warrior war;
			FileInputStream file = new FileInputStream("src/main/resources/Save/Character.bit");
			ObjectInputStream reader = new ObjectInputStream(file);
			war = (Warrior) reader.readObject();
			reader.close();
			file.close();
			return war;
		} catch (FileNotFoundException ex) {
			System.out.println("Error: " + ex.getMessage());
			return null;
		} catch (IOException | ClassNotFoundException ex) {
			System.out.println("Error: " + ex.getMessage());
			return null;
		}
	}
	
	public void levelUpCharacter() {
		Warrior war = loadCharacter();
		war.setLvl(war.getLvl() + 1);
		war.setHp(war.getHp() + 2);
		if (war.getLvl() % 5 == 0) {
			war.setArmour(war.getArmour() + 1);
		}
		if ((war.getLvl()% 3 == 0)) {
			war.setStrenght(war.getStrenght() + 1);
		}
		saveCharacter(war);
	}
	
}
