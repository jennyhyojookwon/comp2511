package unsw.dungeon;

public class FloorSwitch extends Entity{

	private boolean triggered;
	public FloorSwitch(int x, int y) {
		super(x, y, -1);
		triggered = false;
		
	}
	
	public void triggerSwitch() {
		System.out.println("this trigger finished");
		this.triggered = true;
	}
	
	public void untriggerSwitch() {
		this.triggered = false;
	}
	
	public boolean checkTrigger() {
		return triggered;
	}

}
