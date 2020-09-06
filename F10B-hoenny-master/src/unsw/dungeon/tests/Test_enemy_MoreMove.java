package unsw.dungeon.tests;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import unsw.dungeon.*;



public class Test_enemy_MoreMove {
	
	
	
	@Test
	void EnemyMoveUpRight() {
		Dungeon dungeon = new Dungeon(5, 5);
		Player player = new Player(dungeon, 0, 0, 1);
		dungeon.setPlayer(player);
		dungeon.addEntity(player);
		Enemy enemy = new Enemy(dungeon, 4, 4, 1);
		dungeon.addEntity(enemy);
		player.addObderver((Observer)enemy);
		//System.out.println("-4: " + (player.getY() - enemy.getY()));
		assertEquals(-4, player.getY() - enemy.getY());
		//System.out.println("-4: " + (player.getX() - enemy.getX()));
		assertEquals(-4, player.getX() - enemy.getX());
		player.moveRight();
		try {
			Thread.currentThread().sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//System.out.println("true: " + (player.getX() - enemy.getX()));
		assertEquals(true, (player.getX() - enemy.getX()) > -4);
		//System.out.println("-4: " + (player.getY() - enemy.getY()));
		assertEquals(-4, player.getY() - enemy.getY());
		try {
			Thread.currentThread().sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//System.out.println("0: " + (player.getX() - enemy.getX()));
		assertEquals(0, player.getX() - enemy.getX());
		//System.out.println("true: " + (player.getY() - enemy.getY()));
		assertEquals(true, (player.getY() - enemy.getY()) > -4);
	}
	
}
