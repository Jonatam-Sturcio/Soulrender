package bit.soulrender.Model.Utils;

import java.io.Serializable;

public class HistoryModel implements Serializable{
	private String enemyName, result;
	private int wins, loses;

	public HistoryModel(String enemyName, String result) {
		this.enemyName = enemyName;
		this.result = result;
	}


	public String getEnemyName() {
		return enemyName;
	}

	public void setEnemyName(String enemyName) {
		this.enemyName = enemyName;
	}

	public int getWins() {
		return wins;
	}

	public void plusWins() {
		this.wins++;
	}

	public int getLoses() {
		return loses;
	}

	public void plusLoses() {
		this.loses++;
	}

	@Override
	public String toString() {
		return enemyName + "............" +result;
	}
	
	
}
