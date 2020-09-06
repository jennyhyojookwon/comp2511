package unsw.dungeon.tests;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import unsw.dungeon.*;

public class Test_Portal {
	
	@Test
	void PortaRIghtl() {
		Dungeon dungeon = new Dungeon(5, 5);
		Player player = new Player(dungeon, 0, 0, 1);
		dungeon.setPlayer(player);
		dungeon.addEntity(player);
		Portal portal = new Portal(1, 0, 1);
		dungeon.addEntity(portal);
		Portal portal2 = new Portal(2, 4, 1);
		dungeon.addEntity(portal2);
		player.moveRight();
		//System.out.println("x: " + player.getX() + " y: " + player.getY());
		assertEquals(2, player.getX());
		assertEquals(4, player.getY());
	}
	
	@Test
	void PortalDown() {
		Dungeon dungeon = new Dungeon(5, 5);
		Player player = new Player(dungeon, 0, 0, 1);
		dungeon.setPlayer(player);
		dungeon.addEntity(player);
		Portal portal = new Portal(0, 1, 1);
		dungeon.addEntity(portal);
		Portal portal2 = new Portal(2, 4, 1);
		dungeon.addEntity(portal2);
		player.moveDown();
		//System.out.println("x: " + player.getX() + " y: " + player.getY());
		assertEquals(2, player.getX());
		assertEquals(4, player.getY());
	}
	
	@Test
	void PortalLeft() {
		Dungeon dungeon = new Dungeon(5, 5);
		Player player = new Player(dungeon, 1, 0, 1);
		dungeon.setPlayer(player);
		dungeon.addEntity(player);
		Portal portal = new Portal(0, 0, 1);
		dungeon.addEntity(portal);
		Portal portal2 = new Portal(2, 4, 1);
		dungeon.addEntity(portal2);
		player.moveLeft();
		//System.out.println("x: " + player.getX() + " y: " + player.getY());
		assertEquals(2, player.getX());
		assertEquals(4, player.getY());
	}
	
	
	@Test
	void PortalUpBack() {
		Dungeon dungeon = new Dungeon(5, 5);
		Player player = new Player(dungeon, 0, 1, 1);
		dungeon.setPlayer(player);
		dungeon.addEntity(player);
		Portal portal = new Portal(0, 0, 1);
		dungeon.addEntity(portal);
		Portal portal2 = new Portal(2, 4, 1);
		dungeon.addEntity(portal2);
		player.moveUp();
		//System.out.println("x: " + player.getX() + " y: " + player.getY());
		assertEquals(2, player.getX());
		assertEquals(4, player.getY());
		player.moveUp();
		player.moveDown();
		assertEquals(0, player.getX());
		assertEquals(0, player.getY());
	}
	
}
