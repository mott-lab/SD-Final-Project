import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import sun.audio.*;

public class Main {
	
	private Level level;
	public GamePanel gamePanel;
	private GameManager gameManager;
	private GameFrame gameFrame;
	
	public static void main(String[] args) {
		
		JFrame instructionFrame = new JFrame("Instructions");
		
		JPanel instPanel1 = new JPanel();
		JLabel inst1 = new JLabel("A NEW HOPE...", SwingConstants.CENTER);
		JLabel inst2 = new JLabel("You are BB-8, and you will appear in the lower left-hand side of the screen.");
		JLabel inst3 = new JLabel("You have a memory drive containing a map to Jedi Master Luke Skywalker's location.");
		JLabel inst4 = new JLabel("As the Resistance's last hope against the Empire, you must deliver the drive to someone able and willing to fight for the safety of the galaxy.");
		JLabel inst5 = new JLabel("You must reach the golden platform at the end of each level to advance to the next.", SwingConstants.CENTER);
		JLabel inst6 = new JLabel("Use the LEFT and RIGHT arrow keys to move left and right.", SwingConstants.CENTER);
		JLabel inst7 = new JLabel("Press SPACE to jump.", SwingConstants.CENTER);
		JLabel inst8 = new JLabel("Press S to shoot your lasers.", SwingConstants.CENTER);
		
		inst1.setPreferredSize(new Dimension(800, 30));
		inst5.setPreferredSize(new Dimension(800, 20));
		inst6.setPreferredSize(new Dimension(800, 20));
		inst7.setPreferredSize(new Dimension(800, 20));
		inst8.setPreferredSize(new Dimension(800, 20));
				
		instPanel1.add(inst1);
		instPanel1.add(inst2);
		instPanel1.add(inst3);
		instPanel1.add(inst4);
		instPanel1.add(inst5);
		instPanel1.add(inst6);
		instPanel1.add(inst7);
		instPanel1.add(inst8);
		
		instPanel1.setPreferredSize(new Dimension(800, 210));
		
		ImageIcon introGif = new ImageIcon("bb8_intro.gif");
		JLabel imageLabel = new JLabel("", SwingConstants.CENTER);
		imageLabel.setIcon(introGif);
		
		JLabel inst9 = new JLabel("May the force be with you.", SwingConstants.CENTER);
		
		JPanel instPanel2 = new JPanel(new BorderLayout());
		instPanel2.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		instPanel2.add(imageLabel, BorderLayout.NORTH);
		instPanel2.add(inst9, BorderLayout.SOUTH);
		
		
		JPanel btnPanel = new JPanel();
		JButton startButton = new JButton("Start Mission!");
		
		startButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				instructionFrame.setVisible(false);
				//start-up the game main frame 
				GameFrame gameFrame = new GameFrame();
				gameFrame.setLocationRelativeTo(null);		//center the gameFrame
			}
		});
		
		startButton.setPreferredSize(new Dimension(200, 48));
		btnPanel.add(startButton);
		btnPanel.setPreferredSize(new Dimension(200, 68));
		
		instructionFrame.add(instPanel1, BorderLayout.NORTH);
		instructionFrame.add(instPanel2, BorderLayout.CENTER);
		instructionFrame.add(btnPanel, BorderLayout.SOUTH);
		instructionFrame.setVisible(true);
		instructionFrame.setSize(840, 850);
		instructionFrame.setLocationRelativeTo(null);
				
	}
	
}
