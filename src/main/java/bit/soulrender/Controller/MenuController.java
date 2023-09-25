package bit.soulrender.Controller;

import bit.soulrender.Model.Character.Warrior;
import bit.soulrender.Model.Utils.HistoryModel;
import bit.soulrender.View.Menu.CharacterCreationScreen;
import bit.soulrender.View.Menu.GameScreen;
import bit.soulrender.View.Menu.HistoryScreen;
import bit.soulrender.View.Menu.IntroScreen;
import bit.soulrender.View.Menu.MainMenu;
import bit.soulrender.View.Menu.PlayerStatusScreen;
import bit.soulrender.View.Menu.ResultScreen;
import bit.soulrender.View.Menu.SingleplayerScreen;
import bit.soulrender.View.Menu.WelcomeBackScreen;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class MenuController {

	private JPanel introScreen, mainMenu, welcomeBackScreen, characterCreation, historyScreen, gameScreen, singleplayerScreen, resultScreen, statusScreen;
	private Warrior war;
	private CharacterController cc;
	private Random rnd = new Random();
	private MusicController music = new MusicController();

	public MenuController() {
		cc = new CharacterController();
		war = cc.loadCharacter();
	}

	//########################### UTILITIES ######################################
	private boolean saveFileCheck() {
		File file = new File("src/main/resources/Save/Character.bit");
		return file.exists() ? true : false;
	}

	private Font returnFont(float size) {
		try {
			Font pressStart = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/Font/PressStart2P-Regular.ttf")).deriveFont(size);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/Font/PressStart2P-Regular.ttf")));
			return pressStart;
		} catch (FontFormatException | IOException ex) {
			System.out.println("Erro: " + ex.getMessage());
		}
		return null;
	}

	public void startGame(JFrame principal) {
		if (saveFileCheck()) {
			welcomeBackScreen = new WelcomeBackScreen();
			principal.add(welcomeBackScreen, BorderLayout.CENTER);
		} else {
			introScreen = new IntroScreen();
			principal.add(introScreen, BorderLayout.CENTER);
		}
		principal.pack();
	}

	public void enterCheck(KeyEvent key, String name, JPanel panel) {
		if (KeyEvent.VK_ENTER == key.getKeyChar()) {
			cc.createCharacter(name);
			toMainMenu(panel);
		}
	}

	public void setRndBackground(JLabel label) {
		int aux = rnd.nextInt(3) + 1;
		ImageIcon img = new ImageIcon("src/main/resources/Images/Background/scenario_" + aux + ".png");
		label.setIcon(img);
	}

	public void musicOption(JLabel button, JSlider slider, JLabel previous, JLabel next) {
		ImageIcon img;
		if (music.isPlaying()) {
			img = new ImageIcon("src/main/resources/Images/Buttons/sound_off.png");
			button.setIcon(img);
			music.stop();
			slider.setEnabled(false);
			previous.setEnabled(false);
			next.setEnabled(false);
		} else {
			img = new ImageIcon("src/main/resources/Images/Buttons/sound_on.png");
			button.setIcon(img);
			music.start();
			slider.setEnabled(true);
			previous.setEnabled(true);
			next.setEnabled(true);
		}
	}

	public void saveHistory(ArrayList<HistoryModel> list) {
		try {
			FileOutputStream file = new FileOutputStream("src/main/resources/Save/History.bit");
			ObjectOutputStream writer = new ObjectOutputStream(file);
			writer.writeObject(list);
			writer.close();
			file.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Error: " + ex.getMessage());
		} catch (IOException ex) {
			System.out.println("Error: " + ex.getMessage());
		}
	}

	public ArrayList<HistoryModel> loadHistory() {
		ArrayList<HistoryModel> list = new ArrayList();
		try {
			FileInputStream file = new FileInputStream("src/main/resources/Save/History.bit");
			ObjectInputStream reader = new ObjectInputStream(file);
			list = (ArrayList<HistoryModel>) reader.readObject();
			reader.close();
			file.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Error: " + ex.getMessage());
		} catch (IOException ex) {
			System.out.println("Error: " + ex.getMessage());
		} catch (ClassNotFoundException ex) {
			System.out.println("Error: " + ex.getMessage());
		}
		return list;
	}

	public void resetHistory() {
		File file = new File("src/main/resources/Save/History.bit");
		file.delete();
	}

	public DefaultListModel buildHistory() {
		DefaultListModel modelo = new DefaultListModel();
		ArrayList<HistoryModel> list = new ArrayList();
		list = loadHistory();
		for (HistoryModel historyModel : list) {
			modelo.addElement(historyModel.toString());
		}
		return modelo;
	}

	public void setWinAndLoses(JLabel wins, JLabel loses) {
		int win = 0, lose = 0;
		wins.setForeground(Color.GREEN);
		loses.setForeground(Color.RED);
		setFont(wins, 15);
		setFont(loses, 15);
		ArrayList<HistoryModel> list = new ArrayList();
		list = loadHistory();
		for (HistoryModel historyModel : list) {
			win += historyModel.getWins();
			lose += historyModel.getLoses();
		}
		wins.setText("Vitórias: " + win);
		loses.setText("Derrotas: " + lose);
	}

	public void setIconFrame(JFrame frame) {
		ImageIcon icon = new ImageIcon("src/main/resources/Images/Menus/SoulRender_icone.png");
		frame.setIconImage(icon.getImage());
	}

	public void setBackgroundMenu(JLabel background) {
		ImageIcon icon = new ImageIcon("src/main/resources/Images/Menus/SoulRender_background.png");
		background.setIcon(icon);
	}

	public void setBackgroundIntro(JLabel background) {
		ImageIcon icon = new ImageIcon("src/main/resources/Images/Menus/Menu_intro.png");
		background.setIcon(icon);
	}

	public void setExitButtonAnimation(JLabel button, boolean cond) {
		ImageIcon icon;
		if (cond) {
			icon = new ImageIcon("src/main/resources/Images/Buttons/Button Exit Active.png");
		} else {
			icon = new ImageIcon("src/main/resources/Images/Buttons/Button Exit Static.png");
		}
		button.setIcon(icon);
	}

	public void setHistoryButtonAnimation(JLabel button, boolean cond) {
		ImageIcon icon;
		if (cond) {
			icon = new ImageIcon("src/main/resources/Images/Buttons/Button History Active.png");
		} else {
			icon = new ImageIcon("src/main/resources/Images/Buttons/Button History Static.png");
		}
		button.setIcon(icon);
	}

	public void setResetButtonAnimation(JLabel button, boolean cond) {
		ImageIcon icon;
		if (cond) {
			icon = new ImageIcon("src/main/resources/Images/Buttons/Button Reset Active.png");
		} else {
			icon = new ImageIcon("src/main/resources/Images/Buttons/Button Reset Static.png");
		}
		button.setIcon(icon);
	}

	public void setBackButtonAnimation(JLabel button, boolean cond) {
		ImageIcon icon;
		if (cond) {
			icon = new ImageIcon("src/main/resources/Images/Buttons/Button Back Active.png");
		} else {
			icon = new ImageIcon("src/main/resources/Images/Buttons/Button Back Static.png");
		}
		button.setIcon(icon);
	}

	public void setStatusButtonAnimation(JLabel button, boolean cond) {
		ImageIcon icon;
		if (cond) {
			icon = new ImageIcon("src/main/resources/Images/Buttons/Button Status Active.png");
		} else {
			icon = new ImageIcon("src/main/resources/Images/Buttons/Button Status Static.png");
		}
		button.setIcon(icon);
	}

	public void setAtkButtonAnimation(JLabel button, boolean cond) {
		ImageIcon icon;
		if (cond) {
			icon = new ImageIcon("src/main/resources/Images/Buttons/Button Atk Active.png");
		} else {
			icon = new ImageIcon("src/main/resources/Images/Buttons/Button Atk Static.png");
		}
		button.setIcon(icon);
	}

	public void setDefButtonAnimation(JLabel button, boolean cond) {
		ImageIcon icon;
		if (cond) {
			icon = new ImageIcon("src/main/resources/Images/Buttons/Button Def Active.png");
		} else {
			icon = new ImageIcon("src/main/resources/Images/Buttons/Button Def Static.png");
		}
		button.setIcon(icon);
	}

	public void setSpeButtonAnimation(JLabel button, boolean cond) {
		ImageIcon icon;
		if (button.isEnabled()) {
			if (cond) {
				icon = new ImageIcon("src/main/resources/Images/Buttons/Button Spe Active.png");
			} else {
				icon = new ImageIcon("src/main/resources/Images/Buttons/Button Spe Static.png");
			}
			button.setIcon(icon);
		}
	}

	public void setPlayButtonAnimation(JLabel button, boolean cond) {
		ImageIcon icon;
		if (cond) {
			icon = new ImageIcon("src/main/resources/Images/Buttons/Button Play Active.png");
		} else {
			icon = new ImageIcon("src/main/resources/Images/Buttons/Button Play Static.png");
		}
		button.setIcon(icon);
	}

	public void setSurrenderButtonAnimation(JLabel button, boolean cond) {
		ImageIcon icon;
		if (cond) {
			icon = new ImageIcon("src/main/resources/Images/Buttons/Button Surrender Active.png");
		} else {
			icon = new ImageIcon("src/main/resources/Images/Buttons/Button Surrender Static.png");
		}
		button.setIcon(icon);
	}

	public void setFont(JLabel label, float size) {
		try {
			Font pressStart = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/Font/PressStart2P-Regular.ttf")).deriveFont(size);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/Font/PressStart2P-Regular.ttf")));
			label.setFont(pressStart);
		} catch (FontFormatException | IOException ex) {
			System.out.println("Erro: " + ex.getMessage());
		}
	}
	public void setFont(JTextField box, float size) {
		try {
			Font pressStart = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/Font/PressStart2P-Regular.ttf")).deriveFont(size);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/Font/PressStart2P-Regular.ttf")));
			box.setFont(pressStart);
		} catch (FontFormatException | IOException ex) {
			System.out.println("Erro: " + ex.getMessage());
		}
	}

	public void setCreateButtonAnimation(JLabel button, boolean cond) {
		ImageIcon icon;
		if (cond) {
			icon = new ImageIcon("src/main/resources/Images/Buttons/Button Create Active.png");
		} else {
			icon = new ImageIcon("src/main/resources/Images/Buttons/Button Create Static.png");
		}
		button.setIcon(icon);
	}
	
	public void getFocus(JTextField box){
		box.requestFocus();
	}

	public void setStatus(JLabel name, JLabel lvl, JLabel hp, JLabel armour, JLabel str) {
		Warrior war = cc.loadCharacter();
		int tamanho = 14;

		name.setText(war.getName());
		name.setFont(returnFont(tamanho));

		lvl.setText(war.getLvl() + "");
		lvl.setFont(returnFont(tamanho));

		hp.setText(war.getHp() + "");
		hp.setFont(returnFont(tamanho));

		armour.setText(war.getArmour() + "");
		armour.setFont(returnFont(tamanho));

		str.setText(war.getStrenght() + "");
		str.setFont(returnFont(tamanho));
	}

	public void setStatusFont(JLabel name, JLabel lvl, JLabel hp, JLabel armour, JLabel str) {
		int tamanho = 14;
		name.setFont(returnFont(tamanho));
		lvl.setFont(returnFont(tamanho));
		hp.setFont(returnFont(tamanho));
		armour.setFont(returnFont(tamanho));
		str.setFont(returnFont(tamanho));
	}

	public void resultChange(JLabel label, int result) {
		if (result == 1) {
			label.setText("<html><center>Vitória!!! </center><br> Você subiu de nível!</html>");
			label.setFont(returnFont(24));
			cc.levelUpCharacter();
		} else {
			label.setText("<html><center>Derrota! </center><br>Tente novamente!</html>");
			label.setFont(returnFont(24));
		}
	}

	public void easterEgg(JLabel label) {
		new Thread() {
			public void run() {
				try {
					if (rnd.nextInt(100) == 0) {
						ImageIcon icon = new ImageIcon("src/main/resources/Images/Menus/ee.bin");
						label.setIcon(icon);
						Thread.sleep(1000);
						label.setIcon(null);
						interrupt();
					}
				} catch (InterruptedException ex) {
					System.out.println("Erro: " + ex.getMessage());
				}
			}
		}.start();
	}

	public void historySetModel(JList list) {
		list.setModel(buildHistory());
		list.setFont(returnFont(14));
	}

	public void cleanProcess(){
		System.gc();
	}
	//########################### NAVIGATION ######################################
	public void toMainMenu(JPanel painelAtual) {
		JFrame principal = (JFrame) SwingUtilities.getWindowAncestor(painelAtual);
		mainMenu = new MainMenu();
		principal.remove(painelAtual);
		principal.add(mainMenu, BorderLayout.CENTER);
		principal.pack();
	}

	public void toCharacterCreation(JPanel painelAtual) {
		JFrame principal = (JFrame) SwingUtilities.getWindowAncestor(painelAtual);
		characterCreation = new CharacterCreationScreen();
		principal.remove(painelAtual);
		principal.add(characterCreation, BorderLayout.CENTER);
		principal.pack();
	}

	public void toGame(JPanel painelAtual) {
		JFrame principal = (JFrame) SwingUtilities.getWindowAncestor(painelAtual);
		gameScreen = new GameScreen();
		principal.remove(painelAtual);
		principal.add(gameScreen, BorderLayout.CENTER);
		principal.pack();
	}

	public void toSingleplayerMenu(JPanel painelAtual) {
		JFrame principal = (JFrame) SwingUtilities.getWindowAncestor(painelAtual);
		singleplayerScreen = new SingleplayerScreen();
		principal.remove(painelAtual);
		principal.add(singleplayerScreen, BorderLayout.CENTER);
		principal.pack();
	}

	public void toResultScreen(JPanel painelAtual, int result) {
		JFrame principal = (JFrame) SwingUtilities.getWindowAncestor(painelAtual);
		resultScreen = new ResultScreen(result);
		principal.remove(painelAtual);
		principal.add(resultScreen, BorderLayout.CENTER);
		principal.pack();
	}

	public void toStatusScreen(JPanel painelAtual) {
		JFrame principal = (JFrame) SwingUtilities.getWindowAncestor(painelAtual);
		statusScreen = new PlayerStatusScreen();
		principal.remove(painelAtual);
		principal.add(statusScreen, BorderLayout.CENTER);
		principal.pack();
	}

	public void toHistoryScreen(JPanel painelAtual) {
		JFrame principal = (JFrame) SwingUtilities.getWindowAncestor(painelAtual);
		historyScreen = new HistoryScreen();
		principal.remove(painelAtual);
		principal.add(historyScreen, BorderLayout.CENTER);
		principal.pack();
	}

}
