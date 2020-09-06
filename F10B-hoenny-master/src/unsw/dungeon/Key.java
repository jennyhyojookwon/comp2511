package unsw.dungeon;

public class Key extends Entity{

	private int id;
	
	public Key(int x, int y, int id) {
		super(x, y, id);
		this.id = id;
	}
	
	public int getID() {
		return this.id;
	}
	
	public void setID() {
		
	}

}
