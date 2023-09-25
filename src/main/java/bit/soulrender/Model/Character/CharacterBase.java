package bit.soulrender.Model.Character;

import java.io.Serializable;
import java.util.Random;

public abstract class CharacterBase implements CharactersActions, Serializable {

	protected Random rng = new Random();
	
	@Override
	public abstract int atk();
	
	@Override
	public abstract void defence();

	
	protected int round(double valor) {
		if (valor % 1 >= 0.5) {
			return (int) (valor + 1);
		} else {
			return (int) valor;
		}
	}

}
