package unsw.dungeon.tests;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import unsw.dungeon.*;

public class Test_KillEnemy {
	
	@Test
	void KillEnemy_sword() {
		Dungeon dungeon = new Dungeon(5, 1);
		dungeon.setTest();
		Player player = new Player(dungeon, 0, 0, 1);
		dungeon.setPlayer(player);
		dungeon.addEntity(player);
		Enemy enemy = new Enemy(dungeon, 3, 0, 1);
		dungeon.addEntity(enemy);
		player.addObderver(enemy);
		Sword sword = new Sword(1, 0);
		dungeon.addEntity(sword);
		assertEquals(true, dungeon.checkExist(enemy));
		player.moveRight();
		try {
			Thread.currentThread().sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assertEquals(false, dungeon.checkExist(enemy));
	}
	
	@Test
	void KillEnemy_invincible() {
		Dungeon dungeon = new Dungeon(8, 1);
		dungeon.setTest();
		Player player = new Player(dungeon, 0, 0, 1);
		dungeon.setPlayer(player);
		dungeon.addEntity(player);
		Enemy enemy = new Enemy(dungeon, 2, 0, 1);
		dungeon.addEntity(enemy);
		player.addObderver(enemy);
		Potion potion = new Potion(1, 0);
		dungeon.addEntity(potion);
		assertEquals(true, dungeon.checkExist(enemy));
		player.moveRight();
		player.moveRight();
		player.moveRight();
		player.moveRight();
		assertEquals(false, dungeon.checkExist(enemy));
	}
	
	
	
}
