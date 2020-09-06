package unsw.dungeon;

import java.util.Timer;
import java.util.TimerTask;

public class Potion extends Entity {


	public Potion(int x, int y) {
		super(x, y, -1);
		
	}

	@Override
	public void changeStaus(Player player) {
		System.out.println("potion calling changeStatus");
		player.changeinvincibleStatus();
		player.resetTimer();
	}
	
	
	
	
	
	
}
