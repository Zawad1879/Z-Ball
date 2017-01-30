import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class MapGenerator {
	
	public int map[][];
	public int brickWidth;
	public int brickHeight;
	public MapGenerator(int row, int col){
		map = new int[row][col];
		for (int i = 0; i < map.length; i++){
			for(int j = 0; j<map[0].length;j++){
				map[i][j] = 1;
			}
		}
		
		//added 2 unbreakable bricks
		map[1][1] = 2;
		map[1][3] = 2;
		map[1][5] = 2;
		map[2][1] = 2;
		map[2][3] = 2;
		map[2][5] = 2;
		
		
		brickWidth = 540/col;
		brickHeight = 150/row;
	}
	
	public void draw(Graphics2D g){
		for (int i = 0; i < map.length; i++){
			for (int j = 0; j < map[0].length; j++){
				//adding pink unbreakable bricks
//				if(map[i][j] > 0 ){
				if(map[i][j] == 1){
					g.setColor(Color.WHITE);
					g.fillRect(j * brickWidth + 80, i*brickHeight + 50,  brickWidth,  brickHeight);
					
					g.setStroke(new BasicStroke(3));
					g.setColor(Color.black);
					g.drawRect(j * brickWidth + 80, i*brickHeight + 50,  brickWidth,  brickHeight);
				}
				else if(map[i][j] == 2){
					g.setColor(Color.pink);
					g.fillRect(j * brickWidth + 80, i*brickHeight + 50,  brickWidth,  brickHeight);
					
					g.setStroke(new BasicStroke(3));
					g.setColor(Color.black);
					g.drawRect(j * brickWidth + 80, i*brickHeight + 50,  brickWidth,  brickHeight);
				}
			}
		}
	}
	
	public void setBrickValue(int row, int col, int val){
		map[row][col] = val;
		
	}
	
}
