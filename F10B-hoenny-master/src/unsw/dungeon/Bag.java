package unsw.dungeon;

import java.util.ArrayList;

public class Bag {
	
	//potion is not here
	private Dungeon dungeon;
	private Player player;
	private ArrayList<Key> keys;
	private int swordTime;
	private int treasure;
	private int Bombnum;
	
	public Bag(Dungeon dungeon, Player player) {
		this.dungeon = dungeon;
		this.player = player;
		this.keys = new ArrayList<Key>();
		this.swordTime = 0;
		this.treasure = 0;
		this.Bombnum = 0;
	}
	
	/*
	 * remove key from keys (list of keys in the bag)
	 */
	public void removeKey(Entity key) {
		//if(keys.contains(key)) {
			keys.remove(key);
		//}
	}
	
	/*
	 * adding key to keys(arraylist)
	 * restrictions on number of keys
	 */
	public void addKeys(Key key) {
		if(keys.size() >= 1) {
			System.out.println("You cannot hold more than one key");
			System.out.println("You currently have key " + ((keys.get(0))).getID());
			return;
		}
		System.out.println("Collected key with id: " + key.getID());
		keys.add(key);
	}
	
	public ArrayList<Key> getKeys(){return keys;}
	
	public Dungeon getDungeon() {return dungeon;} 
	
	public Player getPlayer() {return player;} 
	
	public void addTreasure() {
		System.out.println("add treasure");
		this.treasure++;
	}
	
	public int getTreasure() {return treasure;}
	
	
	public int getSwordTime() {return swordTime;}
	
	public void addSword() {this.swordTime = this.swordTime + 5;}
	
	/*
	 * reduce the sword time when sword is used
	 */
	public void useSword(Entity enemy) {
		this.swordTime = this.swordTime - enemy.getID();
		System.out.println("swordTime now is " + this.swordTime);
	}
	
	public Boolean checkSword(Entity enemy) {
		return (this.swordTime >= enemy.getID());
	}
	
	/*
	 * reduce the number of bombs when used
	 */
	public void useBomb() {
		if(Bombnum <= 0) {return;}
		Bombnum--;
		System.out.println("use a Bomb");
		//check the distance for all enemy and this player,if the enemy is beside us, they will be killed
		dungeon.checkDistance(player);
	}
	
	public void getBomb() {this.Bombnum++;}
	
	
    /*
     * replace key with the newly picked up one
     */
	public void replaceKey(Entity nextEntity) {
		removeKey(getKeys().get(0));
		addKeys((Key)nextEntity);
    }
	
}
