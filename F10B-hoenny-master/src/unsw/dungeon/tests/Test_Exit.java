package unsw.dungeon.tests;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import unsw.dungeon.*;

public class Test_Exit {
	
	@Test
	void Exit_Reach() {
		Dungeon dungeon = new Dungeon(5, 5);
		dungeon.setTest();
		Player player = new Player(dungeon, 2, 2, 1);
		dungeon.setPlayer(player);
		dungeon.addEntity(player);
		Exit exit = new Exit(2, 4);
		assertEquals(true, exit.getLife());
		dungeon.addEntity(exit);
		player.moveDown();
		player.moveDown();
		assertEquals(false, exit.getLife());
	}
}
