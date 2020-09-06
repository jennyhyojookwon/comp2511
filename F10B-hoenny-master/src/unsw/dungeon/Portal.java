package unsw.dungeon;

public class Portal extends Entity{

	private int id;
	
	public Portal(int x, int y, int id) {
		super(x, y, id);
		this.id = id;
	}

	public int getId() {return id;}
}
