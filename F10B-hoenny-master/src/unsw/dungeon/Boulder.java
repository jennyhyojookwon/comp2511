package unsw.dungeon;

public class Boulder extends Entity{

	public Boulder(int x, int y) {
		super(x, y, -1);
		
	}
	
	public void moveUp() {
		y().set(getY() - 1);
	}
	public void moveDown() {
		y().set(getY() + 1);
	}
	public void moveLeft() {
		x().set(getX() - 1);
	}
	public void moveRight() {
		x().set(getX() + 1);
	}
	
    public Boolean moveBoulder() {
   	 return true;
    }

    
}
