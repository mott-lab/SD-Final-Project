import java.util.ArrayList;

public class Level {
	public ArrayList<Platform> platforms;
	private int level;
	public int backx_one = 0;
	public int backx_two = 1250;
	
	public int getBackx_one() {
		return backx_one;
	}

	public void setBackx_one(int backx_one) {
		this.backx_one = backx_one;
	}

	public int getBackx_two() {
		return backx_two;
	}

	public void setBackx_two(int backx_two) {
		this.backx_two = backx_two;
	}
	
	public Level(int level) {
		this.level = level;
		backx_one = 0;
		backx_two = 1250;
		platforms = new ArrayList<Platform>();
		
		if (level == 1)
		{
		Platform firstplat = new Platform(1, 540, 20);
		platforms.add(firstplat);
		Platform secondplat = new Platform(1325, 540);
		platforms.add(secondplat);

		int farX = 1300;
		
		for(int i = 0; i < 50; i++){
//			Platform platform = new Platform(1300, 320);
			if (i>1)
			{
				System.out.println(i);
				Platform oldplat = (Platform) platforms.get((i-1));
//				platform.setX(1300+(64*oldplat.getNumBlocks())+25);
				int newX = farX + (64*oldplat.getNumBlocks());
				int newY;
				if (oldplat.getY()<90)
				{
//					platform.setY(oldplat.getY()-60);
					newY = oldplat.getY() - 60;
				} else if (oldplat.getY()>570) {
//					platform.setY(oldplat.getY()+60);
					newY = oldplat.getY()+60;
				} else {
					int diff = (int) (Math.random()*50 - 50);
//					platform.setY(oldplat.getY()+diff);
					newY = oldplat.getY() + diff;
				}
				Platform platter = new Platform(newX, newY);
				platforms.add(platter);
				
				farX += (64*oldplat.getNumBlocks())+25;
				
//				platform.setOriginx(newX);
//				platform.setOriginy(newY);
//				
//				Platform newplat = new Platform(newX, newY);
//				platforms.add(platform);
				
//				if (platform.see == true) {
//					g2.drawImage(platform.img, platform.x, platform.y, null);
//					g2.draw(platform.getCollisionBox());
//				}
				
			} else{
				Platform newplat = new Platform(1300,540);
				newplat.setSee(true);
				platforms.add(newplat);
//				if (platform.see == true) {
//					g2.drawImage(platform.img, platform.x, platform.y, null);
//					g2.draw(platform.getCollisionBox());
//				}
			}
			
		}
		}
		// TODO Auto-generated constructor stub
	}

	public ArrayList<Platform> getPlatforms() {
		return platforms;
	}

	public void setPlatforms(ArrayList<Platform> platforms) {
		this.platforms = platforms;
	}
	
	public int getLevel(){
		return level;
	}
}
