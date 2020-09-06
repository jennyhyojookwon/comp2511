/**
 *
 */
package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;


import javafx.scene.control.Tab;



/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 * @author Robert Clifton-Everest
 *
 */
public class Dungeon {

    private int width, height;
    private List<Entity> entities;
    private Player player;
    private Player player2;
    private Composite allGoal;
    private Goal goal;
    private Boolean oneGoal; 
    private int treasureNum;
    private Boolean testBoolean; // this is to tell the game I am running test, do not end the game
    private int start;
    private ArrayList<Entity> enemies;
    private int playerFlag;
    private DungeonController dungeonController;

    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<Entity>();
        this.player = null;
        this.player2 = null;
        this.allGoal = null;
        this.goal = null;
        this.oneGoal = true;
        this.treasureNum = 0;
        this.testBoolean = false;
        this.start = 0;
        this.enemies = new ArrayList<Entity>();
        this.dungeonController = null;
        
    }
    
    public DungeonController getDungeonController() {return dungeonController;}
    
    
    public void setDungeonController(DungeonController dungeonController) {
		this.dungeonController = dungeonController;
	}
    
    public int getStart() {
        return this.start;
    }

    public void setStart() {
        this.start++;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Player getPlayer() {
        return player;
    }
    
    public Player getPlayer2() {
    	return player2;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setPlayer2(Player player) {
    	this.player2 = player;
    }
    
    
    public void addEntity(Entity entity) {
        entities.add(entity);
    }
    
    public void removeEntity(int x, int y) {
    	Entity entity = checkEntity(x, y);
    	removeEntity(entity);
    }
    
    public void removeEntity(Entity entity) {
    	dungeonController.deleteThis(entity);
		entities.remove(entity);
	}
    
    public ArrayList<Entity> getEnemyList() {return enemies;}

    /*
     * used to replace entity 
     * (key exchange)
     */
    public void replaceEntity(Entity close, Entity open) {
    	removeEntity(close);
    	addEntity(open);
    }
    
    /*
     * get entity of a specific x, y coordinates
     */
    public Entity checkEntity(int x, int y) {
    	for (Entity entity : entities) {
    		if (entity.getX() == x && entity.getY() == y) {
    			return entity;
    		}
    	}
    	return null;
    }
    
    /*
     * used in case where boulder and switch is in same x, y coordinates (triggered)
     * to getEntity of the boulder
     */
    public Boulder checkEntityBoulder(int x, int y) {
    	for (Entity entity : entities) {
    		if (entity.getX() == x && entity.getY() == y) {
    			if(entity.getClass().getName().endsWith("Boulder")) {
    				System.out.println(entity.getX() + " " + entity.getY());
    			}
    			return (Boulder) entity;
    		}
    	}
    	return null;
    }
    
    /*
     * get corresponding entity for poral
     */
    public Entity checkEntity(Portal origPortal) { 

    	for (Entity entity : entities) {
    		if (entity.getClass().getName().endsWith("Portal")) {
    			Portal portal = (Portal)entity;
    			if (origPortal.getId() == portal.getId() && !origPortal.equals(portal)) {
    				//System.out.println("find the portal");
    				return entity;
    			}
    		}
    	}
    	return null;
    }
    
    /*
     * return the first player
     */
    public Entity checkPlayer() { 
    	for (Entity entity : entities) {
    		if (entity.getClass().getName().endsWith("Player")) {
    			return entity;
    		}
    	}
    	return null;
    }
    
    
    /*
     * return entity name of x,y coordinates
     */
    public String EntityName(int x, int y) {
    	Entity entity = checkEntity(x, y);
    	return EntityName(entity);
	}
    
    /*
     * return arraylist of entity in a certain x, y coordinates
     */
    public ArrayList<Entity> getAllEntity(int x, int y) {
    	ArrayList<Entity>samePosition = new ArrayList<Entity>();
    	for(Entity e: getEntities() ) {
    		if(e.getX() == x && e.getY() == y) {
    			samePosition.add(e);
    			//System.out.println(e.toString());
    		}
    	}
    	return samePosition;
    }
    
    /*
     * return boulder entity from the list
     */
    public Entity getTriggerBoulder(ArrayList<Entity>list) {
    	
    	for(Entity e: list) {
    		if (e.getClass().getName().endsWith("Boulder")) {
    			return e;
    		}
    	}
    	return null;
    }
    
    /*
     * method for the current player to push the boulder
     */
    public void pushBoulder(Entity boulder, Player currplayer) {
    		if(boulder == null) {return;}

    		//push down
    		if(boulder.getY() == currplayer.getY()+1) {
    			currplayer.y().set(currplayer.getY() + 1);
	    		boulder.setY(currplayer.getY() + 1);
	    		return;
    		}
    		// push down
    		if(boulder.getY() == currplayer.getY()-1) {
    			currplayer.y().set(currplayer.getY() - 1);
	    		boulder.setY(currplayer.getY() - 1);
	    		return;
    		}
    		// push left
    		if(boulder.getX() == currplayer.getX()-1) {
    			currplayer.x().set(currplayer.getX() - 1);
        		boulder.setX(currplayer.getX() - 1);
	    		return;
    		}
    		if(boulder.getX() == currplayer.getX()+1) {
    			currplayer.x().set(currplayer.getX() + 1);
        		boulder.setX(currplayer.getX() + 1);
	    		return;
    		}
    		
    		
    }
    
    /*
     * method to collect items
     */
    public void meetEnetity(Entity nextEntity, Player currplayer) { // do not include boulder
    	//if (!checkMove(nameString)) {return;} // can not go through
    	//if there is sword, potion, treasure, we need to collect it 
    	String nameString = EntityName(nextEntity);
    	if (currplayer.checkItem(nextEntity)) {
    		if(nameString.endsWith("Key")) {return;}
    		removeEntity(nextEntity); // player pick up something// potion treasure or sword
    	} else if (checkEnemy(nextEntity)) {
    		currplayer.meetEnemy(nextEntity); 
    	} 
    }
    
    public String EntityName(Entity entity) {
    	if (entity == null) {
    		return "empty";
    	}
    	return entity.getClass().getName();
    }
    
    /*
     * currplayer dies and other survived, change observers to another player
     */
    public void moveObservers(Player currplayer, Entity enemy) { 
    	Observer ob = (Observer)enemy;
    	currplayer.removeObserver(ob);
    	getAnother(currplayer).addObderver(ob);
    }
    
    public Player getAnother(Player currplayer) {
    	Player anotherPlayer;
    	if (currplayer.getID() == 1) {anotherPlayer = player2;}
    	else {anotherPlayer = player;}
    	return anotherPlayer;
    }
    
    

    public List<Entity> getEntities() {return this.entities;}

	public int getEnemies() {
		// TODO Auto-generated method stub
		int count = 0;
		for(Entity entity: entities) {
			if (entity.getClass().getName().endsWith("Enemy")) {
				count++;
			}
		}
		return count;
	}

	public ArrayList<Entity> getEnemiesArrayList() {
		return this.enemies;
	}
	
	
    public void addObsPlayer1(Observer observer) {
    	if (player == null) {System.out.println("player is null");return;}
    	player.addObderver(observer);
    }
    
    public void addObsPlayer2(Observer observer) {
    	if (player2 == null) {System.out.println("player is null");return;}
    	player2.addObderver(observer);
    }
    
    public Boolean allPlayerLife() { //check if all the player dies
    	if (player == null) {
    		System.out.println("player is null");
    	}
    	if (player2 == null) {
    		System.out.println("player2 is null");
    	}
//    	System.out.println("player1: " + player.getLife() + 
//    			"player2: " + player2.getLife());
    	if (player != null && player.getLife()) {return true;}
    	if (player2 != null && player2.getLife()) {return true;}
    	return false;
    }
    
    public Composite getAllGoals() {return allGoal;}
    
    public Goal getGoal() {return goal;}
    
    public void setComposite(Composite composite) {
    	this.allGoal = composite;
    	this.oneGoal = false;
    }
    
    public void setGoal(Goal goal) {
    	this.goal = goal;
    }
    
    public void checkName(String name) {
    	if (oneGoal) {
    		System.out.println("onegoal");
    		if (!testBoolean) 
    		this.goal.checkName(name);
    	} else {
    		System.out.println("multiplegoal");
    		if (!testBoolean) 
    		this.allGoal.checkName(name);
    	}
    	//System.out.println("hhhhhhhh");
    }
    
    public Boolean checkAllEnemyDead() {
    	for (Entity entity : entities) {
    		if (EntityName(entity).endsWith("Enemy") && entity.getLife()) {
    			return false;
    		}
    	}
    	return true;
    }
    
    public void checkReachGoal() {
    	if (oneGoal) {
    		//System.out.println("onegoal final check");
    		if (this.goal != null && this.goal.getStatus()) {
    			if (this.testBoolean) {return;}
    			System.out.println("one goal done: exxxxxxiiiiiiit");
    			System.exit(0);
    		} 
    	} else {
    		//System.out.println("multiple goals final check");
    		if (this.allGoal.getStatus()) {
    			//System.out.println("multiple ok");
    			if (this.testBoolean) {return;}
    			System.out.println("many goal done: exxxxxxiiiiiiit");
    			System.exit(0);
    		}
    	}
    }
    
    public void addEnemy(Entity enemy) {
    	this.enemies.add(enemy);
    }
    
    public int getAllcollectedTreasure() {
    	if (player != null && player2 != null) {
    		return (player.getTreasure() + player2.getTreasure());
    	} else if (player == null && player2 != null) {
    		return player2.getTreasure();
    	} else if (player != null && player2 == null) {
    		return player.getTreasure();
    	}
    	return 0; //if return 0, something is wrong
    }
    
    
    public void checkDistance(Player currplayer) { // used for Bomb
    	for (Entity enemy: enemies) {
    		if ( ((Math.abs(enemy.getX()-currplayer.getX())) <= 3) && (Math.abs(enemy.getX()-currplayer.getX()) <= 3) ) {
    			enemy.killEnemy();
    		}
    	}
    }
    
    public void setTreasure(int num) {this.treasureNum = num;}
    
    public int getTreasure() {return this.treasureNum;}
    
    public int CountEnetity(String classString) {
    	int count = 0;
    	for (Entity entity : entities) {
    		if (entity.getClass().getName().endsWith(classString)) {
    			count++;
    		}
    	}
    	return count;
    }
    
    public Boolean checkExist(Entity entity) { // use for test
    	for (Entity entity2: entities) {
    		if (entity2.equals(entity)) {
    			return true;
    		}
    	}
    	return false;
    }

    public void setTest() {this.testBoolean = true;}
    
    public Boolean getTest() {return testBoolean;}
    
    /*
     * make sure in the beginning of the game, when there is a triggered switch
     * mark them as triggered
     * this is needed because all switches are set as untriggered initially
     */
    public void checkInitiallyTriggered() {
    	for(int i = 0; i < getWidth(); i++) {
    		for(int j = 0; j < getHeight(); j++) {
    			ArrayList<Entity> entity = getAllEntity(i,j);
    			if(entity.size() > 1 && !(checkEntity(i,j).getClass().getName().endsWith("Player")) && checkEntity(i,j).getClass().getName().endsWith("Switch")) {
    				((FloorSwitch)checkEntity(i,j)).triggerSwitch();
    			}
    		}
    	}
    }
    
    /*
     * returns a list of all switches in the dungeon
     */
    public ArrayList<FloorSwitch> getAllSwitch(){
    	ArrayList<FloorSwitch> switchList = new ArrayList<FloorSwitch>();
    	for(Entity e: getEntities()) {
    		if(EntityName(e).endsWith("Switch")) {
    			switchList.add((FloorSwitch) e);
    		}
    	}
    	return switchList;
    }
    
    /*
     * method to check whether all the switches are triggered
     */
    public boolean checkTriggered(ArrayList<FloorSwitch> list) {
    	int count = 0;
    	for(FloorSwitch f: list) {
    		if(f.checkTrigger() == true) {
    			count++;
    		}
    	}
    	if(count == list.size()) {return true;}
    	return false;
    }

    public void removeObserver(Observer ob) {
    	if (player != null) {player.removeObserver(ob);}
    	if (player2 != null) {player2.removeObserver(ob);}
    }
    
	public Boolean checkSwitch(int x, int y) {
		String nameString = EntityName(x, y);
    	System.out.println(nameString);
    	if (nameString.equals("unsw.dungeon.FloorSwitch")) {return true;}
    	return false;
    }
	
	 /*
	  * returns boolean depending on whethere the player can push the boulder or not
	  */
	public boolean noObstacle(Entity next_en, Entity nnext_en, Player currPlayer) {
			if(next_en != null && nnext_en == null) {
	    		if(next_en.getClass().getName().endsWith("Boulder")) {
	    			pushBoulder(next_en, currPlayer);
	        		return true;
	    		}
	    	}
			return false;
		}
	 
	    public Boolean checkEnemy(Entity next_en) {
	    	String nameString = EntityName(next_en);
	    	return nameString.endsWith("Enemy");
	    }
	    
	    public Boolean checkPortal(Entity next_en) {
	    	String nameString = EntityName(next_en);
			return nameString.endsWith("Portal");
		}
	    
	    public Boolean checkBoulder(Entity next_en) {
	    	String nameString = EntityName(next_en);
	    	return nameString.endsWith("Boulder");
	    }
	    
	    public Boolean checkSwitch(Entity next_en) {
	    	String nameString = EntityName(next_en);
	    	return nameString.endsWith("Switch");
	    }
}

