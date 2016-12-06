import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class Tiles {
	
	public static BufferedImage BACKGROUND;
	public static Tile[][] tiles;
	public static final int ROWS=9;
	public static final int COLS=20;
	
	public Tiles(){
		tiles=new Tile[ROWS][COLS];
		
		try{
			BACKGROUND = ImageIO.read(new File("space_background.png"));
		}catch (IOException e){
			e.printStackTrace();
		}
		
		Scanner scanner = null;
		
		try {
			scanner = new Scanner(new File("level1.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		int row = 0;
		
		while(scanner.hasNextLine()){
			String line = scanner.nextLine();
			String[] words = line.split(" ");
			
			for(int column = 0; column < words.length; column++){
				if(!(words[column].equals("empt"))){
					tiles[row][column] = newTileInstance(words[column], row, column);
				}
			}
			
			row++;
		}

	}
	
	public Tile getTile(int row, int col){
		return tiles[row][col];
	}

	
	private Tile newTileInstance(String name, int i, int j) {
		switch (name) {
	        case "ter0":
	            return new Block("ter0", i, j);
	        case "ter1":
	            return new Block("ter1", i, j);
	        case "terR":
	            return new Block("terR", i, j);
	        case "terL":
	            return new Block("terL", i, j);
	        case "terQ":
	            return new Block("terQ", i, j);
	        case "terP":
	            return new Block("terP", i, j);
	        case "term":
	            return new Block("term", i, j);
	        case "mayC":
	            return new Block("mayC", i, j);
	        case "mayD":
	            return new Block("mayD", i, j);
	        case "mayU":
	            return new Block("mayU", i, j);

		}
		return null;
	}
	
	public static void emptyTile(int currentRow, int currentCol) {
		tiles[currentRow][currentCol]=null;
	}
	
	
}