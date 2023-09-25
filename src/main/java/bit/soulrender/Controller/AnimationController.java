package bit.soulrender.Controller;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class AnimationController {

	public void monsterIdleAnimation(JLabel label, boolean condition) {
		new Thread() {
			public void run() {
				while (condition) {
					try {
						for (int i = 0; i < 25; i++) {
							label.setLocation(label.getX(), label.getY() - 1);
							Thread.sleep(40);
						}
						for (int i = 0; i < 25; i++) {
							label.setLocation(label.getX(), label.getY() + 1);
							Thread.sleep(40);
						}
						Thread.sleep(100);
					} catch (InterruptedException ex) {
						System.out.println("Error: " + ex.getMessage());
					}
				}
				interrupt();
			}
		}.start();
	}

	public void atkAnimation(JLabel label) {
		ImageIcon img = new ImageIcon("src/main/resources/Images/Effects/hit.gif");
		new Thread() {
			public void run() {
				try {
					label.setIcon(img);
					Thread.sleep(480);
					label.setIcon(null);
				} catch (InterruptedException ex) {
					System.out.println("Error: " + ex.getMessage());
				}
				interrupt();
			}
		}.start();
	}

	public void playerAtkAnimation(JLabel label) {
		new Thread() {
			public void run() {
				try {
					for (int i = 0; i < 15; i++) {
						label.setLocation(label.getX() + 1, label.getY() - 1);
						Thread.sleep(5);
					}
					for (int i = 0; i < 15; i++) {
						label.setLocation(label.getX() + 1, label.getY() + 1);
						Thread.sleep(5);
					}
					for (int i = 0; i < 30; i++) {
						label.setLocation(label.getX() - 1, label.getY());
						Thread.sleep(5);
					}
				} catch (InterruptedException ex) {
					System.out.println("Error: " + ex.getMessage());
				}
				interrupt();
			}
		}.start();
	}

	public void BarUpdate(JLabel label, double damage) {
		double aux = 200 * damage;
		new Thread() {
			public void run() {
				label.setSize((int) aux, label.getHeight());
				interrupt();
			}
		}.start();
	}

	public void damageAnimation(JLabel label, String damage) {
		int x = label.getX();
		int y = label.getY();
		label.setText(damage);
		label.setVisible(true);
		new Thread() {
			public void run() {
				try {
					for (int i = 0; i < 30; i++) {
						label.setLocation(label.getX(), label.getY() - 1);
						Thread.sleep(10);
					}
					label.setVisible(false);
					label.setLocation(x, y);
				} catch (InterruptedException ex) {
					System.out.println("Error: " + ex.getMessage());
				}
				interrupt();
			}
		}.start();
	}

	public void playerHitReceive(JLabel label) {
		new Thread() {
			public void run() {
				try {
					for (int i = 0; i < 15; i++) {
						label.setLocation(label.getX() - 1, label.getY());
						Thread.sleep(5);
					}
					for (int i = 0; i < 15; i++) {
						label.setLocation(label.getX() + 1, label.getY());
						Thread.sleep(5);
					}
				} catch (InterruptedException ex) {
					System.out.println("Error: " + ex.getMessage());
				}
				interrupt();
			}
		}.start();
	}
}
