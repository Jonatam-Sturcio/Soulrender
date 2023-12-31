package bit.soulrender.View.Menu;

import bit.soulrender.Controller.MenuController;
import bit.soulrender.Controller.MusicController;

public class MainMenu extends javax.swing.JPanel {

	/**
	 * Creates new form MainMenu
	 */
	private MenuController mc;
	private MusicController music = new MusicController();

	public MainMenu() {
		initComponents();
		mc = new MenuController();
		mc.cleanProcess();
		mc.setBackgroundMenu(Background);
		mc.setExitButtonAnimation(ExitButton, false);
		mc.setPlayButtonAnimation(PlayButton, false);
		mc.setHistoryButtonAnimation(HistoryButton, false);
		mc.setFont(AlphaInfo, 8);
		mc.easterEgg(EasterEgg);
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
   // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
   private void initComponents() {

      EasterEgg = new javax.swing.JLabel();
      MusicButton = new javax.swing.JLabel();
      NextButton = new javax.swing.JLabel();
      PreviousButton = new javax.swing.JLabel();
      jlVolume = new javax.swing.JSlider();
      AlphaInfo = new javax.swing.JLabel();
      HistoryButton = new javax.swing.JLabel();
      PlayButton = new javax.swing.JLabel();
      ExitButton = new javax.swing.JLabel();
      Background = new javax.swing.JLabel();

      setMaximumSize(new java.awt.Dimension(1280, 720));
      setMinimumSize(new java.awt.Dimension(1280, 720));
      setPreferredSize(new java.awt.Dimension(1280, 720));
      setLayout(null);
      add(EasterEgg);
      EasterEgg.setBounds(10, 670, 760, 40);

      MusicButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Buttons/sound_on.png"))); // NOI18N
      MusicButton.addMouseListener(new java.awt.event.MouseAdapter() {
         public void mouseClicked(java.awt.event.MouseEvent evt) {
            MusicButtonMouseClicked(evt);
         }
      });
      add(MusicButton);
      MusicButton.setBounds(1060, 10, 100, 40);

      NextButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Buttons/Button Next Music.png"))); // NOI18N
      NextButton.addMouseListener(new java.awt.event.MouseAdapter() {
         public void mouseClicked(java.awt.event.MouseEvent evt) {
            NextButtonMouseClicked(evt);
         }
      });
      add(NextButton);
      NextButton.setBounds(1180, 10, 40, 40);

      PreviousButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Buttons/Button Previous Music.png"))); // NOI18N
      PreviousButton.addMouseListener(new java.awt.event.MouseAdapter() {
         public void mouseClicked(java.awt.event.MouseEvent evt) {
            PreviousButtonMouseClicked(evt);
         }
      });
      add(PreviousButton);
      PreviousButton.setBounds(1000, 10, 40, 40);

      jlVolume.setMaximum(20);
      jlVolume.setValue(10);
      jlVolume.addChangeListener(new javax.swing.event.ChangeListener() {
         public void stateChanged(javax.swing.event.ChangeEvent evt) {
            jlVolumeStateChanged(evt);
         }
      });
      add(jlVolume);
      jlVolume.setBounds(990, 50, 240, 20);

      AlphaInfo.setFont(new java.awt.Font("Courier New", 1, 12)); // NOI18N
      AlphaInfo.setForeground(new java.awt.Color(255, 255, 255));
      AlphaInfo.setText("Alfa 1.0");
      AlphaInfo.setVerticalAlignment(javax.swing.SwingConstants.TOP);
      add(AlphaInfo);
      AlphaInfo.setBounds(10, 10, 170, 40);

      HistoryButton.addMouseListener(new java.awt.event.MouseAdapter() {
         public void mouseClicked(java.awt.event.MouseEvent evt) {
            HistoryButtonMouseClicked(evt);
         }
         public void mouseEntered(java.awt.event.MouseEvent evt) {
            HistoryButtonMouseEntered(evt);
         }
         public void mouseExited(java.awt.event.MouseEvent evt) {
            HistoryButtonMouseExited(evt);
         }
      });
      add(HistoryButton);
      HistoryButton.setBounds(440, 380, 310, 100);

      PlayButton.addMouseListener(new java.awt.event.MouseAdapter() {
         public void mouseClicked(java.awt.event.MouseEvent evt) {
            PlayButtonMouseClicked(evt);
         }
         public void mouseEntered(java.awt.event.MouseEvent evt) {
            PlayButtonMouseEntered(evt);
         }
         public void mouseExited(java.awt.event.MouseEvent evt) {
            PlayButtonMouseExited(evt);
         }
      });
      add(PlayButton);
      PlayButton.setBounds(440, 240, 310, 100);

      ExitButton.addMouseListener(new java.awt.event.MouseAdapter() {
         public void mouseClicked(java.awt.event.MouseEvent evt) {
            ExitButtonMouseClicked(evt);
         }
         public void mouseEntered(java.awt.event.MouseEvent evt) {
            ExitButtonMouseEntered(evt);
         }
         public void mouseExited(java.awt.event.MouseEvent evt) {
            ExitButtonMouseExited(evt);
         }
      });
      add(ExitButton);
      ExitButton.setBounds(440, 520, 310, 100);

      Background.setMaximumSize(new java.awt.Dimension(1280, 720));
      Background.setMinimumSize(new java.awt.Dimension(1280, 720));
      Background.setPreferredSize(new java.awt.Dimension(1280, 720));
      Background.setRequestFocusEnabled(false);
      add(Background);
      Background.setBounds(0, 0, 1280, 720);
   }// </editor-fold>//GEN-END:initComponents

   private void jlVolumeStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jlVolumeStateChanged
		music.setVolume((float) jlVolume.getValue() / 10);

   }//GEN-LAST:event_jlVolumeStateChanged

   private void ExitButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ExitButtonMouseEntered
		mc.setExitButtonAnimation(ExitButton, true);
   }//GEN-LAST:event_ExitButtonMouseEntered

   private void ExitButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ExitButtonMouseExited
		mc.setExitButtonAnimation(ExitButton, false);
   }//GEN-LAST:event_ExitButtonMouseExited

   private void ExitButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ExitButtonMouseClicked
		System.exit(0);
   }//GEN-LAST:event_ExitButtonMouseClicked

   private void PlayButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PlayButtonMouseClicked
		mc.toSingleplayerMenu(this);
   }//GEN-LAST:event_PlayButtonMouseClicked

   private void PlayButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PlayButtonMouseEntered
		mc.setPlayButtonAnimation(PlayButton, true);
   }//GEN-LAST:event_PlayButtonMouseEntered

   private void PlayButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PlayButtonMouseExited
		mc.setPlayButtonAnimation(PlayButton, false);
   }//GEN-LAST:event_PlayButtonMouseExited

   private void HistoryButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HistoryButtonMouseClicked
		mc.toHistoryScreen(this);
   }//GEN-LAST:event_HistoryButtonMouseClicked

   private void HistoryButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HistoryButtonMouseEntered
		mc.setHistoryButtonAnimation(HistoryButton, true);
   }//GEN-LAST:event_HistoryButtonMouseEntered

   private void HistoryButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HistoryButtonMouseExited
		mc.setHistoryButtonAnimation(HistoryButton, false);
   }//GEN-LAST:event_HistoryButtonMouseExited

   private void PreviousButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PreviousButtonMouseClicked
		music.previousMusic();
		music.setVolume((float) jlVolume.getValue() / 10);
   }//GEN-LAST:event_PreviousButtonMouseClicked

   private void NextButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NextButtonMouseClicked
		music.nextMusic();
		music.setVolume((float) jlVolume.getValue() / 10);
   }//GEN-LAST:event_NextButtonMouseClicked

   private void MusicButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MusicButtonMouseClicked
		mc.musicOption(MusicButton, jlVolume, PreviousButton, NextButton);
   }//GEN-LAST:event_MusicButtonMouseClicked


   // Variables declaration - do not modify//GEN-BEGIN:variables
   private javax.swing.JLabel AlphaInfo;
   private javax.swing.JLabel Background;
   private javax.swing.JLabel EasterEgg;
   private javax.swing.JLabel ExitButton;
   private javax.swing.JLabel HistoryButton;
   private javax.swing.JLabel MusicButton;
   private javax.swing.JLabel NextButton;
   private javax.swing.JLabel PlayButton;
   private javax.swing.JLabel PreviousButton;
   private javax.swing.JSlider jlVolume;
   // End of variables declaration//GEN-END:variables

}
