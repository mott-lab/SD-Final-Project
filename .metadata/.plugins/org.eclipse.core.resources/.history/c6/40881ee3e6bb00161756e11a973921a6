
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class StatsPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;

	public StatsPanel(){
		this.setSize(GameFrame.WIDTH, STATS_HEIGHT);
		this.setBackground(Color.WHITE);
		this.setLayout(null);
	}
	
	public static final int STATS_HEIGHT=40;
}

//public class StatsPanel extends JPanel{
//	
//	private static final long serialVersionUID = 1L;
//
//	public StatsPanel(){
//		this.setSize(GameFrame.WIDTH, STATS_HEIGHT);
//		this.setBackground(Color.BLACK);
//		this.setLayout(null);
//		loadInformations();
//	}
//	
//	private void loadInformations() {
//		try {
//			statsPanel=ImageIO.read(getClass().getResource("statsBar.png"));
//			livingHeart=ImageIO.read(getClass().getResource("livingHeart.png"));
//			deadHeart=ImageIO.read(getClass().getResource("deadHeart.png"));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	@Override
//	protected void paintComponent(Graphics g) {
//		super.paintComponent(g);
//		Graphics2D g2=(Graphics2D)g;
//		
//		g2.drawImage(statsPanel,0,0,GameFrame.WIDTH-5,STATS_HEIGHT,null);
//		
//		for(int i=0; i<bb8.MAX_LIFE; i++){
//			if(bb8.getLife()>i){
//				g2.drawImage(livingHeart,HEARTS_START_X+HEARTS_X_DISTANCE*i,HEARTS_START_Y,HEARTS_SIZE,HEARTS_SIZE,null);
//			} else {
//				g2.drawImage(deadHeart,HEARTS_START_X+HEARTS_X_DISTANCE*i,HEARTS_START_Y,HEARTS_SIZE,HEARTS_SIZE,null);
//			}
//		}
//	}
//	
//
//	public void addProtagonist(Protagonist bb8) {
//		this.bb8=bb8;
//	}
//	
//	private BufferedImage livingHeart;
//	private BufferedImage deadHeart;
//	private BufferedImage statsPanel;
//	public static final int STATS_HEIGHT=40;
//	private Protagonist bb8;
//	private static final int HEARTS_X_DISTANCE=60;
//	private static final int HEARTS_START_X=84;
//	private static final int HEARTS_START_Y=4;
//	private static final int HEARTS_SIZE=32;
//}
