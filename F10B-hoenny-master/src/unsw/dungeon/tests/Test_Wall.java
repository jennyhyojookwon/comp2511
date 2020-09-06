package unsw.dungeon.tests;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import unsw.dungeon.*;

public class Test_Wall {

	@Test 
	void Test_wall() {
		Dungeon dungeon = new Dungeon(5, 5);
		dungeon.setTest();
		Player player = new Player(dungeon, 2, 2, 1);
		dungeon.setPlayer(player);
		dungeon.addEntity(player);
		player.moveDown();
		assertEquals(3, player.getY());
		player.moveUp();
		Wall wall = new Wall(2, 3);
		dungeon.addEntity(wall);
		player.moveDown();
		assertEquals(2, player.getY());
	}
	
	@Test 
	void Test_wall_Enemy() {
		Dungeon dungeon = new Dungeon(5, 5);
		dungeon.setTest();
		Player player = new Player(dungeon, 0, 2, 1);
		dungeon.setPlayer(player);
		dungeon.addEntity(player);
		Enemy enemy = new Enemy(dungeon, 4, 2, 1);
		dungeon.addEntity(enemy);
		player.addObderver(enemy);
		Wall wall = new Wall(2, 2);
		dungeon.addEntity(wall);
		player.moveRight();
		try {
			Thread.currentThread().sleep(2000);
		} catch (InterruptedException e) {
			
		}
		assertEquals(2, enemy.getY());
		assertEquals(3, enemy.getX());
		
	}
	
	
	
	
}
