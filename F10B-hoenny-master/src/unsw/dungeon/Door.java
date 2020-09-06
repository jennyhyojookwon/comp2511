package unsw.dungeon;

public class Door extends Entity {

	private int id;
	private boolean open;
	
	public Door(int x, int y, int id) {
		super(x, y, id);
		this.id = id;
		this.open = false;
	}
	
	public int getID() {
		return this.id;
	}
	
	public void openDoor() {
		this.open = true;
	}
	
	public boolean checkOpen() {
		return open;
	}

}
