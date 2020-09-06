package unsw.dungeon.tests;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import unsw.dungeon.*;
import unsw.dungeon.Observer;

public class Test_enemy_move {

	@Test
	void EnemyMoveLeft () {
		Dungeon dungeon = new Dungeon(10, 1);
		Player player = new Player(dungeon, 0, 0, 1);
		dungeon.setPlayer(player);
		dungeon.addEntity(player);
		Enemy enemy = new Enemy(dungeon, 9, 0, 1);
		dungeon.addEntity(enemy);
		player.addObderver((Observer)enemy);
		assertEquals(-9, player.getX() - enemy.getX());
		player.moveRight();
		try {
			Thread.currentThread().sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assertEquals(true, (player.getX() - enemy.getX()) > -7);
		
	}
	
	@Test
	void EnemyMoveRight () {
		Dungeon dungeon = new Dungeon(10, 1);
		Player player = new Player(dungeon, 9, 0, 1);
		dungeon.setPlayer(player);
		dungeon.addEntity(player);
		Enemy enemy = new Enemy(dungeon, 0, 0, 1);
		dungeon.addEntity(enemy);
		player.addObderver((Observer)enemy);
		System.out.println(player.getX() - enemy.getX());
		assertEquals(9, player.getX() - enemy.getX());
		player.moveLeft();
		try {
			Thread.currentThread().sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assertEquals(true, (player.getX() - enemy.getX()) < 8);
		
	}
	
	@Test
	void EnemyMoveUp () {
		Dungeon dungeon = new Dungeon(1, 10);
		Player player = new Player(dungeon, 0, 0, 1);
		dungeon.setPlayer(player);
		dungeon.addEntity(player);
		Enemy enemy = new Enemy(dungeon, 0, 9, 1);
		dungeon.addEntity(enemy);
		player.addObderver((Observer)enemy);
		System.out.println(player.getY() - enemy.getY());
		assertEquals(-9, player.getY() - enemy.getY());
		player.moveDown();
		try {
			Thread.currentThread().sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assertEquals(true, (player.getY() - enemy.getY()) > -8);
		
	}
	
	@Test
	void EnemyMoveDown () {
		Dungeon dungeon = new Dungeon(1, 10);
		Player player = new Player(dungeon, 0, 9, 1);
		dungeon.setPlayer(player);
		dungeon.addEntity(player);
		Enemy enemy = new Enemy(dungeon, 0, 0, 1);
		dungeon.addEntity(enemy);
		player.addObderver((Observer)enemy);
		System.out.println(player.getY() - enemy.getY());
		assertEquals(9, player.getY() - enemy.getY());
		player.moveUp();
		try {
			Thread.currentThread().sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assertEquals(true, (player.getY() - enemy.getY()) < 7);
		
	}
	

	
}
