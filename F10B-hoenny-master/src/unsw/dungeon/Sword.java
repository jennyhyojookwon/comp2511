package unsw.dungeon;

public class Sword extends Entity {

	private int time;
	
	public Sword(int x, int y) {
		super(x, y , -1);
		this.time = 5;
	}

	public int getTime() {
		return time;
	}
	
	public void MinusTime() {
		this.time = time - 1;
	}
}
