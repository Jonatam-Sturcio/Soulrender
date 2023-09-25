package bit.soulrender.Controller;

import bit.soulrender.Model.Character.Warrior;
import bit.soulrender.Model.Monsters.GabiruModel;
import bit.soulrender.Model.Monsters.MonsterBase;
import bit.soulrender.Model.Monsters.RedKnightModel;
import bit.soulrender.Model.Monsters.SkullModel;
import bit.soulrender.Model.Utils.HistoryModel;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CombatController {

	private final CharacterController cc = new CharacterController();
	private final MenuController mc = new MenuController();
	private final AnimationController ac = new AnimationController();
	private ArrayList<HistoryModel> history = mc.loadHistory();
	private int turn = 1, playerArmour, enemyImg, totalHpEnemy, totalHpPlayer;
	private final SkullModel skull = new SkullModel();
	private final RedKnightModel knight = new RedKnightModel();
	private final GabiruModel gabiru = new GabiruModel();
	private MonsterBase monster;
	private final Warrior war = cc.loadCharacter();
	private Random rand = new Random();

	public CombatController() {
		playerArmour = war.getArmour();
		enemySelection();
		totalHpEnemy = monster.getHp();
		totalHpPlayer = war.getHp();
	}

	//################################### PRIVATE METODS #################################
	private void enemySelection() {
		switch (rand.nextInt(5) + 1) {
			case 1:
			case 2:
				monster = skull;
				enemyImg = 1;
				break;
			case 3:
			case 4:
				monster = gabiru;
				enemyImg = 3;
				break;
			case 5:
				monster = knight;
				enemyImg = 2;
				break;
		}
		if (war.getLvl() > 5) {
			balanceMonster();
		}
	}

	private void balanceMonster() {
		int aux;
		if (rand.nextInt(3) % 2 == 0) {
			aux = rand.nextInt(6);
		} else {
			aux = -rand.nextInt(6);
		}
		if (monster.getClass() == RedKnightModel.class) {
			monster.setLvl(war.getLvl() + aux);
			monster.setHp(monster.getHp() + (war.getHp() / 2));
			monster.setStrenght(monster.getStrenght() + (monster.getLvl() / 4));
			monster.setArmour(monster.getArmour() + (monster.getLvl() / 4));
		} else {
			monster.setLvl(war.getLvl() + aux);
			monster.setHp(monster.getHp() + (int) (monster.getLvl() * 1.3));
			monster.setStrenght(monster.getStrenght() + (monster.getLvl() / 4));
			monster.setArmour(monster.getArmour() + (monster.getLvl() / 5));
		}

	}

	private void setPlayerHpTxt(JLabel player) {
		if (war.getHp() < 0) {
			player.setText(0 + "");
		} else {
			player.setText(war.getHp() + "");
		}

	}

	private void setEnemyHpTxt(JLabel enemy) {
		if (monster.getHp() < 0) {
			enemy.setText(0 + "");
		} else {
			enemy.setText(monster.getHp() + "");
		}
	}

	public void setHpTxts(JLabel playerHp, JLabel enemyHpB) {
		playerHp.setText(war.getHp() + "");
		enemyHpB.setText(monster.getHp() + "");
	}

	private boolean combatLifeCheck() {
		if (war.getHp() > 0 && monster.getHp() > 0) {
			return true;
		} else {
			return false;
		}
	}

	private int hitChance() {
		return rand.nextInt(20);
	}

	private void enemyAction(JLabel hitEffect, JLabel damageOnPlayer) {
		turn = 1;
		ac.atkAnimation(hitEffect);
		if (hitChance() > 2) {
			int aux = monster.Atk() - war.getArmour();
			if (aux >= 0) {
				ac.damageAnimation(damageOnPlayer, aux + "");
				war.setHp(war.getHp() - aux);
			} else {
				ac.damageAnimation(damageOnPlayer, "Errou");
			}

		} else {
			ac.damageAnimation(damageOnPlayer, "Errou");
		}
		war.setArmour(playerArmour);
	}

	private void manaRecharge(int op) {
		// 1 - Recharge ATK
		// 2 - Recharge Defense
		if (op == 1) {
			if (war.getMp() < 9) {
				war.setMp(war.getMp() + 2);
			}
		} else {
			if (war.getMp() < 10) {
				war.setMp(war.getMp() + 1);
			}
		}
	}

	private void setResult(JLabel result, String Txt) {
		result.setText(Txt);
	}

	private void buttonEnable(boolean cond, JLabel Atk, JLabel Def, JLabel Spe) {
		Atk.setEnabled(cond);
		Spe.setEnabled(cond);
		Def.setEnabled(cond);
	}

	//################################### PUBLIC METODS #################################
	public void setEntitysLvl(JLabel playerLvlLabel, JLabel enemyLvlLabel) {
		playerLvlLabel.setText("Lvl: " + war.getLvl());
		enemyLvlLabel.setText("Lvl: " + monster.getLvl());
	}

	public void setEnemyImage(JLabel label) {
		ImageIcon img = new ImageIcon("src/main/resources/Images/Enemys/Enemy_" + enemyImg + ".png");
		label.setIcon(img);
		if (enemyImg == 1) {
			ac.monsterIdleAnimation(label, true);
		}
	}

	public void playerActionAtk(JLabel player, JLabel hitEffect, JLabel damageOnEnemy, JLabel enemyHpTxt, JLabel enemyHpbar,
			  JLabel Atk, JLabel Def, JLabel Spe, JLabel playerMpBar) {
		if (Atk.isEnabled()) {
			buttonEnable(false, Atk, Def, Spe);
			manaRecharge(1);
			ac.BarUpdate(playerMpBar, (double) war.getMp() / 10);
			manaCheck(Spe);
			ac.playerAtkAnimation(player);
			ac.atkAnimation(hitEffect);
			if (hitChance() > 2) {
				int aux = war.atk() - monster.getArmour();
				if (aux >= 0) {
					monster.setHp(monster.getHp() - aux);
					ac.BarUpdate(enemyHpbar, (double) monster.getHp() / totalHpEnemy);
					setEnemyHpTxt(enemyHpTxt);
					ac.damageAnimation(damageOnEnemy, aux + "");
				} else {
					ac.damageAnimation(damageOnEnemy, "Errou");
				}
			} else {
				ac.damageAnimation(damageOnEnemy, "Errou");
			}
			turn = 2;
		}

	}

	public void playerActionStrAtk(JLabel player, JLabel hitEffect, JLabel damageOnEnemy,
			  JLabel enemyHpTxt, JLabel enemyHpbar, JLabel Atk, JLabel Def, JLabel Spe, JLabel playerMpBar) {
		if (Spe.isEnabled()) {
			buttonEnable(false, Atk, Def, Spe);
			war.setMp(war.getMp() - 4);
			ac.BarUpdate(playerMpBar, (double) war.getMp() / 10);
			manaCheck(Spe);
			ac.playerAtkAnimation(player);
			ac.atkAnimation(hitEffect);
			if (hitChance() > 2) {
				int aux = war.strongAtk() - monster.getArmour();
				if (aux >= 0) {
					monster.setHp(monster.getHp() - aux);
					ac.BarUpdate(enemyHpbar, (double) monster.getHp() / totalHpEnemy);
					setEnemyHpTxt(enemyHpTxt);
					ac.damageAnimation(damageOnEnemy, aux + "");
				} else {
					ac.damageAnimation(damageOnEnemy, "Errou");
				}

			} else {
				ac.damageAnimation(damageOnEnemy, "Errou");
			}
			turn = 2;
		}
	}

	public void playerActionDefense(JLabel Atk, JLabel Def, JLabel Spe, JLabel playerMpBar) {
		if (Def.isEnabled()) {
			buttonEnable(false, Atk, Def, Spe);
			turn = 2;
			manaRecharge(2);
			ac.BarUpdate(playerMpBar, (double) war.getMp() / 10);
			war.defence();
		}
	}

	public void manaCheck(JLabel button) {
		if (war.getMp() < 4) {
			button.setEnabled(false);
		} else {
			button.setEnabled(true);
		}
	}

	public void turnManagement(JPanel painelAtual, JLabel turnLabel, JLabel Atk, JLabel Spe, JLabel Def, JLabel hitEffectOnPlayer,
			  JLabel damageOnPlayer, JLabel player, JLabel playerHp, JLabel result, JLabel playerHpBar) {
		new Thread() {
			@Override
			public void run() {
				while (combatLifeCheck()) {
					if (turn == 1) {
						turnLabel.setText("Turno do jogador");
						Atk.setEnabled(true);
						manaCheck(Spe);
						Def.setEnabled(true);
					} else {
						buttonEnable(false, Atk, Def, Spe);
						turnLabel.setText("Turno do inimigo");
						try {
							Thread.sleep(1000);
						} catch (InterruptedException ex) {
							System.out.println("Error:" + ex.getMessage());
						}
						enemyAction(hitEffectOnPlayer, damageOnPlayer);
						setPlayerHpTxt(playerHp);
						ac.BarUpdate(playerHpBar, (double) war.getHp() / totalHpPlayer);
						ac.playerHitReceive(player);

					}
				}
				try {
					HistoryModel hm;
					if (war.getHp() > 0) {
						setResult(result, "Vitória!");
						hm = new HistoryModel(monster.getName(), "Vitória");
						hm.plusWins();
						Thread.sleep(1500);
						mc.toResultScreen(painelAtual, 1);
					} else {
						setResult(result, "Derrota!");
						hm = new HistoryModel(monster.getName(), "Derrota");
						hm.plusLoses();
						Thread.sleep(1500);
						mc.toResultScreen(painelAtual, 0);
					}
					history.add(hm);
					mc.saveHistory(history);
				} catch (InterruptedException ex) {
					System.out.println("Error:" + ex.getMessage());
				}
				interrupt();
			}
		}.start();
	}
}
