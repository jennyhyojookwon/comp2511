package unsw.dungeon.tests;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;


import unsw.dungeon.*;

class Tester {
	
	@Test
	void playerInDungeonTest() {
		System.out.println("TEST1 - set player in the dungeon");
		Dungeon d = new Dungeon(3,3);
		Player p = new Player(d,0,0,1);
		d.setPlayer(p);
		// check player's position
		assertTrue(p.getPlayerState().equals(new VulnerableState()));
		assertEquals(d.getPlayer().getX(), p.getX());
		assertEquals(d.getPlayer().getY(), p.getY());
		
		System.out.println("---PASSED TEST1---\n");
	}

	@Test
	void playerMovementTest() {
		System.out.println("TEST2 - testing player's  movement");
		Dungeon d = new Dungeon(4,4);
		Player p = new Player(d,0,0, 1);
		d.setPlayer(p);
		assertTrue(p.getPlayerState().equals(new VulnerableState()));
		// player shouldn't move
		p.moveUp();
		assertEquals(p.getX(), 0);
		assertEquals(p.getY(), 0);
		// player shouldn't move
		p.moveLeft();
		assertEquals(p.getX(), 0);
		assertEquals(p.getY(), 0);
		// player should move down
		//TODO
		p.moveDown();
		assertEquals(p.getX(), 0);
		assertEquals(p.getY(), 1);
		// player should move right
		p.moveRight();
		assertEquals(p.getX(), 1);
		assertEquals(p.getY(), 1);
		
		System.out.println("---PASSED TEST2---\n");
	}

	@Test
	void wallTest() {
		System.out.println("TEST3 - testing wall collision");
		Dungeon d = new Dungeon(4,4);
		Player p = new Player(d,1,1,1);
		d.setPlayer(p);
		Wall w = new Wall(1,0);
		d.addEntity(w);
		assertTrue(p.getPlayerState().equals(new VulnerableState()));
		// move to wall
		p.moveUp();
		assertEquals(p.getX(), 1);
		assertEquals(p.getY(), 1);
		System.out.println("---PASSED TEST3---\n");
	}
	
	
	@Test
	void boulderTest() {
		System.out.println("TEST4 - tesing boulder collision");
		Dungeon d = new Dungeon(4,4);
		Player p = new Player(d,1,1,1);
		d.setPlayer(p);
		Wall w = new Wall(1,4);
		d.addEntity(w);
		Boulder b = new Boulder(1,2);
		d.addEntity(b);
		assertTrue(p.getPlayerState().equals(new VulnerableState()));
		// push boulder down
		p.moveDown();
		assertEquals(p.getX(), 1);
		assertEquals(p.getY(), 2);
		
		//TODO
		// check boulder position
		assertEquals(b.getX(), 1);
		assertEquals(b.getY(), 3);
		// push down again
		p.moveDown();
		assertEquals(p.getX(), 1);
		assertEquals(p.getY(), 2);
		// check boulder position
		assertEquals(b.getX(), 1);
		assertEquals(b.getY(), 3);
		//System.out.println("---PASSED TEST4---\n");
	}
	
	@Test
	void floorSwitchTest() {
		//System.out.println("TEST5 - floor switch test");
		Dungeon d = new Dungeon(4,4);
		Player p = new Player(d,1,1,1);
		d.setPlayer(p);
		Boulder b = new Boulder(1,2);
		d.addEntity(b);
		FloorSwitch s = new FloorSwitch(1,3);
		d.addEntity(s);
		assertTrue(p.getPlayerState().equals(new VulnerableState()));
		
		// push boulder down
		p.moveDown();
		assertEquals(p.getX(), 1);
		assertEquals(p.getY(), 2);
		// check if boulder on switch
		assertEquals(b.getX(), s.getX());
		assertEquals(b.getY(), s.getY());
		
		//System.out.println("---PASSED TEST5---\n");
	}
	
	
	@Test
	void correctKeyTest() {
		//System.out.println("TEST6 - correct key test");
		Dungeon d = new Dungeon(4,6);
		Player p = new Player(d,1,5,1);
		d.setPlayer(p);
		Key key1 = new Key(1,4, 1);
		d.addEntity(key1);
		//Key key2 = new Key(1,3, 2);
		//d.addEntity(key2);
		Door r = new Door(1,2, 1);
		d.addEntity(r);
		assertTrue(p.getPlayerState().equals(new VulnerableState()));
		
		// move and open door with corresponding key
		p.moveUp();
		p.moveUp();
		p.moveUp();

		assertTrue(r.checkOpen());

		//System.out.println("---PASSED TEST6---\n");
	}
	
	@Test
	void wrongKeyTest() {
		//System.out.println("TEST7 - wrong key test");
		Dungeon d = new Dungeon(4,6);
		Player p = new Player(d,1,5,1);
		d.setPlayer(p);
		Key key1 = new Key(1, 4, 2);
		d.addEntity(key1);
		Key key2 = new Key(1,3, 3);
		d.addEntity(key2);
		Door r = new Door(1,2, 4);
		d.addEntity(r);
		assertTrue(p.getPlayerState().equals(new VulnerableState()));
		//collect key2
		p.moveUp();
		//replace key2 with key3
		//System.out.println("currently have" + p.getBag().getKeys());
		p.moveUp();
		
		// locked door can't pass thru
		p.moveUp();
		assertEquals(p.getX(), 1);
		assertEquals(p.getY(), 3);

		// door is still locked
		p.moveUp();
		assertEquals(p.getX(), 1);
		assertEquals(p.getY(), 3);
		//System.out.println("---PASSED TEST7---\n");
	}
	
}