package unsw.dungeon.tests;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import unsw.dungeon.*;


public class Test_collection {

	@Test
	void CollectionTreasureRightTest() {
		Dungeon dungeon = new Dungeon(5, 5);
		Treasure treasure = new Treasure(1, 0);
		dungeon.addEntity(treasure);
		Player player = new Player(dungeon, 0, 0, 1);
		dungeon.setPlayer(player);
		dungeon.addEntity(player);
		assertEquals(0, player.getTreasure());
		player.moveRight();
		assertEquals(1, player.getTreasure());
		
	}
	
	
	@Test
	void CollectionTreasureUpLeftTest() {
		Dungeon dungeon = new Dungeon(5, 5);
		Treasure treasure = new Treasure(2, 1);
		Treasure treasure2 = new Treasure(1, 1);
		dungeon.addEntity(treasure);
		dungeon.addEntity(treasure2);
		Player player = new Player(dungeon, 2, 2, 1);
		dungeon.setPlayer(player);
		dungeon.addEntity(player);
		assertEquals(0, player.getTreasure());
		player.moveUp();
		assertEquals(1, player.getTreasure());
		player.moveLeft();
		assertEquals(2, player.getTreasure());
	}
	
	@Test
	void CollectionTreasureDownTest() {
		Dungeon dungeon = new Dungeon(5, 5);
		Treasure treasure = new Treasure(2, 3);
		dungeon.addEntity(treasure);
		Player player = new Player(dungeon, 2, 2, 1);
		dungeon.setPlayer(player);
		dungeon.addEntity(player);
		assertEquals(0, player.getTreasure());
		player.moveDown();
		assertEquals(1, player.getTreasure());
		
	}
	
	@Test
	void CollectionPotionRightTest() {
		Dungeon dungeon = new Dungeon(5, 5);
		Player player = new Player(dungeon, 2, 2, 1);
		dungeon.setPlayer(player);
		dungeon.addEntity(player);
		Potion potion = new Potion(3, 2);
		dungeon.addEntity(potion);
		assertEquals(false, player.getStatus());
		player.moveRight();
		assertEquals(true, player.getStatus());
	}
	
	
	@Test
	void CollectionPotionLeftTest() {
		Dungeon dungeon = new Dungeon(5, 5);
		Player player = new Player(dungeon, 2, 2, 1);
		dungeon.setPlayer(player);
		dungeon.addEntity(player);
		Potion potion = new Potion(1, 2);
		dungeon.addEntity(potion);
		player.moveLeft();
		assertEquals(true, player.getStatus());
	}
	
	@Test
	void CollectionPotionUpTest() {
		Dungeon dungeon = new Dungeon(5, 5);
		Player player = new Player(dungeon, 2, 2, 1);
		dungeon.setPlayer(player);
		dungeon.addEntity(player);
		Potion potion = new Potion(2, 1);
		dungeon.addEntity(potion);
		player.moveUp();
		assertEquals(true, player.getStatus());
	}
	
	@Test
	void CollectionPotionDownTest() {
		Dungeon dungeon = new Dungeon(5, 5);
		Player player = new Player(dungeon, 2, 2, 1);
		dungeon.setPlayer(player);
		dungeon.addEntity(player);
		Potion potion = new Potion(2, 3);
		dungeon.addEntity(potion);
		player.moveDown();
		assertEquals(true, player.getStatus());
	}
	
	@Test
	void CollectionSwordRightTest() {
		Dungeon dungeon = new Dungeon(5, 5);
		Player player = new Player(dungeon, 2, 2, 1);
		dungeon.setPlayer(player);
		dungeon.addEntity(player);
		Sword sword = new Sword(3, 2);
		dungeon.addEntity(sword);
		assertEquals(0, player.getSwordTime());
		player.moveRight();
		assertEquals(5, player.getSwordTime());
	}
	
	@Test
	void CollectionSwordLeftDownTest() {
		Dungeon dungeon = new Dungeon(5, 5);
		Player player = new Player(dungeon, 2, 2, 1);
		dungeon.setPlayer(player);
		dungeon.addEntity(player);
		Sword sword = new Sword(1, 2);
		dungeon.addEntity(sword);
		assertEquals(0, player.getSwordTime());
		player.moveLeft();
		assertEquals(5, player.getSwordTime());
		Sword sword2 = new Sword(1, 3);
		dungeon.addEntity(sword2);
		player.moveDown();
		assertEquals(10, player.getSwordTime());
	}
	
	@Test
	void CollectionSwordUpTest() {
		Dungeon dungeon = new Dungeon(5, 5);
		Player player = new Player(dungeon, 2, 2, 1);
		dungeon.setPlayer(player);
		dungeon.addEntity(player);
		Sword sword = new Sword(2, 1);
		dungeon.addEntity(sword);
		assertEquals(0, player.getSwordTime());
		player.moveUp();
		assertEquals(5, player.getSwordTime());
	}
	
	
	
	
	
}
